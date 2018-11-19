package Files

import java.nio.file.{Files, Paths}
import java.io.File
import scala.io.Source

case class CsvFile(path: String, delimiter: Char, fileEncoding: String = "UTF-8" ) {

  require(Files.exists(Paths.get(path)), "File does not exist")

  private val dataSource: List[Map[String, String]] = sourceData()

  def getDataSource() = dataSource
  def isEmpty = dataSource.isEmpty

  private def sourceData():List[Map[String, String]] = {
    val dataSource = Source.fromFile(new File(path), fileEncoding)
                    .mkString
                    .replace("\"", "")
                    .split("\n")
                    .map(_.trim)
                    .map(_.split(delimiter))
                    .toList

    val header = dataSource.head
    val fileSource = dataSource.tail
    createSourceMap(fileSource, header)
  }

  private def createSourceMap(dataList: List[Array[String]],
                              header: Array[String],
                              resultList: List[Map[String, String]] = List()):List[Map[String, String]] = dataList match {
    case (Nil) => resultList
    case (head :: tail) =>
      val map = (header zip head).toMap
      val buffer = map +: resultList
      createSourceMap(dataList.tail, header, buffer)
  }

  def print():Unit = {
    for (item <- getDataSource()) {
      println(" ")
      item foreach {case (key, value) => printf(key + "->" + value + " ")}
    }
  }
}
