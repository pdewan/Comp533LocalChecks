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

    
    // The model stored in the server will now have a remote interface to be used 
    // by the client to register its proxy. 
    // The model class will have to be changed to implement  this interface. 
    // If you change the name of this class, make sure you change the getModelClass
    // method of the registry to reflect the name change.   
    Class getRemoteModelInterface();
    
    
    // The client proxy also has a remote interface for remote callbacks
	Class getRemoteClientObjectInterface();
	
	// This is the class implementing the interface
	Class getRemoteClientObjectClass();
	
	// For each application (token count, int sum), we now have two main classes,
	// one for the server and one for each client.
		
	// The main class of the server token counter.	
	Class getServerTokenCounter();
	
	// The main class of the server int summer.
    Class getServerIntegerSummer();
    
    // The main class of both the client token counter and the int summer,
  	// since the reduction step is the same for both, and the client only
    // performs reduction
    Class getClientTokenCounter();
    
    // A standalone class similar to the two classes in A2 implementing the Facebook 
    // map reduce algorithm
	Class getStandAloneFacebookMapReduce();

	// The main class of the server facebook implementation
	Class getServerFacebookMapReduce();
	// The main class of the client facebook implementation
	Class getRemoteClientFacebookMapReduce();
	
}
