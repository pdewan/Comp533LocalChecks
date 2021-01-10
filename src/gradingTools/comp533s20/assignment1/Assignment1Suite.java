package gradingTools.comp533s20.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;



@RunWith(Suite.class)
@Suite.SuiteClasses({
//	ConfigurationProvided.class,
	A1ConfigurationProvided.class,
	MapReduceFactories.class,
	SingleThreadTokenCount.class,
	SingleThreadIntSum.class




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
