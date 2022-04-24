package academy.reyngardt.annotation.processor;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Mikhail Reyngardt 23.04.2022
 */

@NoArgsConstructor
public class CsvDataReader<T> {

    private String file;
    private List<T> data;
    private Class<T> clazz;

    /**
     *
     * @param file absolute path to scv resource file
     * @param clazz .class for CsvToBeanBuilder
     */
    public CsvDataReader(String file, Class<T> clazz) {
        this.clazz = clazz;
        this.file = file;
        this.data = readCsvToBeanList();
    }

    /**
     *
     * @return List of parsed from csv file objects
     */
    public List<T> getData() {
        return data;
    }

    private List<T> readCsvToBeanList() {

        ColumnPositionMappingStrategy<T> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(clazz);
        try (Reader reader = Files.newBufferedReader(Path.of(file))) {
            CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                    .withMappingStrategy(ms)
                    .withType(clazz)
                    .build();
            return cb.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
