package gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneTokenCountPartition2ReduceChecker extends AStandAloneTokenCountPartition1ReduceChecker{
	public static final String PARTITION_2_TOKEN = "muggles";
	public static final String THREAD_2 = "1";
	public static final String NUM_OCCURENCES_2 = "3";

	
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.AWordPartitioner@68c89b0a:Partition Assigned:Hogwarts:5:0:3

//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ABarrier@6cf712cb:Barrier Wait End:mapreduce.ABarrier@6cf712cb:3:0
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@78308db1:Partition After Barrier:0:[(Hogwarts,5)]
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingReducer@660f79bb:reduce:[(Hogwarts,5)]:{Hogwarts=5}
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:Model:Added to Final Map:{Hogwarts=5, muggles=3, wizards=2}:{Hogwarts=5}
//	
	
	protected String threadNumber() {
		return THREAD_2;
	}
	protected String token() {
		return PARTITION_2_TOKEN;
	}
	protected String numOccurences() {
		return NUM_OCCURENCES_2;
	}

}
