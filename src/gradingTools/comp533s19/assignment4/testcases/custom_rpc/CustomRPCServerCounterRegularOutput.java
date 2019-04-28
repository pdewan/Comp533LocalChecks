package gradingTools.comp533s19.assignment4.testcases.custom_rpc;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(5)
@Explanation("Checks for expected server counter output when explicit receive is implemented.")
public class CustomRPCServerCounterRegularOutput extends DistributedCounterProgramRunningTestCase {

	public CustomRPCServerCounterRegularOutput() {
//		SingleClassTagListTestCase aServerTaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveServerTagged.class);
//		
//		SingleClassTagListTestCase aClient1TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveClient1Tagged.class);
//		SingleClassTagListTestCase aClient2TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveClient2Tagged.class);
//		SubstringSequenceChecker aServerCheck = new ARegularCounterServerChecker();
//		init(aServerCheck, aServerTaggedTestCase, aClient1TaggedTestCase, aClient2TaggedTestCase);
	}
	protected SubstringSequenceChecker outputChecker() {
		return new ARegularCounterServerChecker(); 
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		return independentTest(project, autoGrade);
	}
	public void taggedDefaultTest() {
		SingleClassTagListTestCase aServerTaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(CustomRPCCounterServerTagged.class);

		SingleClassTagListTestCase aClient1TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(CustomRPCCounterClient1Tagged.class);
		SingleClassTagListTestCase aClient2TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(CustomRPCCounterClient2Tagged.class);
		SubstringSequenceChecker aServerCheck = outputChecker();
		init(aServerCheck, aServerTaggedTestCase, aClient1TaggedTestCase, aClient2TaggedTestCase);
		super.defaultTest();
	}
	
	@Override	
	public void defaultTest() {
		 taggedDefaultTest();
//		SingleClassTagListTestCase aServerTaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ExplicitReceiveServerTagged.class);
//
//		SingleClassTagListTestCase aClient1TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ExplicitReceiveClient1Tagged.class);
//		SingleClassTagListTestCase aClient2TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ExplicitReceiveClient2Tagged.class);
//		SubstringSequenceChecker aServerCheck = outputChecker();
//		init(aServerCheck, aServerTaggedTestCase, aClient1TaggedTestCase, aClient2TaggedTestCase);
//		super.defaultTest();
	}

}
