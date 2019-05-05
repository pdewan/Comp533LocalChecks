package gradingTools.comp533s19.assignment0.testcases.sum.standalone.partial_reduce;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSumMultiplePartialReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@78308db1:dequeue:(null,null)
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingReducer@660f79bb:reduce:[(Hogwarts,1), (Hogwarts,1), (muggles,1), (wizards,1), (Hogwarts,1), (Hogwarts,1), (Hogwarts,1), (muggles,1), (muggles,1), (wizards,1)]:{Hogwarts=5, muggles=3, wizards=2}
	public  final String[] MY_SUBSTRINGS = {
//			toRegex(
//				AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUIT,
				toRegex(AMapReduceTracer.SLAVE + ".*:.*" + AMapReduceTracer.REDUCE + ".*"),
				toRegex(AMapReduceTracer.SLAVE + ".*:.*" + AMapReduceTracer.REDUCE + ".*"),
				toRegex(AMapReduceTracer.SLAVE + ".*:.*" + AMapReduceTracer.REDUCE + ".*"),
//				*1.*2",
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneSumMultiplePartialReduceChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
