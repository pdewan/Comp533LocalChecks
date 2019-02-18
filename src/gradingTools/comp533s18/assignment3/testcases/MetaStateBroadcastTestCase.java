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
import grader.basics.execution.BasicExecutionSpecificationSelector;
import gradingTools.comp533s18.assignment2.Assignment2TwoClientSuite;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class MetaStateBroadcastTestCase extends PassFailJUnitTestCase {
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	private final boolean atomic;
	private final boolean clientIsSource;
	
	public MetaStateBroadcastTestCase(boolean atomic, boolean clientIsSource, boolean doNIO, boolean doRMI, boolean doGIPC) {
//		super("Meta state broadcast test case - " +(atomic ? "Atomic" : "Non-atomic") + " from " + (clientIsSource ? "Client" : "Server"));
		
		this.atomic = atomic;
		this.clientIsSource = clientIsSource;
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
			MetaStateBroadcastTestInputGenerator anOutputBasedInputGenerator = new MetaStateBroadcastTestInputGenerator(atomic, clientIsSource, doNIO, doRMI, doGIPC);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 17,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			int correct = 0;
			int possible = 0;
			
			if (doNIO) {
				possible ++;
				if (anOutputBasedInputGenerator.isNIOCorrect()) {
					correct ++;
				}
			}
			if (doRMI) {
				possible ++;
				if (anOutputBasedInputGenerator.isRMICorrect()) {
					correct ++;
				}
			}
			if (doGIPC) {
				possible ++;
				if (anOutputBasedInputGenerator.isGIPCCorrect()) {
					correct ++;
				}
			}
			if (possible == correct) {
				return pass();
			} else if (correct == 0) {
				return fail(anOutputBasedInputGenerator.getErrorMessage());
			} else {
				return partialPass(((double)correct)/possible, anOutputBasedInputGenerator.getErrorMessage());
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void setupProcesses() {
		Assignment2TwoClientSuite.twoClientSetupProcesses();
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", StaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.TEST_SERVER_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", StaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", StaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
