package gradingTools.comp533s20.assignment1.mappers;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.objects.DefaultFactoryMapObject;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestReducer;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Tests the map function of the token counting mapper returned by the configuration")
public class TokenCountingMapper extends DefaultFactoryMapObject{
	protected void setMapper() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
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
