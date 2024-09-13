package blazing.crypto;

import blazing.types.Result;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author hexaredecimal
 */
public class HashUtils {

  public static Result<String, NoSuchAlgorithmException> hash(String input) {
    // Create an instance of the MessageDigest class
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return Result.err(ex);
    }

    // Perform the hashing
    byte[] hashedBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

    // Convert bytes to hex format
    StringBuilder sb = new StringBuilder();
    for (byte b : hashedBytes) {
      sb.append(String.format("%02x", b));
    }

    return Result.ok(sb.toString());
  }

  public static Result<String, NoSuchAlgorithmException> hash(String algorithm, String input) {
    // Create an instance of the MessageDigest class
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(algorithm.toUpperCase());
    } catch (NoSuchAlgorithmException ex) {
      return Result.err(ex);
    }

    // Perform the hashing
    byte[] hashedBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

    // Convert bytes to hex format
    StringBuilder sb = new StringBuilder();
    for (byte b : hashedBytes) {
      sb.append(String.format("%02x", b));
    }

    return Result.ok(sb.toString());
  }
}
