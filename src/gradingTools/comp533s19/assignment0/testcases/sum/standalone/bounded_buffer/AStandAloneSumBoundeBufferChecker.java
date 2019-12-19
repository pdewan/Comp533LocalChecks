package gradingTools.comp533s19.assignment0.testcases.sum.standalone.bounded_buffer;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSumBoundeBufferChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
//				AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUIT,
				".*" + AMapReduceTracer.SLAVE + "0.*",
				".*" + AMapReduceTracer.SLAVE + "1.*",
				".*" + AMapReduceTracer.SLAVE + "2.*"
//				*1.*2",
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneSumBoundeBufferChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
