package ru.sug4chy.demo5.service;

import java.io.File;

public class FileService {

    public File[] getDirectories(String path) {
        if (path == null) {
            path = "C:\\";
        }
        return new File(path).listFiles(File::isDirectory);
    }

    public File[] getFiles(String path) {
        if (path == null) {
            path = "C:\\";
        }
        return new File(path).listFiles(File::isFile);
    }
}
