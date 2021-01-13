package gradingTools.comp533s21.assignment9.testcases.output.textual;

import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.comp533s21.assignment9.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.checks.ASerializationTraceChecker;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Comp533Tags;
import util.annotations.MaxValue;

@MaxValue(25)
public class AnOutputTextualNestedSerializerTest extends AnOutputTextualSerializerTest{
	@Override
	protected SubstringSequenceChecker checker() {
		 
		return new ASerializationTraceChecker(taggedClass(), "namaste", 
//				"Hello world, 5, BLUE, null, \\[.* world, .* world\\], \\{.*greeting=namaste.*\\}"
				"Hello world, 5, BLUE, null.*namaste"

//				"namaste"
//				"Hello world, 3, BLUE, null, .Goodbye world, Hello world., .5=4.0, greeting=ni hao" 
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
		return Comp533Tags.COLLECTION_SERIALIZER;
	}
	

}
