package de.htwg.se.blindmaze.model.fileIO

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.fileIO.fileImp.FileIOXml
import java.io.{File, PrintWriter}

class FileIOXmlSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A FileIOXml" should {

        "save a game manager to an XML file" in {
            val gameManager = mock[IGameManager]
            val fileIO = new FileIOXml

            val xml = <game><test>data</test></game>
            when(gameManager.toXml).thenReturn(xml)

            val tempFile = File.createTempFile("maze", ".xml")
            tempFile.deleteOnExit()
            val pw = new PrintWriter(tempFile)
            pw.write(xml.toString())
            pw.close()

            fileIO.save(gameManager)

            val source = scala.io.Source.fromFile(tempFile)
            val savedXml = scala.xml.XML.loadString(source.mkString)
            source.close()

            savedXml should be(xml)
        }
    }
}