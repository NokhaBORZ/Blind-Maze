package de.htwg.se.blindmaze.model.managers

import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.managers.managersImp.{NotStartedState, RunningState}

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import de.htwg.se.blindmaze.modules.AppModule


//State Pattern

enum GameState:
  case NotStarted, Running, Finished

trait  IGameManager {
  val injector = Guice.createInjector(new AppModule)
  val grid: IGrid
  val current: Int
  def startGame:  IGameManager
  def quitGame: IGameManager
  def moveNext(direction: Direction, player: Int): IGameManager
  def invalidCommand: IGameManager
  def showGrid: String
  def resetGame: IGameManager
  def changeCurrent: IGameManager = this

  def state : GameState

  def toXml: scala.xml.Node = {
    <game>
    <current>{current}</current>
      {grid.toXml}
    </game>
  }

  def toJson: play.api.libs.json.JsObject = {
    play.api.libs.json.Json.obj(
      "current" -> current,
      "grid" -> grid.toJson
    )
  }
}

object IGameManager {
  def fromXml(node: scala.xml.Node): IGameManager = {
    val grid = IGrid.fromXml((node \ "grid").head)
    val current = (node \ "current").text.toInt
    
    RunningState(grid, current)
  }

  def fromJson(json: play.api.libs.json.JsValue): IGameManager = {
    val grid = IGrid.fromJson((json \ "grid").get)
    val current = (json \ "current").as[Int]
    
    RunningState(grid, current)
  }
}
