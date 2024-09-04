/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blazing.fs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hexaredecimal
 */
public class FileSystem {

	private FileSystem() {
	}
	
	public static String readFileToString(String path) {
		try (
			Scanner sc = new Scanner(new File(path));) {
			StringBuilder sb = new StringBuilder();
			while (sc.hasNextLine()) {
				sb.append(sc.nextLine()).append("\n");
			}

			return sb.toString();
		} catch (FileNotFoundException ex) {
			return ex.getMessage();
		}
	}

	public static String fileExtension(String path) {
		int lastDot = path.lastIndexOf('.');
		return lastDot == -1 ? null : path.substring(lastDot + 1);
	}
}
