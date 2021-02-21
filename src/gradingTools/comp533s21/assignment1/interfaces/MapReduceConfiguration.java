package gradingTools.comp533s21.assignment1.interfaces;



public interface MapReduceConfiguration {
	// ---------------A1---------------------------------
    // return <Class Name>.class in returns of methods below

    // return main classes
    Class getStandAloneTokenCounter(); 
    Class getStandAloneIntegerSummer();

    //MVC classes
    Class getModelClass();
    Class getViewClass();
    Class getControllerClass();
    

    // Factories
    Class getMapperFactory();
    Class getReducerFactory();
  

    //KeyValue defining and processing class
    Class getKeyValueClass();
    Class getTokenCountingMapperClass();
    Class getIntSummingMapperClass(); // extra credit
    Class getReducerClass();
    
    

    // Return instances of the required objects, using the relevant factories
    // if they return these objects by default
    Object getTokenCountingMapper(); // default object returned by Mapper factory
    Object getIntSummingMapper(); 
    Object getReducer(); // default object returned by Reducer factory   
    
    // --------------------A2------------------------

    Class getPartitionerClass();
    Class getPartitionerFactory();
    Object getPartitioner(); 
  	Class getJoinerClass();
	Class getBarrierClass();
	Class getSlaveClass();    
    // return some instance of the Barrier and Joiner classes in the  methods
	// below as these are not singletons
    Object getBarrier(int aNumThreads);
    Object getJoiner(int aNumThreads);

    // --------------------A3--------------------------

    Class getServerTokenCounter();
    Class getServerIntegerSummer();
    Class getClientTokenCounter();
    Class getRemoteModelInterface() ;
	Class getRemoteClientObjectInterface();
	Class getRemoteClientObjectClass();
	
	Class getServerFacebookMapReduce();
	Class getRemoteClientFacebookMapReduce();
	Class getStandAloneFacebookMapReduce();
}
