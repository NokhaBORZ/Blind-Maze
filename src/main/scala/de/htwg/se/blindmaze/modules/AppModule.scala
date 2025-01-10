package de.htwg.se.blindmaze.modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.utils.Direction

class AppModule extends AbstractModule {
  override def configure(): Unit = {
    // Binding interface to implementation
    bind(classOf[grid.IGrid]).toProvider(() =>
      new grid.gridImp.Grid(Vector.fill(10, 10)(tiles.TileFactory.getTile(tiles.TileContent.Empty)))
    )
    bind(classOf[managers.IGameManager]).toProvider(() =>
      new managers.managersImp.NotStartedState(new grid.gridImp.Grid(10))
    )
    bind(classOf[item.IItem]).annotatedWith(Names.named("Lantern")).toProvider(() =>
      new item.ItemsImp.Lantern("Lantern")
    )
    bind(classOf[item.IItem]).annotatedWith(Names.named("Lightning")).toProvider(() =>
      new item.ItemsImp.Lightning("Lightning")
    )
    bind(classOf[item.IItem]).annotatedWith(Names.named("CursedMap")).toProvider(() =>
      new item.ItemsImp.CursedMap("CursedMap")
    )
    bind(classOf[item.IItem]).annotatedWith(Names.named("Inventory")).toProvider(() =>
      new item.ItemsImp.Inventory("Inventory")
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveUp")).toProvider(() =>
      new commands.MoveCommand(Direction.Up)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveDown")).toProvider(() =>
      new commands.MoveCommand(Direction.Down)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveLeft")).toProvider(() =>
      new commands.MoveCommand(Direction.Left)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveRight")).toProvider(() =>
      new commands.MoveCommand(Direction.Right)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("Quit")).toProvider(() =>
      new commands.QuitGameCommand()
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("Invalid")).toProvider(() =>
      new commands.InvalidCommand()
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("Start")).toProvider(() =>
      new commands.StartGameCommand()
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("Undo")).toProvider(() =>
      new commands.UndoCommand()
    )
    bind(classOf[player.IPlayer]).annotatedWith(Names.named("1")).toProvider(() =>
      new player.playerImp.Player(1)
    )
    bind(classOf[player.IPlayer]).annotatedWith(Names.named("2")).toProvider(() =>
      new player.playerImp.Player(2)
    )
  }
}
