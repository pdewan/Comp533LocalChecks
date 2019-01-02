package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(5)
public class OneClientThreadsNonAtomic extends OneClientCorrectThreadsTestCase{

	public OneClientThreadsNonAtomic() {
		super(false);
	}

}
