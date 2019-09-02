package gradingTools.comp533s19.assignment0;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicStaticConfigurationUtils;
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
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.StandAloneTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.barrier.StandAloneTokenCountBarrier;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.bounded_buffer.StandAloneTokenCountBoundedBuffer;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.joiner.StandAloneBasicTokenCountJoiner;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multiple_rounds.StandAloneTokenCountMultipleRoundSynchronization;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.mvc.StandAloneTokenCountMVC;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partial_reduce.StandAloneTokenCountBulkPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partial_reduce.StandAloneTokenCountMultiplePartialReduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition1Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition2Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.partition_reduce.StandAloneTokenCountPartition3Reduce;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.quit.StandAloneTokenCountQuit;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.threads.StandAloneTokenCountThreads;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.ReducerFactory;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment0.testcases.objects.ReduceObject;
import gradingTools.comp533s19.assignment0.testcases.objects.TokenCountMapObject;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.DistributedSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.partial_reduce.ClientSumPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.partitioned_reduce.ClientSumPartitionReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.quit.ClientSumQuit;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.quit.DistributedSumQuit;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.StandAloneSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.barrier.StandAloneSumBarrier;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.bounded_buffer.StandAloneSumBoundedBuffer;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.joiner.StandAloneSumJoiner;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.multiple_rounds.StandAloneSumMultipleRoundSynchronization;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.partial_reduce.StandAloneSumBulkPartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.partial_reduce.StandAloneSumMultiplePartialReduce;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.threads.StandAloneSumThreads;
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
import util.tags.DistributedTags;
import util.trace.port.rpc.RemoteCallBlockedForReturnValue;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConfigurationProvided.class,
	BasicStandAloneCounts.class,
	BasicStandAloneSum.class,
	BasicDistributedCounts.class,
	BasicDistributedSum.class,
	MapReduceFactoriesAndObjects.class,
	StandAloneBarrierJoinSynchronization.class,
	StandAlonePartitionedReduce.class,
	DistributedPartitionedReduce.class
	
	
//	PartitionerFactory.class,
//	PartitionerObject.class,
//	MapperFactory.class,
//	TokenCountMapObject.class,
//	ReducerFactory.class,
//	ReduceObject.class,
//	StandAloneTokenCountResult.class,
//	StandAloneSumResult.class,
//	DistributedTokenCountResult.class,
//	DistributedSumResult.class,
//	StandAloneTokenCountMVC.class,
//	StandAloneSumBoundedBuffer.class,
//	StandAloneTokenCountThreads.class,
//	StandAloneSumThreads.class,
//	StandAloneTokenCountBoundedBuffer.class,
//	StandAloneTokenCountBulkPartialReduce.class,
//	StandAloneTokenCountMultiplePartialReduce.class,
//	StandAloneSumBulkPartialReduce.class,
//	StandAloneSumMultiplePartialReduce.class,
//	StandAloneTokenCountPartition1Reduce.class,
//	StandAloneTokenCountPartition2Reduce.class,
//	StandAloneTokenCountPartition3Reduce.class,
//	StandAloneTokenCountBarrier.class,
//	StandAloneSumBarrier.class,
//	StandAloneBasicTokenCountJoiner.class,
//	StandAloneSumJoiner.class,
//	StandAloneTokenCountMultipleRoundSynchronization.class,
//	StandAloneSumMultipleRoundSynchronization.class,
//
//	DistributedTokenCountPartialReduce.class,
////	StandAloneTokenCountQuit.class,
//	DistributedTokenCountQuit.class,
//	DistributedSumQuit.class,
//
//	ClientTokenCountPartialReduce.class,
//	ClientTokenCountPartition3Reduce.class,
//	ClientSumPartialReduce.class,
//	ClientSumPartitionReduce.class,
//
//	ClientTokenCountQuit.class,
//	ClientSumQuit.class
//	Assignment1OneClientSuite.class,
//	Assignment1TwoClientSuite.class,


})
	

	
public class Assignment0Suite extends Assignment1Suite{
//	 static final String[] clientTags = {DistributedTags.NIO, DistributedTags.CLIENT};
//	 static final String[] serverTags = {DistributedTags.NIO, DistributedTags.SERVER};
//	 static final  List<String> clientTagsList = Arrays.asList(clientTags);
//	 static final List<String> serverTagsList = Arrays.asList(serverTags);
	public static final String MAP_REDUCE_PROCESS_TEAM = "MapReduce Team";
	public static final String MAP_REDUCE_SERVER = "MapReduce Server";
	public static final String MAP_REDUCE_CLIENT_1 = "MapReduce Client 1";
	public static final String MAP_REDUCE_CLIENT_2 = "MapReduce Client 2";
//	static int processTimeOut = 45;
//
//	public static int getProcessTimeOut() {
//		return processTimeOut;
//	}
//	public static void setProcessTimeOut(int processTimeOut) {
//		Assignment0Suite.processTimeOut = processTimeOut;
//	}
	public static void main (String[] args) {
		try {
//			setProcessTimeOut(25);

			BasicJUnitUtils.interactiveTest(Assignment0Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		// do not need this as we are using no tahs
//		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryTagCommand();
//		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);

	}
}
