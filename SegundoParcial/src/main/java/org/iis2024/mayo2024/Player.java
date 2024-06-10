package org.iis2024.mayo2024;

/**
 * The {@code Player} class represents a player with a name and an age.
 */
public class Player {
  private final String name;
  private final int age;

  /**
   * Constructs a new {@code Player} with the specified name and age.
   *
   * @param name the name of the player
   * @param age the age of the player
   */
  public Player(String name, int age) {
    this.name = name;
    this.age = age;
  }

  /**
   * Returns the name of the player.
   *
   * @return the name of the player
   */
  public String name() {
    return name;
  }

  /**
   * Returns the age of the player.
   *
   * @return the age of the player
   */
  public int age() {
    return age;
  }
}
