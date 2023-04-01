package org;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DirectoryUtils {
    private DirectoryUtils(){/*utility*/}
    public static boolean isDirPresent(String dir) {
        Path directory = Paths.get(dir);
        return directory.toFile().exists() && directory.toFile().isDirectory();
    }
    public static File createDir(String dir) {
        File directory = new File(dir);
        directory.mkdirs();
        return directory;
    }
}
