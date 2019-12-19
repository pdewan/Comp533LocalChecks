package gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneTokenCountPartition1ReduceChecker extends ASubstringSequenceChecker{
	public static final String PARTITION_1_TOKEN = "Hogwarts";
	public static final String THREAD_1 = "0";
	public static final String NUM_OCCURENCES_1 = "5";
	public static final String BARRIER_END = "(" + AMapReduceTracer.BARRIER_WAIT_END + "|" + AMapReduceTracer.BARRIER_RELEASE_ALL + ")";
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.AWordPartitioner@68c89b0a:Partition Assigned:Hogwarts:5:0:3

//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ABarrier@6cf712cb:Barrier Wait End:mapreduce.ABarrier@6cf712cb:3:0
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@78308db1:Partition After Barrier:0:[(Hogwarts,5)]
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingReducer@660f79bb:reduce:[(Hogwarts,5)]:{Hogwarts=5}
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:Model:Added to Final Map:{Hogwarts=5, muggles=3, wizards=2}:{Hogwarts=5}
//	
//	public  final String[] MY_SUBSTRINGS = {
//		toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.PARTITION_ASSIGNED, token(), threadNumber(), "3"),
//		toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  AMapReduceTracer.BARRIER_WAIT_END),
//		toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  AMapReduceTracer.PARTITION_AFTER_BARRIER, THREAD_1, token()),
//		toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  AMapReduceTracer.ADDED_TO_FINAL_MAP, token(), "5", ":", token(), "5"),
//
//
////				*1.*2",
////				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
////				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
////			)
//
////		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
////			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
//	};
	public  String[] mySubstrings() {
		return new String[] {
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.PARTITION_ASSIGNED, token(), threadNumber(), "3"),
			toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  BARRIER_END),
			toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  AMapReduceTracer.PARTITION_AFTER_BARRIER, threadNumber(), token()),
			toLineRegex(AMapReduceTracer.SLAVE +  threadNumber() ,  AMapReduceTracer.ADDED_TO_FINAL_MAP, token(), numOccurences(), ":", token())
		};
	}

	public AStandAloneTokenCountPartition1ReduceChecker() {
		init( mySubstrings());
	}
	protected String threadNumber() {
		return THREAD_1;
	}
	protected String token() {
		return PARTITION_1_TOKEN;
	}
	protected String numOccurences() {
		return NUM_OCCURENCES_1;
	}

}
