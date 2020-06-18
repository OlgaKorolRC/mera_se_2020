package com.pradedov.lab9.task1;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Path;

public class DirectoryTreeVisitor extends SimpleFileVisitor {
    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) {
        System.out.println(((Path) file).toAbsolutePath().toString());
        return FileVisitResult.CONTINUE;
    }
}
