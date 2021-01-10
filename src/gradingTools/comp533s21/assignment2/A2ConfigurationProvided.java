package gradingTools.comp533s21.assignment2;

import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment1.interfaces.A1MapReduceConfiguration;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.shared.testcases.utils.AbstractConfigurationProvided;
import util.annotations.MaxValue;
@MaxValue(10)
public class A2ConfigurationProvided extends A1ConfigurationProvided{
	public Class referenceClass() {
		return A2ReferenceConfigurationClass.class;
	}

	

}
