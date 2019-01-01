package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class OneClientCorrectThreadsTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient = false;
	
	private boolean quitSubmitted = false;
	
	private boolean foundClientSelect = false;
	private boolean foundClientRead = false;

	private boolean foundServerSelect = false;
	private boolean foundServerRead = false;
	
	private boolean hasWritten = false;

	private int acceptStage = 0;
	
	private static final Pattern[] acceptStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};

	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	public OneClientCorrectThreadsTestInputGenerator(boolean atomic) {
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
			// 
			if (!isAcceptComplete() && checkAccept(anOutputLine)) {
				if (isAcceptComplete() && !hasWritten) {
					notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
					hasWritten = true;
				}
			} else if (!foundServerSelect && checkServerSelect(anOutputLine)) {
			} else if (!foundServerRead && checkServerRead(anOutputLine)) {
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (!foundClientSelect && checkClientSelect(anOutputLine)) {
			} else if (atomic && !foundClientRead && checkClientRead(anOutputLine)) {
			}
		}
		if (!quitSubmitted && foundAll()) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isAcceptComplete() {
		return acceptStage == acceptStages.length;
	}
	
	public boolean foundAll() {
		return foundClientSelect() && foundClientRead() && foundServerSelect() && foundServerRead();
	}
	
	public boolean foundClientSelect() {
		return foundClientSelect;
	}
	
	public boolean foundClientRead() {
		return foundClientRead;
	}

	public boolean foundServerSelect() {
		return foundServerSelect;
	}
	
	public boolean foundServerRead() {
		return foundServerRead;
	}
	
	private boolean checkClientSelect(String anOutputLine) {
		if (doesLineContain(anOutputLine, SELECT_THREAD)) {
			foundClientSelect = true;
			return true;
		}
		return false;
	}

	private boolean checkClientRead(String anOutputLine) {
		if (doesLineContain(anOutputLine, READ_THREAD)) {
			foundClientRead = true;
			return true;
		}
		return false;
	}
	
	private boolean checkServerSelect(String anOutputLine) {
		if (doesLineContain(anOutputLine, SELECT_THREAD)) {
			foundServerSelect = true;
			return true;
		}
		return false;
	}

	private boolean checkServerRead(String anOutputLine) {
		if (doesLineContain(anOutputLine, READ_THREAD)) {
			foundServerRead = true;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[acceptStage].matcher(line).matches()) {
			acceptStage++;
			return true;
		}
		return false;
	}
	
	private static boolean doesLineContain(String str, String sub) {
		return str.startsWith(TRACER_PREFIX) && Pattern.compile(".*?" + sub + ".*", Pattern.DOTALL).matcher(str).matches();
	}
}
