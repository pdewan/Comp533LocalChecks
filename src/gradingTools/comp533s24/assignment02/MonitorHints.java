package gradingTools.comp533s24.assignment02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s24.assignment02.hints.conditionQueueEntered.ConditionQueueEnteredHint1;
import gradingTools.comp533s24.assignment02.hints.conditionQueueEntered.ConditionQueueEnteredHint2;
import gradingTools.comp533s24.assignment02.hints.conditionQueueEntered.ConditionQueueEnteredHint3;
import gradingTools.comp533s24.assignment02.hints.entryQueueHas4.EntryQueueHas4Hint1;
import gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO.EntryQueueNotFIFOHint1;
import gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO.EntryQueueNotFIFOHint2;
import gradingTools.comp533s24.assignment02.hints.urgentNotFIFO.UrgentQueueNotFIFOHint1;
import gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence.UrgentQueueHasPrecedenceHint1;
import gradingTools.comp533s25.assignment02.hints.threadMapping.ThreadMappingHint1;
import gradingTools.comp533s25.assignment02.hints.threadMapping.ThreadMappingHint2;
import gradingTools.comp533s25.assignment02.hints.threadMapping.ThreadMappingHint3;
import gradingTools.comp533s25.assignment02.hints.urgentQueueHas4.UrgentQueueHas4Hint1;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConditionQueueEnteredHint1.class,
	ConditionQueueEnteredHint2.class,
	ConditionQueueEnteredHint3.class,
	EntryQueueHas4Hint1.class,
	UrgentQueueHas4Hint1.class,
	EntryQueueNotFIFOHint1.class,
	EntryQueueNotFIFOHint2.class,
	UrgentQueueHasPrecedenceHint1.class,
//	UrgentQueueHasPrecedenceHint2.class,
	UrgentQueueNotFIFOHint1.class,
//	UrgentQueueNotFIFOHint2.class,
	ThreadMappingHint1.class,
	ThreadMappingHint2.class,
	ThreadMappingHint3.class,	

})
	

//@MaxValue(50)
public class MonitorHints {

	
}
