package academy.reyngardt;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Objects;

public class TestUtils {

    private static final ClassLoader classLoader = TestUtils.class.getClassLoader();

    private TestUtils() {
    }

    public static String getPathToFile(String file) {
        return new File(Objects.requireNonNull(classLoader.getResource(file)).getFile()).getAbsolutePath();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestType {
        @CsvBindByPosition(position = 0)
        private String test1;
        @CsvBindByPosition(position = 1)
        private String test2;
        @CsvBindByPosition(position = 2)
        private String test3;
        @CsvBindByPosition(position = 3)
        private String test4;
    }
}
