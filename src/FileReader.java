import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader { // Метод вынесен в отдельный класс, так же создан приватный конструктор,
    // для исключения возможности создания новых объектов этого класса

    private FileReader() {
    }

    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }
}
