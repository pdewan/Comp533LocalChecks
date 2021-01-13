package gradingTools.comp533s21.assignment9.testcases;

import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class ASerializerPoolChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		toPrefixedRegex("I\\*\\*\\*" , "SerializerTakenFromPool")	
		
		
	};
	public ASerializerPoolChecker() {
		super(MY_SUBSTRINGS);
	}
/*
 .*ReceivedMessageQueueCreated.*Server0<-->Client1 (Opened).*ReceivedMessageQueueCreated.*Server0<-->Client2 (Opened).*
 */
}
