package gradingTools.comp533s19.assignment2.testcases;

import gradingTools.comp533s19.flexible.testcases.FlexibleTwoClientCorrectConnectionTestCase;
import util.annotations.MaxValue;

@MaxValue(20)
//@Group("Test group name")
public class RMINIOTwoClientConnection extends FlexibleTwoClientCorrectConnectionTestCase {
	
	
	public RMINIOTwoClientConnection() {
//		super("One client correct connection test case");

		super(true, true, false);

	}
	

}
