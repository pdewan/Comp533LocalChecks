package gradingTools.comp533s24.assignment02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s24.assignment02.tests.ConditionQueueEntered;
import gradingTools.comp533s24.assignment02.tests.EntryQueueHas4;
import gradingTools.comp533s24.assignment02.tests.EntryQueueNotFIFO;
import gradingTools.comp533s24.assignment02.tests.ThreadMappingChanges;
import gradingTools.comp533s24.assignment02.tests.UrgentQueueHas4;
import gradingTools.comp533s24.assignment02.tests.UrgentQueueHasPrecedence;
import gradingTools.comp533s24.assignment02.tests.UrgentQueueNotFIFO;
import gradingTools.shared.testcases.ConcurrencySuiteSkeleton;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConditionQueueEntered.class,
	EntryQueueHas4.class,
	UrgentQueueHas4.class,
	EntryQueueNotFIFO.class,
	UrgentQueueHasPrecedence.class,
	UrgentQueueNotFIFO.class,
	ThreadMappingChanges.class,
	
})
	

//@MaxValue(50)
public class MonitorTests {

	
}
