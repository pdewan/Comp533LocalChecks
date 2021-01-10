package gradingTools.comp533s21.assignment1.interfaces;



public interface A1MapReduceConfiguration {
	
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
    
    
}
