package gradingTools.comp533s24.assignment6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s24.assignment6.style.A6SpecificStyleSuite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment2.Assignment2OneClientSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s20.assignment6.A6AssignmentTags;
import gradingTools.comp533s20.assignment6.Assignment6OneClientSuite;
import gradingTools.comp533s20.assignment6.Assignment6TwoClientSuite;
import gradingTools.comp533s20.flexible.testcases.FlexibleOneClientCorrectConnectionTestInputGenerator;
import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestInputGenerator;
import gradingTools.comp533s21.assignment6.style.A6GeneralStyleSuite;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment6OneClientSuite.class,
	Assignment6TwoClientSuite.class,
	A6SpecificStyleSuite.class,
	A6GeneralStyleSuite.class
})
public class S24Assignment6Suite extends Assignment2Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A6.xml");
	}
	public static void main (String[] args) {
		try {

			BasicJUnitUtils.interactiveTest(S24Assignment6Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		enableNoFactories();
		TagsFactory.setAssignmentTags(new A6AssignmentTags());
		configureProperties();
	}
	public static void enableNoFactories() {
		FlexibleOneClientCorrectConnectionTestInputGenerator.setUseNoFactories(true);
		FlexibleTwoClientCorrectConnectionTestInputGenerator.setUseNoFactories(true);
	}
	
}
