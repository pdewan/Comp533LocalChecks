package gradingTools.comp533s21.assignment2;

import gradingTools.comp533s21.assignment1.A1ReferenceConfigurationClass;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;

public class A2ReferenceConfigurationClass extends A1ReferenceConfigurationClass{

	
	@Override
	public Class getPartitionerClass() {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public Class getPartitionerFactory() {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public Object getPartitioner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getJoinerClass() {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public Class getBarrierClass() {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public Class getSlaveClass() {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public Object getBarrier(int aNumThreads) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getJoiner(int aNumThreads) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
