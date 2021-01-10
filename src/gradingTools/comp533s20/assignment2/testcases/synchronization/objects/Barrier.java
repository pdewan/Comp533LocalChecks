package gradingTools.comp533s20.assignment2.testcases.synchronization.objects;

import grader.basics.junit.JUnitTestsEnvironment;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import gradingTools.shared.testcases.concurrency.AbstractBarrier;
import gradingTools.shared.testcases.concurrency.AbstractEarlyJoinBasicJoiner;
import util.annotations.MaxValue;

@MaxValue(40)
//public class EarlyJoinBasicJoiner extends PassFailJUnitTestCase {
public class Barrier extends AbstractBarrier {
	@Override
	protected Object getBarrierObject (Class[] aConstructorArgTypes, Object[] aJoinerArgs, Class aProxyClass ) {

//			ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A2ConfigurationProvided aConfigurationProvided = (A2ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);

		return aConfigurationProvided.getTestConfiguration().getBarrier(barrierCount);
			
	
	}
	@Override
	protected Class getBarrierClass() {
		return null;
	}
	
	
	
//	@Override
//	protected Class getJoinerClass() {
//		ImmmutableJoinerClassProvided aCheckClass = (ImmmutableJoinerClassProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ImmmutableJoinerClassProvided.class);
//		if (aCheckClass == null) {
//			assertTrue("No check class found", false);
//		}
//		Class aJoinerClass = aCheckClass.getImmutableJoinerClass();
//		if (aJoinerClass == null) {
//			assertTrue("No Joiner class found", false);
//		}
//		return aJoinerClass;
//	}

//	
//	protected Joiner joiner;
//	protected int joinerCount = 4;
//	protected int taskCount = 0;
//	protected long slaveTimeout;
//	protected long masterTimeout;
//	protected Joiner timingOutJoiner;
//
//	protected void setTimeouts() {
//		slaveTimeout = 300;
//		masterTimeout = 0;
//	}
//	
//	protected TestCaseResult doJoinTest() {
////		for (int i =0; i < joinerCount; i++) {
////			new Thread (()-> {doSlaveTask();}).start();
////		}
////		return doMasterTask();
//		return parallelInc();
//		
//	}
//	protected TestCaseResult parallelInc() {
//		for (int i =0; i < joinerCount; i++) {
//			new Thread (()-> {doSlaveTask();}).start();
//		}
//		return doMasterTask();
//		
//	}
//	
//	protected synchronized void doSlaveTask() {
//		taskCount++;
//		ThreadSupport.sleep(slaveTimeout);
//		Tracer.info(this, Thread.currentThread() + "before finished");
//		timingOutJoiner.finished();
//		Tracer.info(this, Thread.currentThread() + "after finished, taskCount=" + taskCount);
//		
//	}
//	
//	protected TestCaseResult doMasterTask() {
//		try {
//			ThreadSupport.sleep(masterTimeout);
//			Tracer.info(this, Thread.currentThread() + "before join");
//			timingOutJoiner.join();
//			Tracer.info(this, Thread.currentThread() + "after join, taskCount =" + taskCount);
//
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (taskCount == joinerCount) {
//			return pass();
//		}
//		if (taskCount < joinerCount) {
//			return fail("joiner finished early? taskCount:" + taskCount + " joinerCount:" + joinerCount);
//		}
//		return fail("taskCount:" + taskCount + " joinerCount:" + joinerCount);
//
//	}
//	
//	protected void createJoiner() {
//		ImmmutableJoinerClassProvided aCheckClass = (ImmmutableJoinerClassProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ImmmutableJoinerClassProvided.class);
//		if (aCheckClass == null) {
//			assertTrue("No check class found", false);
//		}
//		Class aJoinerClass = aCheckClass.getImmutableJoinerClass();
//		if (aJoinerClass == null) {
//			assertTrue("No Joiner class found", false);
//		}
//		Class[] aConstructorArgTypes = {Integer.TYPE};
//		try {
//			Constructor aJoinerConstructor = aJoinerClass.getConstructor(aConstructorArgTypes);
//		     Object[] anArgs = {joinerCount};
//			joiner = (Joiner) aJoinerConstructor.newInstance(anArgs);
//			timingOutJoiner = 	(Joiner) BasicProjectIntrospection.createTimingOutProxy(Joiner.class, joiner);
//
//		
//		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			Constructor[] aConstructors = aJoinerClass.getConstructors();
//			String aConstructoraString = Arrays.toString(aConstructors);
//			assertTrue("No public constructor with single int argument in joiner class:" + aJoinerClass + "constructors found:" + aConstructoraString, false);
//		}
//	}
//

//	@Override
//	public TestCaseResult test(Project project, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		createJoiner();
//		setTimeouts();
//		TestCaseResult retVal = doJoinTest();
//		return retVal;
////		ImmmutableJoinerClassProvided aCheckClass = (ImmmutableJoinerClassProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ImmmutableJoinerClassProvided.class);
////		if (aCheckClass == null) {
////			return fail("No check class found");
////		}
////		Class aJoinerClass = aCheckClass.getImmutableJoinerClass();
////		if (aJoinerClass == null) {
////			return fail("No Joiner class found");
////
////		}
////		Class[] aConstructorArgTypes = {Integer.TYPE};
////		try {
////			Constructor aJoinerConstructor = aJoinerClass.getConstructor(aConstructorArgTypes);
////		     Object[] anArgs = {joinerCount};
////			joiner = (Joiner) aJoinerConstructor.newInstance(anArgs);
////			timingOutJoiner = 	(Joiner) BasicProjectIntrospection.createTimingOutProxy(Joiner.class, joiner);
////
////		
////		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
////			return fail("No constructor with single int argument in joiner class:" + aJoinerClass);
////		}
//		
//	}

}
