package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class OneClientCorrectReadWriteTestInputGenerator extends OneClientCorrectConnectionTestInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String AWT_THREAD = "\\{AWT-EventQueue-.*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient = false;
	private boolean commandSent = false;
	private boolean quitSubmitted = false;

	private int correctLen;
	private String correctStr;
	
	private int clientWriteLen;
	private String clientWriteStr;
	
	private int serverReadLen;
	private String serverReadStr;
	
	private int serverWriteLen;
	private String serverWriteStr;
	
	private int clientReadLen;
	private String clientReadStr;

	private int clientWriteStage = 0;
	private int serverWriteStage = 0;
	private int clientReadStage = 0;
	private int serverReadStage = 0;
	
	private static final int CLIENT_WRITE_LEN_STAGE = 3;
	private static final int CLIENT_WRITE_STR_STAGE = 4;
	
	private static final int SERVER_WRITE_LEN_STAGE = 1;
	private static final int SERVER_WRITE_STR_STAGE = 2;

	private static final int READ_LEN_STAGE = 2;
	private static final int READ_STR_STAGE = 3;

	private static final Pattern[] clientWriteStages = {
//			checkStr(AWT_THREAD, "SetProperty"),
			checkStr(MAIN_THREAD, "NotifiedPropertyChangeEvent"),
			checkStr(MAIN_THREAD, "SocketChannelWriteRequested"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverWriteStages = {
			checkStr(READ_THREAD, "SocketChannelWriteRequested"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] readStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	 
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	
	public OneClientCorrectReadWriteTestInputGenerator(boolean atomic) {
		this.atomic = atomic;
	}
	

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		boolean didServer = false;
		boolean didClient = false;
		if (setupServer == false && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (setupClient == false && CLIENT_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_NAME, "a " + atomic);
			setupClient = true;
		}
		// use lines for connection first
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isEnableAcceptComplete()) {
				didServer = checkEnableAccept(anOutputLine);
			} else if (!isAcceptComplete()) {
				didServer = checkAccept(anOutputLine);
				if (!commandSent && isAcceptComplete()) {
					notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
					commandSent = true;
				}
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (!isConnectComplete()) {
				didClient = checkForPropertyChangeListener(anOutputLine);
				if (!didClient) {
					didClient = checkForReadListener(anOutputLine);
					if(!didClient && !isConnectFSMComplete()) {
						didClient = checkConnect(anOutputLine);
					}
				}
			}
		}
		// read/write if not connect
		if (!didServer && aProcessName.equals(SERVER_NAME)) {
			if (!isServerReadComplete()) {
				checkServerRead(anOutputLine);
			} else if (atomic && !isServerWriteComplete()) {
				checkServerWrite(anOutputLine);
			}
		} else if (!didClient && aProcessName.equals(CLIENT_NAME)) {
			if (!isClientWriteComplete()) {
				checkClientWrite(anOutputLine);
			} else if (atomic && !isClientReadComplete()) {
				checkClientRead(anOutputLine);
			}
		}
		if (!quitSubmitted && (atomic ? isClientReadComplete() : isServerReadComplete())) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isClientWriteComplete() {
		return clientWriteStage == clientWriteStages.length;
	}
	
	public boolean isServerWriteComplete() {
		return serverWriteStage == serverWriteStages.length;
	}

	public boolean isClientReadComplete() {
		return clientReadStage == readStages.length;
	}
	
	public boolean isServerReadComplete() {
		return serverReadStage == readStages.length;
	}
	
	public boolean serverCanRead() {
		return clientWriteStage >= 9;
	}

	public boolean clientCanRead() {
		return serverWriteStage >= 8;
	}
	
	public boolean clientWriteMatchesCorrect() {
		return correctLen == clientWriteLen && correctStr.equals(clientWriteStr);
	}
	
	public boolean serverReadMatchesClientWrite() {
		return clientWriteLen != 0 && clientWriteLen == serverReadLen && clientWriteStr.equals(serverReadStr);
	}
	
	public boolean serverWriteMatchesRead() {
		return serverReadLen != 0 && serverReadLen == serverWriteLen && serverReadStr.equals(serverWriteStr);
	}

	public boolean clientReadMatchesServerWrite() {
		return serverWriteLen != 0 && serverWriteLen == clientReadLen && serverWriteStr.equals(clientReadStr);
	}
	
	
	public boolean checkClientWrite(String line) {
		if (line.startsWith(TRACER_PREFIX) && clientWriteStages[clientWriteStage].matcher(line).matches()) {
			if (clientWriteStage == CLIENT_WRITE_LEN_STAGE) {
				clientWriteLen = extractWriteLen(line);
			} else if (clientWriteStage == CLIENT_WRITE_STR_STAGE) {
				clientWriteStr = extractWriteStr(line);
			}
			clientWriteStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkServerWrite(String line) {
		if (line.startsWith(TRACER_PREFIX) && serverWriteStages[serverWriteStage].matcher(line).matches()) {
			if (serverWriteStage == SERVER_WRITE_LEN_STAGE) {
				serverWriteLen = extractWriteLen(line);
			} else if (serverWriteStage == SERVER_WRITE_STR_STAGE) {
				serverWriteStr = extractWriteStr(line);
			}
			serverWriteStage++;
			return true;
		}
		return false;
	}

	public boolean checkClientRead(String line) {
		if (line.startsWith(TRACER_PREFIX) && readStages[clientReadStage].matcher(line).matches()) {
			if (clientReadStage == READ_LEN_STAGE) {
				clientReadLen = extractReadLen(line);
			} else if (clientReadStage == READ_STR_STAGE) {
				clientReadStr = extractReadStr(line);
			}
			clientReadStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkServerRead(String line) {
		if (line.startsWith(TRACER_PREFIX) && readStages[serverReadStage].matcher(line).matches()) {
			if (serverReadStage == READ_LEN_STAGE) {
				serverReadLen = extractReadLen(line);
			} else if (serverReadStage == READ_STR_STAGE) {
				serverReadStr = extractReadStr(line);
			}
			serverReadStage++;
			return true;
		}
		return false;
	}
	
	private static int extractReadLen(String line) {
		return 0;
	}
	
	private static int extractWriteLen(String line) {
		return 0;
	}
	
	private static String extractReadStr(String line) {
		return "";
	}
	
	private static String extractWriteStr(String line) {
		return "";
	}

	@Override
	public String getLastNotFoundSource() {
		if (!isAcceptComplete()) {
			return super.getLastNotFoundSource();
		}  else if (!isClientWriteComplete()) {
			return "Client writing to server";
		} else if (serverReadStage != readStages.length) {
			return "Server reading";
		} else if (atomic) {
			if(!isServerWriteComplete()) {
				return "Server writing to client";
			} else if (!isClientReadComplete()) {
				return  "Client reading from server";
			}
		}
		return "";
	}
	
	@Override
	public String getLastNotFound() {
		if (!isAcceptComplete()) {
			return super.getLastNotFound();
		} else if (!isClientWriteComplete()) {
			return clientWriteStages[clientWriteStage].pattern();
		} else if (serverReadStage != readStages.length) {
			return readStages[serverReadStage].pattern();
		} else if (atomic) {
			if (!isServerWriteComplete()) {
				return serverWriteStages[serverWriteStage].pattern();
			} else if (!isClientReadComplete()) {
				return readStages[clientReadStage].pattern();
			}
		}
		return "";
	}
}
