package de.htwg.se.blindmaze.utils

import java.io.File
import scala.collection.mutable
import scalafx.scene.media.AudioClip

object AudioManager {
  private val audioClips = mutable.Map[String, AudioClip]()

  // Initialize sound files
  private val soundFiles = Map(
    "collision" -> "/sound/collision.mp3",
    "move" -> "/sound/move.wav"
    // "victory" -> "/sounds/victory.mp3",
  )

  // Load all sound files
  def initialize(): Unit = {
    soundFiles.foreach { case (name, path) =>
        val audioClip = new AudioClip(getClass.getResource(path).toString)
        audioClips(name) = audioClip
    }
  }

  // Play a sound by name
  def playSound(name: String): Unit = {
    audioClips.get(name).foreach { audioClip =>
      audioClip.play()
    }
  }

  // Stop all sounds
  def stopAllSounds(): Unit = {
    audioClips.foreach { case (_, audioClip) =>
      audioClip.stop()
    }
  }
}

