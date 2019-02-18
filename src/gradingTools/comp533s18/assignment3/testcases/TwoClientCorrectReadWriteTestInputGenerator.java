package gradingTools.comp533s18.assignment3.testcases;

import java.util.regex.Pattern;

import util.trace.Tracer;

public class TwoClientCorrectReadWriteTestInputGenerator extends TwoClientCorrectConnectionTestInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = false;
	
	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String AWT_THREAD = "\\{AWT-EventQueue-.*?\\}";
	private static final String RMI_THREAD = "\\{RMI TCP Connection.*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";
	private static final String GIPC_THREAD = "\\{Asynchronous Received Call Invoker\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	
	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient0 = false;
	private boolean setupClient1 = false;
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

	private int client0NIOWriteStage = 0;
	private int client0RMIWriteStage = 0;
	private int client0GIPCWriteStage = 0;
	private int client1NIOWriteStage = 0;
	private int client1RMIWriteStage = 0;
	private int client1GIPCWriteStage = 0;
	
	private int serverNIORead0Stage = 0;
	private int serverRMIRead0Stage = 0;
	private int serverGIPCRead0Stage = 0;
	private int serverNIORead1Stage = 0;
	private int serverRMIRead1Stage = 0;
	private int serverGIPCRead1Stage = 0;
	
	private int serverNIOWrite0Stage = 0;
	private int serverRMIWrite0Stage = 0;
	private int serverGIPCWrite0Stage = 0;
	private int serverNIOWrite1Stage = 0;
	private int serverRMIWrite1Stage = 0;
	private int serverGIPCWrite1Stage = 0;
	
	private int client0NIOReadStage = 0;
	private int client0RMIReadStage = 0;
	private int client0GIPCReadStage = 0;
	private int client1NIOReadStage = 0;
	private int client1RMIReadStage = 0;
	private int client1GIPCReadStage = 0;
	
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
//			checkStr(SELECT_THREAD, "ReadsEnabled"),
//			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverNIOWriteStages = {
			checkStr(READ_THREAD, "SocketChannelWriteRequested"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "WriteRequestEnqueued"),
			checkStr(READ_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
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
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "ReadsEnabled"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
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
	
	
	public TwoClientCorrectReadWriteTestInputGenerator(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		super(doNIO, doRMI, doGIPC);
		this.atomic = atomic;
	}
	
//	private boolean nioDone0 = false;
//	private boolean nioDone1 = false;
//	private boolean rmiDone0 = false;
//	private boolean rmiDone1 = false;

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		boolean didServer = false;
		boolean didClient0 = false;
		boolean didClient1 = false;
		
		
		if (setupServer == false && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (setupClient0 == false && CLIENT_0_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_0_NAME, "a " + atomic);
			setupClient0 = true;
//			nioDone0 = false;
//			rmiDone0 = false;
		}

		if (setupClient1 == false && CLIENT_1_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_1_NAME, "a " + atomic);
			setupClient1 = true;
//			nioDone1 = false;
//			rmiDone1 = false;
		}
		// use lines for connection first
		if (aProcessName.equals(SERVER_NAME)) {
			didServer = checkServer(anOutputLine);
//			if (didServer && !commandSent && areServerAcceptsComplete()) {
//				if (doNIO()) {
//					notifyNewInputLine(CLIENT_0_NAME, "i nio");
//					notifyNewInputLine(CLIENT_1_NAME, "i nio");
//					doingNIO = true;
//				} else if (doRMI()) {
//					notifyNewInputLine(CLIENT_0_NAME, "i rmi");
//					notifyNewInputLine(CLIENT_1_NAME, "i rmi");
//					doingRMI = true;
//				} else if (doGIPC()) {
//					notifyNewInputLine(CLIENT_0_NAME, "i gipc");
//					notifyNewInputLine(CLIENT_1_NAME, "i gipc");
//					doingGIPC = true;
//				}
//				notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
//				commandSent = true;
//			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			didClient0 = checkClient0(anOutputLine);
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			didClient1 = checkClient1(anOutputLine);
		}
		if (!commandSent && (didServer || didClient0 || didClient1) && areServerAcceptsComplete() && areClientConnectsComplete()) {
			if (doNIO()) {
				notifyNewInputLine(CLIENT_0_NAME, "i nio");
				notifyNewInputLine(CLIENT_1_NAME, "i nio");
				doingNIO = true;
			} else if (doRMI()) {
				notifyNewInputLine(CLIENT_0_NAME, "i rmi");
				notifyNewInputLine(CLIENT_1_NAME, "i rmi");
				doingRMI = true;
			} else if (doGIPC()) {
				notifyNewInputLine(CLIENT_0_NAME, "i gipc");
				notifyNewInputLine(CLIENT_1_NAME, "i gipc");
				doingGIPC = true;
			}
			notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
			commandSent = true;
		}
		// read/write if not connect
		if (!didServer && aProcessName.equals(SERVER_NAME)) {
			if (doingNIO) {
				if (!isServerNIORead0Complete()) {
					checkServerNIORead0(anOutputLine);
					if (!atomic && isServerNIORead0Complete()) {
//						nioDone0 = true;
					}
				} else {
					if (!isServerNIOWrite1Complete() && checkServerNIOWrite1(anOutputLine)) {
					} else if (atomic && !isServerNIOWrite0Complete() && checkServerNIOWrite0(anOutputLine)) {
					}
				}
			} else if (doingRMI) {
				if (!isServerRMIRead0Complete()) {
					checkServerRMIRead0(anOutputLine);
					if (!atomic && isServerRMIRead0Complete()) {
//						rmiDone0 = true;
					}
				} else {
					if (!isServerRMIWrite1Complete() && checkServerRMIWrite1(anOutputLine)) {
					} else if (atomic && !isServerRMIWrite0Complete() && checkServerRMIWrite0(anOutputLine)) {
					}
				}
			} else if (doingGIPC) {
				if (!isServerGIPCRead0Complete()) {
					checkServerGIPCRead0(anOutputLine);
//					if (!atomic && isServerGIPCRead0Complete()) {
//					}
				} else {
					if (!isServerGIPCWrite1Complete() && checkServerGIPCWrite1(anOutputLine)) {
					} else if (atomic && !isServerGIPCWrite0Complete() && checkServerGIPCWrite0(anOutputLine)) {
					}
				}
			}
		} else if (!didClient0 && aProcessName.equals(CLIENT_0_NAME)) {
			if (doingNIO) {
				if (!isClient0NIOWriteComplete()) {
					checkClient0NIOWrite(anOutputLine);
				} else if (atomic && !isClient0NIOReadComplete()) {
					checkClient0NIORead(anOutputLine);
					if (isClient0NIOReadComplete()) {
//						nioDone0 = true;
					}
				}
			} else if (doingRMI) {
				if (!isClient0RMIWriteComplete()) {
					checkClient0RMIWrite(anOutputLine);
				} else if (atomic && !isClient0RMIReadComplete()) {
					checkClient0RMIRead(anOutputLine);
					if (isClient0RMIReadComplete()) {
//						rmiDone0 = true;
					}
				}
			} else if (doingGIPC) {
				if (!isClient0GIPCWriteComplete()) {
					checkClient0GIPCWrite(anOutputLine);
				} else if (atomic && !isClient0GIPCReadComplete()) {
					checkClient0GIPCRead(anOutputLine);
					if (isClient0GIPCReadComplete() && isClient1GIPCReadComplete()) {
//						doingGIPC = false;
					}
				}
			}
		} else if (!didClient1 && aProcessName.equals(CLIENT_1_NAME)) {
			if (doingNIO) {
				if (!isClient1NIOReadComplete()) {
					checkClient1NIORead(anOutputLine);
					if (isClient1NIOReadComplete()) {
//						nioDone1 = true;
					}
				}
			} else if (doingRMI) {
				if (!isClient1RMIReadComplete()) {
					checkClient1RMIRead(anOutputLine);
					if (isClient1RMIReadComplete()) {
//						rmiDone1 = true;
					}
				}
			} else if (doingGIPC) {
				if (!isClient1GIPCReadComplete()) {
					checkClient1GIPCRead(anOutputLine);
					if (isClient1GIPCReadComplete() && (!atomic || isClient0GIPCReadComplete())) {
//						doingGIPC = false;
					}
				}
			}
		}
		if (doingNIO && isNIODone()) {
			doingNIO = false;
			if (doRMI()) {
				notifyNewInputLine(CLIENT_0_NAME, "i rmi");
				notifyNewInputLine(CLIENT_1_NAME, "i rmi");
				notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
				doingRMI = true;
			} else if (doGIPC()) {
				notifyNewInputLine(CLIENT_0_NAME, "i gipc");
				notifyNewInputLine(CLIENT_1_NAME, "i gipc");
				notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
				doingGIPC = true;
			}
//			nioDone0 = false;
//			nioDone1 = false;
		} else if (doingRMI && isRMIDone()) {
			doingRMI = false;
			if (doGIPC()) {
				notifyNewInputLine(CLIENT_0_NAME, "i gipc");
				notifyNewInputLine(CLIENT_1_NAME, "i gipc");
				notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
				doingGIPC = true;
			}
//			rmiDone0 = false;
//			rmiDone1 = false;
		} else if (doingGIPC && isGIPCDone()) {
			doingGIPC = false;
		}
		if (!quitSubmitted && (atomic ? areClientReadsComplete() : areServerReadsComplete())) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isNIO0Done() {
		return isClient0NIOWriteComplete() && isServerNIORead0Complete() && (!atomic || (isServerNIOWrite0Complete() && isClient0NIOReadComplete()));
	}
	
	public boolean isNIO1Done() {
		return isServerNIOWrite1Complete() && isClient1NIOReadComplete();
	}
	
	public boolean isNIODone() {
		return isNIO0Done() && isNIO1Done();
	}
	
	public boolean isRMI0Done() {
		return isClient0RMIWriteComplete() && isServerRMIRead0Complete() && (!atomic || (isServerRMIWrite0Complete() && isClient0RMIReadComplete()));
	}
	
	public boolean isRMI1Done() {
		return isServerRMIWrite1Complete() && isClient1RMIReadComplete();
	}
	
	public boolean isRMIDone() {
		return isRMI0Done() && isRMI1Done();
	}
	
	public boolean isGIPC0Done() {
		return isClient0GIPCWriteComplete() && isServerGIPCRead0Complete() && (!atomic || (isServerGIPCWrite0Complete() && isClient0GIPCReadComplete()));
	}
	
	public boolean isGIPC1Done() {
		return isServerGIPCWrite1Complete() && isClient1GIPCReadComplete();
	}
	
	public boolean isGIPCDone() {
		return isGIPC0Done() && isGIPC1Done();
	}
	
	public boolean areClientReadsComplete() {
		return areClient0ReadsComplete() && areClient1ReadsComplete();
	}

	public boolean areClient0ReadsComplete() {
		return (!doNIO() || isClient0NIOReadComplete()) && (!doRMI() || isClient0RMIReadComplete()) && (!doGIPC() || isClient0GIPCReadComplete());
	}
	
	public boolean areClient1ReadsComplete() {
		return (!doNIO() || isClient1NIOReadComplete()) && (!doRMI() || isClient1RMIReadComplete()) && (!doGIPC() || isClient1GIPCReadComplete());
	}
	
	public boolean areServerReadsComplete() {
		return areServerReads0Complete() && areServerReads1Complete();
	}
	
	public boolean areServerReads0Complete() {
		return (!doNIO() || isServerNIORead0Complete()) && (!doRMI() || isServerRMIRead0Complete()) && (!doGIPC() || isServerGIPCRead0Complete());
	}
	
	public boolean areServerReads1Complete() {
		return (!doNIO() || isServerNIORead1Complete()) && (!doRMI() || isServerRMIRead1Complete()) && (!doGIPC() || isServerGIPCRead1Complete());
	}
	
	public boolean isClient0NIOWriteComplete() {
		return client0NIOWriteStage == clientNIOWriteStages.length;
	}
	
	public boolean isClient1NIOWriteComplete() {
		return client1NIOWriteStage == clientNIOWriteStages.length;
	}
	
	public boolean isClient0RMIWriteComplete() {
		return client0RMIWriteStage == clientRMIWriteStages.length;
	}
	
	public boolean isClient1RMIWriteComplete() {
		return client1RMIWriteStage == clientRMIWriteStages.length;
	}
	
	public boolean isClient0GIPCWriteComplete() {
		return client0GIPCWriteStage == clientGIPCWriteStages.length;
	}
	
	public boolean isClient1GIPCWriteComplete() {
		return client1GIPCWriteStage == clientGIPCWriteStages.length;
	}
	
	public boolean isServerNIOWrite0Complete() {
		return serverNIOWrite0Stage == serverNIOWriteStages.length;
	}
	
	public boolean isServerNIOWrite1Complete() {
		return serverNIOWrite1Stage == serverNIOWriteStages.length;
	}
	
	public boolean isServerRMIWrite0Complete() {
		return serverRMIWrite0Stage == serverRMIWriteStages.length;
	}
	
	public boolean isServerRMIWrite1Complete() {
		return serverRMIWrite1Stage == serverRMIWriteStages.length;
	}
	
	public boolean isServerGIPCWrite0Complete() {
		return serverGIPCWrite0Stage == serverGIPCWriteStages.length;
	}
	
	public boolean isServerGIPCWrite1Complete() {
		return serverGIPCWrite1Stage == serverGIPCWriteStages.length;
	}

	public boolean isClient0NIOReadComplete() {
		return client0NIOReadStage == nioReadStages.length;
	}

	public boolean isClient1NIOReadComplete() {
		return client1NIOReadStage == nioReadStages.length;
	}

	public boolean isClient0RMIReadComplete() {
		return client0RMIReadStage == clientRMIReadStages.length;
	}

	public boolean isClient1RMIReadComplete() {
		return client1RMIReadStage == clientRMIReadStages.length;
	}
	
	public boolean isClient0GIPCReadComplete() {
		return client0GIPCReadStage == clientGIPCReadStages.length;
	}
	
	public boolean isClient1GIPCReadComplete() {
		return client1GIPCReadStage == clientGIPCReadStages.length;
	}
	
	public boolean isServerNIORead0Complete() {
		return serverNIORead0Stage == nioReadStages.length;
	}
	
	public boolean isServerNIORead1Complete() {
		return serverNIORead1Stage == nioReadStages.length;
	}
	
	public boolean isServerRMIRead0Complete() {
		return serverRMIRead0Stage == serverRMIReadStages.length;
	}
	
	public boolean isServerRMIRead1Complete() {
		return serverRMIRead1Stage == serverRMIReadStages.length;
	}
	
	public boolean isServerGIPCRead0Complete() {
		return serverGIPCRead0Stage == serverGIPCReadStages.length;
	}
	
	public boolean isServerGIPCRead1Complete() {
		return serverGIPCRead1Stage == serverGIPCReadStages.length;
	}
	
//	public boolean serverCanReadNIO() {
//		return clientNIOWriteStage >= 9;
//	}
//
//	public boolean clientCanReadNIO() {
//		return serverNIOWriteStage >= 8;
//	}
//	
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
	
	public boolean checkClient0NIOWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientNIOWriteStages[client0NIOWriteStage].pattern());
			}
			if (clientNIOWriteStages[client0NIOWriteStage].matcher(line).matches()) {
				if (client0NIOWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client0NIOWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client0NIOWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClient1NIOWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientNIOWriteStages[client1NIOWriteStage].pattern());
			}
			if (clientNIOWriteStages[client1NIOWriteStage].matcher(line).matches()) {
				if (client1NIOWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client1NIOWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client1NIOWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClient0RMIWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIWriteStages[client0RMIWriteStage].pattern());
			}
			if (clientRMIWriteStages[client0RMIWriteStage].matcher(line).matches()) {
				if (client0RMIWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client0RMIWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client0RMIWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClient1RMIWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIWriteStages[client1RMIWriteStage].pattern());
			}
			if (clientRMIWriteStages[client1RMIWriteStage].matcher(line).matches()) {
				if (client1RMIWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client1RMIWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client1RMIWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClient0GIPCWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCWriteStages[client0GIPCWriteStage].pattern());
			}
			if (clientGIPCWriteStages[client0GIPCWriteStage].matcher(line).matches()) {
				if (client0GIPCWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client0GIPCWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client0GIPCWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClient1GIPCWrite(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCWriteStages[client1GIPCWriteStage].pattern());
			}
			if (clientGIPCWriteStages[client1GIPCWriteStage].matcher(line).matches()) {
				if (client1GIPCWriteStage == CLIENT_WRITE_LEN_STAGE) {
					clientWriteLen = extractWriteLen(line);
				} else if (client1GIPCWriteStage == CLIENT_WRITE_STR_STAGE) {
					clientWriteStr = extractWriteStr(line);
				}
				client1GIPCWriteStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIOWrite0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOWriteStages[serverNIOWrite0Stage].pattern());
			}
			if (serverNIOWriteStages[serverNIOWrite0Stage].matcher(line).matches()) {
				if (serverNIOWrite0Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverNIOWrite0Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverNIOWrite0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIOWrite1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOWriteStages[serverNIOWrite1Stage].pattern());
			}
			if (serverNIOWriteStages[serverNIOWrite1Stage].matcher(line).matches()) {
				if (serverNIOWrite1Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverNIOWrite1Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverNIOWrite1Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIWrite0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIWriteStages[serverRMIWrite0Stage].pattern());
			}
			if (serverRMIWriteStages[serverRMIWrite0Stage].matcher(line).matches()) {
				if (serverRMIWrite0Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverRMIWrite0Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverRMIWrite0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIWrite1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIWriteStages[serverRMIWrite1Stage].pattern());
			}
			if (serverRMIWriteStages[serverRMIWrite1Stage].matcher(line).matches()) {
				if (serverRMIWrite1Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverRMIWrite1Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverRMIWrite1Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCWrite0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCWriteStages[serverGIPCWrite0Stage].pattern());
			}
			if (serverGIPCWriteStages[serverGIPCWrite0Stage].matcher(line).matches()) {
				if (serverGIPCWrite0Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverGIPCWrite0Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverGIPCWrite0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCWrite1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCWriteStages[serverGIPCWrite1Stage].pattern());
			}
			if (serverGIPCWriteStages[serverGIPCWrite1Stage].matcher(line).matches()) {
				if (serverGIPCWrite1Stage == SERVER_WRITE_LEN_STAGE) {
					serverWriteLen = extractWriteLen(line);
				} else if (serverGIPCWrite1Stage == SERVER_WRITE_STR_STAGE) {
					serverWriteStr = extractWriteStr(line);
				}
				serverGIPCWrite1Stage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient0NIORead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[client0NIOReadStage].pattern());
			}
			if (nioReadStages[client0NIOReadStage].matcher(line).matches()) {
				if (client0NIOReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client0NIOReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client0NIOReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient1NIORead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[client1NIOReadStage].pattern());
			}
			if (nioReadStages[client1NIOReadStage].matcher(line).matches()) {
				if (client1NIOReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client1NIOReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client1NIOReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient0RMIRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIReadStages[client0RMIReadStage].pattern());
			}
			if (clientRMIReadStages[client0RMIReadStage].matcher(line).matches()) {
				if (client0RMIReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client0RMIReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client0RMIReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient1RMIRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientRMIReadStages[client1RMIReadStage].pattern());
			}
			if (clientRMIReadStages[client1RMIReadStage].matcher(line).matches()) {
				if (client1RMIReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client1RMIReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client1RMIReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient0GIPCRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCReadStages[client0GIPCReadStage].pattern());
			}
			if (clientGIPCReadStages[client0GIPCReadStage].matcher(line).matches()) {
				if (client0GIPCReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client0GIPCReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client0GIPCReadStage++;
				return true;
			}
		}
		return false;
	}

	public boolean checkClient1GIPCRead(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + clientGIPCReadStages[client1GIPCReadStage].pattern());
			}
			if (clientGIPCReadStages[client1GIPCReadStage].matcher(line).matches()) {
				if (client1GIPCReadStage == READ_LEN_STAGE) {
					clientReadLen = extractReadLen(line);
				} else if (client1GIPCReadStage == READ_STR_STAGE) {
					clientReadStr = extractReadStr(line);
				}
				client1GIPCReadStage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIORead0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[serverNIORead0Stage].pattern());
			}
			if (nioReadStages[serverNIORead0Stage].matcher(line).matches()) {
				if (serverNIORead0Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverNIORead0Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverNIORead0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIORead1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + nioReadStages[serverNIORead1Stage].pattern());
			}
			if (nioReadStages[serverNIORead1Stage].matcher(line).matches()) {
				if (serverNIORead1Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverNIORead1Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverNIORead1Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIRead0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIReadStages[serverRMIRead0Stage].pattern());
			}
			if (serverRMIReadStages[serverRMIRead0Stage].matcher(line).matches()) {
				if (serverRMIRead0Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverRMIRead0Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverRMIRead0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerRMIRead1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMIReadStages[serverRMIRead1Stage].pattern());
			}
			if (serverRMIReadStages[serverRMIRead1Stage].matcher(line).matches()) {
				if (serverRMIRead1Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverRMIRead1Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverRMIRead1Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCRead0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCReadStages[serverGIPCRead0Stage].pattern());
			}
			if (serverGIPCReadStages[serverGIPCRead0Stage].matcher(line).matches()) {
				if (serverGIPCRead0Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverGIPCRead0Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverGIPCRead0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCRead1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverGIPCReadStages[serverGIPCRead1Stage].pattern());
			}
			if (serverGIPCReadStages[serverGIPCRead1Stage].matcher(line).matches()) {
				if (serverGIPCRead1Stage == READ_LEN_STAGE) {
					serverReadLen = extractReadLen(line);
				} else if (serverGIPCRead1Stage == READ_STR_STAGE) {
					serverReadStr = extractReadStr(line);
				}
				serverGIPCRead1Stage++;
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
		if (!areServerAcceptsComplete() || !areClientConnectsComplete() || !isServerSetupComplete()) {
			return super.getLastNotFoundSource();
		}
		if (doNIO()) {
			if(!isClient0NIOWriteComplete()) {
				return "Client 0 writing to server via NIO";
			} else if (!isServerNIORead0Complete()) {
				return "Server reading via NIO";
			} else if(!isServerNIOWrite1Complete()) {
				return "Server writing to client 1 via NIO";
			} else if (!isClient1NIOReadComplete()) {
				return  "Client 1 reading from server via NIO";
			} else if (atomic) {
				if(!isServerNIOWrite0Complete()) {
					return "Server writing to client 0 via NIO";
				} else if (!isClient0NIOReadComplete()) {
					return  "Client 0 reading from server via NIO";
				}
			}
		}
		if (doRMI()) {
			if(!isClient0RMIWriteComplete()) {
				return "Client 0 writing to server via RMI";
			} else if (!isServerRMIRead0Complete()) {
				return "Server reading via RMI";
			} else if(!isServerRMIWrite1Complete()) {
				return "Server writing to client 1 via RMI";
			} else if (!isClient1RMIReadComplete()) {
				return  "Client 1 reading from server via RMI";
			} else if (atomic) {
				if(!isServerRMIWrite0Complete()) {
					return "Server writing to client 0 via RMI";
				} else if (!isClient0RMIReadComplete()) {
					return  "Client 1 reading from server via RMI";
				}
			}
		}
		if (doGIPC()) {
			if(!isClient0GIPCWriteComplete()) {
				return "Client 0 writing to server via GIPC";
			} else if (!isServerGIPCRead0Complete()) {
				return "Server reading via GIPC";
			} else if(!isServerGIPCWrite1Complete()) {
				return "Server writing to client 1 via GIPC";
			} else if (!isClient1GIPCReadComplete()) {
				return  "Client 1 reading from server via GIPC";
			} else if (atomic) {
				if(!isServerGIPCWrite0Complete()) {
					return "Server writing to client 0 via GIPC";
				} else if (!isClient0GIPCReadComplete()) {
					return  "Client 0 reading from server via GIPC";
				}
			}
		}
		return "";
	}
	
	@Override
	public String getLastNotFound() {
		if (!areServerAcceptsComplete() || !areClientConnectsComplete() || !isServerSetupComplete()) {
			return super.getLastNotFound();
		}
		if (doNIO()) {
			if (!isClient0NIOWriteComplete()) {
				return clientNIOWriteStages[client0NIOWriteStage].pattern();
			} else if (!isServerNIORead0Complete()) {
				return nioReadStages[serverNIORead0Stage].pattern();
			} else if (!isServerNIOWrite1Complete()) {
				return serverNIOWriteStages[serverNIOWrite1Stage].pattern();
			} else if (!isClient1NIOReadComplete()) {
				return nioReadStages[client1NIOReadStage].pattern();
			} else if (atomic) {
				if (!isServerNIOWrite0Complete()) {
					return serverNIOWriteStages[serverNIOWrite0Stage].pattern();
				} else if (!isClient0NIOReadComplete()) {
					return nioReadStages[client0NIOReadStage].pattern();
				}
			}
		}
		if (doRMI()) {
			if (!isClient0RMIWriteComplete()) {
				return clientRMIWriteStages[client0RMIWriteStage].pattern();
			} else if (!isServerRMIRead0Complete()) {
				return serverRMIReadStages[serverRMIRead0Stage].pattern();
			} else if (!isServerRMIWrite1Complete()) {
				return serverRMIWriteStages[serverRMIWrite1Stage].pattern();
			} else if (!isClient1RMIReadComplete()) {
				return clientRMIReadStages[client1RMIReadStage].pattern();
			} else if (atomic) {
				if (!isServerRMIWrite0Complete()) {
					return serverRMIWriteStages[serverRMIWrite0Stage].pattern();
				} else if (!isClient0RMIReadComplete()) {
					return clientRMIReadStages[client0RMIReadStage].pattern();
				}
			}
		}
		if (doGIPC()) {
			if (!isClient0GIPCWriteComplete()) {
				return clientGIPCWriteStages[client0GIPCWriteStage].pattern();
			} else if (!isServerGIPCRead0Complete()) {
				return serverGIPCReadStages[serverGIPCRead0Stage].pattern();
			} else if (!isServerGIPCWrite1Complete()) {
				return serverGIPCWriteStages[serverGIPCWrite1Stage].pattern();
			} else if (!isClient1GIPCReadComplete()) {
				return clientGIPCReadStages[client1GIPCReadStage].pattern();
			} else if (atomic) {
				if (!isServerGIPCWrite0Complete()) {
					return serverGIPCWriteStages[serverGIPCWrite0Stage].pattern();
				} else if (!isClient0GIPCReadComplete()) {
					return clientGIPCReadStages[client0GIPCReadStage].pattern();
				}
			}
		}
		return "";
	}
}
