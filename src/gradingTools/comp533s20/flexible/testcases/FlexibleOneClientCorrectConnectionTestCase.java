package gradingTools.comp533s20.flexible.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s20.assignment4.Assignment4OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4Suite;
import gradingTools.comp533s20.flexible.testcases.FlexibleOneClientCorrectConnectionTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class FlexibleOneClientCorrectConnectionTestCase extends PassFailJUnitTestCase {
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	public FlexibleOneClientCorrectConnectionTestCase(boolean doNIO, boolean doRMI, boolean doGIPC) {
//		super("One client correct connection test case");

		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;

	}
	
//	@Test
//	public void test() {
//		TestCaseResult result = null;
//        try {
//        	result = test(CurrentProjectHolder.getOrCreateCurrentProject(), true);  
//        	
//    		BasicJUnitUtils.assertTrue(result.getNotes(), result.getPercentage(), result.isPass());
//        } catch (Throwable e) {
//        	e.printStackTrace();
//        	if (result != null) {
//        		BasicJUnitUtils.assertTrue(e, result.getPercentage());
//        	} else {
//        		BasicJUnitUtils.assertTrue(e, 0);
//        	}
//        }
//	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			FlexibleOneClientCorrectConnectionTestInputGenerator anOutputBasedInputGenerator = new FlexibleOneClientCorrectConnectionTestInputGenerator(doNIO, doRMI, doGIPC);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, /*25,*/ Assignment4Suite.getProcessTimeOut(),
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			if (anOutputBasedInputGenerator.isServerSetupComplete()) {
				if (anOutputBasedInputGenerator.isClientConnectComplete()) {
					if (anOutputBasedInputGenerator.isServerAcceptComplete()) {
						return pass();
					} else {
						return partialPass(0.66, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//						return partialPass(0.66, "Server failed to accept connection from client");
					}
				} else {
					return partialPass(0.33, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//					return partialPass(0.33, "Client failed to connect to server");
				}
			} else {
				return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return fail("Server failed to accept connections");
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	protected  void setupProcesses() {
		
		Assignment4OneClientSuite.oneClientSetupProcesses(doNIO, doRMI, doGIPC);

//		Assignment2OneClientSuite.oneClientSetupProcesses(
//				S19FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS.toArray(new String[] {}), 
//				S19FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS.toArray(new String[] {}), true, false);
		
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
