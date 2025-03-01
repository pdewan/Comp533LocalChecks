package gradingTools.comp533s24.assignment02.hints.conditionQueueEntered;

import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;

@Explanation("Priducers and consumers swutch turns")
public class ConditionQueueEnteredHint2 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ConditionQueueEnteredHint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		return PREVIOUS_HINTS;
	}


	@Override
	protected String hint() {
		String aLine1 = "An out of turn thread is a producer if the last value printed by the monitor was a consumption";
		String aLine2 = "An out of turn thread is a consumer if the monitor has not yet printed a value or has printed a production\n";
		return "\n" + aLine1 + "\n" + aLine2 + "\n";
	}

}
