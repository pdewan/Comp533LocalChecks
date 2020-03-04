package gradingTools.comp533s20.assignment5.testcases;

import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class GIPCRMITwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public GIPCRMITwoClientConnection() {
//		super("One client correct connection test case");

		super(false, true, true);

	}
	

}
