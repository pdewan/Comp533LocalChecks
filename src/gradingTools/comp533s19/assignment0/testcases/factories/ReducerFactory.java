package gradingTools.comp533s19.assignment0.testcases.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.annotations.Explanation;
import util.annotations.MaxValue;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.interfaces.TestMapReduceConfiguration;
import gradingTools.comp533s19.assignment0.interfaces.TestMapper;
import gradingTools.comp533s19.assignment0.interfaces.TestPartitioner;
import gradingTools.comp533s19.assignment0.interfaces.TestReducer;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.shared.testcases.FactoryMethodTest;
@Explanation("Checks that a reducer factory is returned by the configuration and creates a reducer object")
@MaxValue(10)
public class ReducerFactory extends FactoryMethodTest {

	public TestReducer getReducer() {
		return (TestReducer) getRootProxy();
	}
	@Override
	protected Class proxyClass() {
		return TestReducer.class;
	}
	@Override
	protected String factoryMethodName() {
		return "getReducer";
	}
	@Override
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		TestMapReduceConfiguration aTestMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		if (aTestMapReduceConfiguration == null) {
			assertTrue("No configuration", false);
		}
		Class retVal = aConfigurationProvided.getTestConfiguration().getReducerFactory();
		if (retVal == null) {
			assertTrue("Null reducer factory in configuration", false);
		}
		return retVal;
//		return aConfigurationProvided.getTestConfiguration().getReducerFactory();
		
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
