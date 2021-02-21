package gradingTools.comp533s21.assignment3.facebookMapReduce.checkers;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.shared.testcases.ASubstringSequenceChecker;


public class AClientFacebookMapReduceQuitChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions

	String clientName;
	
	public AClientFacebookMapReduceQuitChecker() {
		init( makeMySubstrings());
	}
	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
				toRegex("RMI TCP Connection", AMapReduceTracer.QUITTING),
				toRegex("RMI TCP Connection", AMapReduceTracer.NOTIFY),
				toRegex("main", AMapReduceTracer.EXIT)
	
		};
		return MY_SUBSTRINGS;
	}

}
