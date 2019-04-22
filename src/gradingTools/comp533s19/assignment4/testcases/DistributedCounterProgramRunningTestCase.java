package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

//import org.apache.velocity.runtime.directive.InputBase;
//import org.codehaus.jackson.format.MatchStrength;
import org.junit.Test;

//import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
//import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterTestInputGenerator;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.TagCaseDependentTestCase;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment3.testcases.GIPCRMINIOStaticArguments;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.Tracer;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.testcase.PassFailJUnitTestCase;

@MaxValue(20)
//@Group("Test group name ")
public class DistributedCounterProgramRunningTestCase extends 
TagCaseDependentTestCase
//AStringCheckBasedDependentTestCase 
{
	
	
	protected SubstringSequenceChecker stringChecker ;	
	public static final String CLIENT_1_NAME = "Counter_Client_1";
	public static final String CLIENT_2_NAME = "Counter_Client_2";
	public static final String SERVER_NAME = "Counter_Server";


	
//	protected SubstringSequenceChecker regularClient1Checker = new ARegularCounterClientChecker(0.1);	
//	protected SubstringSequenceChecker regularClient2Checker = new ARegularCounterClientChecker(0.1);
//	protected SubstringSequenceChecker[] checkers = {
//			regularServerChecker,
//			regularClient1Checker,
//			regularClient2Checker
//	};
	
//	protected CheckerList checkerList =

//	SingleClassTagListTestCase serverTaggedTestCase, client1TaggedTestCase, client2TaggedTestCase;
	SingleClassTagListTestCase serverTaggedTestCase, client1TaggedTestCase, client2TaggedTestCase;

	public DistributedCounterProgramRunningTestCase(
			SubstringSequenceChecker aChecker,
			SingleClassTagListTestCase aServerTaggedTestCase, 
			SingleClassTagListTestCase aClient1TaggedTestCase,
			SingleClassTagListTestCase aClient2TaggedTestCase) {
//		super("A4 Program Running Test Case");
		init(aChecker, aServerTaggedTestCase, aClient1TaggedTestCase, aClient2TaggedTestCase);
//		serverTaggedTestCase = aServerTaggedTestCase;
//		client1TaggedTestCase = aClient1TaggedTestCase;
//		client2TaggedTestCase = aClient2TaggedTestCase;
//		stringChecker = aChecker;
//		outputBasedInputGenerator = new DistributedCounterTestInputGenerator();
//		outputBasedInputGenerator = anOutputBufferingTestInputGenerator;


	}
	public DistributedCounterProgramRunningTestCase() {
//		super("A4 Program Running Test Case");

	}
	public void init(
			SubstringSequenceChecker aChecker,
			SingleClassTagListTestCase aServerTaggedTestCase, 
			SingleClassTagListTestCase aClient1TaggedTestCase,
			SingleClassTagListTestCase aClient2TaggedTestCase) {
//		super("A4 Program Running Test Case");
		serverTaggedTestCase = aServerTaggedTestCase;
		client1TaggedTestCase = aClient1TaggedTestCase;
		client2TaggedTestCase = aClient2TaggedTestCase;
		stringChecker = aChecker;
		setOutputBasedInputGenerator(new DistributedCounterTestInputGenerator());
//		outputBasedInputGenerator = new DistributedCounterTestInputGenerator();
//		outputBasedInputGenerator = anOutputBufferingTestInputGenerator;


	}
//	protected ABufferingTestInputGenerator outputBasedInputGenerator = new ExplicitReceiveTestInputGenerator();
//	protected RunningProject interactiveInputProject;
	
	
//    public static boolean check (SingleClassTagListTestCase aTestCase) {
//    	return (aTestCase == null || aTestCase.getLastResult().isPass()) ;
//    }
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		return independentTest(project, autoGrade);
	}

	
	public TestCaseResult independentTest(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
//		if (
//				!serverTaggedTestCase.getLastResult().isPass() ||
//				!client1TaggedTestCase.getLastResult().isPass() ||
//				!client2TaggedTestCase.getLastResult().isPass()
//								
//				) {
//			return fail ("Server or a client not tagged");
//		}
		if (
				!check(serverTaggedTestCase) ||
				!check(client1TaggedTestCase) ||
				!check(client2TaggedTestCase)
								
				) {
			return fail ("Server or a client not tagged");
		}
		
		setupProcesses();
		String incOutput = "";
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//			ExclicitReceiveTestInputGenerator anOutputBasedInputGenerator = 
//					new ExclicitReceiveTestInputGenerator();
			outputBasedInputGenerator.clear();
//			RunningProject interactiveInputProject = null;
			 interactiveInputProject = null;

			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 25, outputBasedInputGenerator);
				 incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			StringBuffer aServerOutput = interactiveInputProject.getProcessOutput().get(SERVER_NAME);
			LinesMatcher aLinesMatcher = interactiveInputProject.getProcessLineMatcher().get(SERVER_NAME);
			
//			ARegularCounterServerChecker aServerChecker = new ARegularCounterServerChecker(1.0);
			
//			boolean aRetval = stringChecker.check(aServerOutput);
			boolean aRetval = stringChecker.check(aLinesMatcher, LinesMatchKind.ONE_TIME_LINE, Pattern.DOTALL);
			if (!aRetval) {
//				return fail("Server output did not match:" + Arrays.toString(stringChecker.getSubstrings()));
				return fail("Server output did not match:" + stringChecker.getRegex());

			}
//			if (interactiveInputProject != null) {
//				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
//			}
//			
//			if (!anOutputBasedInputGenerator.isNonsenseSetupComplete()) {
//			
//				return partialPass(0.80, "No nonsense");
//			}
//			
			return pass();
			
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	public static final String GIPC_PROCESS_TEAM = "GIPCDistributedProgram";
//	public static final String CLIENT_0 = "Client_0";
//	public static final String CLIENT_1 = "Client_1";

//	public ExplicitReceiveTestInputGenerator getOutputBasedInputGenerator() {
//		return outputBasedInputGenerator;
//	}



	public RunningProject getInteractiveInputProject() {
		return interactiveInputProject;
	}

	
//	protected void setupProcesses() {
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList(GIPC_PROCESS_TEAM));
//		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses(GIPC_PROCESS_TEAM, 
//				Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
//		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses(GIPC_PROCESS_TEAM, 
//				Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_SERVER, Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
//				Comp533Tags.EXPLICIT_RECEIVE_SERVER, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_SERVER));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
//				Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
//				Comp533Tags.EXPLICIT_RECEIVE_CLIENT2, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
//		
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, 5000);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2, 5000);
//		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
//	}
	protected void setupProcesses() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList(GIPC_PROCESS_TEAM));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses(GIPC_PROCESS_TEAM, 
				Arrays.asList(CLIENT_1_NAME, CLIENT_2_NAME));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses(GIPC_PROCESS_TEAM, 
				Arrays.asList(SERVER_NAME, CLIENT_1_NAME, CLIENT_2_NAME));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
				SERVER_NAME, Arrays.asList(serverTaggedTestCase.getTags()));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
				CLIENT_1_NAME, Arrays.asList(client1TaggedTestCase.getTags()));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
				CLIENT_2_NAME, Arrays.asList(client2TaggedTestCase.getTags()));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime(SERVER_NAME, 5000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setSleepTime(CLIENT_1_NAME, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setSleepTime(CLIENT_2_NAME, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
