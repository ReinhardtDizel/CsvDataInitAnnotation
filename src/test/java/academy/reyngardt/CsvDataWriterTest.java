package academy.reyngardt;

import academy.reyngardt.annotation.processor.CsvDataReader;
import academy.reyngardt.annotation.processor.CsvDataWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CsvDataWriterTest {

    private static final String absolutePath = TestUtils.getPathToFile("test4.csv");
    private static final CsvDataWriter<TestUtils.TestType> csvDataWriter = new CsvDataWriter<>(absolutePath, TestUtils.TestType.class);
    private static final TestUtils.TestType test = new TestUtils.TestType("write-test1", "write-test2", "write-test3", "write-test4");
    private static final List<TestUtils.TestType> data = new ArrayList<>();

    @BeforeAll
    public static void init() {
        data.add(test);
        csvDataWriter.writeListOfCsvBeanToFile(data);
    }

    @Test
    @DisplayName("CsvDataWriter Write file")
    public void csvDataWriterWriteToListTest() {
        Assertions.assertNotNull(data);
    }

    @Test
    @DisplayName("CsvDataWriter Write correct")
    public void csvDataWriterWriteCorrectTest() {
        Assertions.assertEquals(data.get(0).getTest1(), "write-test1");
        Assertions.assertEquals(data.get(0).getTest2(), "write-test2");
        Assertions.assertEquals(data.get(0).getTest3(), "write-test3");
        Assertions.assertEquals(data.get(0).getTest4(), "write-test4");
    }
}
