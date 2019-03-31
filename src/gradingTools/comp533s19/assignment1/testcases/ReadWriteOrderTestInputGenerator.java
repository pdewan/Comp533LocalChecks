package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class ReadWriteOrderTestInputGenerator extends OneClientConnectionInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient = false;
	private boolean hasWritten = false;
	private boolean quitSubmitted = false;

	private int acceptStage = 0;
	private int updateStage = 0;

	private static final Pattern[] atomicClient = {
			checkStr(MAIN_THREAD, "SetProperty"),
			checkStr(MAIN_THREAD, "SocketChannelWriteRequested"),
			checkStr(READ_THREAD, "CommandSubmitted")
	};

	private static final Pattern[] nonAtomicClient = {
			checkStr(MAIN_THREAD, "SetProperty"),
			checkStr(MAIN_THREAD, "CommandSubmitted"),
			checkStr(MAIN_THREAD, "SocketChannelWriteRequested")
	};
	 
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	public ReadWriteOrderTestInputGenerator(boolean atomic) {
		this.atomic = atomic;
	}
	

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (setupServer == false && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (setupClient == false && CLIENT_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_NAME, "a " + atomic);
			setupClient = true;
		}
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isAcceptComplete()) {
				checkAccept(anOutputLine);
				if (isAcceptComplete() && !hasWritten) {
					notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
					hasWritten = true;
				}
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (isAcceptComplete() && !isUpdateCorrect()) {
				checkUpdate(anOutputLine);
			}
		}
		if (!quitSubmitted && isUpdateCorrect()) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
//	public boolean isAcceptComplete() {
//		return acceptStage == acceptStages.length;
//	}
	
	public boolean isUpdateCorrect() {
		return updateStage == (atomic ? atomicClient.length : nonAtomicClient.length);
	}

//	public boolean checkAccept(String line) {
//		if (line.startsWith(TRACER_PREFIX) && acceptStages[acceptStage].matcher(line).matches()) {
//			acceptStage++;
//			return true;
//		}
//		return false;
//	}
	
	public boolean checkUpdate(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if ((atomic && atomicClient[updateStage].matcher(line).matches())
					|| (!atomic && nonAtomicClient[updateStage].matcher(line).matches())) {
				updateStage++;
				return true;
			}
		}
		return false;
	}
	
}
