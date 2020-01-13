package gradingTools.comp533s20.assignment1.testcases.counts.standalone.singlethread;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSingleThreadTokenCounterMapReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
				toRegex(AMapReduceTracer.MAP + ".*" + "Hogwarts" + ".*" + "1"),
				toRegex(AMapReduceTracer.MAP + ".*" + "Hogwarts" + ".*" + "1"),			
				toRegex(AMapReduceTracer.MAP + ".*" + "muggles" + ".*" + "1"),
				toRegex(AMapReduceTracer.MAP + ".*" + "wizards" + ".*" + "1"),
				toRegex(AMapReduceTracer.MAP + ".*" + "Hogwarts" + ".*" + "1"),
				toRegex(AMapReduceTracer.MAP + ".*" + "muggles" + ".*" + "1"),
				toRegex(AMapReduceTracer.MAP + ".*" + "wizards" + ".*" + "1"),
				toRegex(AMapReduceTracer.REDUCE + ".*" + "Hogwarts" + ".*" + "1" + ".*" +  "Hogwarts" + ".*" + "5"),
//				toRegex(AMapReduceTracer.REDUCE + ".*" + "wizards" + ".*" + "1" + ".*" +  "wizards" + ".*" + "2"),
//				toRegex(AMapReduceTracer.REDUCE + ".*" + "muggles" + ".*" + "1" + ".*" +  "muggles" + ".*" + "3"),






//				"View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneSingleThreadTokenCounterMapReduceChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
