package gradingTools.comp533s21.assignment9.testcases.equals;

import gradingTools.comp533s21.assignment9.testcases.output.AnOutputBinarySerializerTest;
import util.annotations.Comp533Tags;

public abstract class AnEqualsBinarySerializerTest extends AnEqualsSerializerTest {
//	public static final String[] BINARY_SERIALIZER = {Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY};
//	@Override
//	protected String[] proxyClassTags() {
//		return BINARY_SERIALIZER;
//	}
	protected  String classTag(){
		return Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY;
	}
	protected String[] proxyClassTags() {
		return AnOutputBinarySerializerTest.BINARY_SERIALIZER;
	}

	

}
