package Server;

public class StringManipulator {

  public static String reverseChangeCase(String originalString) {
    StringBuilder newString = new StringBuilder();
    for (int i = originalString.length()-1; i >= 0; i--) {
      char newCharacter = changeCase(originalString.charAt(i));
      newString.append(newCharacter);
    }
    return newString.toString();
  }

  public static Character changeCase(Character inputChar) {
    if (Character.isLowerCase(inputChar)) {
      return Character.toUpperCase(inputChar);
    }
    return Character.toLowerCase(inputChar);
  }
}
