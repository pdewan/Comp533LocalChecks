package gradingTools.comp533s20.assignment2;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.MultiThreadSum;
import gradingTools.comp533s19.assignment0.MapReduceFactoriesAndObjects;
import gradingTools.comp533s19.assignment0.MultiThreadTokenCounts;
import gradingTools.comp533s19.assignment0.BarrierJoinSynchronizationCountsAndSum;
import gradingTools.comp533s19.assignment0.PartitionedReduce;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s20.assignment1.mappers.IntSummingMapper;
import gradingTools.comp533s20.assignment1.mappers.TokenCountingMapper;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadSumResult;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadTokenCountResult;
import gradingTools.comp533s20.assignment1.testcases.counts.standalone.singlethread.StandAloneSingleThreadTokenCountMVC;
import gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread.StandAloneSingleThreadSumMVC;
import gradingTools.comp533s20.assignment2.testcases.synchronization.objects.BarrierJoinSynchronizationClassAndObject;
import util.trace.Tracer;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConfigurationProvided.class,
	MapReduceClasses.class,
	MultiThreadTokenCounts.class,
	MultiThreadSum.class,
	PartitionerClassFactoryAndObject.class,
	BarrierJoinSynchronizationClassAndObject.class,
	BarrierJoinSynchronizationCountsAndSum.class,
	PartitionedReduce.class,
})
	

	
public class Assignment2Suite extends Assignment1Suite{

	public static void main (String[] args) {
		try {

			BasicJUnitUtils.interactiveTest(Assignment2Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
