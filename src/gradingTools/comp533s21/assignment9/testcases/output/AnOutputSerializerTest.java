package gradingTools.comp533s21.assignment9.testcases.output;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Assert;

import examples.serialization.SerializationTester;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.Project;
import gradingTools.comp533s21.assignment9.testcases.ASerializerTest;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.shared.testcases.utils.ALinesMatcher;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;
import util.annotations.Comp533Tags;
import util.trace.Tracer;
import util.trace.port.serialization.extensible.ExtensibleSerializationTraceUtility;

public abstract class AnOutputSerializerTest extends ASerializerTest {
	
	static AnOutputSerializerTest singleton;
//	protected SubstringSequenceChecker checker;
//	protected Class integerSerializerClass;
//	protected Class shortSerializerClass;
//	protected Class longSerializerClass;
//	protected Class doubleSerializerClass;
//	protected Class floatSerializerClass;
//	protected Class stringSerializerClass;
//	protected Class booleanSerializerClass;
//	protected Class enumSerializerClass;
//	protected Class arraySerializerClass;
//	protected Class collectionSerClass;
//	protected Class nullSerializerClass;
//	protected Class mapSerializerClass;
//	protected Class beanSerializerClass;
//	protected Class listSerializerClass;
	protected String[] tags = {
			Comp533Tags.BOOLEAN_SERIALIZER,
			Comp533Tags.INTEGER_SERIALIZER,
			Comp533Tags.BEAN_SERIALIZER,
			Comp533Tags.SHORT_SERIALIZER,
			Comp533Tags.LONG_SERIALIZER,
			Comp533Tags.DOUBLE_SERIALIZER,
			Comp533Tags.FLOAT_SERIALIZER,
			Comp533Tags.STRING_SERIALIZER,
			Comp533Tags.FLOAT_SERIALIZER,
			Comp533Tags.ENUM_SERIALIZER,
			Comp533Tags.ARRAY_SERIALIZER,
			Comp533Tags.LIST_PATTERN_SERIALIZER,			
			Comp533Tags.MAP_SERIALIZER,
			Comp533Tags.COLLECTION_SERIALIZER,
			Comp533Tags.NULL_SERIALIZER
			
	};
//	protected SubstringSequenceChecker checker;
//	protected String classTag;

	protected Map<String, Class> tagToSerializer;
//	protected SerializerFactory serializerFactory;
//	protected Serializer serializer;
//	protected Serializer serializerProxy;
	protected LinesMatcher linesMatcher;

	
	protected void initSerializers() {
		Project aProject = CurrentProjectHolder.getCurrentProject();
		tagToSerializer = new HashMap();
		for (String aTag:tags) {
			putClassForTag(aProject, aTag);
		}
		
		
	}
	
	protected void putClassForTag(Project aProject, String aTag) {
		Class aClass = BasicProjectIntrospection.findUniqueClassByTag(aProject, aTag);
		tagToSerializer.put(aTag, aClass);
		
	}
	
	
	public AnOutputSerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}
	protected abstract SubstringSequenceChecker checker();
	protected abstract String classTag();
	
	protected Class taggedClass() {
		String aTag = classTag();
		tagToSerializer = getTagToSerializer();
		Class aClass = tagToSerializer.get(aTag);
		assertTrue("Unique class not found for tag " + aTag + ". Either zero or multiple classes found for the tag.", aClass != null);
		return aClass;
	}
	
//	protected Object createUsingFactoryMethod() {
//		if (serializer == null ) {
//			serializerFactory = (SerializerFactory) createInstance();
//			serializerFactory = (SerializerFactory) BasicProjectIntrospection.
//					forceCreateProxy(SerializerFactory.class, serializerFactory);
//			serializer = serializerFactory.createSerializer();
//			Assert.assertTrue(serializerFactory + " returned null instance", serializer != null);
//			Assert.assertTrue(serializerFactory + " returned instance of " + ASimpleSerializer.class, !ASimpleSerializer.class.isInstance(serializer));
//			serializerProxy = (Serializer) BasicProjectIntrospection.forceCreateProxy(Serializer.class, serializer); 
//			rootProxy = serializerProxy;
//		}
//		return rootProxy;
//	}
	@Override
	public String getOutput() {
		
		String anOutputKey = Arrays.toString(proxyClassTags()).replaceAll(" *", "").toLowerCase();
//		String anOutputKey = proxyClassTags()[0];

		String anOutputLinesKey = anOutputKey + "Lines";
		String anOutputLinesMatcherKey = anOutputKey + "LinesMatcher";
		output = (String) BasicProjectIntrospection.getUserObject(anOutputKey);
		outputLines = (String[]) BasicProjectIntrospection.getUserObject(anOutputLinesKey);
		linesMatcher = (LinesMatcher) BasicProjectIntrospection.getUserObject(anOutputLinesMatcherKey);
		if (output == null) {
			createUsingFactoryClassAndMethodTags();
			ExtensibleSerializationTraceUtility.setTracing();
			boolean prevBufferTracedMessages = Tracer.isBufferTracedMessages();
			Tracer.setBufferTracedMessages(false);

//		serializerFactory = (SerializerFactory) createInstance();
//		serializerFactory = (SerializerFactory) BasicProjectIntrospection.
//				forceCreateProxy(SerializerFactory.class, serializerFactory);
//		serializer = serializerFactory.createSerializer();
//		Assert.assertTrue(serializerFactory + " returned null instance", serializer != null);
//		Assert.assertTrue(serializerFactory + " returned instance of " + ASimpleSerializer.class, !ASimpleSerializer.class.isInstance(serializer));
//		serializerProxy = (Serializer) BasicProjectIntrospection.forceCreateProxy(Serializer.class, serializer); 
//		SerializerSelector.setSerializerFactory(aSerializerFactory);
		BasicProjectExecution.redirectOutput();
		SerializationTester.testSerialization(serializerProxy);	
//		SerializationTester.testSerialization();		

		output = BasicProjectExecution.restoreAndGetOut();
		BasicProjectIntrospection.putUserObject(anOutputKey, output);
		ExtensibleSerializationTraceUtility.setTracing(false);
		Tracer.setBufferTracedMessages(prevBufferTracedMessages);

		}
		if (outputLines == null) {
			outputLines = output.split("\n");
			BasicProjectIntrospection.putUserObject(anOutputLinesKey, outputLines);

		}
		if (linesMatcher == null) {
			linesMatcher = new ALinesMatcher(outputLines);
			BasicProjectIntrospection.putUserObject(anOutputLinesMatcherKey, linesMatcher);

		}
		
		return output;
	}
	@Override
	protected void executeOperations(Object aProxy) throws Exception {
		output = getOutput();
		
	}
	
	@Override
	protected boolean checkOutput(Object aLocatable) {
//		boolean aRetVal = checker().check(output);
		boolean aRetVal = checker().check(linesMatcher, LinesMatchKind.ONE_TIME_LINE, Pattern.DOTALL);
//		Assert.assertTrue(checker().getRegex() + " not matched in output of TestSerialization", aRetVal);
		Assert.assertTrue(linesMatcher.getLastUnmatchedRegex() + " not matched in output of TestSerialization", aRetVal);

		return true;
	}
	@Override
	protected boolean doTest() throws Exception {
//		createUsingFactoryMethod();
		executeOperations(rootProxy);
		return checkOutput(rootProxy);
		
//		output = getOutput();
//		
//		boolean aRetVal = checker().check(output);
//		Assert.assertTrue(checker().getRegex() + " not matched in output of TestSerialization", aRetVal);
//		return true;
	}
	abstract protected String[] proxyClassTags() ;

	
//	@Override
//	protected Class proxyClass() {
//		return SerializerFactory.class;
//	}
//	

	
//
//	@Override
//	protected void setActual(Object aProxy) {
//		// TODO Auto-generated method stub
//		
//	}

	
	
	public static final String TAG_TO_MAP = "TAG_TO_MAP";
	
	public Map<String, Class> getTagToSerializer() {
		tagToSerializer = (Map<String, Class>) BasicProjectIntrospection.getUserObject(TAG_TO_MAP);
		if (tagToSerializer == null) {
			initSerializers();
			BasicProjectIntrospection.putUserObject(TAG_TO_MAP, tagToSerializer);			
		}
		return tagToSerializer;
	}
	


//	static AnAllSerializerTest singleton;
//	public static AnAllSerializerTest getSingleton() {
//		if (singleton == null) {
//			 singleton 
//		}
//		
//	}

}
