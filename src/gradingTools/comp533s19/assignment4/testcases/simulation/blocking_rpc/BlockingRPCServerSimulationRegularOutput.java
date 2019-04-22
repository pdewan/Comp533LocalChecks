package gradingTools.comp533s19.assignment4.testcases.simulation.blocking_rpc;

import org.junit.jupiter.api.Test;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.JUnitTestCase;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.TagbasedTwoClientCorrectReadWriteTestCase;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(5)
@Explanation("Checks for expected server simulation output when blocking procedures are implemented.")
public class BlockingRPCServerSimulationRegularOutput extends TagbasedTwoClientCorrectReadWriteTestCase {

	public BlockingRPCServerSimulationRegularOutput() {
//		SingleClassTagListTestCase aServerTaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveServerTagged.class);
//		
//		SingleClassTagListTestCase aClient1TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveClient1Tagged.class);
//		SingleClassTagListTestCase aClient2TaggedTestCase = (SingleClassTagListTestCase) JUnitTestsEnvironment.getPassFailJUnitTest(ExplicitReceiveClient2Tagged.class);
//		SubstringSequenceChecker aServerCheck = new ARegularCounterServerChecker();
//		init(aServerCheck, aServerTaggedTestCase, aClient1TaggedTestCase, aClient2TaggedTestCase);
	}
//	protected SubstringSequenceChecker outputChecker() {
//		return new ARegularCounterServerChecker(); 
//	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		return independentTest(project, autoGrade);
	}
	protected SingleClassTagListTestCase registryTagTest() {
		return (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCSimulationRegistryTagged.class);
	}
	protected SingleClassTagListTestCase serverTagTest() {
		return (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCSimulationServerTagged.class);
	}
	protected SingleClassTagListTestCase clientTagTest() {
		return (SingleClassTagListTestCase) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(BlockingRPCSimulationClientTagged.class);
	}
	public void taggedDefaultTest() {
//		
//		SubstringSequenceChecker aServerCheck = outputChecker();
		init(
		atomic(),
		doNIO(),
		doRMI(),
		doGIPC(),
		registryTagTest(),
		serverTagTest(),
		clientTagTest()
		);
//		init(atomic, doNIO, doRMI, doGIPC, aRegistryTagTest, aServerTagTest, aClientTagTest);
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
