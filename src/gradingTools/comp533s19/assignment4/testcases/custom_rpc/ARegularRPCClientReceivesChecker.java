package gradingTools.comp533s19.assignment4.testcases.custom_rpc;

import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class ARegularRPCClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
	public ARegularRPCClientReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
