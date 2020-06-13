package com.pradedov.lab9.task2;

/*
Задание 2. Фильтрованные атрибуты
Используйте структуру файлов и папок из Задания 1.

- Выведите полное название и содержимое самого маленького файла. Если таких файлов несколько - выведите их все
- Выведите полное название и первые 10 символов самого большого файла.
- Удалите папку SE2020_LESSON9 и все вложенные файлы и папки.
 */

import com.pradedov.lab9.task1.DirectoryTreeCreator;

public class Task2Main {
    private final static String ROOT_DIR = "SE2020_LESSON9";

    public static void main(String[] args) {
        DirectoryTreeCreator dirCreator = new DirectoryTreeCreator(ROOT_DIR);
        // Remove previous content if exists
        dirCreator.deleteTree(ROOT_DIR);

        // Create another directory tree
        dirCreator.run();

        FileFilter fileFilter = new FileFilter(ROOT_DIR);
        fileFilter.run();

        dirCreator.deleteTree(ROOT_DIR);
    }
}
