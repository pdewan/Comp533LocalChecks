package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class TwoClientCorrectThreadsTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient0 = false;
	private boolean setupClient1 = false;
	
	private boolean quitSubmitted = false;
	
	private boolean foundClient0Select = false;
	private boolean foundClient0Read = false;
	private boolean foundClient1Select = false;
	private boolean foundClient1Read = false;

	private boolean foundServerSelect = false;
	private boolean foundServerRead = false;
	
	private int numAccepted = 0;
	private boolean hasWritten = false;
	
	private final Pattern ACCEPT_COMPLETED_PATTERN = checkStr(SELECT_THREAD, "SocketChannelRegistered");

	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	public TwoClientCorrectThreadsTestInputGenerator(boolean atomic) {
		this.atomic = atomic;
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (setupServer == false && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (setupClient0 == false && CLIENT_0_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_0_NAME, "a " + atomic);
			setupClient0 = true;
		}
		if (setupClient1 == false && CLIENT_1_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_1_NAME, "a " + atomic);
			setupClient1 = true;
		}
		if (aProcessName.equals(SERVER_NAME)) {
			if (!allAccepted() && checkAccepted(anOutputLine)) {
				if (allAccepted() && !hasWritten) {
					notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
					hasWritten = true;
				}
			} else if (!foundServerSelect && checkServerSelect(anOutputLine)) {
			} else if (!foundServerRead && checkServerRead(anOutputLine)) {
			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			
			if (!foundClient0Select && checkClient0Select(anOutputLine)) {
			} else if (atomic && !foundClient0Read && checkClient0Read(anOutputLine)) {
			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			if (!foundClient1Select && checkClient1Select(anOutputLine)) {
			} else if (!foundClient1Read && checkClient1Read(anOutputLine)) {
			}
		}
		if (!quitSubmitted && foundAll()) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	private boolean allAccepted() {
		return numAccepted == 3;
	}
	
	public boolean foundAll() {
		return foundClient0Select() && foundClient0Read()
				&& foundClient1Select() && foundClient1Read()
				&& foundServerSelect() && foundServerRead();
	}
	
	public boolean foundClient0Select() {
		return foundClient0Select;
	}
	
	public boolean foundClient0Read() {
		return foundClient0Read;
	}
	
	public boolean foundClient1Select() {
		return foundClient1Select;
	}
	
	public boolean foundClient1Read() {
		return foundClient1Read;
	}

	public boolean foundServerSelect() {
		return foundServerSelect;
	}
	
	public boolean foundServerRead() {
		return foundServerRead;
	}
	
	private boolean checkClient0Select(String anOutputLine) {
		if (doesLineContain(anOutputLine, SELECT_THREAD)) {
			foundClient0Select = true;
			return true;
		}
		return false;
	}

	private boolean checkClient0Read(String anOutputLine) {
		if (doesLineContain(anOutputLine, READ_THREAD)) {
			foundClient0Read = true;
			return true;
		}
		return false;
	}
	
	private boolean checkClient1Select(String anOutputLine) {
		if (doesLineContain(anOutputLine, SELECT_THREAD)) {
			foundClient1Select = true;
			return true;
		}
		return false;
	}

	private boolean checkClient1Read(String anOutputLine) {
		if (doesLineContain(anOutputLine, READ_THREAD)) {
			foundClient1Read = true;
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
	
	private boolean checkAccepted(String anOutputLine) {
		if (ACCEPT_COMPLETED_PATTERN.matcher(anOutputLine).matches()) {
			numAccepted++;
			return true;
		}
		return false;
	}
	
	private static boolean doesLineContain(String str, String sub) {
		return str.startsWith(TRACER_PREFIX) && Pattern.compile(".*?" + sub + ".*", Pattern.DOTALL).matcher(str).matches();
	}
}
