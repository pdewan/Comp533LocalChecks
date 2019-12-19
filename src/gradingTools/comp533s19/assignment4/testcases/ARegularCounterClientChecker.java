package gradingTools.comp533s19.assignment4.testcases;

import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class ARegularCounterClientChecker extends ASubstringSequenceChecker{
	public static final String[] MY_SUBSTRINGS = {
		"Sending counter increment message:2.*",
		"Making RPC call with increment:1.*",
		"Making RPC call to get counter value.*",
		"Returned remote value.*"
		
	};
	public ARegularCounterClientChecker() {
		super(MY_SUBSTRINGS);
	}

}
