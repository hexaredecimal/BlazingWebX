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

	/**
	 * Hashes a String input using the SHA-256 algorithm.
	 * @param input
	 * @return A Result Object which contains a hashed String if successful and a
	 * NoSuchAlgorithmException if not.
	 */
  public static Result<String, NoSuchAlgorithmException> hash(String input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return Result.err(ex);
    }

    byte[] hashedBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

    StringBuilder sb = new StringBuilder();
    for (byte b : hashedBytes) {
      sb.append(String.format("%02x", b));
    }

    return Result.ok(sb.toString());
  }

	/**
	 * Hashes a String input using Any supported algorithm.
	 * @param algorithm
	 * @param input
	 * @return A Result Object which contains a hashed String if successful and a
	 * NoSuchAlgorithmException if not.
	 */
  public static Result<String, NoSuchAlgorithmException> hash(String algorithm, String input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(algorithm.toUpperCase());
    } catch (NoSuchAlgorithmException ex) {
      return Result.err(ex);
    }

    byte[] hashedBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

    StringBuilder sb = new StringBuilder();
    for (byte b : hashedBytes) {
      sb.append(String.format("%02x", b));
    }

    return Result.ok(sb.toString());
  }
}
