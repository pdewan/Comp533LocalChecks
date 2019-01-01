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

public class ReadWriteUpdateOrderTestCase extends PassFailJUnitTestCase {
	private boolean atomic;
	
	public ReadWriteUpdateOrderTestCase(boolean atomic) {
//		super("Prompt printer test case");
//		super("Read-write-update order - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			ReadWriteOrderTestInputGenerator anOutputBasedInputGenerator = new ReadWriteOrderTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 15,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			if (atomic) {
				if (anOutputBasedInputGenerator.isUpdateCorrect()) {
					return pass();
				} else {
					return fail("Update not found after write.");
				}
			} else {
				if (anOutputBasedInputGenerator.isUpdateCorrect()) {
					return pass();
				} else {
					return fail("Update not found before write.");
				}
			}
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void setupProcesses() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.DEFAULT_SERVER_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", StaticArgumentsTestCase.DEFAULT_CLIENT_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 5000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));

	}
}
