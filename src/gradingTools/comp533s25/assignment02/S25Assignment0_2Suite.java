package gradingTools.comp533s25.assignment02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s24.assignment02.MonitorTests;
import gradingTools.comp533s24.assignment02.hints.conditionQueueHas4.ConditionQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.entryQueueHas4.EntryQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO.EntryQueueNotFIFOHints;
import gradingTools.comp533s24.assignment02.hints.threadMapping.ThreadMapingHints;
import gradingTools.comp533s24.assignment02.hints.urgentNotFIFO.UrgentQueueNotFIFOHints;
import gradingTools.comp533s24.assignment02.hints.urgentQueueHas4.UrgentQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence.UrgentQueuePrecedenceHints;
import gradingTools.comp533s24.assignment02.tests.EntryQueueNotFIFO;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	MonitorTests.class,
	
})
// 7
	

//@MaxValue(50)
public class S25Assignment0_2Suite {
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
			AbstractHint.setCheckIfPrecedingTestIsCorrect(false);
			AbstractHint.setCheckIfPrecedingHintHasBeenExecuted(true);
			BasicJUnitUtils.interactiveTest(S25Assignment0_2Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
//		ObservablePrintStream anObservablePrintStream = ObservablePrintStreamFactory.getObservablePrintStream();
//		anObservablePrintStream.setRedirectionFrozen(true);
//		System.setOut((PrintStream) anObservablePrintStream);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_533_A0_1.xml");
	}
	
}
