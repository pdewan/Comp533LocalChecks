package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class OneClientMessageRatioTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private static final String READ_MESSAGE = "SocketChannelFullMessageRead";
	private static final String WRITE_MESSAGE = "SocketChannelWriteRequested";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient = false;
	
	private boolean hasWritten = false;

	private int acceptStage = 0;
	
	private int clientWriteNum = 0;
	private int clientReadNum = 0;
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
	
	public OneClientMessageRatioTestInputGenerator(boolean atomic) {
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
			} else {
				if (checkForRead(anOutputLine)) {
					serverReadNum ++;
				} else if (checkForWrite(anOutputLine)) {
					serverWriteNum ++;
				}
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (isAcceptComplete()) {
				if (checkForRead(anOutputLine)) {
					clientReadNum ++;
				} else if (checkForWrite(anOutputLine)) {
					clientWriteNum ++;
				}
			}
		}
	}
	
	public int getClientReadCount() {
		return clientReadNum;
	}
	
	public int getClientWriteCount() {
		return clientWriteNum;
	}
	
	public int getServerReadCount() {
		return serverReadNum;
	}
	
	public int getServerWriteCount() {
		return serverWriteNum;
	}
	
	public boolean isAcceptComplete() {
		return acceptStage == acceptStages.length;
	}
	
	private boolean checkForRead(String anOutputLine) {
		return doesLineContain(anOutputLine, READ_MESSAGE);
	}
	
	private boolean checkForWrite(String anOutputLine) {
		return doesLineContain(anOutputLine, WRITE_MESSAGE);
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
