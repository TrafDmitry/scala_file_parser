package Parsers

import java.nio.charset.Charset
import java.nio.file.{Files, Path}
import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import scala.util.Try

object CsvFileEither {

  def load(path: Path, encoding: String = "Windows-1251"): Either[String, List[Map[String, String]]] = {

    val maybeLines: Either[String, List[String]] = Try(Files.readAllLines(path, Charset.forName(encoding)))
      .map(_.asScala.toList).toEither.left.map(ex => ex match {
      case ex => "File does not exist"
    })

    for {
      lines   <- maybeLines
      header  <- extractHeader(lines)
      tail    <- Try(lines.tail).toEither.left.map(ex => ex match {
        case ex => "Empty body"
      })
    } yield tail
      .map(split(";"))
      .map(replaceAll("\"", ""))
      .map(header.zip(_))
      .map(_.toMap)
  }

  private def extractHeader(list: List[String]): Either[String, List[String]] = {
    Try(list.head.split(";").toList.map(_.replace("\"", ""))).toEither.left.map(ex => ex match {
      case ex => "Empty file"
    })
  }

  private def split(regex: String)(string: String): List[String] =
    string.split(regex).toList

  private def replaceAll(target: CharSequence, replacement: CharSequence)(list: List[String]): List[String] =
    list.map(_.replace(target, replacement))

}
