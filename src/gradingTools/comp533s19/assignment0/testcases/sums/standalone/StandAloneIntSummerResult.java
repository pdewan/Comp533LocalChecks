package gradingTools.comp533s19.assignment0.testcases.sums.standalone;

import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicExecutionSpecificationSelector;
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
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.StandAloneTokenCountResult;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.MainMethodForkerTest;
import gradingTools.shared.testcases.MethodExecutionTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;

public class StandAloneIntSummerResult extends StandAloneTokenCountResult {
	public StandAloneIntSummerResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}
//	protected Class mainClass;
//
//	
//	protected String mainClassName;
//	protected String[] emptyStringArgs = {};
//    
//    public String getMainClassName() {
//    	if (mainClassName == null) {
//    		mainClassName = mainClassName();
//    	}
//		return mainClassName;
//	}
//	public void setMainClassName(String mainClassName) {
//		this.mainClassName = mainClassName;
//	}
//	protected Class mainClass() {
//    	return null;
//    }
//    protected Class getMainClass() {
//    	if (mainClass == null) {
//    		mainClass = mainClass();
//    	}
//		return mainClass;
//	}
//	public void setMainClass(Class mainClass) {
//		this.mainClass = mainClass;
//	}
//	protected String mainClassName() {
//    	return getMainClass().getName();
//    }	
//	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		mainClass = aConfigurationProvided.getTestConfiguration().getStandAloneTokenCounter();
//	}
//	protected String[] getInputLines() {
//		return  new String[]{"3", "a an the a an the a a a an an an the the the"};
//	}
//	@Override
//	public String[] getStudentArgs() {
//		return new String[]{"3", "a an the a an the a a a an an an the the the"};
//	}
	
	
//	protected boolean forkMain() {
//		return true;
//	}
	
//	protected void callOrForkMain(boolean aFork) throws Throwable {
//		
//		resultingOutError = BasicProjectExecution.callOrForkMain(
//			true, getMainClassName(), emptyStringArgs, "3", "a an the a an the a a a an an an the the the");
//		error = resultingOutError.getErr();
//		output = resultingOutError.getOut();
//		interactiveInputProject = resultingOutError.getRunningProject();
//		
//
//	}
//	protected void callOrForkMain(boolean aFork) throws Throwable {
//		BasicRunningProject.setProcessOutputSleepTime(10000);
//		super.callOrForkMain(aFork);
//	}
	protected SubstringSequenceChecker checker() {
		return new AStandAloneBasicIntSummerResultChecker();
	}
//	@Override
//	protected boolean isValidOutput() {
//		return checkWithChecker();
//	}
//	@Override
//	protected boolean hasError(String anError) {
//		return false;
//	}
	@Override 
	public String[] getInputLines() {
		return new String[] {"3", 
				"1 2 3 4 5 6 7 8 9 0",
				"100 200 300 400 500 600 700 800 900 1000",
				"quit"
		};
	}
	protected void setMainClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
        setMainClass( aConfigurationProvided.getTestConfiguration().getStandAloneIntegerSummer());

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
