package gradingTools.comp533s20.assignment4.testcases;

import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectReadWriteTestCase;
import util.annotations.MaxValue;
@MaxValue(40)
public class RMITwoClientReadWriteNonAtomic extends FlexibleTwoClientCorrectReadWriteTestCase {

	public RMITwoClientReadWriteNonAtomic() {
		super(false, false, true, false);
	}
	
}
