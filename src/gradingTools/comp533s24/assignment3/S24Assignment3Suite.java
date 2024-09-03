package gradingTools.comp533s24.assignment3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.GradingMode;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment0.BasicDistributedCounts;
import gradingTools.comp533s19.assignment0.BasicDistributedSum;
import gradingTools.comp533s19.assignment0.DistributedPartitionedReduce;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s21.assignment3.A3ConfigurationProvided;
import gradingTools.comp533s21.assignment3.facebookMapReduce.BasicDistributedFacebookMapReduce;
import gradingTools.comp533s21.assignment3.style.A3GeneralStyleSuite;
import gradingTools.comp533s21.assignment3.style.A3SpecificStyleSuite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
//	ConfigurationProvided.class,
	A3ConfigurationProvided.class,
	A3GeneralStyleSuite.class,
	A3SpecificStyleSuite.class,
	BasicDistributedCounts.class,
	BasicDistributedSum.class,
	DistributedPartitionedReduce.class,
	BasicDistributedFacebookMapReduce.class
})
//1.4, 2.6, 3.5, 4.1, 5.2, 6.5, 7.5, 
//28

	
public class S24Assignment3Suite extends Assignment1Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A3.xml");
		
		if(GradingMode.getGraderRun()) {
			BasicProjectExecution.setProcessTimeOut(500);
		}
	}
	static {
		configureProperties();
	}
	public static void main (String[] args) {
		try {
			BasicJUnitUtils.interactiveTest(S24Assignment3Suite.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
