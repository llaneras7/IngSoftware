package org.iis2024.mayo2024;

/**
 * PEGI Pan-European Game Information Service interface.
 * Provides methods to validate the appropriate age for playing games based on PEGI ratings.
 *
 * <p>PEGI codes and their meanings:</p>
 * <ul>
 *   <li>PEGI 3 - Suitable for all ages</li>
 *   <li>PEGI 7 - Suitable for young children</li>
 *   <li>PEGI 12 - Suitable for children over 12 years old</li>
 *   <li>PEGI 16 - Suitable for children over 16 years old</li>
 *   <li>PEGI 18 - Suitable for adults only</li>
 * </ul>
 */
public interface PEGIService {

  /**
   * Enumeration representing PEGI rating codes.
   */
  enum PEGI_CODE {
    PEGI3, PEGI7, PEGI12, PEGI16, PEGI18
  }

  /**
   * Validates if the provided age is appropriate for the given PEGI rating code.
   *
   * @param code the PEGI rating code
   * @param age the age to validate against the PEGI rating
   * @return {@code true} if the age is appropriate for the PEGI rating, {@code false} otherwise
   */
  boolean validateAge(PEGI_CODE code, int age);
}
