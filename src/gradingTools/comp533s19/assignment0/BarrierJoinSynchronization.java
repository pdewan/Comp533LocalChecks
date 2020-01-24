package gradingTools.comp533s19.assignment0;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderAtomic;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderNonAtomic;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.partial_reduce.ClientTokenCountPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.partial_reduce.DistributedTokenCountPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.partitioned_reduce.ClientTokenCountPartition3Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.quit.ClientTokenCountQuit;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.quit.DistributedTokenCountQuit;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.barrier.TokenCountBarrier;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.bounded_buffer.TokenCountBoundedBuffer;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.joiner.TokenCountJoiner;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multiple_rounds.TokenCountMultipleRoundSynchronization;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multithread.mvc.StandAloneMultiThreadTokenCountMVC;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partial_reduce.TokenCountBulkPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partial_reduce.TokenCountMultiplePartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition1Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition2Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition3Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.quit.StandAloneTokenCountQuit;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.threads.TokenCountThreads;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.ReducerFactory;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment0.testcases.objects.DefaultFactoryReduceObject;
import gradingTools.comp533s19.assignment0.testcases.objects.DefaultFactoryMapObject;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.DistributedSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.partial_reduce.ClientSumPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.partitioned_reduce.ClientSumPartitionReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.quit.ClientSumQuit;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.quit.DistributedSumQuit;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.barrier.SumBarrier;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.bounded_buffer.MultiThreadSumBoundedBuffer;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.joiner.SumJoiner;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.multiple_rounds.MultiThreadSumMultipleRoundSynchronization;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.partial_reduce.MultiThreadSumBulkPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.partial_reduce.MultiThreadSumMultiplePartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.threads.MultiThreadSumThreads;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.ClientTagged;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ServerTagged;
//import gradingTools.comp533s19.assignment1.testcases;
import gradingTools.comp533s19.assignment1.testcases.TwoClientConnection;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsNonAtomic;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.port.rpc.RemoteCallBlockedForReturnValue;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	TokenCountBarrier.class,
	SumBarrier.class,
	TokenCountJoiner.class,
	SumJoiner.class,
})
	

@MaxValue(100)	
public class BarrierJoinSynchronization extends Assignment1Suite{

}
