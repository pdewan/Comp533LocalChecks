package gradingTools.comp533s19.assignment0.testcases.standalone.basic;

import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.ResultingOutErr;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;

public class StandAloneBasicTokenCountResult extends PassFailJUnitTestCase {

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		
		String aMainClassName = aConfigurationProvided.getTestConfiguration().getStandAloneTokenCounter().getName();
		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
		String[] emptyArgs = {};
		try {
			ResultingOutErr anOutError = BasicProjectExecution.callOrForkMain(true, aMainClassName, emptyArgs, "3", "a an the a an the a a a an an an the the the");
			String anOut = anOutError.getOut();
			String anError = anOutError.getErr();
			
		} catch (Throwable e) {
			return fail(e.getMessage());
		}
		return null;
	}

}
