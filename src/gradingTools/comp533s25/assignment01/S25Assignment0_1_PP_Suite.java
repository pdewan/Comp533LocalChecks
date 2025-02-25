package gradingTools.comp533s25.assignment01;

import java.io.PrintStream;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.output.observer.ObservablePrintStream;
import grader.basics.output.observer.ObservablePrintStreamFactory;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.AbstractOddNumbersExecution;
import gradingTools.shared.testcases.concurrency.oddNumbers.HelperTestsOddNumbers;
import gradingTools.shared.testcases.concurrency.oddNumbers.LargerNumberTests;
import gradingTools.shared.testcases.concurrency.oddNumbers.SmallNumberTests;
import gradingTools.shared.testcases.concurrency.oddNumbers.SynchronizationSmallProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.context.OddNumbersPiazzaPostSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.context.SynchronizationProblemPiazzaMessage;
import gradingTools.shared.testcases.concurrency.oddNumbers.hanging.OddNumbersPiazzaPosts;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	HelperTestsOddNumbers.class,
	SmallNumberTests.class,
	LargerNumberTests.class,
	OddNumbersPiazzaPosts.class

})
	

//@MaxValue(50)
public class S25Assignment0_1_PP_Suite {
//	public static final String ROOT_CLASS = "ConcurrentOddNumbers";
//	public static final String DISPATCHER_CLASS = "OddNumbersDispatcher";
//
////	public static final String WORKER_CLASS = "OddNumbersWorke";
//	public static final String WORKER_CLASS = "OddNumbersWorkerCode";

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(S25Assignment0_1_PP_Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		ObservablePrintStream anOservablePrintStream = ObservablePrintStreamFactory.getObservablePrintStream();
		anOservablePrintStream.setRedirectionFrozen(true);
		System.setOut((PrintStream) anOservablePrintStream);
		SynchronizationProblemPiazzaMessage.setSynchronizationTestClass(SynchronizationSmallProblem.class);
		if (!AbstractOddNumbersExecution.isDoTests()) {
		PassFailJUnitTestCase.setTimeToStart(2025, 02, 25, 12, 30);
		}
	
	}
	
}
