package academy.reyngardt;

import academy.reyngardt.annotation.processor.CsvDataInit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

public class CsvDataInitAnnotationTest {

    @CsvDataInit(file = "test.csv")
    private List<TestUtils.TestType> data;

    @Test
    @DisplayName("test that annotation parameter initial correct")
    public void csvDataInitAnnotationTest() throws NoSuchFieldException {

        Field field = this.getClass().getDeclaredField("data");
        CsvDataInit annotation = field.getAnnotation(CsvDataInit.class);
        Assertions.assertEquals(annotation.file(), "test.csv");
    }

    @Test
    @DisplayName("test that annotation inject data from file to field")
    public void csvDataInitAnnotationFieldNotNullTest() {
        Assertions.assertNotNull(data);
    }

    @Test
    @DisplayName("test that annotation injected data from file is correct")
    public void csvDataInitAnnotationDataIsCorrectTest() {

        Assertions.assertEquals(data.get(0).getTest1(), "test1");
        Assertions.assertEquals(data.get(0).getTest2(), "test2");
        Assertions.assertEquals(data.get(0).getTest3(), "test3");
        Assertions.assertEquals(data.get(0).getTest4(), "test4");
    }
}
