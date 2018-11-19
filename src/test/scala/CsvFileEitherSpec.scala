
import java.io.{File, PrintWriter}
import java.nio.file.Paths

import Parsers.CsvFileEither
import org.scalatest.{Matchers, WordSpec}

class CsvFileEitherSpec extends WordSpec with Matchers {

  "New implementation" should {
    "If load not exist file" in {
      val path = Paths.get("") // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
      CsvFileEither.load(path) shouldBe Left("File does not exist")
    }

    "If load empty file" in {
      val path = "" // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
      val file = new File(path)
      val fileWriter = new PrintWriter(file)
      fileWriter.close()
      CsvFileEither.load(Paths.get(path)) shouldBe Left("Empty file")
      file.delete()
    }

    "If load file" in {
      val path = "" // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/scala/test.csv
      val file = new File(path)
      val fileWriter = new PrintWriter(file)
      fileWriter.write("\"a\";\"b\";\"c\";\"d\";\"f\";\n1;\"2\";3;\"4\";\"5\";\n1;\"2\";3;4;\"5\";")
      fileWriter.close()
      CsvFileEither.load(Paths.get(path)) shouldBe Right(List(Map("f"->"5","a"->"1","b"->"2","c"->"3","d"->"4"), Map("f"->"5","a"->"1","b"->"2","c"->"3","d"->"4")))
      file.delete()
    }
  }
}
