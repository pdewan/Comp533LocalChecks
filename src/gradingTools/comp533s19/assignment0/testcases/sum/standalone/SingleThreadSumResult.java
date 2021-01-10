package gradingTools.comp533s19.assignment0.testcases.sum.standalone;

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
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.SingleThreadTokenCountResult;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.shared.testcases.MainMethodForkerTest;
import gradingTools.shared.testcases.MethodExecutionTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;

public class SingleThreadSumResult extends SingleThreadTokenCountResult {
	public SingleThreadSumResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}

	@Override 
	public String[] getInputLines() {
		return new String[] {"3", 
				"1 2 3 4 5 6 7 8 9 0",
				"100 200 300 400 500 600 700 800 900 1000",
				"quit"
		};
	}
	protected SubstringSequenceChecker checker() {
		return new AStandAloneIntSummerResultChecker();
	}
	
	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);

		MapReduceConfiguration aTestMapReduceConfiguration =  aConfigurationProvided.getTestConfiguration();
        if (aTestMapReduceConfiguration == null) {
        	assertTrue("No configuration", false);
        }
        Class anIntSummer = aTestMapReduceConfiguration.getStandAloneIntegerSummer();
        if (anIntSummer == null) {
        	assertTrue("No int summer", false);
        }
        setMainClass(anIntSummer);
//		setMainClass( aConfigurationProvided.getTestConfiguration().getStandAloneIntegerSummer());

	}
//	@Override
//	public TestCaseResult test(Project project, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		setMainClass();
//		
//			callOrForkMain(forkMain());
//			setOutputErrorStatus();
//			processOutputErrorStatus();
//			return pass();
//			
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return fail(e.getMessage());
//
//		}
////		String aMainClassName = aConfigurationProvided.getTestConfiguration().getStandAloneTokenCounter().getName();
////		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
////		String[] emptyArgs = {};
////		try {
////			ResultingOutErr anOutError = BasicProjectExecution.callOrForkMain(true, aMainClassName, emptyArgs, "3", "a an the a an the a a a an an an the the the");
////			BasicProjectExecution.callOrForkMain(true, getMainClassName(), emptyArgs, "3", "a an the a an the a a a an an an the the the");
////
////			String anOut = anOutError.getOut();
////			String anError = anOutError.getErr();
////			
////		} catch (Throwable e) {
////			return fail(e.getMessage());
////		}
////		return null;
//	}
//	public void defaultTest() {
//    	passfailDefaultTest();
//    }

}
