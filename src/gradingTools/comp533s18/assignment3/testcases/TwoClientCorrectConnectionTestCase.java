package gradingTools.comp533s18.assignment3.testcases;

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

import gradingTools.comp533s18.assignment2.Assignment2TwoClientSuite;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class TwoClientCorrectConnectionTestCase extends PassFailJUnitTestCase {
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	public TwoClientCorrectConnectionTestCase(boolean doNIO, boolean doRMI, boolean doGIPC) {
//		super("Two client correct connection test case");

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
			TwoClientCorrectConnectionTestInputGenerator anOutputBasedInputGenerator = new TwoClientCorrectConnectionTestInputGenerator(doNIO, doRMI, doGIPC);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 25,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			if (anOutputBasedInputGenerator.isServerSetupComplete()) {
				if (anOutputBasedInputGenerator.areClientConnectsComplete()) {
					if (anOutputBasedInputGenerator.areServerAcceptsComplete()) {
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
	
	private static void setupProcesses() {
		Assignment2TwoClientSuite.twoClientSetupProcesses();
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Registry", StaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.TEST_SERVER_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_0", StaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_1", StaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Registry", 500);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client_0", 5000);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client_1", 5000);
//		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
