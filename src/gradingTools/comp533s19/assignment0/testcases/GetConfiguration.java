package gradingTools.comp533s19.assignment0.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.ResultingOutErr;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.interfaces.TestMapReduceConfiguration;
import gradingTools.comp533s19.assignment0.interfaces.TestMapper;
import gradingTools.comp533s19.assignment0.interfaces.TestReducer;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.shared.testcases.MethodExecutionTest.OutputErrorStatus;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(5)
public class GetConfiguration extends PassFailJUnitTestCase {
	public static final String CONFIGURATION_CLASS = "MyMapReduceConfiguration";
	TestMapReduceConfiguration testConfiguration ;


	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			Class aConfigurationClass = Class.forName(CONFIGURATION_CLASS);
			Object aConfigurationObject = aConfigurationClass.newInstance();
			 testConfiguration =  (TestMapReduceConfiguration) BasicProjectIntrospection.createProxy(TestMapReduceConfiguration.class, aConfigurationObject);
			Object aMapperObject = testConfiguration.getTokenCountingMapper();
			TestMapper aMapper = (TestMapper) BasicProjectIntrospection.createProxy(TestMapper.class, aMapperObject);
			Object aReducerObject = testConfiguration.getReducer();
			TestReducer aReducer = (TestReducer) BasicProjectIntrospection.createProxy(TestReducer.class, aReducerObject);
			Object a1 = aMapper.map("a ");
			Object a2 = aMapper.map("a");
			List aList = new ArrayList();
			aList.add(a1);
			aList.add(a2);
			
//			aList.add("c");
			Object retVal = aReducer.reduce(aList);
			
			Class aStandAloneTokenCountingClass = testConfiguration.getStandAloneTokenCounter();
			BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
			String[] emptyArgs = {};
			ResultingOutErr anOutError = BasicProjectExecution.callOrForkMain(true, aStandAloneTokenCountingClass.getName(), emptyArgs, "3", "a an the a an the a a a an an an the the the");
			Class aStandALoneIntegerCountingClass = testConfiguration.getStandAloneIntegerSummer();
			anOutError = BasicProjectExecution.callOrForkMain(true, aStandALoneIntegerCountingClass.getName(), emptyArgs, "3", "1 2 3 4 5 6 7 8 9\n10 20 30 40 50 60 70 80 90\nquit");

			ABufferingTestInputGenerator aGenerator = this.getOutputBasedInputGenerator();
			setInteractiveInputProject(anOutError.getRunningProject());

						RunningProject aProject = this.getInteractiveInputProject();
			String anOutput = aProject.getOutputAndErrors();
			setupProcesses(testConfiguration);
			Map<String, String> aProcessToInput = new HashMap<>();
			String[] aServerInput = {
					"aaa jjj sss zzzz aaa aaa jjj zzz aaa jjj",
					"bbb iii ttt yyy bbb bbb iii yyy bbb iii"
			};
//			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "bbb iii ttt yyy bbb bbb iii yyy bbb iii");
//			aProcessToInput.put(MAP_REDUCE_SERVER, aServerInput);
			interactiveInputProject = RunningProjectUtils.runProject(project, 600, new MapReduceInputGenerator(3, aServerInput));
			 String anOutput2 = interactiveInputProject.await();
			 
				String aServerClassName = testConfiguration.getServerIntegerSummer().getName();
				BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
						MAP_REDUCE_SERVER , aServerClassName);
				String[] aServerIntInput = {
						"1 2 3 4 5 6 7 8 9",
						"111 222 333 444 555 666 777 888 999",
						"11 22 33 44 55 66 77 88 99"};
				interactiveInputProject = RunningProjectUtils.runProject(project, 300, new MapReduceInputGenerator(3, aServerIntInput));

			 int i = 4;
//			int i = 4;
//			System.out.println(anOutError.out);
			
			
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail("Could not instantiate configuration object:" + e.getMessage());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass();
	}
	public static final String MAP_REDUCE_PROCESS_TEAM = "MapReduce Team";
	public static final String MAP_REDUCE_SERVER = "MapReduce Server";
	public static final String MAP_REDUCE_CLIENT_1 = "MapReduce Client 1";
	public static final String MAP_REDUCE_CLIENT_2 = "MapReduce Client 2";

	protected void setupProcesses(TestMapReduceConfiguration aTestMapReduceConfiguration) {
		String aServerClassName = aTestMapReduceConfiguration.getServerTokenCounter().getName();
		String aClientClassName = aTestMapReduceConfiguration.getClientTokenCounter().getName();
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
			setSleepTime(MAP_REDUCE_CLIENT_1, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setSleepTime(MAP_REDUCE_CLIENT_2, 7000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}

}
