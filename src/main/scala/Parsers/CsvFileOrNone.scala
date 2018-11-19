package Parsers

import java.nio.charset.Charset
import java.nio.file.{Files, Path}

import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import scala.util.Try

object CsvFileOrNone {

  def load(path: Path, encoding: String = "Windows-1251"): Option[List[Map[String, String]]] = {
    val maybeLines = Try(Files.readAllLines(path, Charset.forName(encoding)))
      .map(_.asScala.toList)
      .toOption

    for {
      lines   <- maybeLines
      header  <- extractHeader(lines)
      tail    <- Try(lines.tail).toOption
    } yield tail
      .map(split(";"))
      .map(replaceAll("\"", ""))
      .map(header.zip(_))
      .map(_.toMap)
  }

  private def extractHeader(list: List[String]): Option[List[String]] = list
    .headOption
    .map(split(";"))
    .map(replaceAll("\"", ""))

  private def split(regex: String)(string: String): List[String] =
    string.split(regex).toList

  private def replaceAll(target: CharSequence, replacement: CharSequence)(list: List[String]): List[String] =
    list.map(_.replace(target, replacement))


}
