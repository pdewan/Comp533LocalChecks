package gradingTools.comp533s20.assignment1.mappers;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.objects.DefaultFactoryMapObject;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestReducer;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Tests the map function of the int summung mapper returned by the configuration")
public class IntSummingMapper extends TokenCountingMapper{
	 public static final String VALUE_TO_STRING = "42";
	 public static final String INPUT_VALUE = "0042";

	protected String toKeyValueRegex(String aName){
	    	return " *\\(.*" +  " *, *" + VALUE_TO_STRING  + " *" + "\\) *";
	}   
	protected void setMapper() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
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
	protected void testMapper() {
		String aFirstTest = test(mapper, INPUT_VALUE);
		if (aFirstTest != null) {
			BasicJUnitUtils.assertTrue(aFirstTest, 0, false);
		}
		
		
	}
}
