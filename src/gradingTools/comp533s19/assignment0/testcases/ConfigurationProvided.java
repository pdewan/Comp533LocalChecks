package gradingTools.comp533s19.assignment0.testcases;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grader.basics.config.BasicExecutionSpecificationSelector;
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
import gradingTools.shared.testcases.utils.ConfigurationWriter;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Explanation;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(10)
@Explanation("Tests that the mapreduce configuration class is provided under the rright name.")
public class ConfigurationProvided extends PassFailJUnitTestCase {
	public static final String CONFIGURATION_CLASS = "MyMapReduceConfiguration";
	TestMapReduceConfiguration testConfiguration ;
	public static final String CONFIGURATION_FILE_NAME = CONFIGURATION_CLASS + ".csv";


	public TestMapReduceConfiguration getTestConfiguration() {
		return testConfiguration;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
//			Class aConfigurationClass = Class.forName(CONFIGURATION_CLASS);
			Class aConfigurationClass = BasicProjectIntrospection.findClassByName(project, CONFIGURATION_CLASS);
			if (aConfigurationClass == null) {
				throw new ClassNotFoundException(CONFIGURATION_CLASS);
			}
			Object aConfigurationObject = aConfigurationClass.newInstance();
			 testConfiguration =  (TestMapReduceConfiguration) BasicProjectIntrospection.createProxy(TestMapReduceConfiguration.class, aConfigurationObject);
			 File aProjectDirectory = project.getProjectFolder();
			 String aConfigurationFileName = aProjectDirectory.getCanonicalPath() + "/" + CONFIGURATION_FILE_NAME;
			 ConfigurationWriter.writeConfiguration(aConfigurationFileName, aConfigurationObject);

//			 File aFile = new File(aConfigurationFileName);
//			 if (!aFile.exists()) {
//				 aFile.createNewFile();
//			 }
//			 PrintWriter aPrintWriter = new PrintWriter (aFile);
//			 aPrintWriter.println ("time, " + System.currentTimeMillis());
//			 aPrintWriter.close();
			
			
			
		} 
		catch (ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail("Could not find configuration class:" + e.getMessage());
		
		
		} 
		catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail("Could not instantiate configuration object:" + e.getMessage());		
		
		} 
		catch (Throwable e) {
			e.printStackTrace();
			return fail("Exception occurred:" + e.getMessage());

		}
		return pass();
	}
//	public static final String MAP_REDUCE_PROCESS_TEAM = "MapReduce Team";
//	public static final String MAP_REDUCE_SERVER = "MapReduce Server";
//	public static final String MAP_REDUCE_CLIENT_1 = "MapReduce Client 1";
//	public static final String MAP_REDUCE_CLIENT_2 = "MapReduce Client 2";
//
//	protected void setupProcesses(TestMapReduceConfiguration aTestMapReduceConfiguration) {
//		String aServerClassName = aTestMapReduceConfiguration.getServerTokenCounter().getName();
//		String aClientClassName = aTestMapReduceConfiguration.getClientTokenCounter().getName();
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList(MAP_REDUCE_PROCESS_TEAM));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses(MAP_REDUCE_PROCESS_TEAM, 
//				Arrays.asList(MAP_REDUCE_CLIENT_1));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses(MAP_REDUCE_PROCESS_TEAM, 
//				Arrays.asList(MAP_REDUCE_SERVER, MAP_REDUCE_CLIENT_1, MAP_REDUCE_CLIENT_2));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
//				MAP_REDUCE_SERVER , aServerClassName);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
//				MAP_REDUCE_CLIENT_1 , aClientClassName);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryPoint(
//				MAP_REDUCE_CLIENT_2 , aClientClassName);
////		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
////				CLIENT_1_NAME, Arrays.asList(client1TaggedTestCase.getTags()));
////		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags(
////				CLIENT_2_NAME, Arrays.asList(client2TaggedTestCase.getTags()));
////		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime(SERVER_NAME, 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setSleepTime(MAP_REDUCE_CLIENT_1, 7000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setSleepTime(MAP_REDUCE_CLIENT_2, 7000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
//	}

}
