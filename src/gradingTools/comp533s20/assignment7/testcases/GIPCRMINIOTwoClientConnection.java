package gradingTools.comp533s20.assignment7.testcases;

import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class GIPCRMINIOTwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public GIPCRMINIOTwoClientConnection() {
//		super("One client correct connection test case");

		super(true, true, true);

	}
	

}
