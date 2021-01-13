package gradingTools.comp533s21.assignment9.testcases.output.checks;

import gradingTools.comp533s21.assignment9.testcases.output.AnOutputBinarySerializerTest;
import gradingTools.comp533s21.assignment9.testcases.output.AnOutputTextualSerializerTest;
import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class ASerializationTraceChecker extends ASubstringSequenceChecker{
	
	public ASerializationTraceChecker(Class aTaggedClass, String aBinaryOrText, String aStringRepresentation) {
		String aClassName = aTaggedClass.getSimpleName();
		String anActualBinaryOrText = aBinaryOrText;
		if (!aBinaryOrText.equals(AnOutputBinarySerializerTest.BYTE_BUFFER_PATTERN)) {
			anActualBinaryOrText = "(" + aBinaryOrText + "|" + AnOutputTextualSerializerTest.TEXT_BUFFER_PATTERN + ")";
		}
		String[] subStrings = {
				
				toPrefixedRegex("Serializing ", aStringRepresentation) ,

//				"ExtensibleValueSerializationInitiated",
//				"I\\*\\*\\*.*" + aClassName + ".*" +aBinaryOrText +".*",
//				aStringRepresentation,
//				"ExtensibleValueSerializationFinished",
//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
//				"ExtensibleBufferDeserializationInitiated",
//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
//				"ExtensibleBufferDeserializationFinished",
//				"I\\*\\*\\*.*ExtensibleBufferDeserializationFinished.*" + aClassName + ".*" +aBinaryOrText +".*",
				toPrefixedRegex("I\\*\\*\\*", "ExtensibleBufferDeserializationFinished", anActualBinaryOrText),

//				aClassName,
//				"HeapByteBuffer",
//				aStringRepresentation,
//				"Deserialized.*"+ aStringRepresentation + ".*",
				toPrefixedRegex("Deserialized", aStringRepresentation)

				};
		init (subStrings);
	}
//	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
//	public  String[] subStrings = {
//			"Serializing 5",
//			"Deserialized 5",
//			"Serializing 5",
//			"Deserialized 5",
//			"Serializing 5",
//			"Deserialized 5"
//	};
	
	

}
