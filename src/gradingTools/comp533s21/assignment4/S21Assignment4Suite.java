package gradingTools.comp533s21.assignment4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s20.assignment4.Assignment4OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4TwoClientSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	Assignment4OneClientSuite.class,
	Assignment4TwoClientSuite.class
})
public class S21Assignment4Suite extends Assignment1Suite{
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(S21Assignment4Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}