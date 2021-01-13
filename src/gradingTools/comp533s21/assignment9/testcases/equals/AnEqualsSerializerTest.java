package gradingTools.comp533s21.assignment9.testcases.equals;

import org.junit.Assert;

import examples.serialization.SerializationTester;
import gradingTools.comp533s21.assignment9.testcases.ASerializerTest;
import util.trace.Tracer;
import util.trace.port.serialization.extensible.ExtensibleSerializationTraceUtility;

public abstract class AnEqualsSerializerTest extends ASerializerTest {
	
	static AnEqualsSerializerTest singleton;
	protected Object originalObject;
	protected Object deserializedObject;

	protected abstract Object createSerializableObject();
	
	public AnEqualsSerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}

	
	@Override
	protected void executeOperations(Object aProxy) throws Exception {
		originalObject = createSerializableObject();
		ExtensibleSerializationTraceUtility.setTracing();
		boolean prevBufferTracedMessages = Tracer.isBufferTracedMessages();
		createUsingFactoryClassAndMethodTags();
		Tracer.setBufferTracedMessages(false);

		deserializedObject = SerializationTester.translate(serializerProxy,originalObject);
		Tracer.setBufferTracedMessages(prevBufferTracedMessages);


		
		
	}
	
	@Override
	protected boolean checkOutput(Object aLocatable) {
		Assert.assertTrue("Original object " + originalObject + " not equal to deserialized object " + deserializedObject , originalObject.equals(deserializedObject));
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

	
	
	


//	static AnAllSerializerTest singleton;
//	public static AnAllSerializerTest getSingleton() {
//		if (singleton == null) {
//			 singleton 
//		}
//		
//	}

}
