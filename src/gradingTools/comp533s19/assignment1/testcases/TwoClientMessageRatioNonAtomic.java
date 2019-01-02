package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(10)
public class TwoClientMessageRatioNonAtomic extends TwoClientMessageRatioTestCase {

	public TwoClientMessageRatioNonAtomic() {
		super(false);
	}

}
