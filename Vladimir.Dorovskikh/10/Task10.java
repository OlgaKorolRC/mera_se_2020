import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Задание 10. Многопоточность.
 * 1. Создайте коллекцию из трех http ссылок
 *
 * 2. Загрузите содержимое всех трех ссылок последовательно.
 *
 * 3. Выведите на экран:
 * - время, которое потребовалось, чтобы загрузить всё содержимое
 * - суммарный размер содержимого всех трех ссылок в байтах.
 * Нужно вывести время, которое потратилось на загрузку всех трех ссылок,
 * а не время, которое было потрачено на загрузку одной ссылки.
 * Идея в том, чтобы сравнить последовательную и параллельную загрузку. Параллельная должна быть быстрее (раза в три)
 *
 * 4. Теперь загрузите содержимое параллельно, используя три потока, выведите на экран тоже самое (время и размер).
 * 	+ Нужно вывести суммарный размер (размер ссылки1 + размер ссылки 2 + размер ссылки 3).
 * 	Идея в том, что вы должны не просто запускать код асинхронно, но потом и иметь возможность дождаться результатов каждой задачи
 *
 * Советы:
 * - Для хранения содержимого разных ссылок используйте потокобезопасные коллекции:
 * ConcurrentHashMap,ConcurrentHashSet, CopyOnWriteArrayList, ConcurrentLinkedQueue
 * - Для получения содержимого используйте new URL().openStream() (не забывайте закрывать поток)
 *
 */
class Read
{
    public static long size;
    public static long duration;
}

public class Task10 {
    static ArrayList<String> links;
    static CopyOnWriteArrayList<Integer> sizeArrayList;


    private static void readSequential()
    {
        long startAll = System.currentTimeMillis();
        for (int i = 0; i < links.size(); ++i) {
            readURL(links.get(i), sizeArrayList, i);
        }
        Read.duration = System.currentTimeMillis() - startAll;

        Read.size = 0;
        for (int i = 0; i < links.size(); ++i) {
            Read.size += sizeArrayList.get(i);
        }
        System.out.println(links.size() + " links size: " + Read.size + " read time " + Read.duration + "ms.");
        System.out.println();
    }

    private static void init() {
        links = new ArrayList<>();
        links.add("https://habr.com/ru/post/213319/");
        links.add("https://habr.com/ru/company/luxoft/blog/157273/");
        links.add("https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html");

        sizeArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < links.size(); ++i) {
            sizeArrayList.add(0);
        }
    }

    private static void reInit() {
        for (int i = 0; i < sizeArrayList.size(); ++i) {
            sizeArrayList.set(i,0);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        init();

        readSequential();
        long durationSequential = Read.duration;

        reInit();
        final long durationParallel = getDurationParallel();
        System.out.printf("sequential reading took %.2f times of parallel.\n", ((double) durationSequential/durationParallel));
        System.out.println();

        reInit();
        readSequential();
        durationSequential = Read.duration;

        System.out.printf("sequential reading took %.2f times of parallel.\n", ((double) durationSequential/durationParallel));
    }

    private static long getDurationParallel() throws InterruptedException {
        Thread t0 = new Thread(() -> readURL(links.get(0), sizeArrayList, 0));
        Thread t1 = new Thread(() -> readURL(links.get(1), sizeArrayList, 1));
        Thread t2 = new Thread(() -> readURL(links.get(2), sizeArrayList, 2));

        final long start = System.currentTimeMillis();

        t0.start();
        t1.start();
        t2.start();

        t0.join();
        t1.join();
        t2.join();

        final long durationParallel = System.currentTimeMillis() - start;
        long sizeAll = 0;
        for (Integer integer : sizeArrayList) {
            sizeAll += integer;
        }
        System.out.println(links.size() + " links size: " + sizeAll + " read time " + durationParallel + "ms.");
        return durationParallel;
    }


    private static void readURL(String link, CopyOnWriteArrayList<Integer> sizeArrayList, int index)  {
        int size = 0;
        try (InputStream input = new URL(link).openStream()) {
            while (input.read() != -1) {
                ++size;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sizeArrayList.set(index, size);
    }
}
