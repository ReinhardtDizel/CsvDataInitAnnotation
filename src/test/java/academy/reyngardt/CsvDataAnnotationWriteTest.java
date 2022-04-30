package academy.reyngardt;

import academy.reyngardt.annotation.processor.CsvDao;
import academy.reyngardt.annotation.processor.CsvDaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class CsvDataAnnotationWriteTest implements CsvDaoRepository {

    @CsvDao(file = "test.csv")
    private List<TestUtils.TestType> data;

    @Test
    public void csvDataWriteFileTest() {
        TestUtils.TestType test = new TestUtils.TestType("write-test1", "write-test2", "write-test3", "write-test4");
        data.add(test);
        write();

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
