package de.htwg.se.blindmaze.model.fileIO.fileImp

import de.htwg.se.blindmaze.model.fileIO.IFileIO
import de.htwg.se.blindmaze.model.managers.IGameManager

class FileIOXml extends IFileIO {
  override def load: IGameManager = {
    val source = scala.io.Source.fromFile("maze.xml")
    val xml = source.mkString
    source.close()
    IGameManager.fromXml(scala.xml.XML.loadString(xml))
  }

  override def save(gameManager: IGameManager): Unit = {
    val pw = new java.io.PrintWriter("maze.xml")
    val prettyPrinter = new scala.xml.PrettyPrinter(80, 2)
    val xml = prettyPrinter.format(gameManager.toXml)
    pw.write(xml)
    pw.close()
  }
}