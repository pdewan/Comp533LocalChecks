package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
//import gradingTools.comp110.assignment1.testcases.PromptTestCase;
//import gradingTools.comp110.assignment4.Assignment4Requirements;
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
import grader.basics.testcase.JUnitTestCase;
import grader.basics.testcase.PassFailJUnitTestCase;

@MaxValue(20)
//@Group("Test group name ")
public abstract class TagCaseDependentTestCase extends PassFailJUnitTestCase {
	
	
	
    public TagCaseDependentTestCase(String string) {
//		super(string);
	}
    public TagCaseDependentTestCase() {
//		super(string);
	}

	public static boolean check (JUnitTestCase aTestCase) {
    	return (aTestCase == null || aTestCase.getLastResult().isPass()) ;
    }

	public static List<String> toEntryTags (SingleClassTagListTestCase aTestCase) {
    	return Arrays.asList(aTestCase.getOriginalTags());
    }

	
}
