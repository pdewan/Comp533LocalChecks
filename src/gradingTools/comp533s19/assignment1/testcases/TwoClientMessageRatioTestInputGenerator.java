package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class TwoClientMessageRatioTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	
	private static final String READ_MESSAGE = "SocketChannelFullMessageRead";
	private static final String WRITE_MESSAGE = "SocketChannelWriteRequested";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient0 = false;
	private boolean setupClient1 = false;
	
	private boolean hasWritten = false;

	private int accept0Stage = 0;
	private int accept1Stage = 0;
	
	private int client0WriteNum = 0;
	private int client0ReadNum = 0;
	private int client1WriteNum = 0;
	private int client1ReadNum = 0;
	private int serverWriteNum = 0;
	private int serverReadNum = 0;

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
	
	public TwoClientMessageRatioTestInputGenerator(boolean atomic) {
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
			if (!areAcceptsComplete()) {
				// each output can only be from 1 accept
				boolean accepted = false;
				if (!isAccepted0Complete()) {
					accepted = checkAccept0(anOutputLine);
				}
				if (!accepted && !isAccepted1Complete()) {
					checkAccept1(anOutputLine);
				}
				if (areAcceptsComplete() && !hasWritten) {
					notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
					hasWritten = true;
				}
			} else {
				if (checkForRead(anOutputLine)) {
					serverReadNum ++;
				} else if (checkForWrite(anOutputLine)) {
					serverWriteNum ++;
				}
			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			if (areAcceptsComplete()) {
				if (checkForRead(anOutputLine)) {
					client0ReadNum ++;
				} else if (checkForWrite(anOutputLine)) {
					client0WriteNum ++;
				}
			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			if (areAcceptsComplete()) {
				if (checkForRead(anOutputLine)) {
					client1ReadNum ++;
				} else if (checkForWrite(anOutputLine)) {
					client1WriteNum ++;
				}
			}
		}
	}
	
	public int getClient0ReadCount() {
		return client0ReadNum;
	}
	
	public int getClient0WriteCount() {
		return client0WriteNum;
	}
	
	public int getClient1ReadCount() {
		return client1ReadNum;
	}
	
	public int getClient1WriteCount() {
		return client1WriteNum;
	}
	
	public int getServerReadCount() {
		return serverReadNum;
	}
	
	public int getServerWriteCount() {
		return serverWriteNum;
	}

	public boolean isAccepted0Complete() {
		return accept0Stage == acceptStages.length;
	}
	
	public boolean isAccepted1Complete() {
		return accept1Stage == acceptStages.length;
	}
	
	public boolean areAcceptsComplete() {
		return isAccepted0Complete() && isAccepted1Complete();
	}
	
	
	private boolean checkForRead(String anOutputLine) {
		return doesLineContain(anOutputLine, READ_MESSAGE);
	}
	
	private boolean checkForWrite(String anOutputLine) {
		return doesLineContain(anOutputLine, WRITE_MESSAGE);
	}
	
	public boolean checkAccept0(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept0Stage].matcher(line).matches()) {
			accept0Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept1(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept1Stage].matcher(line).matches()) {
			accept1Stage++;
			return true;
		}
		return false;
	}
	
	private static boolean doesLineContain(String str, String sub) {
		return str.startsWith(TRACER_PREFIX) && Pattern.compile(".*?" + sub + ".*", Pattern.DOTALL).matcher(str).matches();
	}
}
