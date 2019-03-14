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
    out.write(str.toUpperCase(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String temp = new String(cbuf);
    out.write(temp.toUpperCase().toCharArray(),off,len);
  }

  @Override
  public void write(int c) throws IOException {
    out.write(Character.toUpperCase((char) c));
  }
}
