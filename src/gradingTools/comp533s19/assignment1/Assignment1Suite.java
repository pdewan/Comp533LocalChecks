package gradingTools.comp533s19.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment1.testcases.OneClientCorrectConnectionTestCase;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	OneClientCorrectConnectionTestCase.class,
})
public class Assignment1Suite {
}
