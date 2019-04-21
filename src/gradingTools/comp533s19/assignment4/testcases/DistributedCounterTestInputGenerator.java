package gradingTools.comp533s19.assignment4.testcases;

import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;

import java.util.regex.Pattern;

import util.annotations.Comp533Tags;
import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class DistributedCounterTestInputGenerator extends ABufferingTestInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = false;

	public static final String TRACER_PREFIX = "I***";
	
	public static final String MAIN_THREAD = "\\{main\\}";
	public static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	public static final String GIPC_THREAD = "\\{Asynchronous Received Call Invoker\\}";
	
	protected int noneseSetupStage = 0;

	protected Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}

	protected  final Pattern[] nonsenseStages = {
		checkStr(MAIN_THREAD, "foo"),
		checkStr(MAIN_THREAD, "fooBar"),
   };
	
	public boolean isNonsenseSetupComplete() {
		return  noneseSetupStage == nonsenseStages.length;
	}
	public boolean checkNonsense(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nonsenseStages[noneseSetupStage].pattern());
			}
			if (nonsenseStages[noneseSetupStage].matcher(line).matches()) {
				noneseSetupStage++;
				return true;
			}
		}
		return false; 
	}
	
	protected  Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		super.newOutputLine(aProcessName, anOutputLine);
		if (aProcessName.equals(Comp533Tags.EXPLICIT_RECEIVE_SERVER)) {
			checkServer(anOutputLine);

		} else if (aProcessName.equals(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1)) {
			checkClient0(anOutputLine);
//			if (!isClient0ConnectComplete()) {
//				if (!isClient0NIOConnectComplete() && checkClient0NIOConnect(anOutputLine)) {
//				} else if (!isClient0RMIConnectComplete() && checkClient0RMIConnect(anOutputLine)) {
//				} else if (!isClient0GIPCConnectComplete() && checkClient0GIPCConnect(anOutputLine)) {}
//			}
		} else if (aProcessName.equals(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2)) {
			checkClient1(anOutputLine);
//			if (!isClient1ConnectComplete()) {
//				if (!isClient1NIOConnectComplete() && checkClient1NIOConnect(anOutputLine)) {
//				} else if (!isClient1RMIConnectComplete() && checkClient1RMIConnect(anOutputLine)) {
//				} else if (!isClient1GIPCConnectComplete() && checkClient1GIPCConnect(anOutputLine)) {}
//			}
		}
		
	}
	
	protected boolean checkServer(String line) {
		boolean used = false;
		if (!isNonsenseSetupComplete() && checkNonsense(line)) {
			used = true;
		}
		return used;
	}
	
	protected boolean checkClient0(String line) {
		boolean used = false;
		
		return used;
	}
	
	protected boolean checkClient1(String line) {
		boolean used = false;
		
		return used;
	}
	
	
}
