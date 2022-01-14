package gradingTools.comp533s22.assignment1.listUpdatedTests;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class AStandAloneSingleThreadSumMapReduceCheckerListImplementation extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public final String[] MY_SUBSTRINGS = {
				".*"+AMapReduceTracer.MAP+".*"+appendBetween(", ",numberList)+".*"+resultKeyRegex+".*",
				".*"+AMapReduceTracer.REDUCE+".*"+resultKeyRegex+".*5500.*",
				
	};
	
	private static String resultKeyRegex="(\\(ResultKey, ?[0-9]+\\).*){10}";
	private static String [] numberList= {
			"100",
			"200",
			"300",
			"400",
			"500",
			"600",
			"700",
			"800",
			"900",
			"1000"
	};
	
	protected static String appendBetween( String add, String... arr) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length-1;i++) {
			sb.append(arr[i]);
			sb.append(add);
		}
		sb.append(arr[arr.length-1]);
		return sb.toString();
	}
	
	public AStandAloneSingleThreadSumMapReduceCheckerListImplementation() {
		init( MY_SUBSTRINGS);
	}
	

}
