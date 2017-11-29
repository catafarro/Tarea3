package model.card.type;

import java.util.Arrays;

/**
 * All the colors allowed in UNO Cards (Including a constant for colorless card, like Wild Cards not
 * used)
 * 
 * @author eriveros
 *
 */
public enum Color {
  NONE("none"), BLUE("blue"), GREEN("green"), RED("red"), YELLOW("yellow");

  private String name;

  /**
   * Sets a color with a name (In spanish in this case)
   * 
   * @param name
   */
  Color(String name) {
    this.name = name;
  }

  /**
   * Returns color's name
   * 
   * @return name of the color
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns all the available colors (Without <i>colorless</i> color)
   * 
   * @return
   */
  public static Color[] getColors() {
    return Arrays.copyOfRange(Color.values(), 1, 5);
  }
}
