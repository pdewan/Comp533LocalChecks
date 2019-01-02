package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(5)
public class OneClientThreadsAtomic extends OneClientCorrectThreadsTestCase{

	public OneClientThreadsAtomic() {
		super(true);
	}

}
