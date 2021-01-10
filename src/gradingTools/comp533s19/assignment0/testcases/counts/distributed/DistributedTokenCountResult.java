package gradingTools.comp533s19.assignment0.testcases.counts.distributed;

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

public class DistributedTokenCountResult extends MultiThreadTokenCountResult {
	public DistributedTokenCountResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}
	public static final String MAP_REDUCE_PROCESS_TEAM = "MapReduce Team";
	public static final String MAP_REDUCE_SERVER = "MapReduce Server";
	public static final String MAP_REDUCE_CLIENT = "MapReduce Client";

	public static final String MAP_REDUCE_CLIENT_1 = MAP_REDUCE_CLIENT + " 1";
	public static final String MAP_REDUCE_CLIENT_2 = MAP_REDUCE_CLIENT + " 2";
	MapReduceConfiguration testMapReduceConfiguration;
	public static void setupProcesses(String aServerClassName, String aClientClassName) {
		BasicRunningProject.setProcessTeamOutputSleepTime(15000);

//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderWaitForResort(false);
		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList(MAP_REDUCE_PROCESS_TEAM));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses(MAP_REDUCE_PROCESS_TEAM, 
				Arrays.asList(MAP_REDUCE_CLIENT_1));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses(MAP_REDUCE_PROCESS_TEAM, 
				Arrays.asList(MAP_REDUCE_SERVER, MAP_REDUCE_CLIENT_1, MAP_REDUCE_CLIENT_2));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
				MAP_REDUCE_SERVER , aServerClassName);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
				MAP_REDUCE_CLIENT_1 , aClientClassName);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
				MAP_REDUCE_CLIENT_2 , aClientClassName);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
//				CLIENT_1_NAME, Arrays.asList(client1TaggedTestCase.getTags()));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
//				CLIENT_2_NAME, Arrays.asList(client2TaggedTestCase.getTags()));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime(SERVER_NAME, 5000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setGraderResourceReleaseTime(MAP_REDUCE_CLIENT_1, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setGraderResourceReleaseTime(MAP_REDUCE_CLIENT_2, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
//	protected String processName() {
//		return MAP_REDUCE_SERVER;
//	}
	protected String processName() {
		return MAP_REDUCE_SERVER;
	}
	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		setMainClass( aConfigurationProvided.getTestConfiguration().getStandAloneTokenCounter());

	}
	@Override
	protected void callOrForkMain(boolean aFork) throws Throwable {
		BasicRunningProject.setProcessTeamOutputSleepTime(10000);

//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		testMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		setupProcesses(	testMapReduceConfiguration.getServerTokenCounter().getName(),
				testMapReduceConfiguration.getClientTokenCounter().getName());


//		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
//		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();


		interactiveInputProject = RunningProjectUtils.runProject(project, 
				Assignment0Suite.getProcessTimeOut(), new MapReduceInputGenerator (getInputLines()));
		interactiveInputProject.await();
		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryTagCommand();
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().resetProcessTeams();


	}

	public void defaultTest() {
    	passfailDefaultTest();
    }

}
