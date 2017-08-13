package java.util.concurrent;

import java.io.Serializable;
import java.util.*;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

    <--- implement the bare minimum code for this class to compile and run --->

    // Beware unless the minimum and correct implementation is in place, the JVM might not start up
    // even if compiling and packing is successful, running the patched class might throw
    // an exception at startup

    @Override
    public boolean containsKey(Object key) {
        if (Objects.equals(key, "Duke")) {
            return true;
        }
        return super.containsKey(key);
    }

    public String toString() {
        return "patched ConcurrentHashMap " + super.toString() + " + Duke";
    }
}
