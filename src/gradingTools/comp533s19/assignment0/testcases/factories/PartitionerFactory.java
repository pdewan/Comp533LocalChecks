package gradingTools.comp533s19.assignment0.testcases.factories;

import grader.basics.junit.JUnitTestsEnvironment;
import gradingTools.comp533s19.assignment0.interfaces.TestPartitioner;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.shared.testcases.FactoryMethodTest;

public class PartitionerFactory extends FactoryMethodTest {

	protected TestPartitioner getPartitioner() {
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
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getPartitionerFactory();
		
	}
	@Override
	protected Object createUsingFactory() {
		return createUsingFactoryClassAndMethodName();
	}
	@Override
	protected boolean doTest() throws Throwable {
		 return doFactoryMethodTest();
		
	}

}
