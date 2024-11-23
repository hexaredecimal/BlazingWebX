package blazing.fs;

import blazing.types.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hexaredecimal
 */
public class FileSystem {

  private FileSystem() {
  }

	/**
	 * Opens a file and reads the contents into a String
	 * @param path
	 * @return A result Object which has a string if successful and a FileNotFoundException if not.
	 */
  public static Result<String, FileNotFoundException> readFileToString(String path) {
    try (
      Scanner sc = new Scanner(new File(path));) {
      StringBuilder sb = new StringBuilder();
      while (sc.hasNextLine()) {
        sb.append(sc.nextLine()).append("\n");
      }

      return Result.ok(sb.toString());
    } catch (FileNotFoundException ex) {
      return Result.err(ex);
    }
  }

	/**
	 * Write the data into the file provided
	 * @param fp
	 * @param data
	 * @return A Result Object with the boolean value true is successful and an Exception if not. 
	 */
  public static Result<Boolean, Exception> writeToFile(File fp, String data) {
    if (fp == null) {
      return Result.err(new Exception("File reference is null"));
    }

    if (!fp.exists()) {
      return Result.err(new Exception(String.format("File `%s` does not exist", fp.getName())));
    }

    if (fp.isDirectory()) {
      return Result.err(new Exception(String.format("File `%s` is a directory", fp.getName())));
    }

    try (FileWriter fw = new FileWriter(fp)) {
      fw.write(data);
      return Result.ok(true);
    } catch (IOException e) {
      return Result.err(e);
    }
  }

	/**
	 * Writes and appends the data provided into the file
	 * @param fp
	 * @param data
	 * @return A Result Object with the boolean value true is successful and an Exception if not. 
	 */
  public static Result<Boolean, Exception> appendToFile(File fp, String... data) {
    if (fp == null) {
      return Result.err(new Exception("File reference is null"));
    }

    if (!fp.exists()) {
      return Result.err(new Exception(String.format("File `%s` does not exist", fp.getName())));
    }

    if (!fp.isAbsolute()) {
      return Result.err(new Exception(String.format("File `%s` is a directory", fp.getName())));
    }

    try (FileWriter fw = new FileWriter(fp, true)) {
      for (var dt : data) {
        fw.append(dt);
      }
      return Result.ok(true);
    } catch (IOException e) {
      return Result.err(e);
    }
  }

	/**
	 * Extracts the file extension of a file.
	 * @param path
	 * @return The file extension or null if there is none.
	 */
  public static String fileExtension(String path) {
    int lastDot = path.lastIndexOf('.');
    return lastDot == -1 ? null : path.substring(lastDot + 1);
  }
}
