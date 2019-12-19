package gradingTools.comp533s19.assignment0.testcases.sum.standalone.threads;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneCountThreadsChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
//				AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUIT,
				toRegex(AMapReduceTracer.SLAVE + "0"),
				toRegex(AMapReduceTracer.SLAVE + "1"),
//				toRegex(AMapReduceTracer.SLAVE + "2")
//				*1.*2",
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneCountThreadsChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
