package academy.reyngardt.annotation.processor;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @author Mikhail Reyngardt 23.04.2022
 */
@NoArgsConstructor
public class CsvDataWriter<T> {

    private String file;
    private Class<T> clazz;

    /**
     * @param file  absolute path to scv resource file
     * @param clazz .class for CsvToBeanBuilder
     */
    public CsvDataWriter(String file, Class<T> clazz) {
        this.clazz = clazz;
        this.file = file;
    }

    public void writeListOfCsvBeanToFile(List<T> list) {
        try (Writer writer = new FileWriter(file)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .build();
            beanToCsv
                    .write(list);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ioException) {
            ioException.printStackTrace();
        }
    }
}
