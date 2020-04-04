package gradingTools.comp533s20.flexible.testcases;

import java.util.Arrays;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4Suite;
import gradingTools.comp533s20.assignment4.Assignment4TwoClientSuite;
import gradingTools.comp533s19.flexible.testcases.FlexibleOneClientCorrectReadWriteTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class FlexibleOneClientCorrectReadWriteTestCase extends PassFailJUnitTestCase {
	private boolean atomic;
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	private boolean NIOPassed = false;
	private boolean	RMIPassed = false;
	
	private static int RUNTIME = /*40*/  Assignment4Suite.getProcessTimeOut();
	
	private static String formatName(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		StringBuilder sb = new StringBuilder("One client correct read write test case - ");
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
	
	public FlexibleOneClientCorrectReadWriteTestCase(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
//		super(formatName(atomic, doNIO, doRMI, doGIPC));
		this.atomic = atomic;

		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			FlexibleOneClientCorrectReadWriteTestInputGenerator anOutputBasedInputGenerator = new FlexibleOneClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, RUNTIME,
						anOutputBasedInputGenerator);
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
				check(scoring, NIOPassed = anOutputBasedInputGenerator.isClientNIOWriteComplete());
				check(scoring, NIOPassed = anOutputBasedInputGenerator.isServerNIOReadComplete());
				if (atomic) {
					check(scoring, NIOPassed = anOutputBasedInputGenerator.isServerNIOWriteComplete());
					check(scoring, NIOPassed = anOutputBasedInputGenerator.isClientNIOReadComplete());
				}
			}
			if (doRMI) {
				check(scoring, RMIPassed = anOutputBasedInputGenerator.isClientRMIWriteComplete());
				check(scoring, RMIPassed = anOutputBasedInputGenerator.isServerRMIReadComplete());
				if (atomic) {
					check(scoring, RMIPassed = anOutputBasedInputGenerator.isServerRMIWriteComplete());
					check(scoring, RMIPassed = anOutputBasedInputGenerator.isClientRMIReadComplete());
				}
			}
			if (doGIPC) {
				check(scoring, anOutputBasedInputGenerator.isClientGIPCWriteComplete());
				check(scoring, anOutputBasedInputGenerator.isServerGIPCReadComplete());
				if (atomic) {
					check(scoring, anOutputBasedInputGenerator.isServerGIPCWriteComplete());
					check(scoring, anOutputBasedInputGenerator.isClientGIPCReadComplete());
				}
			}
			correct = scoring[0];
			possible = scoring[1];
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				if (doNIO && !NIOPassed) {
					if (doRMI && doGIPC) {
						return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nRMI and GIPC are not checked because NIO failed");
					} else if (doRMI) {
						return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nRMI is not checked because NIO failed");
					} else if (doGIPC) {
						return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nGIPC is not checked because NIO failed");
					} else {
						return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
					}
				} else if (doRMI && !RMIPassed && doGIPC) {
					return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nGIPC is not checked because RMI failed");
				} else {
					return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
				}
			} else {
				if (doNIO && !NIOPassed) {
					if (doRMI && doGIPC) {
						return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nRMI and GIPC are not checked because NIO failed");
					} else if (doRMI) {
						return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nRMI is not checked because NIO failed");
					} else if (doGIPC) {
						return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nGIPC is not checked because NIO failed");
					} else {
						return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
					}
				} else if (doRMI && !RMIPassed && doGIPC) {
					return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound() + "\nGIPC is not checked because RMI failed");
				} else {
					return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());			
				}
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
	
	protected  void setupProcesses() {
		Assignment4OneClientSuite.oneClientSetupProcesses(doGIPC);

//		Assignment1OneClientSuite.oneClientSetupProcesses();
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Registry", StaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.TEST_SERVER_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client", StaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Registry", 500);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client", 5000);
//		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
