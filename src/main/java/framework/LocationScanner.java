package framework;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 * user 하위 디렉터리에 있는 클래스를 읽어 들이는 클래스
 * SpringFactoriesLoader 참고
 * */
public class LocationScanner {

    private final String BASE_DIR = "src/main/java/users";

    public List<String> read() throws IOException {
        List<String> classNames = new ArrayList<>();
        Path basePath = Paths.get(BASE_DIR);
        Files.walkFileTree(basePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    String className = convertPathToClassName(basePath, file);
                    classNames.add(className);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return classNames;
    }

    private static String convertPathToClassName(Path basePath, Path file) {
        Path relativePath = basePath.relativize(file);
        String packageName = basePath.getName(basePath.getNameCount()-1).toString();
        String className = relativePath.toString()
                .replace(File.separatorChar, '.')
                .replaceAll("\\.java$", ".class");
        return packageName + "/" + className;
    }


}
