package gradingTools.comp533s24.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s20.assignment2.MapReduceClasses;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.style.A1GeneralStyleSuite;
import gradingTools.comp533s21.assignment1.style.A1SpecificStyleSuite;
import gradingTools.comp533s22.assignment1.MapReduceFactories;
import gradingTools.comp533s22.assignment1.SingleThreadIntSum;
import gradingTools.comp533s22.assignment1.SingleThreadTokenCount;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	A1ConfigurationProvided.class,
	MapReduceFactories.class,
	MapReduceClasses.class,
	SingleThreadTokenCount.class,
	SingleThreadIntSum.class,
	A1GeneralStyleSuite.class,
	A1SpecificStyleSuite.class
})
	

	
public class S24Assignment1Suite extends Assignment0Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A1.xml");
	}
	static {
		configureProperties();
	}
	public static void main (String[] args) {
		try {
			BasicJUnitUtils.interactiveTest(S24Assignment1Suite.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
