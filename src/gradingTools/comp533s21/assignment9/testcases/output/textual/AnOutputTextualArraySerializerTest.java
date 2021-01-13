package gradingTools.comp533s21.assignment9.testcases.output.textual;

import gradingTools.comp533s21.assignment9.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.checks.ASerializationTraceChecker;
import gradingTools.shared.testcases.SubstringSequenceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(10)
public class AnOutputTextualArraySerializerTest extends AnOutputTextualSerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), "Hello World.*Goodbye World.*GREEN", "Hello World, Goodbye World, GREEN" );
	}
//	@Override
//	protected boolean doTest() {
//		String anOutput = getOutput();
//		boolean aRetVal = checker.check(anOutput);
//		Assert.assertTrue(checker.getRegex() + " not matched in output of TestSerialization", aRetVal);
//		return true;
//	}

	@Override
	protected String classTag() {
		// TODO Auto-generated method stub
		return Comp533Tags.ARRAY_SERIALIZER;
	}
	

}
