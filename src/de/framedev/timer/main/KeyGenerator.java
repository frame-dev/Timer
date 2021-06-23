/**
 * Dies ist ein Plugin von FrameDev
 * Bitte nichts �ndern, @Copyright by FrameDev 
 */
package de.framedev.timer.main;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Darryl
 *
 */
public class KeyGenerator {
	
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
 
    private static final String STRING_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    // optional, make it more random
    private static final String STRING_ALLOW_BASE_SHUFFLE = shuffleString(STRING_ALLOW_BASE);
    private static final String STRING_ALLOW = STRING_ALLOW_BASE_SHUFFLE;
 
    private static SecureRandom random = new SecureRandom();
 
    public static String generatorString(int length) {
        if (length < 1) throw new IllegalArgumentException();
 
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
 
            int rndCharAt = random.nextInt(STRING_ALLOW.length());
            char rndChar = STRING_ALLOW.charAt(rndCharAt);
 
            sb.append(rndChar);
 
        }
 
        return sb.toString();
 
    }
 
    // shuffle
    public static String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        return letters.stream().collect(Collectors.joining());
    }

}
