package gradingTools.comp533s19.assignment0.testcases.sums.standalone;

import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneBasicIntSummerResultChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
				".*=45.*",
				".*=5500.*",
//=======
//			toRegex(
//				"View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*=45"
////				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*=5500.*",
//>>>>>>> refs/remotes/origin/master
//				
//			)

	};
	public AStandAloneBasicIntSummerResultChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
