package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
  // Added attribute to count lines and handle the \r\n newline
  private int lineCounter = 0;
  private boolean tempo;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    tempo = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(off + len > str.length()){
      throw new IndexOutOfBoundsException("Offset and length ar not correct for the given string");
    }
    for(int i = off; i < off + len; ++i){
      this.write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(off + len > cbuf.length){
      throw new IndexOutOfBoundsException("Offset and length ar not correct for the given string");
    }
    for(int i = off; i < off + len; ++i){
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // We call this method to handle all types
    // At first we write the first number and the tab in all cases
    if(lineCounter == 0){
      out.write(String.valueOf(++lineCounter) + '\t');
    }
    char currentChar = (char) c;

    if(currentChar == '\r'){
      // We don't write the number of line directly in case of \r\n
      tempo = true;
      out.write(c);
    }
    else if(currentChar == '\n'){
      // If the previous char was \r it permits to write the \r\n correctly
      tempo = false;
      out.write(c);
      out.write(String.valueOf(++lineCounter) + '\t');
    } else {
      // If the previous char was \r and the next one not a \n we don't forget to write the number of line
      if(tempo){
        out.write(String.valueOf(++lineCounter) + '\t');
        tempo = false;
      }
      out.write(c);
    }
  }
}
