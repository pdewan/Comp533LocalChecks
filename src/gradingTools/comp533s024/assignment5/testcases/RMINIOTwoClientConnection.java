package gradingTools.comp533s024.assignment5.testcases;

import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class RMINIOTwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public RMINIOTwoClientConnection() {
//		super("One client correct connection test case");

		super(true, true, false);

	}
	

}
