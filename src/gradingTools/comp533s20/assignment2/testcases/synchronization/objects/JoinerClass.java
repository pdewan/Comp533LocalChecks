package gradingTools.comp533s20.assignment2.testcases.synchronization.objects;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCCounterServerTagged;
import gradingTools.comp533s20.assignment2.PartitionerClass;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import gradingTools.comp533s21.assignment1.interfaces.TestPartitioner;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.shared.testcases.FactoryMethodTest;
@MaxValue(5)
@Explanation("Checks that a mapper factory is returned by the configuration and creates a mapper object")
public class JoinerClass extends PassFailJUnitTestCase {
	Class clazz;
	
	
	protected Class joinerClass() {
//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A2ConfigurationProvided aConfigurationProvided = (A2ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A2ConfigurationProvided.class);

		MapReduceConfiguration aTestMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		if (aTestMapReduceConfiguration == null) {
			assertTrue("No configuration", false);
		}
		return aConfigurationProvided.getTestConfiguration().getJoinerClass();
		
	}
	
//	@Override
//	protected boolean doTest() throws Throwable {
//		 return doFactoryMethodTest();
//		
//	}
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		clazz = joinerClass();
		if (clazz == null) {
			return fail ("Null partitioner class returned by configuration:");
		}
//		String aPackageName = clazz.getPackage().getName();
//		boolean aCorrectPackageName = aPackageName.startsWith(ConfigurationProvided.TOP_LEVEL_PACKAGE_NAME);
//		if (!aCorrectPackageName) {
//			return partialPass(0.5, "Joiner class package name, " + aPackageName + ",  does not start with:" + ConfigurationProvided.TOP_LEVEL_PACKAGE_NAME + ". ");
//		}
		if (!PartitionerClass.testForCorrectPackageName(clazz)) {
			return partialPass(0.5, "Barrier class package name, " + clazz + ",  does not start with:" + ConfigurationProvided.TOP_LEVEL_PACKAGE_NAME + ". ");
		}
		return pass();
			
		
	}
//	public void defaultTest() {
//    	passfailDefaultTest();
//    }
//	

}
