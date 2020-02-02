package gradingTools.comp533s20.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;



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
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setReRunTests(false);

			BasicJUnitUtils.interactiveTest(Assignment1Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
