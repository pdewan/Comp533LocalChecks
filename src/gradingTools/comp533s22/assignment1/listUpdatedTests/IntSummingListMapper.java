package gradingTools.comp533s22.assignment1.listUpdatedTests;

import java.util.Arrays;
import java.util.List;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Tests the map function of the int summung mapper returned by the configuration")
public class IntSummingListMapper extends TokenCountingListMapper{
	 public final String aRegex = ".*ResultKey.*21.*21.*";
	 public final String [] input = {"0021","0021"};
  
	protected void setMapper() {
		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);

		Object anObjectMapper =  aConfigurationProvided.getTestConfiguration().getIntSummingMapper();
		if (anObjectMapper == null) {
			assertTrue("Null object mapper returned by configiration", false);
		}
		mapper = (TestMapper) BasicProjectIntrospection.createProxy(TestMapper.class, anObjectMapper);
		if (mapper == null) {
    		BasicJUnitUtils.assertTrue("Configuration returned a null mapper", 0, false);

		}
	}
	
	protected TestCaseResult testMapper() {
		TestMapper aMapper = getMapper();
		List<String> aInputList = Arrays.asList(input);
    	List<Object> retVal = aMapper.map(aInputList);
		if (retVal == null) 
			return fail("Map function with input returned null");
		String anObjectString = retVal.toString();
		
		if(anObjectString.matches(aRegex))
			return pass();
		
		return fail("Map function with input " + aInputList + " returned " + anObjectString + " which does not match regex:" + aRegex);
	}
}
