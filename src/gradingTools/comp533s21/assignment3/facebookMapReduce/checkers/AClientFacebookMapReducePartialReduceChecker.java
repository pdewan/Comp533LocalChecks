package gradingTools.comp533s21.assignment3.facebookMapReduce.checkers;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class AClientFacebookMapReducePartialReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	String clientName;
	String slaveName;
	
	public  final String[] MY_SUBSTRINGS = {
			    toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REMOTE_LIST),
				toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REDUCE),
	};

	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
				    toRegex("RMI TCP Connection" +   ".*:.*" + "Remote List"),
					toRegex("RMI TCP Connection" +   ".*:.*" + "reduce:"),
		};
		return MY_SUBSTRINGS;
	}
	public AClientFacebookMapReducePartialReduceChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
