package gradingTools.comp533s20.assignment5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment5OneClientSuite.class,
	Assignment5TwoClientSuite.class
})
public class Assignment5Suite extends Assignment1Suite{
	public static void main (String[] args) {
		try {
			
			TagsFactory.setAssignmentTags(new A5AssignmentTags());
			
			BasicJUnitUtils.interactiveTest(Assignment5Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}