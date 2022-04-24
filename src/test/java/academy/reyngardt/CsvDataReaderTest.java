package academy.reyngardt;

import academy.reyngardt.annotation.processor.CsvDataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CsvDataReaderTest {

    private final String absolutePath = TestUtils.getPathToFile("test.csv");
    private final CsvDataReader<TestUtils.TestType> csvDataReader = new CsvDataReader<>(absolutePath, TestUtils.TestType.class);
    private final List<TestUtils.TestType> data = csvDataReader.getData();

    @Test
    @DisplayName("CsvDataReader read file")
    public void csvDataReaderReadToListTest() {
        Assertions.assertNotNull(data);
    }

    @Test
    @DisplayName("CsvDataReader read correct")
    public void csvDataReaderReadCorrectTest() {
        Assertions.assertEquals(data.get(0).getTest1(), "test1");
        Assertions.assertEquals(data.get(0).getTest2(), "test2");
        Assertions.assertEquals(data.get(0).getTest3(), "test3");
        Assertions.assertEquals(data.get(0).getTest4(), "test4");
    }
}
