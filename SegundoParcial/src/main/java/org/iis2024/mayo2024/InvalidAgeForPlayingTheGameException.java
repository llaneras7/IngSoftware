package org.iis2024.mayo2024;

/**
 * The {@code InvalidAgeForPlayingTheGameException} class represents an exception that is thrown
 * when a player is not of a valid age to play a particular game.
 */
public class InvalidAgeForPlayingTheGameException extends RuntimeException {

  /**
   * Constructs a new {@code InvalidAgeForPlayingTheGameException} with a specified detail message.
   *
   * @param message the detail message explaining the reason for the exception
   */
  public InvalidAgeForPlayingTheGameException(String message) {
    super(message);
  }
}
