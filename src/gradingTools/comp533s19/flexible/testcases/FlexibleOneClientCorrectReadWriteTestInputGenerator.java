package gradingTools.comp533s19.flexible.testcases;

import java.util.regex.Pattern;

import gradingTools.comp533s19.flexible.testcases.FlexibleOneClientCorrectConnectionTestInputGenerator;
import util.trace.Tracer;

public class FlexibleOneClientCorrectReadWriteTestInputGenerator extends FlexibleOneClientCorrectConnectionTestInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = false;

	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String AWT_THREAD = "\\{AWT-EventQueue-.*?\\}";
	private static final String RMI_THREAD = "\\{RMI TCP Connection.*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	private static final String GIPC_THREAD = "\\{Asynchronous Received Call Invoker\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient = false;
	private boolean commandSent = false;
	private boolean quitSubmitted = false;
	
	private boolean doingNIO = false;
	private boolean doingRMI = false;
	private boolean doingGIPC = false;

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

	private int clientNIOWriteStage = 0;
	private int clientRMIWriteStage = 0;
	private int clientGIPCWriteStage = 0;
	private int serverNIOWriteStage = 0;
	private int serverRMIWriteStage = 0;
	private int serverGIPCWriteStage = 0;
	private int clientNIOReadStage = 0;
	private int clientRMIReadStage = 0;
	private int clientGIPCReadStage = 0;
	private int serverNIOReadStage = 0;
	private int serverRMIReadStage = 0;
	private int serverGIPCReadStage = 0;
	
	private static final int CLIENT_WRITE_LEN_STAGE = 0;//3;
	private static final int CLIENT_WRITE_STR_STAGE = 0;//4;
	
	private static final int SERVER_WRITE_LEN_STAGE = 0;//1;
	private static final int SERVER_WRITE_STR_STAGE = 0;//2;

	private static final int READ_LEN_STAGE = 0;//2;
	private static final int READ_STR_STAGE = 0;//3;

	private static final Pattern[] clientNIOWriteStages = {
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
	
	private static final Pattern[] clientRMIWriteStages = {
			checkStr(MAIN_THREAD, "NotifiedPropertyChangeEvent"),
			checkStr(MAIN_THREAD, "RemoteProposeRequestSent")
	};
	
	private static final Pattern[] clientGIPCWriteStages = {
			checkStr(MAIN_THREAD, "NotifiedPropertyChangeEvent"),
			checkStr(MAIN_THREAD, "RemoteProposeRequestSent"),
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
			checkStr(SELECT_THREAD, "ReadsEnabled"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverNIOWriteStages = {
			checkStr(READ_THREAD, "SocketChannelWriteRequested"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty")
//			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverRMIWriteStages = {
			checkStr(RMI_THREAD, "ProposalLearnedNotificationSent")
	};
	
	private static final Pattern[] serverGIPCWriteStages = {
			checkStr(GIPC_THREAD, "ProposalLearnedNotificationSent"),
			checkStr(GIPC_THREAD, "SocketChannelWriteRequested"),
			checkStr(GIPC_THREAD, "WriteRequestEnqueued"),
			checkStr(GIPC_THREAD, "WriteRequestEnqueued"),
			checkStr(GIPC_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty")
//			checkStr(SELECT_THREAD, "ReadsEnabled"),
//			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
//			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] nioReadStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead")
//			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverRMIReadStages = {
//			checkStr(RMI_THREAD, "RemoteProposeRequestReceived"),
			checkStr(RMI_THREAD, "RemoteProposeRequestReceived")
	};

	
	private static final Pattern[] clientRMIReadStages = {
			checkStr(RMI_THREAD, "ProposalLearnedNotificationReceived")
	};
	
	private static final Pattern[] serverGIPCReadStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(GIPC_THREAD, "ReceivedCallInitiated"),
//			checkStr(GIPC_THREAD, "RemoteProposeRequestReceived"),
			checkStr(GIPC_THREAD, "RemoteProposeRequestReceived")
	};
	
	private static final Pattern[] clientGIPCReadStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(GIPC_THREAD, "ReceivedCallInitiated"),
			checkStr(GIPC_THREAD, "ProposalLearnedNotificationReceived")
	};
	 
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	
	public FlexibleOneClientCorrectReadWriteTestInputGenerator(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		super(doNIO, doRMI, doGIPC);
		this.atomic = atomic;
	}
	

	@Override
	public synchronized void newOutputLine(String aProcessName, String anOutputLine) {
		boolean didServer = false;
		boolean didClient = false;
		boolean nioDone = false;
		boolean rmiDone = false;
		if (!setupServer && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (!setupClient && CLIENT_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_NAME, "a " + atomic);
			setupClient = true;
		}
		// use lines for connection first
		if (aProcessName.equals(SERVER_NAME)) {
			didServer = checkServer(anOutputLine);
//			if (didServer && !commandSent && isServerAcceptComplete() && isClientConnectComplete()) {
//				if (doNIO()) {
//					notifyNewInputLine(CLIENT_NAME, "i nio");
//					doingNIO = true;
//				} else if (doRMI()) {
//					notifyNewInputLine(CLIENT_NAME, "i rmi");
//					doingRMI = true;
//				} else if (doGIPC()) {
//					notifyNewInputLine(CLIENT_NAME, "i gipc");
//					doingGIPC = true;
//				}
//				notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
//				commandSent = true;
//			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			didClient = checkClient(anOutputLine);
		}
		if (!commandSent && (didServer || didClient) && isServerAcceptComplete() && isClientConnectComplete()) {
			commandSent = true;
			if (doNIO()) {
				doingNIO = true;
				notifyNewInputLine(CLIENT_NAME, "i nio");
			} else if (doRMI()) {
				doingRMI = true;
				notifyNewInputLine(CLIENT_NAME, "i rmi");
			} else if (doGIPC()) {
				doingGIPC = true;
				notifyNewInputLine(CLIENT_NAME, "i gipc");
			}
			notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
		}
//		System.err.println("+++ " + aProcessName + " " + doNIO() + " " + didClient + " " + doingNIO + " " + isServerAcceptComplete() + " " + isClientConnectComplete() + " " + isClientNIOWriteComplete() + " " + anOutputLine);
		// read/write if not connect
		if (!didServer && aProcessName.equals(SERVER_NAME)) {
			if (doingNIO) {
				if (!isServerNIOReadComplete()) {
					checkServerNIORead(anOutputLine);
					if (!atomic && isServerNIOReadComplete()) {
					}
				} else if (atomic && !isServerNIOWriteComplete()) {
					checkServerNIOWrite(anOutputLine);
				}
			} else if (doingRMI) {
				if (!isServerRMIReadComplete()) {
					checkServerRMIRead(anOutputLine);
					if (!atomic && isServerRMIReadComplete()) {
					}
				} else if (atomic && !isServerRMIWriteComplete()) {
					checkServerRMIWrite(anOutputLine);
				}
			} else if (doingGIPC) {
				if (!isServerGIPCReadComplete()) {
					checkServerGIPCRead(anOutputLine);
					if (!atomic && isServerGIPCReadComplete()) {
					}
				} else if (atomic && !isServerGIPCWriteComplete()) {
					checkServerGIPCWrite(anOutputLine);
				}
			}
		} else if (!didClient && aProcessName.equals(CLIENT_NAME)) {
//			System.err.println("=== FROM CLIENT");
			if (doingNIO) {
//				System.err.println("=== DOING NIO");
				if (!isClientNIOWriteComplete()) {
					checkClientNIOWrite(anOutputLine);
				} else if (atomic && !isClientNIOReadComplete()) {
					checkClientNIORead(anOutputLine);
					if (isClientNIOReadComplete()) {
					}
				}
			} else if (doingRMI) {
				if (!isClientRMIWriteComplete()) {
					checkClientRMIWrite(anOutputLine);
				} else if (atomic && !isClientRMIReadComplete()) {
					checkClientRMIRead(anOutputLine);
					if (isClientRMIReadComplete()) {
					}
				}
			} else if (doingGIPC) {
				if (!isClientGIPCWriteComplete()) {
					checkClientGIPCWrite(anOutputLine);
				} else if (atomic && !isClientGIPCReadComplete()) {
					checkClientGIPCRead(anOutputLine);
					if (isClientGIPCReadComplete()) {
					}
				}
			}
		}
		if (doingNIO && isNIODone()) {
			doingNIO = false;
			if (doRMI()) {
				notifyNewInputLine(CLIENT_NAME, "i rmi");
				notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
				doingRMI = true;
			} else if (doGIPC()) {
				notifyNewInputLine(CLIENT_NAME, "i gipc");
				notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
				doingGIPC = true;
			}
		} else if (doingRMI && isRMIDone()) {
			doingRMI = false;
			if (doGIPC()) {
				notifyNewInputLine(CLIENT_NAME, "i gipc");
				notifyNewInputLine(CLIENT_NAME, "s \"move 0 10\"");
				doingGIPC = true;
			}
		} else if (doingGIPC && isGIPCDone()) {
			doingGIPC = false;
		}
		if (!quitSubmitted && (atomic ? areClientReadsComplete() : areServerReadsComplete())) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isNIODone() {
		return isClientNIOWriteComplete() && isServerNIOReadComplete() && (!atomic || (isServerNIOWriteComplete() && isClientNIOReadComplete()));
	}
	
	public boolean isRMIDone() {
		return isClientRMIWriteComplete() && isServerRMIReadComplete() && (!atomic || (isServerRMIWriteComplete() && isClientRMIReadComplete()));
	}
	
	public boolean isGIPCDone() {
		return isClientGIPCWriteComplete() && isServerGIPCReadComplete() && (!atomic || (isServerGIPCWriteComplete() && isClientGIPCReadComplete()));
	}
	
	public boolean areClientReadsComplete() {
		return (!doNIO() || isClientNIOReadComplete()) && (!doRMI() || isClientRMIReadComplete()) && (!doGIPC() || isClientGIPCReadComplete());
	}
	
	public boolean areServerReadsComplete() {
		return (!doNIO() || isServerNIOReadComplete()) && (!doRMI() || isServerRMIReadComplete()) && (!doGIPC() || isServerGIPCReadComplete());
	}
	
	public boolean isClientNIOWriteComplete() {
		return clientNIOWriteStage == clientNIOWriteStages.length;
	}
	
	public boolean isClientRMIWriteComplete() {
		return clientRMIWriteStage == clientRMIWriteStages.length;
	}
	
	public boolean isClientGIPCWriteComplete() {
		return clientGIPCWriteStage == clientGIPCWriteStages.length;
	}
	
	public boolean isServerNIOWriteComplete() {
		return serverNIOWriteStage == serverNIOWriteStages.length;
	}
	
	public boolean isServerRMIWriteComplete() {
		return serverRMIWriteStage == serverRMIWriteStages.length;
	}
	
	public boolean isServerGIPCWriteComplete() {
		return serverGIPCWriteStage == serverGIPCWriteStages.length;
	}

	public boolean isClientNIOReadComplete() {
		return clientNIOReadStage == nioReadStages.length;
	}

	public boolean isClientRMIReadComplete() {
		return clientRMIReadStage == clientRMIReadStages.length;
	}
	
	public boolean isClientGIPCReadComplete() {
		return clientGIPCReadStage == clientGIPCReadStages.length;
	}
	
	public boolean isServerNIOReadComplete() {
		return serverNIOReadStage == nioReadStages.length;
	}
	
	public boolean isServerRMIReadComplete() {
		return serverRMIReadStage == serverRMIReadStages.length;
	}
	
	public boolean isServerGIPCReadComplete() {
		return serverGIPCReadStage == serverGIPCReadStages.length;
	}
	
	public boolean serverCanReadNIO() {
		return clientNIOWriteStage >= 9;
	}

	public boolean clientCanReadNIO() {
		return serverNIOWriteStage >= 8;
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
	
	public boolean checkClientNIOWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientNIOWriteStages[clientNIOWriteStage].pattern());
			}
			if (clientNIOWriteStages[clientNIOWriteStage].matcher(line).matches()) {
				if (clientNIOWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (clientNIOWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				clientNIOWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClientRMIWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIWriteStages[clientRMIWriteStage].pattern());
			}
			if (clientRMIWriteStages[clientRMIWriteStage].matcher(line).matches()) {
				if (clientRMIWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (clientRMIWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				clientRMIWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClientGIPCWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCWriteStages[clientGIPCWriteStage].pattern());
			}
			if (clientGIPCWriteStages[clientGIPCWriteStage].matcher(line).matches()) {
				if (clientGIPCWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (clientGIPCWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				clientGIPCWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIOWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOWriteStages[serverNIOWriteStage].pattern());
			}
			if (serverNIOWriteStages[serverNIOWriteStage].matcher(line).matches()) {
				if (serverNIOWriteStage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverNIOWriteStage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverNIOWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIWriteStages[serverRMIWriteStage].pattern());
			}
			if (serverRMIWriteStages[serverRMIWriteStage].matcher(line).matches()) {
				if (serverRMIWriteStage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverRMIWriteStage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverRMIWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCWriteStages[serverGIPCWriteStage].pattern());
			}
			if (serverGIPCWriteStages[serverGIPCWriteStage].matcher(line).matches()) {
				if (serverGIPCWriteStage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverGIPCWriteStage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverGIPCWriteStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClientNIORead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[clientNIOReadStage].pattern());
			}
			if (nioReadStages[clientNIOReadStage].matcher(line).matches()) {
				if (clientNIOReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (clientNIOReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				clientNIOReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClientRMIRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIReadStages[clientRMIReadStage].pattern());
			}
			if (clientRMIReadStages[clientRMIReadStage].matcher(line).matches()) {
				if (clientRMIReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (clientRMIReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				clientRMIReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClientGIPCRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCReadStages[clientGIPCReadStage].pattern());
			}
			if (clientGIPCReadStages[clientGIPCReadStage].matcher(line).matches()) {
				if (clientGIPCReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (clientGIPCReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				clientGIPCReadStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIORead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[serverNIOReadStage].pattern());
			}
			if (nioReadStages[serverNIOReadStage].matcher(line).matches()) {
				if (serverNIOReadStage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverNIOReadStage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverNIOReadStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIReadStages[serverRMIReadStage].pattern());
			}
			if (serverRMIReadStages[serverRMIReadStage].matcher(line).matches()) {
				if (serverRMIReadStage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverRMIReadStage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverRMIReadStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCReadStages[serverGIPCReadStage].pattern());
			}
			if (serverGIPCReadStages[serverGIPCReadStage].matcher(line).matches()) {
				if (serverGIPCReadStage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverGIPCReadStage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverGIPCReadStage++;
				return true;
			}
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
		if (!isServerAcceptComplete() || !isClientConnectComplete() || !isServerSetupComplete()) {
			return super.getLastNotFoundSource();
		}
		if (doNIO()) {
			if(!isClientNIOWriteComplete()) {
				return "Client writing to server via NIO";
			} else if (!isServerNIOReadComplete()) {
				return "Server reading via NIO";
			} else if (atomic) {
				if(!isServerNIOWriteComplete()) {
					return "Server writing to client via NIO";
				} else if (!isClientNIOReadComplete()) {
					return  "Client reading from server via NIO";
				}
			}
		}
		if (doRMI()) {
			if(!isClientRMIWriteComplete()) {
				return "Client writing to server via RMI";
			} else if (!isServerRMIReadComplete()) {
				return "Server reading via RMI";
			} else if (atomic) {
				if(!isServerRMIWriteComplete()) {
					return "Server writing to client via RMI";
				} else if (!isClientRMIReadComplete()) {
					return  "Client reading from server via RMI";
				}
			}
		}
		if (doGIPC()) {
			if(!isClientGIPCWriteComplete()) {
				return "Client writing to server via GIPC";
			} else if (!isServerGIPCReadComplete()) {
				return "Server reading via GIPC";
			} else if (atomic) {
				if(!isServerGIPCWriteComplete()) {
					return "Server writing to client via GIPC";
				} else if (!isClientGIPCReadComplete()) {
					return  "Client reading from server via GIPC";
				}
			}
		}
		return "";
	}
	
	@Override
	public String getLastNotFound() {
		if (!isServerAcceptComplete() || !isClientConnectComplete() || !isServerSetupComplete()) {
			return super.getLastNotFound();
		}
		if (doNIO()) {
			if (!isClientNIOWriteComplete()) {
				return clientNIOWriteStages[clientNIOWriteStage].pattern();
			} else if (!isServerNIOReadComplete()) {
				return nioReadStages[serverNIOReadStage].pattern();
			} else if (atomic) {
				if (!isServerNIOWriteComplete()) {
					return serverNIOWriteStages[serverNIOWriteStage].pattern();
				} else if (!isClientNIOReadComplete()) {
					return nioReadStages[clientNIOReadStage].pattern();
				}
			}
		}
		if (doRMI()) {
			if (!isClientRMIWriteComplete()) {
				return clientRMIWriteStages[clientRMIWriteStage].pattern();
			} else if (!isServerRMIReadComplete()) {
				return serverRMIReadStages[serverRMIReadStage].pattern();
			} else if (atomic) {
				if (!isServerRMIWriteComplete()) {
					return serverRMIWriteStages[serverRMIWriteStage].pattern();
				} else if (!isClientRMIReadComplete()) {
					return clientRMIReadStages[clientRMIReadStage].pattern();
				}
			}
		}
		if (doGIPC()) {
			if (!isClientGIPCWriteComplete()) {
				return clientGIPCWriteStages[clientGIPCWriteStage].pattern();
			} else if (!isServerGIPCReadComplete()) {
				return serverGIPCReadStages[serverGIPCReadStage].pattern();
			} else if (atomic) {
				if (!isServerGIPCWriteComplete()) {
					return serverGIPCWriteStages[serverGIPCWriteStage].pattern();
				} else if (!isClientGIPCReadComplete()) {
					return clientGIPCReadStages[clientGIPCReadStage].pattern();
				}
			}
		}
		return "";
	}
}
