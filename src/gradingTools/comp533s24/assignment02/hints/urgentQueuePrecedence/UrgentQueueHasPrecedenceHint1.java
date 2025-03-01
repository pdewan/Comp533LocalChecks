package gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence;

import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("Relationship between condition, urgent  and entry queues")
public class UrgentQueueHasPrecedenceHint1 extends AbstractHint{	
	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}
	@Override
	protected String hint() {
		String aLine1 = "Create a situation in which similatenously there are 4 threads in the urgent queue, four in the entry queue, and a thread in the monitor";
		String aLine2 = "Release the monitor occupying thread, which will do a notify all";
		String aLine3 = "Look at the queues";
		String aLine4 = "The urgent queue shold be reduced and the entry queue unchanged";
		String aLine5 = "It is possible that the entry queue is changed and not urgent queue, do not worry if that happens";

		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4 + "\n" + "aLine5"  ; 
	}
	

	

}
