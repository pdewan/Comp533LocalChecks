package gradingTools.comp533s19.assignment0.testcases.sums.standalone;

import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneBasicIntSummerResultChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*=45.*",
				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*=5500.*",
				
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneBasicIntSummerResultChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
