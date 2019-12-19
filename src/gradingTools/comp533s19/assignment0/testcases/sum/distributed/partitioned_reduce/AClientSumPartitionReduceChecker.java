package gradingTools.comp533s19.assignment0.testcases.sum.distributed.partitioned_reduce;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.AStandAloneTokenCountPartition1ReduceChecker;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.AStandAloneTokenCountPartition3ReduceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AClientSumPartitionReduceChecker extends AStandAloneTokenCountPartition3ReduceChecker{

//	public  final String[] MY_SUBSTRINGS = {
//
//			    toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REMOTE_LIST),
//				toRegex("RMI TCP Connection" +   ".*:.*" + "reduce:"),
//
//	};
	public  String[] mySubstrings() {
		return new String[] {
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_LIST),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REDUCE),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_RESULT, "45"),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_LIST),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REDUCE),
				 toLineRegex("RMI TCP Connection", AMapReduceTracer.REMOTE_RESULT, "5500"),
		};
	}

}
