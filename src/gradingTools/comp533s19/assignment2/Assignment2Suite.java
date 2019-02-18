package gradingTools.comp533s19.assignment2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment2OneClientSuite.class,
	Assignment2OneClientSuite.class
})
public class Assignment2Suite extends Assignment1Suite{
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(Assignment2Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}