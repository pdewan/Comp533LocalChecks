package gradingTools.comp533s19.flexible.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class FlexibleTwoClientCorrectConnectionTestInputGenerator extends AnAbstractInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = false;

	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String GIPC_THREAD = "\\{Asynchronous Received Call Invoker\\}";
	
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	
	private int serverNIOSetupStage = 0;
	private int serverRMISetupStage = 0;
	private int serverGIPCSetupStage = 0;
	private int client0NIOConnectStage = 0;
	private int client0RMIConnectStage = 0;
	private int client0GIPCConnectStage1 = 0;
	private int client0GIPCConnectStage2 = 0;
	private int client1NIOConnectStage = 0;
	private int client1RMIConnectStage = 0;
	private int client1GIPCConnectStage1 = 0;
	private int client1GIPCConnectStage2 = 0;
	private int serverNIOAccept0Stage = 0;
	private int serverGIPCAccept0Stage = 0;
	private int serverNIOAccept1Stage = 0;
	private int serverGIPCAccept1Stage = 0;
	
	private boolean serverRegisteredGIPCObject = false;

	private boolean hasPropertyChangeListener0 = false;
	private boolean hasPropertyChangeListener1 = false;
	private boolean[] hasNIOListeners0 = {false, false};
	private boolean[] hasRMIListeners0 = {false};
	private boolean[] hasGIPCListeners0 = {false, false, false};
	private boolean hasGIPCObjectLookedUp0  = false;
	private boolean[] hasNIOListeners1 = {false, false};
	private boolean[] hasRMIListeners1 = {false};
	private boolean[] hasGIPCListeners1 = {false, false, false};
	private boolean hasGIPCObjectLookedUp1  = false;
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	private boolean quitSubmitted = false;

	private static final Pattern[] serverNIOSetupStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelBound"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverRMISetupStages = {
			checkStr(MAIN_THREAD, "RMIRegistryLocated"),
			checkStr(MAIN_THREAD, "RMIObjectRegistered")
	};
	
	private static final Pattern[] serverGIPCSetupStages = {
			checkStr(MAIN_THREAD, "GIPCRegistryCreated"),
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	// PropertyChangeListener, ReadListener
	private static final Pattern[] clientNIOConnectStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	// PropertyChangeListener
	private static final Pattern[] clientRMIConnectStages = {
			checkStr(MAIN_THREAD, "RMIRegistryLocated"),
			checkStr(MAIN_THREAD, "RMIObjectLookedUp")
	};
	
	// PropertyChangeListener, ReadListener, WriteListener, GIPCObjectLookedUp
	private static final Pattern[] clientGIPCConnectStages1 = {
			checkStr(MAIN_THREAD, "GIPCRegistryLocated"),
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
//			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SocketChannelWriteRequested"),
			checkStr(SELECT_THREAD, "WriteRequestEnqueued"),
			checkStr(SELECT_THREAD, "WriteRequestEnqueued"),
			checkStr(SELECT_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "ReadsEnabled"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
//			checkStr(MAIN_THREAD, "GIPCObjectLookedUp"),
//			checkStr(MAIN_THREAD, "SocketChannelWriteRequested"),
//			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
//			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
//			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			
	};
	
	private static final Pattern[] clientGIPCConnectStages2 = {
			checkStr(MAIN_THREAD, "SocketChannelWriteRequested"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
//			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
//			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
//			checkStr(SELECT_THREAD, "ReadsEnabled"),
//			checkStr(SELECT_THREAD, "SocketChannelInterestOp")
//			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern readListenerPattern = multipleCheckStr(MAIN_THREAD, "ReadListenerAdded", SELECT_THREAD, "ReadListenerAdded");
	private static final Pattern writeListenerPattern = multipleCheckStr(MAIN_THREAD, "WriteListenerAdded", SELECT_THREAD, "WriteListenerAdded");
	private static final Pattern propertyChangeListenerPattern = multipleCheckStr(MAIN_THREAD, "AddedPropertyChangeListener", SELECT_THREAD, "AddedPropertyChangeListener");

	private static final Pattern gipcObjectLookedUpPattern = checkStr(MAIN_THREAD, "GIPCObjectLookedUp");
	private static final Pattern gipcObjectRegisteredPattern = checkStr(MAIN_THREAD, "GIPCObjectRegistered");
	
	private static final Pattern[] serverNIOAcceptStages = {
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverGIPCAcceptStages = {
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "WriteListenerAdded"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(GIPC_THREAD, "ReceivedCallInitiated")
	};
	
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	public FlexibleTwoClientCorrectConnectionTestInputGenerator(boolean doNIO, boolean doRMI, boolean doGIPC) {
		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			checkServer(anOutputLine);
//			if (!isServerSetupComplete()) {
//				if (!isServerNIOSetupComplete() && checkServerNIOSetup(anOutputLine)) {
//				} else if (!isServerRMISetupComplete() && checkServerRMISetup(anOutputLine)) {
//				} else if (!isServerGIPCSetupComplete() && checkServerGIPCSetup(anOutputLine)) {}
//			} else if (!areServerAcceptsComplete()) {
//				boolean didServer = false;
//				if (!isServerAccept0Complete()) {
//					if (!isServerNIOAccept0Complete() && checkServerNIOAccept0(anOutputLine)) {
//						didServer = true;
//					} else if (!isServerGIPCAccept0Complete() && checkServerGIPCAccept0(anOutputLine)) {
//						didServer = true;
//					}
//				}
//				if (!didServer && !isServerAccept1Complete()) {
//					if (!isServerNIOAccept1Complete() && checkServerNIOAccept1(anOutputLine)) {
//					} else if (!isServerGIPCAccept1Complete() && checkServerGIPCAccept1(anOutputLine)) {}
//				}
//			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			checkClient0(anOutputLine);
//			if (!isClient0ConnectComplete()) {
//				if (!isClient0NIOConnectComplete() && checkClient0NIOConnect(anOutputLine)) {
//				} else if (!isClient0RMIConnectComplete() && checkClient0RMIConnect(anOutputLine)) {
//				} else if (!isClient0GIPCConnectComplete() && checkClient0GIPCConnect(anOutputLine)) {}
//			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			checkClient1(anOutputLine);
//			if (!isClient1ConnectComplete()) {
//				if (!isClient1NIOConnectComplete() && checkClient1NIOConnect(anOutputLine)) {
//				} else if (!isClient1RMIConnectComplete() && checkClient1RMIConnect(anOutputLine)) {
//				} else if (!isClient1GIPCConnectComplete() && checkClient1GIPCConnect(anOutputLine)) {}
//			}
		}
		if (!quitSubmitted && areServerAcceptsComplete()) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	protected boolean checkServer(String line) {
		boolean used = false;
		if (!isServerSetupComplete()) {
			if (!isServerGIPCSetupComplete() && checkServerGIPCSetup(line)) {
				used = true;
			} else if (!isServerNIOSetupComplete() && checkServerNIOSetup(line)) {
				used = true;
			} else if (!isServerRMISetupComplete() && checkServerRMISetup(line)) {
				used = true;
			}
		} else if (!areServerAcceptsComplete()) {
			boolean didServer = false;
			if (!isServerAccept0Complete()) {
				if (!isServerNIOAccept0Complete() && checkServerNIOAccept0(line)) {
					didServer = true;
					used = true;
				} else if (!isServerGIPCAccept0Complete() && checkServerGIPCAccept0(line)) {
					didServer = true;
					used = true;
				}
			}
			if (!didServer && !isServerAccept1Complete()) {
				if (!isServerNIOAccept1Complete() && checkServerNIOAccept1(line)) {
					used = true;
				} else if (!isServerGIPCAccept1Complete() && checkServerGIPCAccept1(line)) {
					used = true;
				}
			}
		}
		return used;
	}
	
	protected boolean checkClient0(String line) {
		boolean used = false;
		if (!isClient0ConnectComplete()) {
			if (!isClient0GIPCConnectComplete() && checkClient0GIPCConnect(line)) {
				used = true;
			} else if (!isClient0NIOConnectComplete() && checkClient0NIOConnect(line)) {
				used = true;
			} else if (!isClient0RMIConnectComplete() && checkClient0RMIConnect(line)) {
				used = true;
			}
		}
		return used;
	}
	
	protected boolean checkClient1(String line) {
		boolean used = false;
		if (!isClient1ConnectComplete()) {
			if (!isClient1GIPCConnectComplete() && checkClient1GIPCConnect(line)) {
				used = true;
			} else if (!isClient1NIOConnectComplete() && checkClient1NIOConnect(line)) {
				used = true;
			} else if (!isClient1RMIConnectComplete() && checkClient1RMIConnect(line)) {
				used = true;
			}
		}
		return used;
	}
	
	public boolean isServerNIOSetupComplete() {
		return !doNIO || serverNIOSetupStage == serverNIOSetupStages.length;
	}

	public boolean isServerRMISetupComplete() {
		return !doRMI || serverRMISetupStage == serverRMISetupStages.length;
	}
	
	public boolean isServerGIPCSetupFSMComplete() {
		return serverGIPCSetupStage == serverGIPCSetupStages.length;
	}
	
	public boolean isServerGIPCSetupComplete() {
		return !doGIPC || isServerGIPCSetupFSMComplete();
	}
	
	public boolean isServerSetupComplete() {
		return isServerNIOSetupComplete() && isServerRMISetupComplete() && isServerGIPCSetupComplete();
	}
	
	public boolean isClient0NIOConnectFSMComplete() {
		return client0NIOConnectStage == clientNIOConnectStages.length;
	}
	
	public boolean isClient0NIOConnectComplete() {
		return !doNIO || (isClient0NIOConnectFSMComplete() && (hasPropertyChangeListener0 || hasNIOListeners0[0]) && hasNIOListeners0[1]);
	}
	
	public boolean isClient1NIOConnectFSMComplete() {
		return client1NIOConnectStage == clientNIOConnectStages.length;
	}
	
	public boolean isClient1NIOConnectComplete() {
		return !doNIO || (isClient1NIOConnectFSMComplete() && (hasPropertyChangeListener1 || hasNIOListeners1[0]) && hasNIOListeners1[1]);
	}

	public boolean isClient0RMIConnectFSMComplete() {
		return client0RMIConnectStage == clientRMIConnectStages.length;
	}
	
	public boolean isClient0RMIConnectComplete() {
		return !doRMI || (isClient0RMIConnectFSMComplete() && (hasPropertyChangeListener0 || hasRMIListeners0[0]));
	}

	public boolean isClient1RMIConnectFSMComplete() {
		return client1RMIConnectStage == clientRMIConnectStages.length;
	}
	
	public boolean isClient1RMIConnectComplete() {
		return !doRMI || (isClient1RMIConnectFSMComplete() && (hasPropertyChangeListener1 || hasRMIListeners1[0]));
	}
	
	public boolean isClient0GIPCConnectFSM1Complete() {
		return client0GIPCConnectStage1 == clientGIPCConnectStages1.length;
	}
	
	public boolean isClient0GIPCConnectFSM2Complete() {
		return client0GIPCConnectStage2 == clientGIPCConnectStages2.length;
	}
	
	public boolean isClient0GIPCConnectComplete() {
		return !doGIPC || (isClient0GIPCConnectFSM1Complete() && isClient0GIPCConnectFSM2Complete() && (hasPropertyChangeListener0 || hasGIPCListeners0[0]) && hasGIPCListeners0[1] && hasGIPCListeners0[2] && hasGIPCObjectLookedUp0);
	}
	
	public boolean isClient1GIPCConnectFSM1Complete() {
		return client1GIPCConnectStage1 == clientGIPCConnectStages1.length;
	}
	
	public boolean isClient1GIPCConnectFSM2Complete() {
		return client1GIPCConnectStage2 == clientGIPCConnectStages2.length;
	}
	
	public boolean isClient1GIPCConnectComplete() {
		return !doGIPC || (isClient1GIPCConnectFSM1Complete() && isClient1GIPCConnectFSM2Complete() && (hasPropertyChangeListener1 || hasGIPCListeners1[0]) && hasGIPCListeners1[1] && hasGIPCListeners1[2] && hasGIPCObjectLookedUp1);
	}

	public boolean areClientConnectsComplete() {
		return isClient0ConnectComplete() && isClient1ConnectComplete();
	}
	
	public boolean isClient0ConnectComplete() {
		return isClient0NIOConnectComplete() && isClient0RMIConnectComplete() && isClient0GIPCConnectComplete();
	}
	
	public boolean isClient1ConnectComplete() {
		return isClient1NIOConnectComplete() && isClient1RMIConnectComplete() && isClient1GIPCConnectComplete();
	}

	public boolean canProcessAccept() {
		return true;//connectStage >= 7;
	}

	public boolean areServerAcceptsComplete() {
		return isServerAccept0Complete() && isServerAccept1Complete();
	}
	
	public boolean isServerAccept0Complete() {
		return isServerNIOAccept0Complete() && isServerGIPCAccept0Complete();
	}
	
	public boolean isServerAccept1Complete() {
		return isServerNIOAccept1Complete() && isServerGIPCAccept1Complete();
	}
	
	public boolean isServerNIOAccept0Complete() {
		return !doNIO || serverNIOAccept0Stage == serverNIOAcceptStages.length;
	}
	
	public boolean isServerNIOAccept1Complete() {
		return !doNIO || serverNIOAccept1Stage == serverNIOAcceptStages.length;
	}
	
	public boolean isServerGIPCAccept0Complete() {
		return !doGIPC || serverGIPCAccept0Stage == serverGIPCAcceptStages.length;
	}
	
	public boolean isServerGIPCAccept1Complete() {
		return !doGIPC || serverGIPCAccept1Stage == serverGIPCAcceptStages.length;
	}
	
	public boolean checkServerNIOSetup(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOSetupStages[serverNIOSetupStage].pattern());
			}
			if (serverNIOSetupStages[serverNIOSetupStage].matcher(line).matches()) {
				serverNIOSetupStage++;
				return true;
			}
		}
		return false; 
	}
	
	public boolean checkServerRMISetup(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverRMISetupStages[serverRMISetupStage].pattern());
			}
			if (serverRMISetupStages[serverRMISetupStage].matcher(line).matches()) {
				serverRMISetupStage++;
				return true;
			}
		}
		return false; 
	}
	
	public boolean checkServerGIPCSetup(String line) {
		if (!isServerGIPCSetupFSMComplete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + serverGIPCSetupStages[serverGIPCSetupStage].pattern());
				}
				if (serverGIPCSetupStages[serverGIPCSetupStage].matcher(line).matches()) {
					serverGIPCSetupStage++;
					return true;
				}
			}
		}
		if (!serverRegisteredGIPCObject && checkForGIPCObjectRegistered(line)) {
			serverRegisteredGIPCObject = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient0NIOConnect(String line) {
		if (!isClient0NIOConnectFSMComplete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientNIOConnectStages[client0NIOConnectStage].pattern());
				}
				if (clientNIOConnectStages[client0NIOConnectStage].matcher(line).matches()) {
					client0NIOConnectStage++;
					return true;
				}
			}
		}
		if (!hasNIOListeners0[0] && checkForPropertyChangeListener(line)) {
			hasNIOListeners0[0] = true;
			hasPropertyChangeListener0 = true;
			return true;
		} else if (!hasNIOListeners0[1] && checkForReadListener(line)) {
			hasNIOListeners0[1] = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient1NIOConnect(String line) {
		if (!isClient1NIOConnectFSMComplete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientNIOConnectStages[client1NIOConnectStage].pattern());
				}
				if (clientNIOConnectStages[client1NIOConnectStage].matcher(line).matches()) {
					client1NIOConnectStage++;
					return true;
				}
			}
		}
		if (!hasNIOListeners1[0] && checkForPropertyChangeListener(line)) {
			hasNIOListeners1[0] = true;
			hasPropertyChangeListener1 = true;
			return true;
		} else if (!hasNIOListeners1[1] && checkForReadListener(line)) {
			hasNIOListeners1[1] = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient0RMIConnect(String line) {
		if (!isClient0RMIConnectFSMComplete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientRMIConnectStages[client0RMIConnectStage].pattern());
				}
				if(clientRMIConnectStages[client0RMIConnectStage].matcher(line).matches()) {
					client0RMIConnectStage++;
					return true;
				}
			}
		}
		if (!hasRMIListeners0[0] && checkForPropertyChangeListener(line)) {
			hasRMIListeners0[0] = true;
			hasPropertyChangeListener0 = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient1RMIConnect(String line) {
		if (!isClient1RMIConnectFSMComplete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientRMIConnectStages[client1RMIConnectStage].pattern());
				}
				if(clientRMIConnectStages[client1RMIConnectStage].matcher(line).matches()) {
					client1RMIConnectStage++;
					return true;
				}
			}
		}
		if (!hasRMIListeners1[0] && checkForPropertyChangeListener(line)) {
			hasRMIListeners1[0] = true;
			hasPropertyChangeListener1 = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient0GIPCConnect(String line) {
		if (!isClient0GIPCConnectFSM1Complete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientGIPCConnectStages1[client0GIPCConnectStage1].pattern());
				}
				if (clientGIPCConnectStages1[client0GIPCConnectStage1].matcher(line).matches()) {
					client0GIPCConnectStage1++;
					return true;
				}
			}
		}
		if (!isClient0GIPCConnectFSM2Complete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientGIPCConnectStages2[client0GIPCConnectStage2].pattern());
				}
				if (clientGIPCConnectStages2[client0GIPCConnectStage2].matcher(line).matches()) {
					client0GIPCConnectStage2++;
					return true;
				}
			}
		}
		if (!hasGIPCListeners0[0] && checkForPropertyChangeListener(line)) {
			hasGIPCListeners0[0] = true;
			hasPropertyChangeListener0 = true;
			return true;
		} else if (!hasGIPCListeners0[1] && checkForReadListener(line)) {
			hasGIPCListeners0[1] = true;
			return true;
		} else if (!hasGIPCListeners0[2] && checkForWriteListener(line)) {
			hasGIPCListeners0[2] = true;
			return true;
		} else if (!hasGIPCObjectLookedUp0 && checkForGIPCObjectLookedUp(line)) {
			hasGIPCObjectLookedUp0 = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClient1GIPCConnect(String line) {
		if (!isClient1GIPCConnectFSM1Complete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientGIPCConnectStages1[client1GIPCConnectStage1].pattern());
				}
				if (clientGIPCConnectStages1[client1GIPCConnectStage1].matcher(line).matches()) {
					client1GIPCConnectStage1++;
					return true;
				}
			}
		}
		if (!isClient1GIPCConnectFSM2Complete()) {
			if (line.startsWith(TRACER_PREFIX)) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + clientGIPCConnectStages2[client1GIPCConnectStage2].pattern());
				}
				if (clientGIPCConnectStages2[client1GIPCConnectStage2].matcher(line).matches()) {
					client1GIPCConnectStage2++;
					return true;
				}
			}
		}
		if (!hasGIPCListeners1[0] && checkForPropertyChangeListener(line)) {
			hasGIPCListeners1[0] = true;
			hasPropertyChangeListener1 = true;
			return true;
		} else if (!hasGIPCListeners1[1] && checkForReadListener(line)) {
			hasGIPCListeners1[1] = true;
			return true;
		} else if (!hasGIPCListeners1[2] && checkForWriteListener(line)) {
			hasGIPCListeners1[2] = true;
			return true;
		} else if (!hasGIPCObjectLookedUp1 && checkForGIPCObjectLookedUp(line)) {
			hasGIPCObjectLookedUp1 = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkServerNIOAccept0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOAcceptStages[serverNIOAccept0Stage].pattern());
			}
			if (serverNIOAcceptStages[serverNIOAccept0Stage].matcher(line).matches()) {
				serverNIOAccept0Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerNIOAccept1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + serverNIOAcceptStages[serverNIOAccept1Stage].pattern());
			}
			if (serverNIOAcceptStages[serverNIOAccept1Stage].matcher(line).matches()) {
				serverNIOAccept1Stage++;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCAccept0(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (serverNIOAccept0Stage == 3 && serverGIPCAccept0Stage < 3) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + serverGIPCAcceptStages[3]);
				}
				if (serverGIPCAcceptStages[serverNIOAccept0Stage].matcher(line).matches()) {
					serverNIOAccept0Stage = serverGIPCAccept0Stage;
					serverGIPCAccept0Stage = 4;
					return true;
				}
			} else {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + serverGIPCAcceptStages[serverGIPCAccept0Stage]);
				}
				if (serverGIPCAcceptStages[serverGIPCAccept0Stage].matcher(line).matches()) {
					serverGIPCAccept0Stage++;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkServerGIPCAccept1(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (serverNIOAccept1Stage == 3 && serverGIPCAccept1Stage < 3) {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + serverGIPCAcceptStages[3]);
				}
				if (serverGIPCAcceptStages[serverNIOAccept1Stage].matcher(line).matches()) {
					serverNIOAccept1Stage = serverGIPCAccept1Stage;
					serverGIPCAccept1Stage = 4;
					return true;
				}
			} else {
				if (PRINT_CHECKED_REGEX) {
					Tracer.info(this, "Checking for line matching: " + serverGIPCAcceptStages[serverGIPCAccept1Stage]);
				}
				if (serverGIPCAcceptStages[serverGIPCAccept1Stage].matcher(line).matches()) {
					serverGIPCAccept1Stage++;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkForReadListener(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + readListenerPattern.pattern());
			}
			return readListenerPattern.matcher(line).matches();
		}
		return false;
	}
	
	public boolean checkForWriteListener(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + writeListenerPattern.pattern());
			}
			return writeListenerPattern.matcher(line).matches();
		}
		return false;
	}
	
	public boolean checkForPropertyChangeListener(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + propertyChangeListenerPattern.pattern());
			}
			return propertyChangeListenerPattern.matcher(line).matches();
		}
		return false;
	}
	
	public boolean checkForGIPCObjectRegistered(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + gipcObjectRegisteredPattern.pattern());
			}
			return gipcObjectRegisteredPattern.matcher(line).matches();
		}
		return false;
	}
	
	public boolean checkForGIPCObjectLookedUp(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line matching: " + gipcObjectLookedUpPattern.pattern());
			}
			return gipcObjectLookedUpPattern.matcher(line).matches();
		}
		return false;
	}
	
	public String getLastNotFoundSource() {
		if (!isServerSetupComplete()) {
			if (!isServerNIOSetupComplete()) {
				return "Server enabling NIO";
			} else if (!isServerRMISetupComplete()) {
				return "Server enabling RMI";
			} else if (!isServerGIPCSetupComplete()) {
				return "Server enabling GIPC";
			}
		} else if (!isClient0ConnectComplete()) {
			if (!isClient0NIOConnectComplete()) {
				return "Client 0 connecting with NIO";
			} else if (!isClient0RMIConnectComplete()) {
				return "Client 0 connecting with RMI";
			} else if (!isClient0GIPCConnectComplete()) {
				return "Client 0 connecting with GIPC";
			}
		} else if (!isClient1ConnectComplete()) {
			if (!isClient1NIOConnectComplete()) {
				return "Client 1 connecting with NIO";
			} else if (!isClient1RMIConnectComplete()) {
				return "Client 1 connecting with RMI";
			} else if (!isClient1GIPCConnectComplete()) {
				return "Client 1 connecting with GIPC";
			}
		} else if (!isServerAccept0Complete()) {
			if (!isServerNIOAccept0Complete()) {
				return "Server accepting NIO connection from client 0";
			} else if (!isServerGIPCAccept0Complete()) {
				return "Server accepting GIPC connection from client 0";
			} 
		} else if (!isServerAccept1Complete()) {
			if (!isServerNIOAccept1Complete()) {
				return "Server accepting NIO connection from client 1";
			} else if (!isServerGIPCAccept1Complete()) {
				return "Server accepting GIPC connection from client 1";
			} 
		}
		return "";
	}
	
	public String getLastNotFound() {
		if (!isServerSetupComplete()) {
			if (!isServerNIOSetupComplete()) {
				return serverNIOSetupStages[serverNIOSetupStage].pattern();
			} else if (!isServerRMISetupComplete()) {
				return serverRMISetupStages[serverRMISetupStage].pattern();
			} else if (!isServerGIPCSetupComplete()) {
				if (!isServerGIPCSetupFSMComplete()) {
					return serverGIPCSetupStages[serverGIPCSetupStage].pattern();
				} else if (!serverRegisteredGIPCObject) {
					return gipcObjectRegisteredPattern.pattern();
				} 
			}
		} else if (!isClient0ConnectComplete()) {
			if (!isClient0NIOConnectComplete()) {
				if (!isClient0NIOConnectFSMComplete()) {
					return clientNIOConnectStages[client0NIOConnectStage].pattern();
				} else if (!hasPropertyChangeListener0 && !hasNIOListeners0[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasNIOListeners0[1]) {
					return readListenerPattern.pattern();
				}
			} else if (!isClient0RMIConnectComplete()) {
				if (!isClient0RMIConnectFSMComplete()) {
					return clientRMIConnectStages[client0RMIConnectStage].pattern();
				} else if (!hasPropertyChangeListener0 && !hasRMIListeners0[0]) {
					return propertyChangeListenerPattern.pattern();
				}
			} else if (!isClient0GIPCConnectComplete()) {
				if (!isClient0GIPCConnectFSM1Complete()) {
					return clientGIPCConnectStages1[client0GIPCConnectStage1].pattern();
				} else if (!isClient0GIPCConnectFSM2Complete()) {
					return clientGIPCConnectStages2[client0GIPCConnectStage2].pattern();
				} else if (!hasPropertyChangeListener0 && !hasGIPCListeners0[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasGIPCListeners0[1]) {
					return readListenerPattern.pattern();
				} else if (!hasGIPCListeners0[2]) {
					return writeListenerPattern.pattern();
				} else if (!hasGIPCObjectLookedUp0) {
					return gipcObjectLookedUpPattern.pattern();
				}
			}
		} else if (!isClient1ConnectComplete()) {
			if (!isClient1NIOConnectComplete()) {
				if (!isClient1NIOConnectFSMComplete()) {
					return clientNIOConnectStages[client1NIOConnectStage].pattern();
				} else if (!hasPropertyChangeListener1 && !hasNIOListeners1[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasNIOListeners1[1]) {
					return readListenerPattern.pattern();
				}
			} else if (!isClient1RMIConnectComplete()) {
				if (!isClient1RMIConnectFSMComplete()) {
					return clientRMIConnectStages[client1RMIConnectStage].pattern();
				} else if (!hasPropertyChangeListener1 && !hasRMIListeners1[0]) {
					return propertyChangeListenerPattern.pattern();
				}
			} else if (!isClient1GIPCConnectComplete()) {
				if (!isClient1GIPCConnectFSM1Complete()) {
					return clientGIPCConnectStages1[client1GIPCConnectStage1].pattern();
				} else if (!isClient1GIPCConnectFSM2Complete()) {
					return clientGIPCConnectStages2[client1GIPCConnectStage2].pattern();
				} else if (!hasPropertyChangeListener1 && !hasGIPCListeners1[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasGIPCListeners1[1]) {
					return readListenerPattern.pattern();
				} else if (!hasGIPCListeners1[2]) {
					return writeListenerPattern.pattern();
				} else if (!hasGIPCObjectLookedUp1) {
					return gipcObjectLookedUpPattern.pattern();
				}
			}
		} else if (!isServerAccept0Complete()) {
			if (!isServerNIOAccept0Complete()) {
				return serverNIOAcceptStages[serverNIOAccept0Stage].pattern();
			} else if (!isServerGIPCAccept0Complete()) {
				return serverGIPCAcceptStages[serverGIPCAccept0Stage].pattern();
			} 
		} else if (!isServerAccept1Complete()) {
			if (!isServerNIOAccept1Complete()) {
				return serverNIOAcceptStages[serverNIOAccept1Stage].pattern();
			} else if (!isServerGIPCAccept1Complete()) {
				return serverGIPCAcceptStages[serverGIPCAccept1Stage].pattern();
			} 
		}
		return "";
	}

	public boolean doNIO() {
		return doNIO;
	}

	public boolean doRMI() {
		return doRMI;
	}

	public boolean doGIPC() {
		return doGIPC;
	}
}
