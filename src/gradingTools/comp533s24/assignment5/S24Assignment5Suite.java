package gradingTools.comp533s24.assignment5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s20.assignment5.A5AssignmentTags;
import gradingTools.comp533s20.assignment5.Assignment5OneClientSuite;
import gradingTools.comp533s20.assignment5.Assignment5TwoClientSuite;
import gradingTools.comp533s21.assignment5.style.A5GeneralStyleSuite;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;
//import gradingTools.comp533s22.assignment5.style.A5SpecificStyleSuite;
import gradingTools.comp533s24.assignment5.style.A5SpecificStyleSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment5OneClientSuite.class,
	Assignment5TwoClientSuite.class,
	A5GeneralStyleSuite.class,
	A5SpecificStyleSuite.class
})
//1.5, 2.6, 3.3, 4.6
//20
public class S24Assignment5Suite extends Assignment1Suite{
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A5.xml");
	}
	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//			setCheckStyleConfiguration("unc_checks_533_A5.xml");
			
			BasicJUnitUtils.interactiveTest(S24Assignment5Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		TagsFactory.setAssignmentTags(new A5AssignmentTags());
		configureProperties();
	}
}