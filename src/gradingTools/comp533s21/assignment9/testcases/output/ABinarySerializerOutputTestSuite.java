package gradingTools.comp533s21.assignment9.testcases.output;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryArrayListSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryArraySerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryBeanSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryBooleanSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryDoubleSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryEnumSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryFloatSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryHashMapSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryHashSetSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryHashTableSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryInheritingBeanSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryIntegerSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryListPatternSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryLongSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryNestedSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryRecursiveSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryShortSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinarySimpleRecursiveSerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.binary.AnOutputBinaryStringSerializerTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AnOutputBinaryIntegerSerializerTest.class,
	AnOutputBinaryShortSerializerTest.class,
	AnOutputBinaryLongSerializerTest.class,
	AnOutputBinaryDoubleSerializerTest.class,
	AnOutputBinaryFloatSerializerTest.class,
	AnOutputBinaryStringSerializerTest.class,
	AnOutputBinaryBooleanSerializerTest.class,
	AnOutputBinaryEnumSerializerTest.class,
	AnOutputBinaryArraySerializerTest.class,
	AnOutputBinaryArrayListSerializerTest.class,
//	AnOutputBinaryVectorSerializerTest.class,	
	AnOutputBinaryHashMapSerializerTest.class,
	AnOutputBinaryHashTableSerializerTest.class,
	AnOutputBinaryHashSetSerializerTest.class,
	AnOutputBinaryNestedSerializerTest.class,
	AnOutputBinaryRecursiveSerializerTest.class,
	AnOutputBinaryBeanSerializerTest.class,

	AnOutputBinaryInheritingBeanSerializerTest.class,
	AnOutputBinaryListPatternSerializerTest.class,
	AnOutputBinarySimpleRecursiveSerializerTest.class,


})
public class ABinarySerializerOutputTestSuite {
	
	
	

}
