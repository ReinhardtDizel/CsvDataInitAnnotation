// Generated by CsvDaoProcessor processor, do not modify
package academy.reyngardt;

import academy.reyngardt.TestUtils.TestType;
import academy.reyngardt.annotation.processor.CsvDataReader;
import academy.reyngardt.annotation.processor.CsvDataWriter;
import java.util.List;

abstract class CsvDataAnnotationWriteTest$$Proxy {
  private CsvDataReader dataReader = new CsvDataReader<>("F:\\Documents\\Programming\\CsvDataInitAnnotation\\target\\test-classes\\test.csv",TestType.class);

  private CsvDataWriter dataWriter = new CsvDataWriter<>("F:\\Documents\\Programming\\CsvDataInitAnnotation\\target\\test-classes\\test.csv",TestType.class);

  {
    List<TestType> data = dataReader.getData();
    if (data != null) {
      ((CsvDataAnnotationWriteTest) this).data = data;
    }
    else {
      throw new NullPointerException();
    }
  }

  public void commit() {
    dataWriter.writeListOfCsvBeanToFile(((CsvDataAnnotationWriteTest) this).data);
  }
}
