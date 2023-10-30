public class Main {

    private static final int NUM_WRITERS = 6;
    private static final int NUM_READERS = 6;

    public static void main(String[] args) {
        final Resource resource = new Resource();

        for (int i = 0; i < NUM_WRITERS; i++) {
            new Thread(new Writer(resource), "Escritor " + (i + 1)).start();
        }

        for (int i = 0; i < NUM_READERS; i++) {
            new Thread(new Reader(resource), "Leitor " + (i + 1)).start();
        }

    }
}
