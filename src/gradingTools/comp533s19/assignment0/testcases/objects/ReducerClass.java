package gradingTools.comp533s19.assignment0.testcases.objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.interfaces.TestMapReduceConfiguration;
import gradingTools.comp533s19.assignment0.interfaces.TestMapper;
import gradingTools.comp533s19.assignment0.interfaces.TestPartitioner;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.shared.testcases.FactoryMethodTest;
@MaxValue(5)
@Explanation("Checks that a mapper factory is returned by the configuration and creates a mapper object")
public class ReducerClass extends PassFailJUnitTestCase {
	Class clazz;
	
	
	protected Class reducerClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		TestMapReduceConfiguration aTestMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		if (aTestMapReduceConfiguration == null) {
			assertTrue("No configuration", false);
		}
		return aConfigurationProvided.getTestConfiguration().getReducerClass();
		
	}
	
//	@Override
//	protected boolean doTest() throws Throwable {
//		 return doFactoryMethodTest();
//		
//	}
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		clazz = reducerClass();
		if (clazz == null) {
			return fail ("Null reducer class returned by configuration:");
		}
		String aPackageName = clazz.getPackage().getName();
		boolean aCorrectPackageName = aPackageName.startsWith(ConfigurationProvided.TOP_LEVEL_PACKAGE_NAME);
		if (!aCorrectPackageName) {
			return partialPass(0.5, "Reducer class package name, " + aPackageName + ",  does not start with:" + ConfigurationProvided.TOP_LEVEL_PACKAGE_NAME + ". ");
		}
		return pass();
			
		
	}
//	public void defaultTest() {
//    	passfailDefaultTest();
//    }
//	

}