package gradingTools.comp533s21.assignment6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment2.Assignment2OneClientSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s20.assignment6.Assignment6OneClientSuite;
import gradingTools.comp533s20.assignment6.Assignment6TwoClientSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment6OneClientSuite.class,
	Assignment6TwoClientSuite.class
})
public class Assignment6Suite extends Assignment2Suite{
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(Assignment6Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
