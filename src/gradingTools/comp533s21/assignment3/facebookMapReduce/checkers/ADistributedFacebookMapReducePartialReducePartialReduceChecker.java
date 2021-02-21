package gradingTools.comp533s21.assignment3.facebookMapReduce.checkers;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class ADistributedFacebookMapReducePartialReducePartialReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	String clientName;
	String slaveName;
	
	public  final String[] MY_SUBSTRINGS = {
			    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
				toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote List"),
			    toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "Remote List"),
				toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "reduce:"),
			    toRegex(clientName + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote Result"),
	};
	public ADistributedFacebookMapReducePartialReducePartialReduceChecker(String aClientName, String aSlaveName) {
		clientName = aClientName;
		slaveName = aSlaveName;
		init( makeMySubstrings());

	}
	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote List"),
				    toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "Remote List"),
					toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "reduce:"),
				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote Result"),
		};
		return MY_SUBSTRINGS;
	}	

}
