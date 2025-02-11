package gradingTools.comp533s25.assignment2;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.GradingMode;
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
import gradingTools.comp533s20.assignment2.MapReduceClasses;
import gradingTools.comp533s20.assignment2.PartitionerClassFactoryAndObject;
import gradingTools.comp533s20.assignment2.testcases.synchronization.objects.BarrierJoinSynchronizationClassAndObject;
import gradingTools.comp533s21.assignment1.style.A1GeneralStyleSuite;
import gradingTools.comp533s21.assignment1.style.A1SpecificStyleSuite;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.comp533s21.assignment2.style.A2GeneralStyleSuite;
import gradingTools.comp533s21.assignment2.style.A2SpecificStyleSuite;
import util.trace.Tracer;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	A2ConfigurationProvided.class,
//	ConfigurationProvided.class,
//	MapReduceClasses.class,
	MultiThreadTokenCounts.class,
	MultiThreadSum.class,
	PartitionerClassFactoryAndObject.class,
	BarrierJoinSynchronizationClassAndObject.class,
	BarrierJoinSynchronizationCountsAndSum.class,
	PartitionedReduce.class,
	A2GeneralStyleSuite.class,
	A2SpecificStyleSuite.class
})
//1.1, 2.3, 3.6, 4.5, 5.6, 6.3, 7.4, 8.6, 9.4
//41
	

	
public class S25Assignment2Suite extends Assignment1Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A2.xml");
		
		if(GradingMode.getGraderRun()) {
			BasicProjectExecution.setProcessTimeOut(250);
//			BasicProjectExecution.setMethodTimeOut(1000);
		}
//		System.out.println("Test2");
	}
	static {
		configureProperties();
	}

	public static void main (String[] args) {
		try {

			//BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setBetweenInputDelay(2000);
			
			//BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setMaxTraces(2000);
			BasicJUnitUtils.interactiveTest(S25Assignment2Suite.class);
			//System.out.println("Test");
			//BasicProjectExecution.setMethodTimeOut(9000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
