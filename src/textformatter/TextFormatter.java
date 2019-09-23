package textformatter;

/**
 * Class containing static methods to format strings and characters.
 */
public class TextFormatter {

  /**
   * Given a string, returns it reversed and with the capitalization of each character inverted.
   * (E.g., given "Hello World." returns ".DLROw OLLEh"
   *
   * @param originalString - String to reverse and change case of.
   * @return String reversed and with capitalization of each character inverted.
   */
  public static String reverseChangeCase(String originalString) {
    StringBuilder newString = new StringBuilder();
    for (int i = originalString.length() - 1; i >= 0; i--) {
      char newCharacter = changeCase(originalString.charAt(i));
      newString.append(newCharacter);
    }
    return newString.toString();
  }

  /**
   * Given a character, returns it with the capitalization inverted. (E.g, if character passed is
   * lowercase, returns it uppercase. If uppercase, returns it lowercase.)
   *
   * @param inputChar - Character to invert the case of.
   * @return Character with captialization case inverted.
   */
  public static Character changeCase(Character inputChar) {
    if (Character.isLowerCase(inputChar)) {
      return Character.toUpperCase(inputChar);
    }
    return Character.toLowerCase(inputChar);
  }
}
