import java.util.Random;

public class Writer implements Runnable {
    private final Resource resource;
    private static final Random random = new Random();

    public Writer(final Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            resource.writeLock();
            try {
                int value = random.nextInt(100);
                resource.write(value);
                System.out.println(Thread.currentThread().getName()
                        + " está escrevendo. Valor " + value + " adicionado à lista: " + resource.read());
                Thread.sleep(1000);
            } finally {
                resource.writeUnlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
