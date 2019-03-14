package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    // We first visit the current location
    visitor.visit(rootDirectory);
    // If it's a dir we first check if it contains other dirs
    if(rootDirectory.isDirectory()) {
      // Create a list of dirs
      File[] list = rootDirectory.listFiles(File::isDirectory);
      // We sort it for the tests
      Arrays.sort(list);
      // Explore the dirs recursively
      for (File dir : list) {
        explore(dir, visitor);
      }

      // We make a list of files and visit each of it
      list = rootDirectory.listFiles(File::isFile);
      Arrays.sort(list);
      for (File file : list) {
        explore(file, visitor);
      }
    }
  }

}
