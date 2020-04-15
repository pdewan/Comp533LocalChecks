package gradingTools.comp533s20.assignment7;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment2.Assignment2OneClientSuite;
import gradingTools.comp533s19.assignment2.Assignment2Suite;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment7OneClientSuite.class,
	Assignment7TwoClientSuite.class
})
public class Assignment7Suite extends Assignment2Suite{
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(Assignment7Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
