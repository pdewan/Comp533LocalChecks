package gradingTools.comp533s19.assignment0.testcases.objects;

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
public class DefaultFactoryReduceObject extends PassFailJUnitTestCase{
	public static final String NAME_1 = "Abbott";
    public static final String NAME_2 = "Longbottom";
    public static final String NAME_3 = "Zabini";
    public static final String[] tokens_1 = {NAME_1, NAME_2, NAME_3}; 
    public static final int[] counts_1 = {3, 2, 1}	; 


//			".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Abbott=1, Zabini=1, Creevey=1, Weasley=1, Dumbledore=2, Potter=2, Longbottom=1, Snape=1, Voldemort=2.; propagationId=null; source=Model..*",
    protected String toKeyValueRegex(String aName){
    	return ".*" + aName + ".*" + 1 + ".*";
    }
	List aList = new ArrayList(20);

    protected String test(TestReducer aReducer, TestMapper aMapper, String[] aTokens, int[] aCounts) {
    	aList.clear();
    	
    	for (int i = 0; i < aTokens.length; i++) {
    		String aToken = aTokens[i];
    		int aCount = aCounts[i];
    		for (int j = 0; j < aCount; j++) {
    			Object aMapObject = aMapper.map(aToken);
    			aList.add(aMapObject);    		
    		}
    	}
    		
    	
    	Map<String, Integer> retVal = null;
    	try {
			retVal = aReducer.reduce(aList);
			if (retVal == null) {
				return "Reduce function with input:" + aList + " returned null";

			}
			if (retVal.size() != aTokens.length) {
				return "Reduce function with tokens:" + Arrays.asList(aTokens) + " returned size of " + retVal.size();

			}
			for (int i = 0; i < aTokens.length; i++) {
	    		String aToken = aTokens[i];
	    		int aCount = retVal.get(aToken);
	    		if (aCount != aCounts[i]) {
	    			return "Count of " + aToken + " is " + aCount + " rather than " + aCounts[i];
	    		}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return null;
    }
    
    protected String message(String aName, String anActualVal, String aDesiredVal) {
    	return "Map function with input " + aName + " returned " + anActualVal + " instead of " + aDesiredVal;
    }

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		ReducerFactory aFactory = (ReducerFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ReducerFactory.class);
		TestReducer aReducer = aFactory.getReducer();
		if (aReducer == null) {
			return fail ("Reducer factory returned a null reducer");
		}
		DefaultFactoryMapObject aMapperTest = (DefaultFactoryMapObject) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(DefaultFactoryMapObject.class);
		TestMapper aMapper = aMapperTest.getMapper();
		if (aMapper == null) {
			return fail("Null mapper");
		}
		String aResult = test(aReducer, aMapper, tokens_1, counts_1);
		if (aResult != null) {
			return fail(aResult);
		}
		
		return pass();
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getMapperFactory();
		
	}

}
