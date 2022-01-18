package gradingTools.comp533s22.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.ReducerFactory;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s22.assignment1.listUpdatedTests.DefaultFactoryMapObjectList;
import gradingTools.comp533s22.assignment1.listUpdatedTests.DefaultFactoryReduceObjectList;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapperFactory.class,
	DefaultFactoryMapObjectList.class,
	ReducerFactory.class,
	DefaultFactoryReduceObjectList.class,
})
	

//@MaxValue(120)	
public class MapReduceFactories extends Assignment1Suite{

}
