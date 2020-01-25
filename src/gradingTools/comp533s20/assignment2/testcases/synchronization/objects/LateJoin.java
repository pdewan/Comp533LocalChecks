package gradingTools.comp533s20.assignment2.testcases.synchronization.objects;

import util.annotations.MaxValue;
@MaxValue(20)
public class LateJoin extends EarlyJoin {
	
	

	protected void setTimeouts() {
		slaveTimeout = 0;
		masterTimeout = 300;
	}
	
	

}
