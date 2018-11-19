import org.scalatest._
import Files.CsvFile
import java.io.{File, PrintWriter}

class CsvFileTest extends FlatSpec{

  "If file not exist" should " be throw IllegalArgumentException" in {
    val filename = "" // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
    assertThrows[IllegalArgumentException] {
      CsvFile(filename, ';')
    }
  }

  "If csv file is empty" should " equal empty source" in {
    val filename = "" // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
    val file = new File(filename)
    val fileWriter = new PrintWriter(file)
    fileWriter.close()
    val shouldBe = List()
    val result = CsvFile(filename, ';' )
    assert(shouldBe === result.getDataSource())
    file.delete()
  }

  "Data source from csv file" should "be equal List of Map" in {
    val filename = "" // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
    val file = new File(filename)
    val fileWriter = new PrintWriter(file)
    fileWriter.write("\"a\";\"b\";\"c\";\"d\";\"f\";\n1;\"2\";3;\"4\";\"5\";\n1;\"2\";3;4;\"5\";")
    fileWriter.close()
    val shouldBe = List(Map("f"->"5","a"->"1","b"->"2","c"->"3","d"->"4"), Map("f"->"5","a"->"1","b"->"2","c"->"3","d"->"4"))
    val result = CsvFile(filename, ';' )
    assert(shouldBe === result.getDataSource())
    file.delete()
  }
}
