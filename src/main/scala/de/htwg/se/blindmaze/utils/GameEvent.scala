package de.htwg.se.blindmaze.utils

import de.htwg.se.blindmaze.model.{Direction, Position}

import de.htwg.se.blindmaze.model.Player

enum GameEvent:
  // Start/Running Game Events
  case OnGameStartEvent
  case OnGameResumeEvent
  // Players
  case OnPlayerMoveEvent
  case OnPlayerWinEvent(player: Player)

  // Items
  case OnItemsCollectionEvent(player: Player)


  // End Game Events
  case OnGameEndEvent

  // Set Game Size Events
  case OnSetGridSizeEvent(size: Int)

  // Set Player Size Events
  case OnSetPlayerSizeEvent(size: Int)

  // Pause Game Events
  case OnGamePauseEvent

  // Special
  case OnUpdateRenderEvent
  case OnErrorEvent(message: String)
  case OnInfoEvent(message: String)
  case OnQuitEvent
  case OnNoneEvent
  case OnUndoEvent