package gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSingleThreadSumMapReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
				toRegex(AMapReduceTracer.MAP + ".*" + "100" + ".*,.*" + "100"),
				toRegex(AMapReduceTracer.MAP + ".*" + "200" +  ".*,.*"  + "200"),
				toRegex(AMapReduceTracer.MAP + ".*" + "300" +  ".*,.*"  + "300"),
				toRegex(AMapReduceTracer.MAP + ".*" + "400" +  ".*,.*"  + "400"),
				toRegex(AMapReduceTracer.MAP + ".*" + "500" +  ".*,.*"  + "500"),
				toRegex(AMapReduceTracer.MAP + ".*" + "600" +  ".*,.*"  + "600"),
				toRegex(AMapReduceTracer.MAP + ".*" + "700" +  ".*,.*"  + "700"),
				toRegex(AMapReduceTracer.MAP + ".*" + "800" +  ".*,.*"  + "800"),
				toRegex(AMapReduceTracer.MAP + ".*" + "900" +  ".*,.*"  + "900"),
				toRegex(AMapReduceTracer.MAP + ".*" + "1000" +  ".*,.*"  + "1000"),
				toRegex(AMapReduceTracer.REDUCE + ".*" + "100" + ".*" + "200" + ".*" + ".*" + "1000" + ".*" +  "5500"),

				//				"View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneSingleThreadSumMapReduceChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
