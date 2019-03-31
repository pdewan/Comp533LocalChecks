package gradingTools.comp533s19.assignment3.testcases;

import gradingTools.comp533s19.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class GIPCRMINIOTwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public GIPCRMINIOTwoClientConnection() {
//		super("One client correct connection test case");

		super(true, true, true);

	}
	

}
