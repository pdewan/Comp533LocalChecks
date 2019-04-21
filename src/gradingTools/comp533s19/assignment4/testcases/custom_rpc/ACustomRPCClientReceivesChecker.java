package gradingTools.comp533s19.assignment4.testcases.custom_rpc;

import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class ACustomRPCClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
			toPrefixedRegex("I\\*\\*\\*" , "ReceivedMessageDequeued"),
//		"main..RemoteCallWaitingForReturnValue",
			toPrefixedRegex("I\\*\\*\\*" , "RemoteCallReturnValueDetermined")
//		"main..ProxyPureFunctionCalled",
	};
	public ACustomRPCClientReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
