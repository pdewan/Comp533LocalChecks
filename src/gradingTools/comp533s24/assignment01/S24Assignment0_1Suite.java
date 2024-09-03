package gradingTools.comp533s24.assignment01;

import java.io.PrintStream;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.output.observer.ObservablePrintStream;
import grader.basics.output.observer.ObservablePrintStreamFactory;
import gradingTools.shared.testcases.ConcurrencySuiteSkeleton;
import gradingTools.shared.testcases.concurrency.oddNumbers.BasicsLargerProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.BasicsSmallProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.FairAllocationLargerProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.FairAllocationSmallProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.ForkJoinLargerProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.ForkJoinSmallProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.LargerNumberOfRandoms;
import gradingTools.shared.testcases.concurrency.oddNumbers.LargerNumberSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.SmallNumberOfRandoms;
import gradingTools.shared.testcases.concurrency.oddNumbers.SmallNumberSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.SynchronizationLargerProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.SynchronizationSmallProblem;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.FairAllocationHintsSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.ForkJoinHintsSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.SynchronizationHintsSuite;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.HangingHintsSuite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	SmallNumberSuite.class,
	LargerNumberSuite.class,
	ForkJoinHintsSuite.class,
	FairAllocationHintsSuite.class,
	SynchronizationHintsSuite.class,
	HangingHintsSuite.class
//	SmallNumberOfRandoms.class,
//	LargerNumberOfRandoms.class,
//	ForkJoinSmallProblem.class,
//	BasicsSmallProblem.class,
//	FairAllocationSmallProblem.class,
//	SynchronizationSmallProblem.class,
//	ForkJoinLargerProblem.class,
//	BasicsLargerProblem.class,
//	SynchronizationLargerProblem.class,
//	FairAllocationLargerProblem.class,
	
})
	
// 1.1, 2.2, 3.5 = 8
//@MaxValue(50)
public class S24Assignment0_1Suite extends ConcurrencySuiteSkeleton{
//	public static final String ROOT_CLASS = "ConcurrentOddNumbers";
//	public static final String DISPATCHER_CLASS = "OddNumbersDispatcher";
//
////	public static final String WORKER_CLASS = "OddNumbersWorke";
//	public static final String WORKER_CLASS = "OddNumbersWorkerCode";

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setCheckStyleConfiguration("unc_checks_533_A0_1.xml");
			BasicJUnitUtils.interactiveTest(S24Assignment0_1Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
//		ObservablePrintStream anObservablePrintStream = ObservablePrintStreamFactory.getObservablePrintStream();
//		anObservablePrintStream.setRedirectionFrozen(true);
//		System.setOut((PrintStream) anObservablePrintStream);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A0_1.xml");
	}
	
}
