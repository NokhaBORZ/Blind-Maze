package de.htwg.se.blindmaze.utils

import scalafx.scene.media.{Media, MediaPlayer}
import java.io.File

object AudioManager {
  private var mediaPlayers: Map[String, MediaPlayer] = Map.empty

  // Initialize sound files
  private val soundFiles = Map(
    "collision" -> "/sound/invalidMove.mp3",
    "move" -> "/sound/movesound.wav",
    "victory" -> "/sounds/victory.mp3",
  )

  // Load all sound files
  def initialize(): Unit = {
    soundFiles.foreach { case (name, path) =>
      val resource = getClass.getResource(path)
      if (resource != null) {
        val media = new Media(resource.toExternalForm)
        val mediaPlayer = new MediaPlayer(media)
        mediaPlayers += (name -> mediaPlayer)
      } else {
        println(s"Warning: Sound file not found: $path")
      }
    }
  }

  // Play a sound by name
  def playSound(name: String): Unit = {
    mediaPlayers.get(name) match {
      case Some(player) =>
        player.stop()
        player.play()
      case None =>
        println(s"Warning: Sound not found: $name")
    }
  }

  // Stop all sounds
  def stopAllSounds(): Unit = {
    mediaPlayers.values.foreach(_.stop())
  }
}

