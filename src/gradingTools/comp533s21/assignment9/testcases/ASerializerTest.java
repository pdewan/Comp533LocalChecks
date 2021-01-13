package gradingTools.comp533s21.assignment9.testcases;

import org.junit.Assert;

import grader.basics.project.BasicProjectIntrospection;
import gradingTools.shared.testcases.FactoryMethodTest;
import serialization.Serializer;
import serialization.SerializerFactory;
import serialization.SerializerSelector;
import serialization.simple.ASimpleSerializer;

public abstract class ASerializerTest extends FactoryMethodTest {
	
//	static ASerializerTest singleton;

	


//	protected Map<String, Class> tagToSerializer;
	protected SerializerFactory serializerFactory;
	protected Serializer serializer;
	protected Serializer serializerProxy;
//	protected LinesMatcher linesMatcher;

	
	
	
	
	public ASerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}
//	protected abstract SubstringSequenceChecker checker();
//	protected abstract String classTag();
	
	
	
	protected Object createUsingFactoryClassAndMethodTags() {
		if (serializerFactory == null ) {
			serializerFactory = (SerializerFactory) createInstance();
			serializerFactory = (SerializerFactory) BasicProjectIntrospection.
					createTimingOutProxy(SerializerFactory.class, serializerFactory);
			serializer = serializerFactory.createSerializer();
			Assert.assertTrue(serializerFactory + " returned null instance", serializer != null);
			Assert.assertTrue(serializerFactory + " returned instance of " + ASimpleSerializer.class, !ASimpleSerializer.class.isInstance(serializer));
			serializerProxy = (Serializer) BasicProjectIntrospection.createTimingOutProxy(Serializer.class, serializer); 
			rootProxy = serializerProxy;
			SerializerSelector.setSerializerFactory(serializerFactory);
		
		}
		return rootProxy;
	}
	
	
	
	
	
	@Override
	protected Class proxyClass() {
		return SerializerFactory.class;
	}
	

	

	@Override
	protected void setActual(Object aProxy) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
