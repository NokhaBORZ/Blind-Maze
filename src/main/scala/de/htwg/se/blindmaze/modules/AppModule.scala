package de.htwg.se.blindmaze.modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.utils.Direction

class AppModule extends AbstractModule {
  override def configure(): Unit = {
    // Binding interface to implementation

    bind(classOf[fileIO.IFileIO]).annotatedWith(Names.named("Json")).to(classOf[fileIO.fileImp.FileIOJson])
    bind(classOf[fileIO.IFileIO]).annotatedWith(Names.named("Xml")).to(classOf[fileIO.fileImp.FileIOXml])

    bind(classOf[grid.IGrid]).toProvider(() => new grid.gridImp.Grid(11)) 
  
    bind(classOf[managers.IGameManager]).toProvider(() =>
      new managers.managersImp.NotStartedState(new grid.gridImp.Grid(11))
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
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveUp1")).toProvider(() =>
      new commands.MoveCommand(Direction.Up, 1)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveDown1")).toProvider(() =>
      new commands.MoveCommand(Direction.Down, 1)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveLeft1")).toProvider(() =>
      new commands.MoveCommand(Direction.Left, 1)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveRight1")).toProvider(() =>
      new commands.MoveCommand(Direction.Right, 1)
    )

    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveUp2")).toProvider(() =>
      new commands.MoveCommand(Direction.Up, 2)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveDown2")).toProvider(() =>
      new commands.MoveCommand(Direction.Down, 2)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveLeft2")).toProvider(() =>
      new commands.MoveCommand(Direction.Left, 2)
    )
    bind(classOf[commands.ICommand]).annotatedWith(Names.named("MoveRight2")).toProvider(() =>
      new commands.MoveCommand(Direction.Right, 2)
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
