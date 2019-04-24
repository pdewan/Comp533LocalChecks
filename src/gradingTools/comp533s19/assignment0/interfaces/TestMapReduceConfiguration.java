package gradingTools.comp533s19.assignment0.interfaces;

public interface TestMapReduceConfiguration {
	// return instances of the classes named by getters
		// use factory classes to return instances of the following three
		Object getMapper(); 
	    Object getReducer();
	    Object getPartitioner();
	    // return some instance of the Barrier and Joiner classes
	    Object getBarrier(int aNumThreads);
	    Object getJoiner(int aNumThreads);
	    // return <Class Name>.class in methods below
	    Class getMapperFactory();
	    Class getReducerFactory();
	    Class getPartitionerFactory();
	    Class getStandAloneTokenCounter(); // non distributed case
	    Class getStandAloneIntegerSummer();
	    Class getServerTokenCounter();
	    Class getServerTokenSummer();
	    Class getClientTokenCounter();// client remains the same in both cases
}
