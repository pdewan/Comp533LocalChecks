package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;

@MaxValue(10)
public class ReadWriteUpdateOrderNonAtomic extends ReadWriteUpdateOrderTestCase{

	public ReadWriteUpdateOrderNonAtomic() {
		super(false);
	}

}
