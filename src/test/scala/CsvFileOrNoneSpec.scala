import java.nio.file.Paths

import Parsers.CsvFileOrNone
import org.scalatest.{Matchers, WordSpec}

class CsvFileOrNoneSpec extends WordSpec with Matchers {

  "New implementation" should {
    "successfully load file" in {
      val path = Paths.get("") // TODO ADD YOUR TEST FILE PATH /home/dmitry/Desktop/fileParser/src/test/q20180909_08_TE0216_30000216.csv
      CsvFileOrNone.load(path) shouldBe defined
    }

    "return None when file not found" in {
      val path = Paths.get("") // TODO ADD YOUR TEST FILE PATH 180909_08_TE0216_30000216.csv
      CsvFileOrNone.load(path) shouldBe empty
    }
  }

}
