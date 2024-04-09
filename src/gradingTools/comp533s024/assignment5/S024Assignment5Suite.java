package gradingTools.comp533s024.assignment5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s024.assignment5.style.A6GeneralStyleSuite;
import gradingTools.comp533s024.assignment5.style.A6SpecificStyleSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s20.assignment6.A6AssignmentTags;
import gradingTools.comp533s20.flexible.testcases.FlexibleOneClientCorrectConnectionTestInputGenerator;
import gradingTools.comp533s20.flexible.testcases.FlexibleTwoClientCorrectConnectionTestInputGenerator;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment5OneClientSuite.class,
	Assignment5TwoClientSuite.class,
	A6SpecificStyleSuite.class,
	A6GeneralStyleSuite.class
})
public class S024Assignment5Suite extends Assignment2Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A5.xml");
	}
	public static void main (String[] args) {
		try {

			BasicJUnitUtils.interactiveTest(S024Assignment5Suite.class);


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
