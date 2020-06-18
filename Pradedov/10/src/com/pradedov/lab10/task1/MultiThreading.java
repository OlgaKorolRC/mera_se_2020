package com.pradedov.lab10.task1;

/*
Задание 10. Многопоточность.
1. Создайте коллекцию из трех http ссылок
2. Загрузите содержимое всех трем ссылкам последовательно.
3. Выведите на экран:
- время, которое потребовалось, чтобы загрузить всё содержимое
- суммарный размер содержимого всех трех ссылок в байтах.
4. Теперь загрузите содержимое параллельно, используя три потока, выведите на экран тоже самое (время и размер).
Советы:
- Для хранения содержимого разных ссылок используйте потокобезопасные коллекции:
ConcurrentHashMap,ConcurrentHashSet, CopyOnWriteArrayList, ConcurrentLinkedQueue
- Для получения содержимого используйте new URL().openStream() (не забывайте закрывать поток)
 */


public class MultiThreading {
    static final int NUM_OF_ITERATIONS = 10;
    static final String [] urls = {"https://javarush.ru/groups/posts/2048-threadom-java-ne-isportishjh--chastjh-ii---sinkhronizacija",
            "https://javarush.ru/groups/posts/2060-threadom-java-ne-isportishjh--chastjh-iii---vzaimodeystvie",
            "https://javarush.ru/groups/posts/2065-threadom-java-ne-isportishjh--chastjh-iv---callable-future-i-druzjhja",
            "https://javarush.ru/groups/posts/2078-threadom-java-ne-isportishjh--chastjh-v---executor-threadpool-fork-join-pool"};

    public static void main(String[] args) {
        MultithreadPerformance test = new MultithreadPerformance(urls);

        // Total time highly depends on execution, number of urls(the more urls, the better results)
        // and even on which one of tests was run first. Sometime parallel test gives worst results
        // than sequential one. So it's better to get some average total time.
        // This is the reason for these cycles below.

        long averageParallelTime = 0;
        long averageParallelSize = 0;
        for (int iter = 0; iter < NUM_OF_ITERATIONS; ++iter) {
            System.out.println("\nTest parallel loading");
            CommonStatistics parallelResults = test.testParallelLoading();
            averageParallelTime += parallelResults.getLoadingTime();
            averageParallelSize += parallelResults.getSize();
        }
        averageParallelTime /= NUM_OF_ITERATIONS;
        averageParallelSize /= NUM_OF_ITERATIONS;


        long averageSequentialTime = 0;
        long averageSequentialSize = 0;
        for (int iter = 0; iter < NUM_OF_ITERATIONS; ++iter) {
            System.out.println("\nTest sequential loading");
            CommonStatistics sequentialResults = test.testSequentialLoading();
            averageSequentialTime += sequentialResults.getLoadingTime();
            averageSequentialSize += sequentialResults.getSize();
        }
        averageSequentialTime /= NUM_OF_ITERATIONS;
        averageSequentialSize /= NUM_OF_ITERATIONS;

        System.out.println("\nExecution results:");
        System.out.println("    Sequential loading:");
        System.out.println("        Total size (bytes): " + averageSequentialSize);
        System.out.println("        Total time (ms):  " + averageSequentialTime);
        System.out.println("    Parallel loading:");
        System.out.println("        Total size (bytes): " + averageParallelSize);
        System.out.println("        Total time (ms):  " + averageParallelTime);

        System.out.println("\nParallel loading is in "
                + (float)averageSequentialTime/averageParallelTime
                + " times faster");
    }
}
