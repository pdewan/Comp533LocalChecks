package gradingTools.comp533s21.assignment5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s20.assignment5.Assignment5OneClientSuite;
import gradingTools.comp533s20.assignment5.Assignment5TwoClientSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment5OneClientSuite.class,
	Assignment5TwoClientSuite.class
})
public class S21Assignment5Suite extends Assignment1Suite{
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(S21Assignment5Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}