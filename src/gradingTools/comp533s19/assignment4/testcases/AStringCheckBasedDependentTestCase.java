package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

//import org.codehaus.jackson.format.MatchStrength;
import org.junit.Test;

//import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterTestInputGenerator;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterRegularOutput;
//import grader.execution.ExecutionSpecificationSelector;
//import gradingTools.comp110.assignment1.testcases.PromptTestCase;
//import gradingTools.comp110.assignment4.Assignment4Requirements;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment3.testcases.GIPCRMINIOStaticArguments;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.shared.testcases.utils.ALinesMatcher;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.Tracer;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.testcase.JUnitTestCase;
import grader.basics.testcase.PassFailJUnitTestCase;

@MaxValue(20)
//@Group("Test group name ")
public class AStringCheckBasedDependentTestCase extends PassFailJUnitTestCase
//TagCaseDependentTestCase 
{ // do not make it subclass of Tagbased
	
	
//	protected SubstringSequenceChecker checker = new ARegularCounterServerChecker(0.1);	

	protected SubstringSequenceChecker checker;;	

	protected JUnitTestCase outputGeneratingTestCase;
	protected ABufferingTestInputGenerator outputBasedInputGenerator = new DistributedCounterTestInputGenerator();
//	protected RunningProject interactiveInputProject;
	protected LinesMatcher linesMatcher;
	protected StringBuffer programmingRunOutput;
//	protected List<String> programmingRunOutputLinesList;
	


	protected String processName;
	protected boolean checkTrue = true;
	
	public AStringCheckBasedDependentTestCase(String aCheckName, String aProcessName,
			SubstringSequenceChecker aChecker,
			JUnitTestCase anOutputGeneratingTestcase) {
//		super (aCheckName);		
		init(aProcessName, aChecker, true, anOutputGeneratingTestcase);
	}
	public AStringCheckBasedDependentTestCase(
			String aCheckName,
			String aProcessName,
			SubstringSequenceChecker aChecker,
			boolean aCheckTrue,
			JUnitTestCase anOutputGeneratingTestcase) {
//		super (aCheckName);	
		init(aProcessName, aChecker, aCheckTrue, anOutputGeneratingTestcase);
	}
	public AStringCheckBasedDependentTestCase(
			) {
//		super (aCheckName);	
		init(processName(), checker(), checkTrue(), outputGeneratingTestCase());
	}
	
	protected String processName() {
		return null;
	}
	protected boolean checkTrue() {
		return true;
	}
	
	protected SubstringSequenceChecker checker() {
		return null;
	}
//	protected JUnitTestCase outputGeneratingTestCase() {
//		return null;
//	}
	protected Class outputgeneratingTestCaseClass() {
		return null;
	}
	protected JUnitTestCase outputGeneratingTestCase() {
		Class aTestClass = outputgeneratingTestCaseClass();
		if (aTestClass == null) return null;
		return JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest( aTestClass);
//		return null;
	}
	

	public void init (String aProcessName,
			SubstringSequenceChecker aChecker,
			boolean aCheckTrue,
			JUnitTestCase anOutputGeneratingTestcase) {
		processName = aProcessName;
		outputGeneratingTestCase = anOutputGeneratingTestcase;
		checker = aChecker;
		checkTrue = aCheckTrue;		
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
	
	

	
	public TestCaseResult dependentTest(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
	
		try {
			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//			ExclicitReceiveTestInputGenerator anOutputBasedInputGenerator = 
//					new ExclicitReceiveTestInputGenerator();
			if (outputGeneratingTestCase == null) {
				return fail("Internal error: output generating test case is null");
			}
			if (outputGeneratingTestCase.getLastResult() == null) {
				return fail("Internal error: last result of output generating test case is null");
			}
			if (!outputGeneratingTestCase.getLastResult().isPass()) {
				return fail("Did not pass regular output test");
			}
			outputBasedInputGenerator = outputGeneratingTestCase.getOutputBasedInputGenerator();
			 interactiveInputProject = outputGeneratingTestCase.getInteractiveInputProject();

			if (outputBasedInputGenerator == null || interactiveInputProject == null) {
				return fail("Problem running test case" + outputGeneratingTestCase.getName() );
			}
//			
			programmingRunOutput = 
					interactiveInputProject.getProcessOutput().get(processName);
			if (programmingRunOutput == null || programmingRunOutput.length() == 0) {
				return fail ("Could not find output for:" + processName);
			}
//			programmingRunOutputLinesList =
//					interactiveInputProject.getProcessOutputLines().get(processName);
			linesMatcher = 
					interactiveInputProject.getProcessLineMatcher().get(processName);
			if (linesMatcher == null) {
				return fail ("Internal error: Could not find line matcher for process:" + processName);
			}
			
			if (checker == null) {
				return pass();
			}
			
//			ARegularCounterServerChecker aServerChecker = new ARegularCounterServerChecker(1.0);
			
//			boolean aCheckVal = checker.check(programmingRunOutput);
			boolean aCheckVal = checker.check(linesMatcher, LinesMatchKind.ONE_TIME_LINE, Pattern.DOTALL);
		
			boolean aRetVal = checkTrue&&aCheckVal || !checkTrue&&!aCheckVal;
			if (!aRetVal && checkTrue) {
//				return fail(processName + " Output Did not match:" + Arrays.toString(checker.getSubstrings()));
//				return fail(processName + " Output Did not match:" + checker.getRegex());
				return fail(processName + " Output Did not match:" + linesMatcher.getLastUnmatchedRegex());


			} 
			if (!aRetVal && !checkTrue) {
//				return fail("Did not match:" + Arrays.toString(checker.getSubstrings()));
				return fail(processName + " Output matched:" + checker.getRegex());


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
	
	
}
