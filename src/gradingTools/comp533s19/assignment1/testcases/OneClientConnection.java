package gradingTools.comp533s19.assignment1.testcases;

import java.util.Arrays;

import org.junit.Test;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class OneClientConnection extends PassFailJUnitTestCase {
	
	
//	public OneClientCorrectConnectionTestCase() {
////		super("Prompt printer test case");
//		super("One client correct connection test case");
//
//	}
	
	@Test
	public void test() {
		TestCaseResult result = null;
        try {
        	result = test(CurrentProjectHolder.getOrCreateCurrentProject(), true);  
        	
    		BasicJUnitUtils.assertTrue(result.getNotes(), result.getPercentage(), result.isPass());
        } catch (Throwable e) {
        	e.printStackTrace();
        	if (result != null) {
        		BasicJUnitUtils.assertTrue(e, result.getPercentage());
        	} else {
        		BasicJUnitUtils.assertTrue(e, 0);
        	}
        }
	}
	
	
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			OneClientConnectionInputGenerator anOutputBasedInputGenerator = new OneClientConnectionInputGenerator();
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 20,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			if (anOutputBasedInputGenerator.isEnableAcceptComplete()) {
				if (anOutputBasedInputGenerator.isConnectComplete()) {
					if (anOutputBasedInputGenerator.isAcceptComplete()) {
//						return pass();
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
		Assignment1OneClientSuite.oneClientSetupProcesses();
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArguments.DEFAULT_SERVER_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", StaticArguments.DEFAULT_CLIENT_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
