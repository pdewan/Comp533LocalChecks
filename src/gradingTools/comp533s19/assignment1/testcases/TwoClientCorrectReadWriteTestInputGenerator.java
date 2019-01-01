package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class TwoClientCorrectReadWriteTestInputGenerator extends TwoClientCorrectConnectionTestInputGenerator {
	private static final String TRACER_PREFIX = "I***";

	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String AWT_THREAD = "\\{AWT-EventQueue-.*?\\}";
	private static final String READ_THREAD = "\\{Read Thread\\}";

	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";

	private boolean atomic;
	private boolean setupServer = false;
	private boolean setupClient0 = false;
	private boolean setupClient1 = false;
	private boolean commandSent = false;
	private boolean quitSubmitted = false;

	private boolean client0Writing = false;
	private boolean client1Writing = false;

	private int correctLen;
	private String correctStr;

	private int clientWriteLen;
	private String clientWriteStr;

	private int serverReadLen;
	private String serverReadStr;

	private int serverWrite0Len;
	private String serverWrite0Str;

	private int serverWrite1Len;
	private String serverWrite1Str;

	private int client0ReadLen;
	private String client0ReadStr;

	private int client1ReadLen;
	private String client1ReadStr;

	protected int client0UpdateStage = 0;
	protected int client1UpdateStage = 0;
	protected int client0WriteStage = 0;
	protected int client1WriteStage = 0;
	protected boolean serverWriteStarted = false;
	protected int serverWrite0Stage = 0;
	protected int serverWrite1Stage = 0;
	protected int client0ReadStage = 0;
	protected int client1ReadStage = 0;
	protected int serverReadStage = 0;

	private static final int CLIENT_WRITE_LEN_STAGE = 2;
	private static final int CLIENT_WRITE_STR_STAGE = 3;

	private static final int SERVER_WRITE_LEN_STAGE = 1;
	private static final int SERVER_WRITE_STR_STAGE = 2;

	private static final int READ_LEN_STAGE = 2;
	private static final int READ_STR_STAGE = 3;

	protected static final Pattern[] clientWriteStages = {
			// checkStr(AWT_THREAD, "SetProperty"),
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
	
	protected static final Pattern serverStartWritePattern = checkStr(READ_THREAD, "SocketChannelWriteRequested");

	protected static final Pattern[] serverWriteStages = {
//			checkStr(READ_THREAD, "SocketChannelWriteRequested"),
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
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty")//,
//			checkStr(SELECT_THREAD, "SelectCalled")
	};

	protected static final Pattern[] readStages = {
			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};

	protected static final Pattern[] immediateUpdateStages = {
			checkStr(AWT_THREAD, "SetProperty")
	};

	protected static final Pattern[] readUpdateStages = {
			checkStr(READ_THREAD, "CommandSubmitted")
	};

	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}

	public TwoClientCorrectReadWriteTestInputGenerator(boolean atomic) {
		this.atomic = atomic;
	}

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		boolean didServer = false;
		boolean didClient0 = false;
		boolean didClient1 = false;
		if (!setupServer && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "a " + atomic);
			setupServer = true;
		}
		if (!setupClient0 && CLIENT_0_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_0_NAME, "a " + atomic);
			setupClient0 = true;
		}
		if (!setupClient1 && CLIENT_1_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_1_NAME, "a " + atomic);
			setupClient1 = true;
		}
		// use lines for connection first
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isEnableAcceptComplete()) {
				didServer = checkEnableAccept(anOutputLine);
			} else if (!areAcceptsComplete()) {
				// each output can only be from 1 accept
				if (!isAccepted0Complete()) {
					didServer = checkAccept0(anOutputLine);
				}
				if (!didServer && !isAccepted1Complete()) {
					didServer = checkAccept1(anOutputLine);
				}
				if (!commandSent && areAcceptsComplete()) {
					notifyNewInputLine(CLIENT_0_NAME, "s \"move 0 10\"");
					commandSent = true;
				}
			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			if (!isConnect0Complete()) {
				didClient0 = checkForPropertyChangeListener0(anOutputLine);
				if (!didClient0) {
					didClient0 = checkForReadListener0(anOutputLine);
					if(!didClient0 && !isConnect0FSMComplete()) {
						didClient0 = checkConnect0(anOutputLine);
					}
				}
			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			if (!isConnect1Complete()) {
				didClient1 = checkForPropertyChangeListener1(anOutputLine);
				if (!didClient1) {
					didClient1 = checkForReadListener1(anOutputLine);
					if(!didClient1 && !isConnect1FSMComplete()) {
						didClient1 = checkConnect1(anOutputLine);
					}
				}
			}
		}
		// read/write if not connect
		if (!didServer && aProcessName.equals(SERVER_NAME)) {
			if (!isServerReadComplete()) {
				checkServerRead(anOutputLine);
			} else {
				if (!serverWriteStarted) {
					checkServerWriteStart(anOutputLine);
				} else {
					if (atomic) {
						boolean processed = false;
						if (!isServerWrite0Complete()) {
							processed = checkServerWrite0(anOutputLine);
						}
						if (!processed && !isServerWrite1Complete()) {
							checkServerWrite1(anOutputLine);
						}
					} else {
						if (client0Writing && !isServerWrite1Complete()) {
							checkServerWrite1(anOutputLine);
						} else if (client1Writing && !isServerWrite0Complete()) {
							checkServerWrite0(anOutputLine);
						}
					}
				}
			}
		} else if (!didClient0 && aProcessName.equals(CLIENT_0_NAME)) {
			if (!isClientWriteComplete()) {
				if (checkClient0Write(anOutputLine)) {
					client0Writing = true;
				}
			} else if (!isClient0ReadComplete()) {
				if (atomic || client1Writing) {
					checkClient0Read(anOutputLine);
				}
			} // else if (!isClient0Updated()) {
			// checkClient0Update(anOutputLine);
			// }
		} else if (!didClient1 && aProcessName.equals(CLIENT_1_NAME)) {
			if (!isClientWriteComplete()) {
				if (checkClient1Write(anOutputLine)) {
					client1Writing = true;
				}
			} else if (!isClient1ReadComplete()) {
				if (atomic || client0Writing) {
					checkClient1Read(anOutputLine);
				}
			} // else if (!isClient1Updated()) {
			// checkClient1Update(anOutputLine);
			// }
		}
		if (!quitSubmitted && areClientReadsComplete()) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}

	public boolean areClientsUpdated() {
		return isClient0Updated() && isClient1Updated();
	}

	public boolean isClient0Updated() {
		return client0UpdateStage == ((atomic || client1Writing) ? readUpdateStages.length
				: immediateUpdateStages.length);
	}

	public boolean isClient1Updated() {
		return client1UpdateStage == ((atomic || client0Writing) ? readUpdateStages.length
				: immediateUpdateStages.length);
	}

	public boolean isClientWriteComplete() {
		return client0WriteStage == clientWriteStages.length || client1WriteStage == clientWriteStages.length;
	}

	public boolean areServerWritesComplete() {
		return atomic ? (isServerWrite0Complete() && isServerWrite1Complete())
				: ((client0Writing && isServerWrite1Complete()) || (client1Writing && isServerWrite0Complete()));
	}

	public boolean isServerWrite0Complete() {
		return serverWrite0Stage == serverWriteStages.length;
	}

	public boolean isServerWrite1Complete() {
		return serverWrite1Stage == serverWriteStages.length;
	}

	public boolean areClientReadsComplete() {
		return atomic ? (isClient0ReadComplete() && isClient1ReadComplete())
				: ((client0Writing && isClient1ReadComplete()) || (client1Writing && isClient0ReadComplete()));
	}

	public boolean isClient0ReadComplete() {
		return client0ReadStage == readStages.length;
	}

	public boolean isClient1ReadComplete() {
		return client1ReadStage == readStages.length;
	}

	public boolean isServerReadComplete() {
		return serverReadStage == readStages.length;
	}

	public boolean serverCanRead() {
		return client0WriteStage >= 9 || client1WriteStage >= 9;
	}

	public boolean client0CanRead() {
		return serverWrite0Stage >= 8;
	}

	public boolean client1CanRead() {
		return serverWrite1Stage >= 8;
	}

	public boolean clientWriteMatchesCorrect() {
		return correctLen == clientWriteLen && correctStr.equals(clientWriteStr);
	}

	public boolean serverReadMatchesClientWrite() {
		return clientWriteLen != 0 && clientWriteLen == serverReadLen && clientWriteStr.equals(serverReadStr);
	}

	public boolean serverWritesMatchRead() {
		return bothOrAtomicAndOne(serverWrite0MatchesRead(), serverWrite1MatchesRead());
	}

	public boolean serverWrite0MatchesRead() {
		return serverReadLen != 0 && serverReadLen == serverWrite0Len && serverReadStr.equals(serverWrite0Str);
	}

	public boolean serverWrite1MatchesRead() {
		return serverReadLen != 0 && serverReadLen == serverWrite1Len && serverReadStr.equals(serverWrite1Str);
	}

	public boolean clientReadsMatchServerWrites() {
		return bothOrAtomicAndOne(client0ReadMatchesServerWrite0(), client1ReadMatchesServerWrite1());
	}

	public boolean client0ReadMatchesServerWrite0() {
		return serverWrite0Len != 0 && serverWrite0Len == client0ReadLen && serverWrite0Str.equals(client0ReadStr);
	}

	public boolean client1ReadMatchesServerWrite1() {
		return serverWrite1Len != 0 && serverWrite1Len == client1ReadLen && serverWrite1Str.equals(client1ReadStr);
	}

	public boolean checkClient0Update(String line) {
		Pattern pattern = (atomic || client1Writing) ? readUpdateStages[client0UpdateStage]
				: immediateUpdateStages[client0UpdateStage];
		if (line.startsWith(TRACER_PREFIX) && pattern.matcher(line).matches()) {
			client0UpdateStage++;
			return true;
		}
		return false;
	}

	public boolean checkClient1Update(String line) {
		Pattern pattern = (atomic || client0Writing) ? readUpdateStages[client1UpdateStage]
				: immediateUpdateStages[client1UpdateStage];
		if (line.startsWith(TRACER_PREFIX) && pattern.matcher(line).matches()) {
			client1UpdateStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkServerWriteStart(String line) {
		if (line.startsWith(TRACER_PREFIX) && serverStartWritePattern.matcher(line).matches()) {
			serverWriteStarted = true;
			return true;
		}
		return false;
	}

	public boolean checkClient0Write(String line) {
		if (line.startsWith(TRACER_PREFIX) && clientWriteStages[client0WriteStage].matcher(line).matches()) {
			if (client0WriteStage == CLIENT_WRITE_LEN_STAGE) {
				clientWriteLen = extractWriteLen(line);
			} else if (client0WriteStage == CLIENT_WRITE_STR_STAGE) {
				clientWriteStr = extractWriteStr(line);
			}
			client0WriteStage++;
			return true;
		}
		return false;
	}

	public boolean checkClient1Write(String line) {
		if (line.startsWith(TRACER_PREFIX) && clientWriteStages[client1WriteStage].matcher(line).matches()) {
			if (client1WriteStage == CLIENT_WRITE_LEN_STAGE) {
				clientWriteLen = extractWriteLen(line);
			} else if (client1WriteStage == CLIENT_WRITE_STR_STAGE) {
				clientWriteStr = extractWriteStr(line);
			}
			client1WriteStage++;
			return true;
		}
		return false;
	}

	public boolean checkServerWrite0(String line) {
		if (line.startsWith(TRACER_PREFIX) && serverWriteStages[serverWrite0Stage].matcher(line).matches()) {
			if (serverWrite0Stage == SERVER_WRITE_LEN_STAGE) {
				serverWrite0Len = extractWriteLen(line);
			} else if (serverWrite0Stage == SERVER_WRITE_STR_STAGE) {
				serverWrite0Str = extractWriteStr(line);
			}
			serverWrite0Stage++;
			return true;
		}
		return false;
	}

	public boolean checkServerWrite1(String line) {
		if (line.startsWith(TRACER_PREFIX) && serverWriteStages[serverWrite1Stage].matcher(line).matches()) {
			if (serverWrite1Stage == SERVER_WRITE_LEN_STAGE) {
				serverWrite1Len = extractWriteLen(line);
			} else if (serverWrite1Stage == SERVER_WRITE_STR_STAGE) {
				serverWrite1Str = extractWriteStr(line);
			}
			serverWrite1Stage++;
			return true;
		}
		return false;
	}

	public boolean checkClient0Read(String line) {
		if (line.startsWith(TRACER_PREFIX) && readStages[client0ReadStage].matcher(line).matches()) {
			if (client0ReadStage == READ_LEN_STAGE) {
				client0ReadLen = extractReadLen(line);
			} else if (client0ReadStage == READ_STR_STAGE) {
				client0ReadStr = extractReadStr(line);
			}
			client0ReadStage++;
			return true;
		}
		return false;
	}

	public boolean checkClient1Read(String line) {
		if (line.startsWith(TRACER_PREFIX) && readStages[client1ReadStage].matcher(line).matches()) {
			if (client1ReadStage == READ_LEN_STAGE) {
				client1ReadLen = extractReadLen(line);
			} else if (client1ReadStage == READ_STR_STAGE) {
				client1ReadStr = extractReadStr(line);
			}
			client1ReadStage++;
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

	private boolean bothOrAtomicAndOne(boolean a, boolean b) {
		return (a && b) || (!atomic && (a || b));
	}

	@Override
	public String getLastNotFoundSource() {
		if (!areAcceptsComplete()) {
			return super.getLastNotFoundSource();
		}  else if (!isClientWriteComplete()) {
			if (!client0Writing && !client1Writing) {
				return "Determining client write start";
			} else if (client0Writing && (client0WriteStage != clientWriteStages.length)) {
				return "Client 0 writing to server";
			} else if (client1Writing && (client1WriteStage != clientWriteStages.length)) {
				return "Client 1 writing to server";
			}
		} else if (serverReadStage != readStages.length) {
			return "Server reading";
		} else if (!areServerWritesComplete()) {
			if (atomic) {
				if (!isServerWrite0Complete()) {
					return "Server writing to client 0";
				} else if (!isServerWrite1Complete()) {
					return "Server writing to client 1";
				}
			} else {
				if (client0Writing && !isServerWrite1Complete()) {
					return "Server writing to client 1";
				} else if (client1Writing && !isServerWrite0Complete()) {
					return "Server writing to client 0";
				}
			}
		} else if (!areClientReadsComplete()) {
			if (atomic) {
				if (!isClient0ReadComplete()) {
					return  "Client 0 reading from server";
				} else if (!isClient1ReadComplete()) {
					return "Client 1 reading from server";
				}
			} else {
				if (client0Writing && !isClient1ReadComplete()) {
					return "Client 1 reading from server";
				} else if (client1Writing && !isClient0ReadComplete()) {
					return "Client 0 reading from server";
				}
			}
		}
		return "";
	}
	
	@Override
	public String getLastNotFound() {
		String ret = "";
		if (!areAcceptsComplete()) {
			ret = super.getLastNotFound();
		} else if (!isClientWriteComplete()) {
			if (!client0Writing && !client1Writing) {
				ret = clientWriteStages[0].pattern();
			} else if (client0Writing && (client0WriteStage != clientWriteStages.length)) {
				ret = clientWriteStages[client0WriteStage].pattern();
			} else if (client1Writing && (client1WriteStage != clientWriteStages.length)) {
				ret = clientWriteStages[client1WriteStage].pattern();
			}
		} else if (serverReadStage != readStages.length) {
			return readStages[serverReadStage].pattern();
		} else if (!areServerWritesComplete()) {
			if (atomic) {
				if (!isServerWrite0Complete()) {
					return serverWriteStages[serverWrite0Stage].pattern();
				} else if (!isServerWrite1Complete()) {
					return serverWriteStages[serverWrite1Stage].pattern();
				}
			} else {
				if (client0Writing && !isServerWrite1Complete()) {
					return serverWriteStages[serverWrite1Stage].pattern();
				} else if (client1Writing && !isServerWrite0Complete()) {
					return serverWriteStages[serverWrite0Stage].pattern();
				}
			}
		} else if (!areClientReadsComplete()) {
			if (atomic) {
				if (!isClient0ReadComplete()) {
					return readStages[client0ReadStage].pattern();
				} else if (!isClient1ReadComplete()) {
					return readStages[client1ReadStage].pattern();
				}
			} else {
				if (client0Writing && !isClient1ReadComplete()) {
					return readStages[client1ReadStage].pattern();
				} else if (client1Writing && !isClient0ReadComplete()) {
					return readStages[client0ReadStage].pattern();
				}
			}
		}
		return ret;
	}
}
