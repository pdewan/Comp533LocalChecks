package gradingTools.comp533s19.assignment0.testcases.counts.standalone;

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
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.shared.testcases.MainMethodForkerTest;
import gradingTools.shared.testcases.MethodExecutionTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;

//public class MultiThreadTokenCountResult extends MainMethodForkerTest {
	public class MultiThreadTokenCountResult extends SingleThreadTokenCountResult {

	public MultiThreadTokenCountResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}

//	protected void callOrForkMain(boolean aFork) throws Throwable {
////		BasicRunningProject.setProcessOutputSleepTime(15000);
//		super.callOrForkMain(aFork);
//	}
//	protected SubstringSequenceChecker checker() {
//		return new AStandAloneTokenCountResultChecker();
//	}
//	@Override
//	protected boolean isValidOutput() {
//		return checkWithChecker();
//	}
//	@Override
//	protected boolean hasError(String anError) {
//		return false;
//	}
//	@Override 
//	public String[] getInputLines() {
//		return new String[] {"3", 
//				"Hogwarts Hogwarts muggles wizards Hogwarts Hogwarts Hogwarts muggles muggles wizards",
////				"Abbott Creevey Dumbledore Longbottom Potter Snape Voldemort Weasley Zabini Potter Dumbledore Voldemort",
//				"quit"
//		};
//	}
	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);
		A2ConfigurationProvided aConfigurationProvided = (A2ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A2ConfigurationProvided.class);

		setMainClass( aConfigurationProvided.getTestConfiguration().getStandAloneTokenCounter());

	}

	public void defaultTest() {
    	passfailDefaultTest();
    }

}
