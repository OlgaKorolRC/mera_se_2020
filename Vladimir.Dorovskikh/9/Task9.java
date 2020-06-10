import com.mera.training.dorovskikh.randomfiles.AttributesFiltering;
import com.mera.training.dorovskikh.randomfiles.RandomFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Random;

/**
 * Лекция 9. Потоки и файлы.
 * Задание 1. Файловый произвол
 * - Создате новую папку с названием SE2020_LESSON9 в любом месте
 * - Создайте от 1 до 3 (произвольное число) вложенных папок. Название папки - Directory_<уровень вложенности>
 *
 * Пример, какие папки могут получится
 * SE2020_LESSON9/1 - если создалась всего одна папка
 * SE2020_LESSON9/1/2- если создались две папки.
 * SE2020_LESSON9/1/2/3 - если создались все три папки.
 * Обратите внимание, что папка 3 - вложена в папку 2, а папка 2 - в папку 1.
 *
 * - В каждой папке (включая корень SE2020_LESSON9) создайте 1-3 файла:
 *     содержимое каждого файла - 10-200 случайных цифр (цифр, а не чисел).
 *     название файла - File_<номер файла, начиная с 1>
 *
 * - выведите на экран полный путь до всех файлов  (но не папок, вложенные файлы тоже считаются)) в папке SE2020_LESSON9
 * Совет: Для обхода можно использовать Files.walkFileTree
 *
 * Задание 2. Фильтрованные атрибуты
 * Используйте структуру файлов и папок из Задания 1.
 *
 * - Выведите полное  название и содержимое самого маленького файла. Если таких файлов несколько - выведите их все
 * - Выведите полное название и первые 10 символов самого большого файла.
 * - Удалите папку SE2020_LESSON9 и все вложенные файлы и папки.
 */
public class Task9 {
    public static void main(String[] args) throws IOException {

        RandomFiles.main();
        AttributesFiltering.main();
    }
}
