package gradingTools.comp533s19.assignment0.testcases.counts.standalone;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneBasicTokenCounterMVCChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
				toRegex(AMapReduceTracer.CONTROLLER + ".*" + "Please"),
//				AMapReduceTracer.VIEW + "." + "propertyName=Threads" + ".*" + AMapReduceTracer.SLAVE + "0.*1.*2",

				toRegex(AMapReduceTracer.VIEW + ".*" + "propertyName=Threads" + ".*" + AMapReduceTracer.SLAVE),
//					AMapReduceTracer.VIEW + "." + "propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"

				".*"+ AMapReduceTracer.VIEW + ".*" + "propertyName=Result" + ".*Hogwarts=5.*"
//				"View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneBasicTokenCounterMVCChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
