package gradingTools.comp533s21.assignment1.interfaces;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface TestReducer<KeyType, ValueType> {
	public Map reduce(List aSublist) throws RemoteException;

}
