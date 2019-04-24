package gradingTools.comp533s19.assignment0.testcases;

import java.util.ArrayList;
import java.util.List;

import grader.basics.config.BasicStaticConfigurationUtils;
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
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(5)
public class GetConfiguration extends PassFailJUnitTestCase {
	public static final String CONFIGURATION_CLASS = "MyMapReduceConfiguration";
	

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			Class aConfigurationClass = Class.forName(CONFIGURATION_CLASS);
			Object aConfigurationObject = aConfigurationClass.newInstance();
			TestMapReduceConfiguration aProxy =  (TestMapReduceConfiguration) BasicProjectIntrospection.createProxy(TestMapReduceConfiguration.class, aConfigurationObject);
			Object aMapperObject = aProxy.getTokenCountingMapper();
			TestMapper aMapper = (TestMapper) BasicProjectIntrospection.createProxy(TestMapper.class, aMapperObject);
			Object aReducerObject = aProxy.getReducer();
			TestReducer aReducer = (TestReducer) BasicProjectIntrospection.createProxy(TestReducer.class, aReducerObject);
			Object a1 = aMapper.map("a ");
			Object a2 = aMapper.map("a");
			List aList = new ArrayList();
			aList.add(a1);
			aList.add(a2);
			
//			aList.add("c");
			Object retVal = aReducer.reduce(aList);
			
			Class aStandAloneTokenCountingClass = aProxy.getStandAloneTokenCounter();
			BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryPointCommand();
			String[] emptyArgs = {};
			ResultingOutErr anOutError = BasicProjectExecution.callOrForkMain(true, aStandAloneTokenCountingClass.getName(), emptyArgs, "3", "a an the a an the a a a an an an the the the");
			ABufferingTestInputGenerator aGenerator = this.getOutputBasedInputGenerator();
			setInteractiveInputProject(anOutError.getRunningProject());

						RunningProject aProject = this.getInteractiveInputProject();
			String anOutput = aProject.getOutputAndErrors();
			int i = 4;
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

}
