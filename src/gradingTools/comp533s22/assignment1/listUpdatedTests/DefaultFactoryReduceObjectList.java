package gradingTools.comp533s22.assignment1.listUpdatedTests;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.ReducerFactory;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestPartitioner;
import gradingTools.comp533s21.assignment1.interfaces.TestReducer;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Tests the reduce object returned by the configured reduce factory")
public class DefaultFactoryReduceObjectList extends PassFailJUnitTestCase{
	
	private final String aRegex = ".*[(Abbott=4.*)(Longbottom=2.*)(Zabini=3.*)]{3}.*";
	
    protected TestCaseResult test(TestReducer aReducer, TestMapper aMapper) {
    	
    	List<String> inputList = Arrays.asList(DefaultFactoryMapObjectList.INPUTS);
    	
    	List<Object> mapRetval = aMapper.map(inputList);
    	
    	Map<String, Integer> retVal = null;
    	try {
			retVal = aReducer.reduce(mapRetval);
			if (retVal == null) {
				return fail("Reduce function with input:" + mapRetval + " returned null");
			}
			if (retVal.size() != 3) {
				return fail("Reduce function with tokens:" + inputList + " returned size of " + retVal.size());
			}
			
			if(retVal.toString().matches(aRegex)) 
				return pass();
			return fail(message(inputList.toString(),retVal.toString(),aRegex));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return fail("Unexpected error, report to instructor");
    }
    
    protected String message(String aName, String anActualVal, String aDesiredVal) {
    	return "Map function with input " + aName + " returned " + anActualVal + " instead of " + aDesiredVal;
    }

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
		ReducerFactory aFactory = (ReducerFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ReducerFactory.class);
		TestReducer aReducer = aFactory.getReducer();
		if (aReducer == null) {
			return fail ("Reducer factory returned a null reducer");
		}
		DefaultFactoryMapObjectList aMapperTest = (DefaultFactoryMapObjectList) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(DefaultFactoryMapObjectList.class);
		TestMapper aMapper = aMapperTest.getMapper();
		if (aMapper == null) {
			return fail("Null mapper");
		}
		
		
		return test(aReducer, aMapper);
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getMapperFactory();
	}

}
