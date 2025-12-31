import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lab: JVM Memory Analysis
 * 
 * TODO: Complete this application to demonstrate memory behavior
 * 
 * Run with: java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log MemoryLabApp
 */
public class MemoryLabApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== JVM Memory Lab ===");
        printMemoryStatus("Initial");

        List<byte[]> memoryBlocks = new ArrayList<>();
        byte[] mem;
        int counter = 0;

        // 1. Allocate memory in a loop (e.g., 1MB chunks)
        // 2. Print memory status after each allocation
        // 3. Handle OutOfMemoryError gracefully
        // 4. Add a small delay between allocations for observation

        // Your code here:
        while(counter < 1000) {
            // 1MB chunk created
            mem = new byte[1024 * 1024];
            Arrays.fill(mem, (byte) counter);

            // Chunk added to list
            memoryBlocks.add(mem);

            // Memory Status
            printMemoryStatus(counter + "-nth");
            counter++;

            // Delay
            Thread.sleep(1000);

            // OutOfMemoryError is handled by
            // -XX:+ExitOnOutOfMemoryError
            // in CLI
        }


        printMemoryStatus("Final");
    }

    /**
     * Helper method to print current memory status
     */
    private static void printMemoryStatus(String label) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.printf("[%s] Max: %d MB, Total: %d MB, Used: %d MB, Free: %d MB%n",
                label, maxMemory, totalMemory, usedMemory, freeMemory);
    }
}
