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
import gradingTools.comp533s19.assignment0.interfaces.TestMapReduceConfiguration;
import gradingTools.comp533s19.assignment0.interfaces.TestMapper;
import gradingTools.comp533s19.assignment0.interfaces.TestReducer;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.MapReduceInputGenerator;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.AStandAloneIntSummerResultChecker;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
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
	TestMapReduceConfiguration testMapReduceConfiguration;
	public static void setupProcesses(String aServerClassName, String aClientClassName) {
		BasicRunningProject.setProcessTeamOutputSleepTime(15000);

		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
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
	@Override
	protected void callOrForkMain(boolean aFork) throws Throwable {
		BasicRunningProject.setProcessTeamOutputSleepTime(10000);

		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setWaitForResort(false);
//		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
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
//	//need to put in superclass
//	protected void callOrForkInteractiveMain(boolean aFork) throws Throwable {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//
//		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
//		testMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
//		setupProcesses();
//		Map<String, String> aProcessToInput = new HashMap<>();
//		interactiveInputProject = RunningProjectUtils.runProject(project, 
//				Assignment0Suite.getProcessTimeOut(), new MapReduceInputGenerator(3, getInputLines()));
//		error = interactiveInputProject.getErrorOutput();
//		output = interactiveInputProject.getOutput();
//		resultingOutError = new ResultingOutErr(output, error);
////		interactiveInputProject = resultingOutError.getRunningProject();		
//		BasicStaticConfigurationUtils.setBasicCommandToDefaultCommand();
//
//
//	}
	
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
//	protected SubstringSequenceChecker checker() {
//		return new AStandAloneBasicTokenCounterResultChecker();
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
//				"a an the a an the a a a an an an the the the"
//		};
//	}
//	protected void setMainClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//        setMainClass( aConfigurationProvided.getTestConfiguration().get);
//
//	}
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
	public void defaultTest() {
    	passfailDefaultTest();
    }

}
