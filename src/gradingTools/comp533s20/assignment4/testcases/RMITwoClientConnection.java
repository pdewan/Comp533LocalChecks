package gradingTools.comp533s20.assignment4.testcases;

import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class RMITwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public RMITwoClientConnection() {
//		super("One client correct connection test case");

		super(false, true, false);

	}
	

}
