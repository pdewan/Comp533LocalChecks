package gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.annotations.Explanation;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;
@Explanation("Checks that the int summing main changes the mapper and traces this change")
public class AStandAloneSingleThreadSumMapFactoryChangeChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
				toRegex(AMapReduceTracer.NEW_MAPPER)				

				//				"View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneSingleThreadSumMapFactoryChangeChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
