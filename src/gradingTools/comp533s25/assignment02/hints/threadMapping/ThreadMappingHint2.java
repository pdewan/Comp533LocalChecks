package gradingTools.comp533s25.assignment02.hints.threadMapping;

import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("Try to make a non main thread take a main thread's released server thread")
public class ThreadMappingHint2 extends AbstractHint{
	
	static Class[] PREVIOUS_HINTS = {
			ThreadMappingHint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}

	@Override
	protected String hint() {
		String aLine1 = "Try to put at least two non-main threads between two main threads in the entry queue, ensuring no thread goes into the condition queue";
		String aLine2 = "Use the thread mapping comamnd to see the initial mappings";
		String aLine3 = "Use the release command to make these threads finish their calls";
		String aLine4 = "Use the thread mapping command to see if any maping changed";
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4 + "\n" ;
	}
}
