package gradingTools.comp533s21.assignment9.testcases.output.textual;

import gradingTools.comp533s21.assignment9.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.checks.ASerializationTraceChecker;
import gradingTools.shared.testcases.SubstringSequenceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(35)
public class AnOutputTextualBeanSerializerTest extends AnOutputTextualSerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), 
				"2.0.*75.0.*18.75.*true", 
				"examples.serialization.AnotherBMISpreadsheet.*\\(2.0,75.0,18.75,true\\)" 
				);
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
		return Comp533Tags.BEAN_SERIALIZER;
	}
	

}
