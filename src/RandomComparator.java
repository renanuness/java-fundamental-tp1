import java.time.LocalDate;
import java.util.Comparator;
import java.util.Random;

public class RandomComparator  implements Comparator {
    private static long seed = System.nanoTime();
    public int compare(Object o1, Object o2) {
        Random rand = new Random();
        rand.setSeed(seed++);

        int n1 = rand.nextInt();
        int n2 = rand.nextInt();

        if (n1 < n2) {
            return 1;
        } if (n1 > n2) {
            return -1;
        }

        return 0;
    }
}
