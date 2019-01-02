package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(10)
public class OneClientMessageRatioNonAtomic extends OneClientMessageRatioTestCase {

	public OneClientMessageRatioNonAtomic() {
		super(false);
	}

}
