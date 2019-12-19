package gradingTools.comp533s19.assignment0.testcases.counts.standalone;

import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneTokenCountResultChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
			toRegex(
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Hogwarts=5, muggles=3, wizards=2.; propagationId=null; source=Model..*",
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Abbott=1, Zabini=1, Creevey=1, Weasley=1, Dumbledore=2, Potter=2, Longbottom=1, Snape=1, Voldemort=2.; propagationId=null; source=Model..*",
				"java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5"
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public AStandAloneTokenCountResultChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
