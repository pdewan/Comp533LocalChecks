package gradingTools.comp533s19.assignment0.testcases.sum.distributed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.BasicRunningProject;
import grader.basics.execution.GradingMode;
import grader.basics.execution.ResultingOutErr;
import grader.basics.execution.RunningProject;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.MapReduceInputGenerator;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.AStandAloneIntSummerResultChecker;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestReducer;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.comp533s21.assignment3.A3ConfigurationProvided;
import gradingTools.shared.testcases.MainMethodForkerTest;
import gradingTools.shared.testcases.MethodExecutionTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;

public class DistributedSumResult extends MultiThreadSumResult {
	public DistributedSumResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}

	MapReduceConfiguration testMapReduceConfiguration;

	protected String processName() {
		return DistributedTokenCountResult.MAP_REDUCE_SERVER;
	}
	@Override
	protected void callOrForkMain(boolean aFork) throws Throwable {
		BasicRunningProject.setProcessTeamOutputSleepTime(10000);

//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		
		testMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		DistributedTokenCountResult.setupProcesses(	testMapReduceConfiguration.getServerIntegerSummer().getName(),
				testMapReduceConfiguration.getClientTokenCounter().getName());



		interactiveInputProject = RunningProjectUtils.runProject(project, 
				Assignment0Suite.getProcessTimeOut(), new MapReduceInputGenerator (getInputLines()));
		interactiveInputProject.await();
		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryTagCommand();
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().resetProcessTeams();


	}
	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		MapReduceConfiguration aTestMapReduceConfiguration =  aConfigurationProvided.getTestConfiguration();
        if (aTestMapReduceConfiguration == null) {
        	assertTrue("No configuration", false);
        }
        Class anIntSummer = aTestMapReduceConfiguration.getStandAloneIntegerSummer();
        if (anIntSummer == null) {
        	assertTrue("No int summer", false);
        }
        setMainClass(anIntSummer);

	}

	public void defaultTest() {
    	passfailDefaultTest();
    }

}
