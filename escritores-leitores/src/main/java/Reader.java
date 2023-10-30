import java.util.List;

public class Reader implements Runnable {
    private final Resource resource;

    public Reader(final Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            resource.readLock();
            try {
                List<Integer> list = resource.read();
                System.out.println(Thread.currentThread().getName() + " está lendo. Conteúdo da Lista: " + list);
                Thread.sleep(1000);
            } finally {
                resource.readUnlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
