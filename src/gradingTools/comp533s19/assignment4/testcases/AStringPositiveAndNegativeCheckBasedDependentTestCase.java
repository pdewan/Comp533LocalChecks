package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

//import org.apache.maven.project.interpolation.AbstractStringBasedModelInterpolator;
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
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
//import grader.execution.ExecutionSpecificationSelector;
//import gradingTools.comp110.assignment1.testcases.PromptTestCase;
//import gradingTools.comp110.assignment4.Assignment4Requirements;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment3.testcases.GIPCRMINIOStaticArguments;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.shared.testcases.utils.LinesMatchKind;
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
public class AStringPositiveAndNegativeCheckBasedDependentTestCase extends AStringCheckBasedDependentTestCase {
	
	SubstringSequenceChecker negativeChecker;
	
	public AStringPositiveAndNegativeCheckBasedDependentTestCase(String aCheckName, String aProcessName,
			SubstringSequenceChecker aPositiveChecker,
			SubstringSequenceChecker aNegativeChecker,
			JUnitTestCase anOutputGeneratingTestcase) {
		super(aCheckName, aProcessName, aPositiveChecker, anOutputGeneratingTestcase);	
		negativeChecker = aNegativeChecker;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		TestCaseResult aSuperResult = super.test(project, autoGrade);
		if (!aSuperResult.isPass() || negativeChecker == null) {
			return aSuperResult;
		}
//		boolean aCheckVal = negativeChecker.check(programmingRunOutput);
		boolean aCheckVal = negativeChecker.check(linesMatcher, LinesMatchKind.ONE_TIME_LINE, Pattern.DOTALL);
		if (aCheckVal) {
			fail(processName + " Output matched:" + linesMatcher.getLastUnmatchedRegex());
		}
		return pass();		
	
	}
	
	
}
