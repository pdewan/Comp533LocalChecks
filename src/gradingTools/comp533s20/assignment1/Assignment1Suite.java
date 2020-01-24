package gradingTools.comp533s20.assignment1;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.MapReduceFactoriesAndObjects;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s20.assignment1.mappers.IntSummingMapper;
import gradingTools.comp533s20.assignment1.mappers.TokenCountingMapper;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadSumResult;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadTokenCountResult;
import gradingTools.comp533s20.assignment1.testcases.counts.standalone.singlethread.StandAloneSingleThreadTokenCountMVC;
import gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread.StandAloneSingleThreadSumMVC;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConfigurationProvided.class,
	MapReduceFactories.class,
	SingleThreadTokenCount.class,
	SingleThreadIntSum.class
//	ConfigurationProvided.class,
//	TokenCountingMapper.class,
//
//	StandAloneSingleThreadTokenCountResult.class,
//	StandAloneSingleThreadTokenCountMVC.class,
//	StandAloneSingleThreadSumMVC.class,
//	IntSummingMapper.class,
//	StandAloneSingleThreadSumResult.class,
//	

//	BasicStandAloneCounts.class,
//	BasicStandAloneSum.class,
//	BasicDistributedCounts.class,
//	BasicDistributedSum.class,
//	MapReduceFactoriesAndObjects.class,
//	StandAloneBarrierJoinSynchronization.class,
//	StandAlonePartitionedReduce.class,
//	DistributedPartitionedReduce.class
	
	
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
	

	
public class Assignment1Suite extends Assignment0Suite{

	public static void main (String[] args) {
		try {

			BasicJUnitUtils.interactiveTest(Assignment1Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
