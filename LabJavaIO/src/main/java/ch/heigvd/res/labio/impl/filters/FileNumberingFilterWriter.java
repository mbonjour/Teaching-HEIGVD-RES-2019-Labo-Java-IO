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
  private int lineCounter = 0;
  private boolean tempo;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    tempo = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i){
      this.write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i){
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(lineCounter == 0){
      out.write(String.valueOf(++lineCounter) + '\t');
    }
    char temp = (char) c;

    if(temp == '\r'){
      tempo = true;
      out.write(c);
      //out.write(String.valueOf(++lineCounter) + '\t');
    }
    else if(temp == '\n'){
      tempo = false;
      out.write(c);
      out.write(String.valueOf(++lineCounter) + '\t');
    } else {
      if(tempo){
        out.write(String.valueOf(++lineCounter) + '\t');
        tempo = false;
      }
      out.write(c);
    }
  }
}
