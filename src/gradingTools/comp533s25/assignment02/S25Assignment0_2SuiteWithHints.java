package gradingTools.comp533s25.assignment02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s24.assignment02.MonitorHints;
import gradingTools.comp533s24.assignment02.MonitorTests;
import gradingTools.comp533s24.assignment02.hints.conditionQueueEntered.ConditionQueueEnteredHints;
import gradingTools.comp533s24.assignment02.hints.conditionQueueHas4.ConditionQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.entryQueueHas4.EntryQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO.EntryQueueNotFIFOHints;
import gradingTools.comp533s24.assignment02.hints.urgentNotFIFO.UrgentQueueNotFIFOHints;
import gradingTools.comp533s24.assignment02.hints.urgentQueueHas4.UrgentQueueHas4Hints;
import gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence.UrgentQueuePrecedenceHints;
import gradingTools.comp533s24.assignment02.tests.EntryQueueNotFIFO;
import gradingTools.comp533s25.assignment02.hints.threadMapping.ThreadMapingHints;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	MonitorTests.class,
	MonitorHints.class
})
	

//@MaxValue(50)
public class S25Assignment0_2SuiteWithHints {

	public static void setTestMagicString(String aString) {
		PassFailJUnitTestCase.setTesterRunMagic(aString);
	}
	public static void main (String[] args) {
		try {
			AbstractHint.setCheckIfPrecedingTestIsCorrect(false);
			AbstractHint.setCheckIfPrecedingHintHasBeenExecuted(true);
			BasicJUnitUtils.interactiveTest(S25Assignment0_2SuiteWithHints.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		PassFailJUnitTestCase.setRunMagic("pdc");
		PassFailJUnitTestCase.setTimeToStart(2025, 03, 04, 12, 30);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLogOutput(false);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLogSource(false);
	}
	
}
