package gradingTools.comp533s21.assignment9.testcases;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;


import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;










import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;









//import Controllers.LogicalBinarySerializerFactory;
//import Controllers.LogicalTextualSerializerFactory;
import examples.serialization.ANamedBMISpreadsheet;
import examples.serialization.AStringHistory;
import examples.serialization.AnotherBMISpreadsheet;
import examples.serialization.BMISpreadsheet;
import examples.serialization.NamedBMISpreadsheet;
import examples.serialization.StringHistory;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;

import gradingTools.shared.testcases.ProxyTest;
import serialization.Serializer;
import serialization.SerializerFactory;
import serialization.SerializerSelector;
import util.annotations.Comp533Tags;
import util.misc.RemoteReflectionUtility;


/**
 * This class is the tester for the serializer. The only change someone using it might
 * have to make would be in the setUp method they would have to put in the proper import
 * for their factories.
 * TURN OFF TRACING TO MAKE THIS RUN FASTER. WITH TRACING TAKES FOREVER
 * @author parth96
 *
 */
public class PrimitiveTest  {
	private static final double DELTA = 0.01;
	private static final float FLOAT_DELTA = 0.01f;
	// increasing EXP_SIZE increases inputs to each test case. can lead to out of memory erros
	private static final int EXP_SIZE = 100;
	private static final String STRING = "123ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static Serializer logicalSerializer;
	private static Serializer binarySerializer;
	public enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
		THURSDAY, FRIDAY, SATURDAY 
	}
	protected SerializerFactory createLogicalTextualSerializerFactory() {
		String[] aClassTags = {Comp533Tags.LOGICAL_TEXTUAL_SERIALIZER_FACTORY};
		return (SerializerFactory) BasicProjectIntrospection.createInstance(SerializerFactory.class,  aClassTags);
		
	}
	protected SerializerFactory createLogicalBinarySerializerFactory() {
		String[] aClassTags = {Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY};
		return (SerializerFactory) BasicProjectIntrospection.createInstance(SerializerFactory.class,  aClassTags);
		
	}

//	@BeforeEach
	@Before
	void setUp() throws Exception {
		SerializerSelector  logical = new SerializerSelector();
		SerializerSelector  binary = new SerializerSelector();
// want the buffer size for the LogicalBinarySerializerFactory to be atleast 1000000 to not run out of buffer space during test.
		binary.setSerializerFactory(createLogicalBinarySerializerFactory());
		logical.setSerializerFactory(createLogicalBinarySerializerFactory());

		logicalSerializer = logical.createSerializer();
		binarySerializer = binary.createSerializer();

	}

	@Test
	void longTest() {
		getLongs()
		.forEach(i -> performTest(i));	}
	@Test
	void floatTest() {
		getFloats()
		.forEach(i -> performTest(i));
	}
	@Test
	void intTest() {
		getIntegers()
		.forEach(i -> performTest(i));
	}
	@Test
	void doubleTest() {
		getDoubles()
		.forEach(i -> performTest(i));	}
	@Test
	void stringTest() {
		getStrings()
		.forEach(i -> performTest(i));	}
	@Test
	void booleanTest() {
		getBoolean()
		.forEach(i -> performTest(i));	}
	@Test
	void shortTest() {
		getShorts()
		.forEach(i -> performTest(i));	}
	@Test	
	void arrayListAndHashSetTest() {
		//		 test list of strings
		List strings = getStrings();
		HashSet strSet = new HashSet(strings);
		Vector vector = new Vector(strings);
		performTest(strings);
		performTest(strSet);
		performTest(vector);

		// test list of integers
		ArrayList<Integer> integers = getIntegers();
		HashSet intSet = new HashSet(integers);
		vector = new Vector(integers);
		performTest(vector);
		performTest(integers);
		performTest(intSet);

		// test boolean
		List<Boolean> booleans = getBoolean();
		HashSet boolSet = new HashSet(booleans);
		vector = new Vector(booleans);
		performTest(vector);
		performTest(booleans);
		performTest(boolSet);

		// test shorts
		List<Short> shorts = getShorts();
		HashSet shortSet = new HashSet(shorts);
		vector = new Vector(shorts);
		performTest(vector);
		performTest(shorts);
		performTest(shortSet);

		// test double
		List<Double> doubles = getDoubles();
		HashSet doubleSet = new HashSet(doubles);
		vector = new Vector(doubles);
		performTest(vector);
		performTest(doubles);
		performTest(doubleSet);

		// test Long
		List<Long> longs = getLongs();
		HashSet longSet = new HashSet(longs);
		vector = new Vector(longs);
		performTest(vector);
		performTest(longs);
		performTest(longSet);

		// test all objects combined
		List allObjs = getAllObjs();
		HashSet objSet = new HashSet(allObjs);
		vector = new Vector(allObjs);
		performTest(vector);
		performTest(allObjs);
		performTest(objSet);

		// test list of lists
		List listOfList = new ArrayList();
		listOfList.add("asdf");
		listOfList.add(strings);
		listOfList.add(shorts);
		listOfList.add(booleans);
		listOfList.add(integers);
		listOfList.add(longs);
		HashSet listOfListSet = new HashSet(listOfList);
		vector = new Vector(listOfList);
		performTest(vector);
		performTest(listOfList);
		performTest(listOfListSet);

		List nulls = new ArrayList();
		nulls.add(null);
		performTest(nulls);

		List nullList = null;
		performTest(nullList);
	}
	@Test
	void hashTableAndMapTest() {
		Hashtable table = new Hashtable();
		table.put("key", "value");
		table.put(1, 2);
		table.put("aasdf", "adfasdfasfasf");
		table.put(getStrings(), getLongs());
		table.put(getBoolean(), getDoubles());
		HashMap map = new HashMap();
		map.put("key", "value");
		map.put(1, 2);
		map.put("asdf", "fu3823421ck");
		map.put(getStrings(), getLongs());
		map.put(getBoolean(), getDoubles());
		performTest(table);
		performTest(map);
	}
	@Test
	void recursiveTest() {
		List recursive = new ArrayList();
		recursive.add(1);
		recursive.add(2);
		recursive.add(null);
		HashSet recursiveSet = new HashSet(recursive);
		Vector vector = new Vector();
		vector.add(1);
		vector.add(2);
		vector.add(null);
		vector.add(vector);
		recursiveSet.add(recursiveSet);
		recursive.add(recursive);
		performRecursiveTest(vector);
		performRecursiveTest(recursive);
		performRecursiveTest(recursiveSet);

		List listOfList = new ArrayList();
		listOfList.add("warriorsBLew3-1LeadInTheFInals");
		listOfList.add(getStrings());
		listOfList.add(getBoolean());
		listOfList.add(getIntegers());
		listOfList.add(null);
		vector = new Vector();
		HashSet listOfListSet = new HashSet(listOfList);
		vector.addAll(listOfList);
		vector.add(vector);
		listOfListSet.add(listOfListSet);
		listOfList.add(listOfList);
		performRecursiveTest(vector);
		performRecursiveTest(listOfList);
		performRecursiveTest(listOfListSet);
	}
	@Test
	void nullTest() {
		Object a = null;
		performTest(a);
		performTest(null);
	}
	@Test
	void arrayTest() {
		Object [] array = {1 , 2.0 , true, "string", null };
		performArrayTest(array);
		Object [] arrayofLists = {getDoubles() , getShorts() , getStrings(), "string", null };
		performArrayTest(arrayofLists);
		Object [] enumArray = {Day.FRIDAY, Day.MONDAY, "string" , null};
		performArrayTest(enumArray);
	}
	@Test
	void enumTest() {
		for (Day day: Day.values()) {
			performTest(day);
		}

	}
	@Test
	void beanTest() {
		BMISpreadsheet bmi = new AnotherBMISpreadsheet();
		bmi.setHeight(2.0);
		bmi.setMale(true);
		performBeanTest(bmi);
		NamedBMISpreadsheet namedBMI = new ANamedBMISpreadsheet();
		namedBMI.setName("First Person");
		namedBMI.setHeight(2.0);
		performBeanTest(namedBMI);
		performBeanTest(null);
	}
	@Test
	void listPatternTest() {
		StringHistory stringHistory = new AStringHistory();
		stringHistory.add("James Dean");
		stringHistory.add("Joe Doe");
		stringHistory.add("Jane Smith");
		stringHistory.add("John Smith");
		performStringPattern(stringHistory);
		StringHistory nullHistory = null;
		performStringPattern(nullHistory);
	}

	private void performStringPattern(StringHistory a) {
		helperPatternString(logicalSerializer,a);
		helperPatternString(binarySerializer,a);

	}
	private void helperPatternString(Serializer serial, StringHistory a) {
		try {
			ByteBuffer byteBuffer = serial.outputBufferFromObject(a);
			Object returned = serial.objectFromInputBuffer(byteBuffer);
			if (returned==null) {
				Assert.assertEquals(returned,a);

			} else {
				if (!RemoteReflectionUtility.isList(returned.getClass())) {
					Assert.fail("not a list");
				}

				StringHistory ret = (StringHistory) returned;
				Assert.assertEquals(a.size(), ret.size());
				for(int i=0; i<a.size(); i++) {
					Assert.assertEquals(a.get(i), ret.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void performBeanTest(BMISpreadsheet bmi) {
		helperBean(logicalSerializer,  bmi);
		helperBean(binarySerializer, bmi);	
	}
	static void performTest(Object obj) {
		helper(logicalSerializer,  obj);
		helper(binarySerializer, obj);
	}
	static void performRecursiveTest(Object obj) {
		helperRecursive(logicalSerializer,  obj);
		helperRecursive(binarySerializer, obj);
	}
	private void performArrayTest(Object[] array) {
		helperArray(logicalSerializer, array);
		helperArray(binarySerializer, array);
	}
	static void helperRecursive(Serializer serial, Object obj) {
		try {
			ByteBuffer byteBuffer = serial.outputBufferFromObject(obj);
			Object returned = serial.objectFromInputBuffer(byteBuffer);
			// for recursive have to do toString comparison according to stackoverflow. otherwise juni will keep going down the recursive obj infinetly
			Assert.assertEquals(obj.toString(),returned.toString()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void helper(Serializer serial, Object obj) {
		try {
			ByteBuffer byteBuffer = serial.outputBufferFromObject(obj);
			Object returned = serial.objectFromInputBuffer(byteBuffer);
			// if float or double added a delta to avoid false positives.
			if (returned instanceof Float  ) {
				Assert.assertEquals((Float)obj, (Float)returned, FLOAT_DELTA);
			} else if (returned instanceof Double) {
				Assert.assertEquals((double)obj, (double)returned, DELTA);
			} else {
				Assert.assertEquals(obj,returned);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void helperArray(Serializer serial, Object[] array) {
		try {
			ByteBuffer byteBuffer = serial.outputBufferFromObject(array);
			Object returned = serial.objectFromInputBuffer(byteBuffer);
			Assert.assertEquals(array.length, Array.getLength(returned));
			for(int i=0; i<array.length; i++) {
				Assert.assertEquals(array[i], Array.get(returned, i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void helperBean(Serializer serial, BMISpreadsheet bmi) {
		try {
			ByteBuffer byteBuffer = serial.outputBufferFromObject(bmi);
			Object returned = serial.objectFromInputBuffer(byteBuffer);
			if (returned==null) {
				Assert.assertEquals(returned,bmi);
			} else {
				if (!(returned instanceof BMISpreadsheet)) {
					Assert.fail("not of type BMISpreadsheet");
				}

				BMISpreadsheet ret = (BMISpreadsheet) returned;
				Assert.assertEquals(bmi.getHeight(), ret.getHeight(),DELTA);
				Assert.assertEquals(bmi.getWeight(), ret.getWeight());
				Assert.assertEquals(bmi.getBMI(), ret.getBMI());
				Assert.assertEquals(bmi.isMale(), ret.isMale());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static ArrayList<String> getStrings(){
		ArrayList<String> list = new ArrayList<>();
		for(int i=0; i<EXP_SIZE; i++) {
			list.add(generateRandomString());
		}
		list.add(new String());
		list.add(null);
		return list;
	}
	static ArrayList<Integer> getIntegers() {
		return (ArrayList) IntStream.range(-1*EXP_SIZE, EXP_SIZE)
				.boxed()
				.collect(Collectors.toList());
	}
	static List<Boolean> getBoolean() {
		List<Boolean> bool = new ArrayList<>();
		bool.add(new Boolean(true));
		bool.add(new Boolean(false));
		return bool;
	}
	static List getAllObjs() {
		List objs = new ArrayList();
		objs.addAll(getStrings());
		objs.addAll(getIntegers());
		objs.addAll(getBoolean());
		objs.addAll(getShorts());
		objs.addAll(getLongs());
		return objs;

	}
	static String generateRandomString() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for(int i=0; i<STRING.length(); i++) {
			builder.append(STRING.charAt(random.nextInt(STRING.length())));
		}
		return builder.toString();
	}
	static List<Short> getShorts() {
		List shorts = new ArrayList();
		for(int i=-1*EXP_SIZE; i<EXP_SIZE; i++) {
			short curr = (short)i;
			Short obj = new Short(curr);
			shorts.add(obj);
		}
		shorts.add(null);
		return shorts;

	}
	static List<Long> getLongs() {
		Random r = new Random();
		return LongStream.range(-1*EXP_SIZE,EXP_SIZE)
				.boxed()
				.map(i -> i+r.nextLong())
				.collect(Collectors.toList());
	}
	static List<Double> getDoubles() {
		Random r = new Random();
		return IntStream.range(-1*EXP_SIZE, EXP_SIZE)
				.asDoubleStream()
				.boxed()
				.map(i -> i+r.nextDouble())
				.collect(Collectors.toList());
	}
	static List<Float> getFloats() {
		ArrayList <Float> vals = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < EXP_SIZE; i++) {
			vals.add(rand.nextFloat());
		}
		return vals;
	}
	private HashSet makeSet(List list) {
		HashSet set = new HashSet();
		for (Object o : list)
			set.add(o);
		return set;
	}
}