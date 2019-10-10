import java.util.Hashtable;
import java.util.Set;

public class DirectoryManager {
	static Hashtable<StringBuffer, FileInfo> T = new Hashtable<StringBuffer, FileInfo>();
	void enter(StringBuffer key, FileInfo file) {
		T.put(key,file);
	}
	FileInfo lookup(StringBuffer key) {
		Set<StringBuffer> keySet = T.keySet();
		for(StringBuffer Key:keySet) {
			if(Key.toString().equals(key.toString())) {
				return T.get(Key);
			}
		}
		return null;
	}
}
