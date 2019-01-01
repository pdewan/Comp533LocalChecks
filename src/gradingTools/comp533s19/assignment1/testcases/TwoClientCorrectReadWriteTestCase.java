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
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class TwoClientCorrectReadWriteTestCase extends PassFailJUnitTestCase {
	private boolean atomic;
	
	public TwoClientCorrectReadWriteTestCase(boolean atomic) {
//		super("Prompt printer test case");
//		super("Two client correct read write - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			TwoClientCorrectReadWriteTestInputGenerator anOutputBasedInputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 45,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = 4;
//			StringBuilder message = new StringBuilder();
			if (anOutputBasedInputGenerator.isClientWriteComplete()) {
				correct++;
			} else {
//				message.append("Improper client write.");
			}
			if (anOutputBasedInputGenerator.isServerReadComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper server read.");
			}
			if (anOutputBasedInputGenerator.areServerWritesComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper server write(s).");
			}
			if (anOutputBasedInputGenerator.areClientReadsComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper client read(s).");
			}
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return fail(message.toString());
			} else {
				return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return partialPass(((double)correct)/possible, message.toString());
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private void setupProcesses() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client_0", "Client_1"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client_0", "Client_1"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.DEFAULT_SERVER_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", StaticArgumentsTestCase.DEFAULT_CLIENT_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", StaticArgumentsTestCase.DEFAULT_CLIENT_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 15000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
