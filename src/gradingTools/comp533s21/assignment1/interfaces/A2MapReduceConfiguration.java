package gradingTools.comp533s21.assignment1.interfaces;



public interface A2MapReduceConfiguration  extends A1MapReduceConfiguration{	

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
    
}
