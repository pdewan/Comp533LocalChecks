package gradingTools.comp533s19.assignment4.testcases.blocking_rpc;

import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class ABlockingRPCClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
			toPrefixedRegex("I\\*\\*\\*",  "ProxyMethodCalled"),
			toPrefixedRegex("I\\*\\*\\*", "ReceivedMessageDequeued", "AnRPCReturnValue"),
//			toPrefixedRegex("I\\*\\*\\*",  "main..ProxyMethodCalled"),
			toPrefixedRegex("I\\*\\*\\*",  "ProxyMethodCalled"),

			toPrefixedRegex("I\\*\\*\\*", "ReceivedMessageDequeued", "AnRPCReturnValue")
//			toPrefixedRegex("I\\*\\*\\*." + "main..ProxyMethodCalled.*",
//			"I\\*\\*\\*." + "main..ReceivedMessageDequeued.*AnRPCReturnValue.*"
		
	};
	public ABlockingRPCClientReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
