package gradingTools.comp533s19.assignment0;

import java.beans.PropertyChangeEvent;

import util.misc.ThreadSupport;
import util.trace.bean.AddedPropertyChangeListener;

public abstract class AMapReduceTracer {
	public static String INFO_PREFIX = "";
	public static String WAIT = "wait";
	public static String NOTIFY = "notify";
	public static String ENQUEUE = "enqueue:";
	public static String DEQUEUE = "dequeue:";
	public static String ADD = "add:";
	public static String REDUCE = "reduce:";
	public static String NEW_MAPPER = "new mapper:";
	public static String MODEL = "Model"; // value returned by toString() method of model
	public static String VIEW = "View"; // value returned by toString() method of view
	public static String CONTROLLER = "Controller"; // value returned by toString() method of controller
	public static String REDUCER = "Reducer"; // value returned by toString() method of reducer
	public static String TOKEN_COUNTING_MAPPER = "Token Counting Mapper"; // value returned by toString() method of token counting mapper
	public static String INT_SUMMING_MAPPER = "Int Summing Mapper"; // value returned by toString() method of int summing mapper

	public static String SLAVE = "Slave"; // value returned by toString() method of slave summer
	public static final String QUIT = "quit";
	public static final String EXIT = "exit";

	public static final String QUITTING = "Quitting";
	public static final String REGISTER = "Register:";
	public static final String CLIENT_ASSIGNED = "Client Assigned:";

	public static final String REMOTE_LIST = "Remote List:";
	public static final String REMOTE_RESULT = "Remote Result:";


	public static final String START_TOKEN = "Start";
	public final int BUFFER_SIZE = 2;	


	// -----------------------------A1 Traces----------------------------------
	protected String toPrefix() {
		return INFO_PREFIX + Thread.currentThread() + ":" + this + ":";
	}
	static final int FLUSH_SLEEP = 100;
	protected void trace(String aSuffix) {
		System.out.println(toPrefix() + aSuffix);
		System.out.flush();
		ThreadSupport.sleep(FLUSH_SLEEP);
	}
	/**
	 * To be called before scanning for input string
	 */
	protected void traceNumbersPrompt() {
		trace ("Please enter " + QUIT + " or a line of tokens to be processed separated by spaces");
	}
	/**
	 * To be called in the controller (A1) and slave runnable (A2)
	 */
	protected void traceQuit() {
		trace (QUITTING);
	}
	/**
	 * To be called in the view at start of call to propertyChange
	 */
	protected void tracePropertyChange(PropertyChangeEvent anEvent) {
		trace(anEvent.toString());
	}
	/*
	 * To be called in the map methods of the mappers for each tokenlist
	 */
	protected void traceMap(Object anInputs, Object aKeyValues) {
		trace (MAP  + anInputs + ":" + aKeyValues);		
	}
	/*
	 * To be called in the reducer before returning
	 */
	protected void traceReduce(Object aList,  Object aReducedMap) {
		trace(REDUCE + aList + ":" +  aReducedMap);
	}
	/*
	 * to be called in the mapper factory when the mapper changes (deprecated)
	 */
	@Deprecated
	public static void traceMapperChange( Class aFactoryClass, Object aNewMapper) {
		System.out.println(INFO_PREFIX + Thread.currentThread() + ":" + aFactoryClass + ":" + NEW_MAPPER +  aNewMapper);
	}
	/*
	 * to be called in the mapper/reducer factory when the mapper/reducer changes
	 */
	public static void traceSingletonChange(Class aFactoryClass, Object aNewSingleton) {
		if (aNewSingleton == null) {
			System.out.println("Null new singleton");
		} else {
		System.out.println(INFO_PREFIX + Thread.currentThread() + ":" + aFactoryClass + ":" + NEW_MAPPER +  aNewSingleton);
		}
	}
	
	
	//----------------------------A2 traces -----------------------------------
	/**
	 * To be called before scanning for number of threads
	 */
	protected void traceThreadPrompt() {
		trace("Please enter the number of threads");
	}
	/**
	 * To be called before a wait on this
	 */
	protected void traceWait() {
		trace (WAIT);
	}
	/**
	 * To be called before doing a notify on this 
	 */
	protected void traceNotify() {
		trace(NOTIFY);
	}
//	/**
//	 * To be called by a summer slave thread before adding an int to its sublist 
//	 */
//	protected void traceAdd(int anInt) {
//		trace ( ADD + anInt);
//	}
	/**
	 * To be called by a master thread before adding an element to 
	 * a queue
	 */
	protected void traceEnqueueRequest(Object anElement) {
		trace(ENQUEUE + anElement);
	}
	/**
	 * To be called by a  master thread after adding an object to 
	 * a queue
	 */
	protected void traceEnqueue(Object aQueue) {
		trace(ENQUEUE + aQueue);
	}
	/**
	 * To be called by a slave thread before executing a take from
	 * queue 
	 */
	protected void traceDequeueRequest(Object aQueue) {
		trace (DEQUEUE + aQueue);
	}
	/**
	 * To be called by a slave thread after taking an object from a
	 * queue 
	 */
	protected void traceDequeue(Object anElement) {
		trace (DEQUEUE + anElement);
	}
	
//	/**
//	 * To be called in the model at the start of its reduce method	 
//	 */
//	protected void traceReduce(int aNumItemsReduced, Object aReducedMap) {
//		trace(REDUCE + aNumItemsReduced + ":" + aReducedMap);
//	}
	
	
	/*
	 * Additions since assignment was last given
	 */
	public static final String BARRIER_CREATED = "Barrier Created:";
	public static final String BARRIER_WAIT_START = "Barrier Wait Start:"; 
	public static final String BARRIER_WAIT_END = "Barrier Wait End:"; 
	public static final String BARRIER_RELEASE_ALL = "Barrier Release All:"; 
	
	public static final String JOINER_CREATED = "Joiner Created:";
	public static final String JOINER_WAIT_START = "Joiner Wait Start:"; 
	public static final String JOINER_WAIT_END = "Joiner Wait End:"; 
	public static final String JOINER_FINISHED_TASK = "Joiner Finished:"; 
	public static final String JOINER_RELEASE = "Joiner Release:"; 	
	public static final String PARTITION_ASSIGNED = "Partition Assigned:"; 
	public static final String MAP = "Map:";
//	public static final String REDUCE = "Reduce:";

	public static final String PARTITION_AFTER_BARRIER = "Partition After Barrier:";
	public static final String ADDED_TO_FINAL_MAP = "Added to Final Map:";	
	
	protected void traceBarrierCreated(Object aBarrier, int aNumThreads) {
		trace (BARRIER_CREATED  + aBarrier + ":" + aNumThreads);		
	}
	protected void traceBarrierWaitStart(Object aBarrier, int aNumThreads, int aNumWaitingThreads) {
		trace (BARRIER_WAIT_START + aBarrier + ":" + aNumThreads + ":" + aNumWaitingThreads);		
	}
	protected void traceBarrierWaitEnd(Object aBarrier, int aNumThreads, int aNumWaitingThreads) {
		trace (BARRIER_WAIT_END + aBarrier + ":" + aNumThreads + ":" + aNumWaitingThreads);		
	}
	protected void traceBarrierReleaseAll(Object aBarrier, int aNumThreads, int aNumWaitingThreads) {
		trace (BARRIER_RELEASE_ALL + aBarrier + ":" + aNumThreads + ":" + aNumWaitingThreads);		
	}
	
	protected void traceJoinerCreated(Object aJoiner, int aNumThreads) {
		trace (JOINER_CREATED +aJoiner + ":" + aNumThreads);		
	}
	protected void traceJoinerWaitStart(Object aJoiner, int aNumThreads, int aNumFinishedThreads) {
		trace (JOINER_WAIT_START + aJoiner + ":" + aNumThreads + ":" + aNumFinishedThreads);		
	}
	protected void traceJoinerWaitEnd(Object aJoiner, int aNumThreads, int aNumFinishedThreads) {
		trace (JOINER_WAIT_END + aJoiner + ":" + aNumThreads + ":" + aNumFinishedThreads);		
	}
	/*
	 * Call after changing finished threads in the joiner
	 */
	protected void traceJoinerFinishedTask(Object aJoiner, int aNumThreads, int aNumFinishedThreads) {
		trace (JOINER_FINISHED_TASK + aJoiner + ":" + aNumThreads + ":" + aNumFinishedThreads);		
	}
	protected void traceJoinerRelease(Object aJoiner, int aNumThreads, int aNumFinishedThreads) {
		trace (JOINER_RELEASE + aJoiner + ":" + aNumThreads + ":" + aNumFinishedThreads);		
	}
	protected void tracePartitionAssigned(Object aKey, Object aValue, int aPartitionNum, int aNumPartitions ) {
		trace (PARTITION_ASSIGNED + aKey + ":" + aValue + ":" + aPartitionNum + ":" + aNumPartitions );
	}
	/*
	 * To be printed by thread aThreadNumber
	 */
	protected void traceSplitAfterBarrier(int aThreadNumber, Object aList) {
		trace (PARTITION_AFTER_BARRIER + aThreadNumber + ":" + aList);
	}
	
	
	/*
	 * Call after adding reduced map to final map
	 */
	protected void traceAddedToMap(Object anOriginalMap, Object aReducedMap) {
		trace( ADDED_TO_FINAL_MAP + anOriginalMap + ":" + aReducedMap);
	}
	
	//---------------------------A3 Traces--------------------------------------
	/**
	 * To be called in the server at the start of its method for registering a client
	 */
	protected void traceRegister(Object aClient) {
		trace (REGISTER + aClient);
	}
	/**
	 * To be called in the server runnable when it is assigned a  client
	 */
	protected void traceClientAssignment(Object aClient) {
		trace(CLIENT_ASSIGNED + aClient);
	}	
	
	/**
	 * To be called in the server runnable before invoking the callback in the client.
	 * To be also called in the client at the start of its callback to process sublist	 * 
	 */
	protected void traceRemoteList (Object aSublist) {
		trace (REMOTE_LIST + aSublist);		
	}
	/**
	 * To be called in the client before returning its result.
	 * To be also called in server runnable after receiving the result from the client
	 */
	protected void traceRemoteResult (Object aResult) {
		trace (REMOTE_RESULT + aResult);
	}
	/**
	 * Static method be called by the client main method
	 */
	public static void traceExit(Class aClass) {
		System.out.println (INFO_PREFIX + Thread.currentThread() + ":" + EXIT);
	}
	
	/**
	 * Slave runnables and client objects can define the following two methods
	 */
	public synchronized void synchronizedNotify() {
		traceNotify();
		this.notify();
	}	
	public synchronized void synchronizedWait() throws InterruptedException {
		traceWait();		
		this.wait();
		
	}
}
