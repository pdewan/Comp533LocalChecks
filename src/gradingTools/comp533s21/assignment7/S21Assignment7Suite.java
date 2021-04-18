package gradingTools.comp533s21.assignment7;

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
import gradingTools.comp533s21.assignment7.style.A7GeneralStyleSuite;
import gradingTools.comp533s21.assignment7.style.A7SpecificStyleSuite;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment7OneClientSuite.class,
	Assignment7TwoClientSuite.class,
	A7GeneralStyleSuite.class,
	A7SpecificStyleSuite.class
})
public class S21Assignment7Suite extends Assignment2Suite{
	
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A7.xml");
	}
	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setCheckStyleConfiguration("unc_checks_533_A7.xml");
//			configureProperties();
			TagsFactory.setAssignmentTags(new S21A7AssignmentTags());
			BasicJUnitUtils.interactiveTest(S21Assignment7Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		configureProperties();
	}
}
