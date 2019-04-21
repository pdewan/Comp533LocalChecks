package gradingTools.comp533s19.assignment4.testcases.explicit_receive;

import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class AnExplicitReceiveClientReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
		toPrefixedRegex("I\\*\\*\\*" , "main..ReceivedMessageDequeued", "AnRPCReturnValue"),
		toPrefixedRegex("Received message:","AnRPCReturnValue")
	};
	public AnExplicitReceiveClientReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
