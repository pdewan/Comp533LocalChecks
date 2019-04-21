package gradingTools.comp533s19.assignment4.testcases.explicit_receive;

import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;

public class AnExplicitReceiveServerReceivesChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
		toPrefixedRegex("I\\*\\*\\*" , "main..ReceivedMessageDequeued", "Client1"),
		toPrefixedRegex("Received message", "Client1"),
		toPrefixedRegex("I\\*\\*\\*" , "main..ReceivedMessageDequeued", "Client2"),
		toPrefixedRegex( "Received message:", "Client2")
	};
	public AnExplicitReceiveServerReceivesChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
