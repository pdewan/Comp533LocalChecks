package gradingTools.comp533s19.assignment0.testcases;

import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class MapReduceInputGenerator extends ABufferingTestInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = false;

	private static final String TRACER_PREFIX = "I***";
	
	
	
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		super.newOutputLine(aProcessName, anOutputLine);
		if (aProcessName.equals(GetConfiguration.MAP_REDUCE_CLIENT_2)) { // can give input to server
			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "3");
			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "aaa jjj sss zzzz aaa aaa jjj zzz aaa jjj");
			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "bbb iii ttt yyy bbb bbb iii yyy bbb iii");
			notifyNewInputLine(GetConfiguration.MAP_REDUCE_SERVER, "quit");
		}
		
	}
	
	
}
