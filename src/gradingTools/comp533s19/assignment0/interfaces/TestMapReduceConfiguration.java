package gradingTools.comp533s19.assignment0.interfaces;

public interface TestMapReduceConfiguration {
	// return instances of the classes named by getters
		// return instances of the following, you can use actual interfaces instead of 
	    // Object as return type. So if your mapper interface is MyMapper, you can
	    // have getTokenCountingMapper() return MyMapper instead of Object
	    // return null if you have not defined the kind of object expected
		Object getTokenCountingMapper(); 
		Object getIntSummingMapper(); 
	    Object getReducer();
	    Object getPartitioner();
	    // return some instance of the Barrier and Joiner classes
	    Object getBarrier(int aNumThreads);
	    Object getJoiner(int aNumThreads);
	    // return <Class Name>.class in methods below
	    // return null if you have not defined the kind of class expected

	    Class getMapperFactory();
	    Class getReducerFactory();
	    Class getPartitionerFactory();
	    Class getStandAloneTokenCounter(); // non distributed case
	    Class getStandAloneIntegerSummer();
	    Class getServerTokenCounter();
	    Class getServerTokenSummer();
	    Class getClientTokenCounter();// client remains the same in both cases
}
