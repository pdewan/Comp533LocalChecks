package gradingTools.comp533s21.assignment9.testcases.output;

import util.annotations.Comp533Tags;

public abstract class AnOutputTextualSerializerTest extends AnOutputSerializerTest {
	public static final String TEXT_BUFFER_PATTERN = ".*?(StringReader).*?";


	public static final String[] TEXTUAL_SERIALIZER = {Comp533Tags.LOGICAL_TEXTUAL_SERIALIZER_FACTORY};
	@Override
	protected String[] proxyClassTags() {
		return  TEXTUAL_SERIALIZER;
	}
	

}
