package org.iis2024.mayo2024;

/**
 * The {@code GameNotInTheServiceException} class represents an exception that is thrown
 * when a game is not found in the game service.
 */
public class GameNotInTheServiceException extends RuntimeException {

  /**
   * Constructs a new {@code GameNotInTheServiceException} with a specified game title.
   *
   * @param gameTitle the title of the game that is not found in the game service
   */
  public GameNotInTheServiceException(String gameTitle) {
    super("Game " + gameTitle + " is not in the game service");
  }
}
