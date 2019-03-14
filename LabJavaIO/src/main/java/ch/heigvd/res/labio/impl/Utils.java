package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    // We first treat \r\n the limit of 2 permit to analyze until the second newline
    // The regex (?<=\r\n) permit to get the \r\n separator and keep it ont the splitted result
    String[] splitterLines =  lines.split("(?<=\\r\\n)",2);
    // If we have 1 line we got the wrong separator or there's no newline
    if(splitterLines.length != 1){
      return splitterLines;
    }
    // We then treat the \r or \n case
    splitterLines =  lines.split("(?<=\\n)|(?<=\\r)",2);
    if(splitterLines.length != 1){
      return splitterLines;
    } else {
      // Finally if there isn't newline we return this
      splitterLines = new String[] {"", lines};
      return splitterLines;
    }
  }

}
