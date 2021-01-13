package gradingTools.comp533s21.assignment9;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment2.Assignment2OneClientSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s19.assignment4.CounterBlockingRPCSuite;
import gradingTools.comp533s19.assignment4.CounterCustomRPCSuite;
import gradingTools.comp533s19.assignment4.CounterExplicitReceiveSuite;
import gradingTools.comp533s19.assignment4.SimulationBlockingRPCSuite;
import gradingTools.comp533s20.assignment7.Assignment7OneClientSuite;
import gradingTools.comp533s20.assignment7.Assignment7TwoClientSuite;
import gradingTools.comp533s21.assignment7.style.A7GeneralStyleSuite;
import gradingTools.comp533s21.assignment7.style.A7SpecificStyleSuite;
import gradingTools.comp533s21.assignment9.testcases.equals.ABinarySerializerEqualsTestSuite;
import gradingTools.comp533s21.assignment9.testcases.output.ABinarySerializerOutputTestSuite;
import gradingTools.comp533s21.assignment9.testcases.output.ATextualSerializerOutputTestSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ABinarySerializerOutputTestSuite.class,
	ATextualSerializerOutputTestSuite.class,
	ABinarySerializerEqualsTestSuite.class,

	
})
public class S21Assignment9Suite extends Assignment2Suite{
	public static void main (String[] args) {
		try {
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setCheckStyleConfiguration("unc_checks_533_A7.xml");
			BasicJUnitUtils.interactiveTest(S21Assignment9Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
