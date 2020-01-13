package gradingTools.comp533s20.assignment1.singleThread;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.BasicRunningProject;
import grader.basics.execution.GradingMode;
import grader.basics.execution.ResultingOutErr;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.AStandAloneTokenCountResultChecker;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.StandAloneMultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.MainMethodForkerTest;
import gradingTools.shared.testcases.MethodExecutionTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@Explanation("Checks that token counts are correcetly input and counted")
@MaxValue(10)
public class StandAloneSingleThreadTokenCountResult extends StandAloneMultiThreadTokenCountResult {

	
	@Override 
	public String[] getInputLines() {
		return new String[] { 
				"Hogwarts Hogwarts muggles wizards Hogwarts Hogwarts Hogwarts muggles muggles wizards",
//				"Abbott Creevey Dumbledore Longbottom Potter Snape Voldemort Weasley Zabini Potter Dumbledore Voldemort",
				"quit"
		};
	}
	

}
