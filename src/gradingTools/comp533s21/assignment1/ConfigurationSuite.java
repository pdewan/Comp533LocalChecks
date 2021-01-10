package gradingTools.comp533s21.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment0.BarrierJoinSynchronizationCountsAndSum;
import gradingTools.comp533s19.assignment0.MultiThreadSum;
import gradingTools.comp533s19.assignment0.MultiThreadTokenCounts;
import gradingTools.comp533s19.assignment0.PartitionedReduce;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s20.assignment2.PartitionerClassFactoryAndObject;
import gradingTools.comp533s20.assignment2.testcases.synchronization.objects.BarrierJoinSynchronizationClassAndObject;
import gradingTools.comp533s21.assignment2.style.A2GeneralStyleSuite;
import gradingTools.comp533s21.assignment2.style.A2SpecificStyleSuite;

	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		ConfigurationProvided.class,
		A1ConfigurationProvided.class
	})
	public class ConfigurationSuite {
	}


