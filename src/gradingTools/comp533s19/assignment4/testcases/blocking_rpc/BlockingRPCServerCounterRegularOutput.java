package gradingTools.comp533s19.assignment4.testcases.blocking_rpc;

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
@Explanation("Checks for expected server counter output when blocking procedures are implemented.")
public class BlockingRPCServerCounterRegularOutput extends DistributedCounterProgramRunningTestCase {

	public BlockingRPCServerCounterRegularOutput() {
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
		SingleClassTagListTestCase aServerTaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCCounterServerTagged.class);

		SingleClassTagListTestCase aClient1TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCCounterClient1Tagged.class);
		SingleClassTagListTestCase aClient2TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCCounterClient2Tagged.class);
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
