package gradingTools.comp533s19.assignment0.testcases;

import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class MapReduceInputGenerator extends ABufferingTestInputGenerator {
	String numThreads;
	String[] inputLines;
	public MapReduceInputGenerator (String[] anInputLines) {
//		numThreads = Integer.toString(aNumThreads);
		inputLines = anInputLines;		
	}
	
	
	
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		super.newOutputLine(aProcessName, anOutputLine);
		if (aProcessName.equals(Assignment0Suite.MAP_REDUCE_CLIENT_2)) { // can give input to server
//			notifyNewInputLine(Assignment0Suite.MAP_REDUCE_SERVER, numThreads);
//			for (int i = 0; i < inputLines.length - 1; i++) {
//				String aLine = inputLines[i];

			for (String aLine:inputLines) {
				notifyNewInputLine(Assignment0Suite.MAP_REDUCE_SERVER, aLine);

//			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "aaa jjj sss zzzz aaa aaa jjj zzz aaa jjj");
//			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "bbb iii ttt yyy bbb bbb iii yyy bbb iii");
			}
//			notifyNewInputLine(Assignment0Suite.MAP_REDUCE_SERVER, "quit");
		}
//		if (anOutputLine.contains("Processing line:" )) {
//			notifyNewInputLine(Assignment0Suite.MAP_REDUCE_SERVER, inputLines[inputLines.length -1]);
//		}
		
	}
	
	
}
