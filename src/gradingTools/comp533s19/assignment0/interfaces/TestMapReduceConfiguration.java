package gradingTools.comp533s19.assignment0.interfaces;

public interface TestMapReduceConfiguration {
	// ---------------Mostly A1---------------------------------
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
    Class getPartitionerFactory(); // A2

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
    Object getPartitioner(); // default object returned by Reducer factory, needed in A2
    
    // --------------------A2------------------------
    
    // return some instance of the Barrier and Joiner classes
    Object getBarrier(int aNumThreads);
    Object getJoiner(int aNumThreads);

//    // return A2 main classes
//    Class getMultiThreadTokenCounter(); 
//    Class getMultiThreadIntegerSummer();
    
    // --------------------A3--------------------------

    Class getServerTokenCounter();
    Class getServerIntegerSummer();
    Class getClientTokenCounter();// client remains the same in both cases
}
