package org.iis2024.mayo2024;

import org.iis2024.mayo2024.PEGIService.PEGI_CODE;

/**
 * The {@code VideoGame} class represents a video game with a title and a PEGI rating code.
 */
public class VideoGame {
  private final String title;
  private final PEGI_CODE pegiCode;

  /**
   * Constructs a new {@code VideoGame} with the specified title and PEGI rating code.
   *
   * @param title the title of the video game
   * @param pegiCode the PEGI rating code of the video game
   */
  public VideoGame(String title, PEGI_CODE pegiCode) {
    this.title = title;
    this.pegiCode = pegiCode;
  }

  /**
   * Returns the title of the video game.
   *
   * @return the title of the video game
   */
  public String title() {
    return title;
  }

  /**
   * Returns the PEGI rating code of the video game.
   *
   * @return the PEGI rating code of the video game
   */
  public PEGI_CODE pegiCode() {
    return pegiCode;
  }
}
