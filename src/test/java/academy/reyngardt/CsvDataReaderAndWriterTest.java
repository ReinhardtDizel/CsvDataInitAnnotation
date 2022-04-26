package academy.reyngardt;

import academy.reyngardt.annotation.processor.CsvDataReader;
import academy.reyngardt.annotation.processor.CsvDataWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CsvDataReaderAndWriterTest {

    private static final String absolutePath = TestUtils.getPathToFile("test2.csv");
    private static final CsvDataWriter<TestUtils.TestType> csvDataWriter = new CsvDataWriter<>(absolutePath, TestUtils.TestType.class);
    private static final CsvDataReader<TestUtils.TestType> csvDataReader = new CsvDataReader<>(absolutePath, TestUtils.TestType.class);
    private static final List<TestUtils.TestType> data = csvDataReader.getData();
    private static final TestUtils.TestType test = new TestUtils.TestType("write-test1", "write-test2", "write-test3", "write-test4");

    static {
        data.add(test);
        csvDataWriter.writeListOfCsvBeanToFile(data);
    }

    @Test
    @DisplayName("List of Bean size is correct")
    public void listOfBeanSizeTest() {
        Assertions.assertEquals(data.size(), 2);
    }

    @Test
    @DisplayName("CsvDataWriter Write all data")
    public void writeAllDataTest() {
        Assertions.assertEquals(data.get(0).getTest1(), "test1");
        Assertions.assertEquals(data.get(0).getTest2(), "test2");
        Assertions.assertEquals(data.get(0).getTest3(), "test3");
        Assertions.assertEquals(data.get(0).getTest4(), "test4");
        Assertions.assertEquals(data.get(1).getTest1(), "write-test1");
        Assertions.assertEquals(data.get(1).getTest2(), "write-test2");
        Assertions.assertEquals(data.get(1).getTest3(), "write-test3");
        Assertions.assertEquals(data.get(1).getTest4(), "write-test4");
    }
}
