package gradingTools.comp533s19.assignment4.testcases;

import java.util.Arrays;

import grader.basics.config.BasicExecutionSpecificationSelector;
//import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
//import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.flexible.testcases.S19FlexibleStaticArgumentsTestCase;
import gradingTools.comp533s19.flexible.testcases.FlexibleTwoClientCorrectReadWriteTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class TagbasedTwoClientCorrectReadWriteTestCase extends 
TagCaseDependentTestCase
//AStringCheckBasedDependentTestCase 
{
	private boolean atomic;
	private  boolean doNIO;
	private  boolean doRMI;
	private  boolean doGIPC;
	
	
	private static int RUNTIME = 60;
	protected SingleClassTagListTestCase registryTaggedTestCase, serverTaggedTestCase, clientTaggedTestCase;
	public TagbasedTwoClientCorrectReadWriteTestCase(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC, 
			SingleClassTagListTestCase aRegistryTagTest,
			SingleClassTagListTestCase aServerTagTest,
			SingleClassTagListTestCase aClientTagTest) {
//		super(formatName(atomic, doNIO, doRMI, doGIPC));
		init(atomic, doNIO, doRMI, doGIPC, aRegistryTagTest, aServerTagTest, aClientTagTest);
//		registryTaggedTestCase = aRegistryTagTest;
//		serverTaggedTestCase = aServerTagTest;
//		clientTaggedTestCase = aClientTagTest;
//		this.atomic = atomic;
//
//		this.doNIO = doNIO;
//		this.doRMI = doRMI;
//		this.doGIPC = doGIPC;
//		setOutputBasedInputGenerator(new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC));
		

	}
	
	private static String formatName(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		StringBuilder sb = new StringBuilder("Two client correct read write test case - ");
		if (atomic) {
			sb.append("Atomic");
		} else {
			sb.append("Non-atomic");
		}
		sb.append(" (using");
		boolean hasMethod = false;
		if (doNIO) {
			hasMethod = true;
			sb.append(" NIO");
		}
		if (doRMI) {
			if (hasMethod) {
				sb.append(",");
			}
			sb.append(" RMI");
		}
		if (doGIPC) {
			if (hasMethod) {
				sb.append(",");
			}
			sb.append(" GIPC");
		}
		sb.append(")");
		
		return sb.toString();
	}
	protected boolean atomic() {
		return true;
	}
	protected boolean doNIO() {
		return false;
	}
	protected boolean doRMI() {
		return true;
	}
	protected boolean doGIPC() {
		return true;
	}
	protected SingleClassTagListTestCase registryTagTest() {
		return null;
	}
	protected SingleClassTagListTestCase serverTagTest() {
		return null;
	}
	protected SingleClassTagListTestCase clientTagTest() {
		return null;
	}
	public TagbasedTwoClientCorrectReadWriteTestCase() {
//		init(
//				atomic(),
//				doNIO(),
//				doRMI(),
//				doGIPC(),
//				registryTagTest(),
//				serverTagTest(),
//				clientTagTest()
//				);
	
		
	}
	public void init(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC, 
			SingleClassTagListTestCase aRegistryTagTest,
			SingleClassTagListTestCase aServerTagTest,
			SingleClassTagListTestCase aClientTagTest) {
//		super(formatName(atomic, doNIO, doRMI, doGIPC));
		registryTaggedTestCase = aRegistryTagTest;
		serverTaggedTestCase = aServerTagTest;
		clientTaggedTestCase = aClientTagTest;
		this.atomic = atomic;

		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
		setOutputBasedInputGenerator(new FlexibleTwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC));
		

	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		return independentTest(project, autoGrade);
	}
	
	
	
	
	public TestCaseResult independentTest(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		if (
				!check(registryTaggedTestCase) ||
				!check(serverTaggedTestCase) ||
				!check(clientTaggedTestCase) 
								
				) {
			return fail ("Registry, server or a client not tagged");
		}
		setupProcesses(); // This can be done in the constructor but there may be conflicts between test cases
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//			TwoClientCorrectReadWriteTestInputGenerator aTwoClientOutputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);
//			outputBasedInputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);
			getOutputBasedInputGenerator().clear();
//			OldTwoClientCorrectReadWriteTestInputGenerator aTwoClientOutputGenerator = (OldTwoClientCorrectReadWriteTestInputGenerator) getOutputBasedInputGenerator();
			FlexibleTwoClientCorrectReadWriteTestInputGenerator aTwoClientOutputGenerator = (FlexibleTwoClientCorrectReadWriteTestInputGenerator) getOutputBasedInputGenerator();

			interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, RUNTIME,
						aTwoClientOutputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = atomic ? 4 : 2;
			int[] scoring = new int[] {0,0};
			if (doNIO) {
				check(scoring, aTwoClientOutputGenerator.isClient0NIOWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerNIORead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerNIOWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1NIOReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerNIOWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0NIOReadComplete());
				}
			}
			if (doRMI) {
				check(scoring, aTwoClientOutputGenerator.isClient0RMIWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerRMIRead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerRMIWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1RMIReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerRMIWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0RMIReadComplete());
				}
			}
			if (doGIPC) {
				check(scoring, aTwoClientOutputGenerator.isClient0GIPCWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerGIPCRead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerGIPCWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1GIPCReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerGIPCWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0GIPCReadComplete());
				}
			}
			correct = scoring[0];
			possible = scoring[1];
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail("In " + aTwoClientOutputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + aTwoClientOutputGenerator.getLastNotFound());
			} else {
				return partialPass(((double)correct)/possible, "In " + aTwoClientOutputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + aTwoClientOutputGenerator.getLastNotFound());
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void check(int[] scoring, boolean condition) {
		scoring[1] ++;
		if (condition) {
			scoring[0] ++;
		}
	}
	
	private  void setupProcesses() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
		
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server", Comp533Tags.CUSTOM_IPC));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client", Comp533Tags.CUSTOM_IPC));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client", Comp533Tags.CUSTOM_IPC));
		
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", toEntryTags(registryTaggedTestCase));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", toEntryTags(serverTaggedTestCase));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", toEntryTags(clientTaggedTestCase));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", toEntryTags(clientTaggedTestCase));
		
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", S19FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", S19FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", S19FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", S19FlexibleStaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Registry", 500);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Server", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Client_0", 5000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Client_1", 5000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
