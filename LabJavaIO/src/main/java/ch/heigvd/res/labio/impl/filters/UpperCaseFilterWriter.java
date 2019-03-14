package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class make an input in UpperCase
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(off + len > str.length()){
      throw new IndexOutOfBoundsException("Offset and length ar not correct for the given string");
    }
    for(int i = off; i - off < len; ++i){
      this.write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(off + len > cbuf.length){
      throw new IndexOutOfBoundsException("Offset and length ar not correct for the given string");
    }
    for(int i = off; i - off < len; ++i) {
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    out.write(Character.toUpperCase((char) c));
  }
}
