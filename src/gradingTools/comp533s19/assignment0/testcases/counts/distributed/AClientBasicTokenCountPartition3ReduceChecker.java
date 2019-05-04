package gradingTools.comp533s19.assignment0.testcases.counts.distributed;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.AStandAloneBasicTokenCountPartition1ReduceChecker;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.AStandAloneBasicTokenCountPartition3ReduceChecker;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AClientBasicTokenCountPartition3ReduceChecker extends AStandAloneBasicTokenCountPartition3ReduceChecker{

//	public  final String[] MY_SUBSTRINGS = {
//
//			    toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REMOTE_LIST),
//				toRegex("RMI TCP Connection" +   ".*:.*" + "reduce:"),
//
//	};
	public  String[] mySubstrings() {
		return new String[] {
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_LIST, PARTITION_3_TOKEN),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REDUCE, PARTITION_3_TOKEN),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_RESULT, PARTITION_3_TOKEN, NUM_OCCURENCES_3),
		};
	}

}
