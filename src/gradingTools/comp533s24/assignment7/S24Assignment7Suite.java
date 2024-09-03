package gradingTools.comp533s24.assignment7;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment2.Assignment2OneClientSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s20.assignment7.Assignment7OneClientSuite;
import gradingTools.comp533s20.assignment7.Assignment7TwoClientSuite;
import gradingTools.comp533s21.assignment7.S21A7AssignmentTags;
import gradingTools.comp533s21.assignment7.style.A7GeneralStyleSuite;
import gradingTools.comp533s22.assignment6.S22Assignment6Suite;
import gradingTools.comp533s24.assignment7.style.A7SpecificStyleSuite;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment7OneClientSuite.class,
	Assignment7TwoClientSuite.class,
	A7GeneralStyleSuite.class,
	A7SpecificStyleSuite.class
})
//1.8, 2.4, 3.6, 4.3
//21
public class S24Assignment7Suite extends Assignment2Suite{
	
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A7.xml");
	}
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(S24Assignment7Suite.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		S22Assignment6Suite.enableNoFactories();
		configureProperties();
		TagsFactory.setAssignmentTags(new S21A7AssignmentTags());
	}
}
