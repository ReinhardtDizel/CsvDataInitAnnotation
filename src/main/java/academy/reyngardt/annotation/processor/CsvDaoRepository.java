package academy.reyngardt.annotation.processor;

/**
 * interface for write CSV Bean to file
 *
 * @author Mikhail Reyngardt 23.04.2022
 */
public interface CsvDaoRepository {
    /**
     * this method write List of CSV Bean to file
     */
    default void write() {
    }
}
