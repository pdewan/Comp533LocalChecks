package gradingTools.comp533s19.assignment0.testcases.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestPartitioner;
import gradingTools.shared.testcases.FactoryMethodTest;
@MaxValue(10)
@Explanation("Checks that a mapper factory is returned by the configuration and creates a mapper object")
public class MapperFactory extends FactoryMethodTest {

	public TestMapper getMapper() {
		return (TestMapper) getRootProxy();
	}
	@Override
	protected Class proxyClass() {
		return TestMapper.class;
	}
	@Override
	protected String factoryMethodName() {
		return "getMapper";
	}
	@Override
	protected Class factoryClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);

		MapReduceConfiguration aTestMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		if (aTestMapReduceConfiguration == null) {
			assertTrue("No configuration", false);
		}
		Class retVal = aConfigurationProvided.getTestConfiguration().getMapperFactory();
		if (retVal == null) {
			assertTrue("Null mapper factory in configuration", false);
		}
		return retVal;
	}
	@Override
	protected Object createUsingFactory() {
		return createUsingFactoryClassAndMethodName();
	}
//	@Override
//	protected boolean doTest() throws Throwable {
//		 return doFactoryMethodTest();
//		
//	}
	public void defaultTest() {
    	passfailDefaultTest();
    }
	@Override
	protected boolean matchProxyActualClass() {
		return false;
	}
	public  TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
   	NotGradableException {
    	
		boolean aResult = doFactoryMethodTest();
		return (aResult == true? pass(): fail("Factory method test failed"));
    }

}
