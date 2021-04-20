package gradingTools.comp533s21.assignment4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s20.assignment4.A4AssignmentTags;
import gradingTools.comp533s20.assignment4.Assignment4OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4TwoClientSuite;
import gradingTools.comp533s21.assignment4.style.A4GeneralStyleSuite;
import gradingTools.comp533s21.assignment4.style.A4SpecificStyleSuite;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment4OneClientSuite.class,
	Assignment4TwoClientSuite.class,
	A4SpecificStyleSuite.class,
	A4GeneralStyleSuite.class
})
public class S21Assignment4Suite extends Assignment1Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A4.xml");
	}
	static {
		configureProperties();
	}
	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setCheckStyleConfiguration("unc_checks_533_A4.xml");
			TagsFactory.setAssignmentTags(new A4AssignmentTags());
			BasicJUnitUtils.interactiveTest(S21Assignment4Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}