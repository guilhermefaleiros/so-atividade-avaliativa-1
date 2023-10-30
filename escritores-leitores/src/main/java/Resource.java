import java.util.ArrayList;
import java.util.List;

public class Resource {
    private final List<Integer> sharedList = new ArrayList<>();
    private int activeReaders = 0;
    private boolean resourceLocked = false;

    public synchronized void readLock() throws InterruptedException {
        while (resourceLocked) {
            wait();
        }
        activeReaders++;
    }

    public synchronized void readUnlock() {
        activeReaders--;
        if (activeReaders == 0) {
            notifyAll();
        }
    }

    public synchronized void writeLock() throws InterruptedException {
        while (activeReaders > 0 || resourceLocked) {
            wait();
        }
        resourceLocked = true;
    }

    public synchronized void writeUnlock() {
        resourceLocked = false;
        notifyAll();
    }

    public List<Integer> read() {
        return new ArrayList<>(sharedList);
    }

    public void write(int value) {
        sharedList.add(value);
    }
}
