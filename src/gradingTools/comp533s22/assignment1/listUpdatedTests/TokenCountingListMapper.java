package gradingTools.comp533s22.assignment1.listUpdatedTests;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Tests the map function of the token counting mapper returned by the configuration")
public class TokenCountingListMapper extends DefaultFactoryMapObjectList{
	protected void setMapper() {
		
		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);

		Object anObjectMapper =  aConfigurationProvided.getTestConfiguration().getTokenCountingMapper();
		if (anObjectMapper == null) {
    		BasicJUnitUtils.assertTrue("Configuration returned a null mapper", 0, false);

		}
		mapper = (TestMapper) BasicProjectIntrospection.createProxy(TestMapper.class, anObjectMapper);
		if (mapper == null) {
    		BasicJUnitUtils.assertTrue("Configuration returned a null mapper", 0, false);

		}
	}
}
