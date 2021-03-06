package gradingTools.comp533s19.assignment0.testcases.factories;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.annotations.MaxValue;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.comp533s21.assignment1.interfaces.TestPartitioner;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.shared.testcases.FactoryMethodTest;
@MaxValue(10)
public class PartitionerFactory extends FactoryMethodTest {

	public TestPartitioner getPartitioner() {
		return (TestPartitioner) getRootProxy();
	}
	@Override
	protected Class proxyClass() {
		return TestPartitioner.class;
	}
	@Override
	protected String factoryMethodName() {
		return "getPartitioner";
	}
	@Override
	protected Class factoryClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A2ConfigurationProvided aConfigurationProvided = (A2ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A2ConfigurationProvided.class);
		if (aConfigurationProvided == null) {
			System.err.println("Internal error in dependent test:" + PartitionerFactory.class.getName());
			assertTrue("Internal error in dependent test:" + PartitionerFactory.class.getName(), false);
		}
		MapReduceConfiguration aTestMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		
		if (aTestMapReduceConfiguration == null) {
			assertTrue("No configuration", false);
		}
		Class retVal = aTestMapReduceConfiguration.getPartitionerFactory();
		if (retVal == null) {
			assertTrue("Null partitioner factory:", false);
		}
		return retVal;
//		return aConfigurationProvided.getTestConfiguration().getPartitionerFactory();
		
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
