package org.iis2024.mayo2024;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OnlineGameService} class manages a repository of video games and provides methods
 * to add, remove, and play games.
 */
public class OnlineGameService {
  private final List<VideoGame> gameRepository;

  /**
   * Constructs a new {@code OnlineGameService} with an empty game repository.
   */
  public OnlineGameService() {
    gameRepository = new ArrayList<>();
  }

  /**
   * Returns a list of all video games in the game repository.
   *
   * @return a list of all video games
   */
  public List<VideoGame> getAllGames() {
    return new ArrayList<>(gameRepository);
  }

  /**
   * Adds a video game to the game repository.
   *
   * @param game the video game to add
   */
  public void addGame(VideoGame game) {
    gameRepository.add(game);
  }

  /**
   * Removes a video game from the game repository.
   *
   * @param game the video game to remove
   * @throws GameNotInTheServiceException if the game is not in the repository
   */
  public void removeGame(VideoGame game) {
    if (gameDoesNotExist(game.title())) {
      throw new GameNotInTheServiceException(game.title());
    }
    gameRepository.remove(game);
  }

  /**
   * Allows a player to play a video game if the player's age is appropriate for the game's PEGI rating.
   *
   * @param gameTitle the title of the video game to play
   * @param player the player who wants to play the game
   * @param pegiService the PEGI service used to validate the player's age
   * @return the video game if the player is allowed to play it
   * @throws GameNotInTheServiceException if the game is not in the repository
   * @throws InvalidAgeForPlayingTheGameException if the player's age is not appropriate for the game's PEGI rating
   */
  public VideoGame playGame(String gameTitle, Player player, PEGIService pegiService) {
    if (gameDoesNotExist(gameTitle)) {
      throw new GameNotInTheServiceException(gameTitle);
    }

    VideoGame game = findGame(gameTitle);
    boolean validation = pegiService.validateAge(game.pegiCode(), player.age());
    if (!validation) {
      throw new InvalidAgeForPlayingTheGameException("The player's age is not valid to play the game");
    }

    return game;
  }

  /**
   * Finds a video game by its title.
   *
   * @param title the title of the video game
   * @return the video game with the specified title, or {@code null} if not found
   */
  public VideoGame findGame(String title) {
    return gameRepository.stream()
        .filter(game -> game.title().equals(title))
        .findFirst()
        .orElse(null);
  }

  /**
   * Checks if a game does not exist in the game repository.
   *
   * @param gameTitle the title of the game
   * @return {@code true} if the game does not exist, {@code false} otherwise
   */
  private boolean gameDoesNotExist(String gameTitle) {
    return findGame(gameTitle) == null;
  }
}
