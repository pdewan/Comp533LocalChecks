package gradingTools.comp533s21.assignment9.testcases.output.textual;

import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.comp533s21.assignment9.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(5)
public class AnOutputTextualHashTableSerializerTest extends AnOutputTextualSerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), "2.0.*ni hao", "5=2.0, greeting=ni hao" );
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
		return Comp533Tags.MAP_SERIALIZER;
	}
	

}
