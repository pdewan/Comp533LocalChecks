package gradingTools.comp533s19.assignment1.testcases;

import java.util.Arrays;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment1.Assignment1TwoClientSuite;
import gradingTools.utils.RunningProjectUtils;

public class TwoClientMessageRatioTestCase extends PassFailJUnitTestCase {
	
	
	boolean atomic = false;
	public TwoClientMessageRatioTestCase(boolean atomic) {
//		super("Prompt printer test case");
//		super("Two client message ratios - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			TwoClientMessageRatioTestInputGenerator anOutputBasedInputGenerator = new TwoClientMessageRatioTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 30,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> System.out.println("*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int correctAssumingBroadcast = 0;
			int possible = 6;
			int numSeen = anOutputBasedInputGenerator.getClient0WriteCount();
			int expected = 1;
			StringBuilder message = new StringBuilder();
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 1) {
					correctAssumingBroadcast ++;
				}
				message.append("Incorrect number of client 0 writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient0ReadCount();
			expected = atomic ? 1 : 0;
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 2) {
					correctAssumingBroadcast ++;
				}
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 0 reads (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient1ReadCount();
			expected = 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 2) {
					correctAssumingBroadcast ++;
				}
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 1 reads (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient1WriteCount();
			expected = 0;
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 1) {
					correctAssumingBroadcast ++;
				}
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 1 writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getServerWriteCount();
			expected = atomic ? 2 : 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 4) {
					correctAssumingBroadcast ++;
				}
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of server writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getServerReadCount();
			expected = 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (numSeen == expected + 2) {
					correctAssumingBroadcast ++;
				}
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of server reads (saw " + numSeen + ", expected " + expected + ").");
			}
			if (correctAssumingBroadcast == possible) {
				correct = possible;
			}
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail(message.toString());
			} else {
				return partialPass(((double)correct)/possible, message.toString());
			}
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void setupProcesses() {
		Assignment1TwoClientSuite.twoClientSetupProcesses();
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArguments.DEFAULT_SERVER_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", StaticArguments.DEFAULT_CLIENT_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", StaticArguments.DEFAULT_CLIENT_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 2000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
