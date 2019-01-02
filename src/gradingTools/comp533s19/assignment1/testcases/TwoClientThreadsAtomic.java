package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(5)
public class TwoClientThreadsAtomic extends TwoClientCorrectThreadsTestCase{

	public TwoClientThreadsAtomic() {
		super(true);
	}

}
