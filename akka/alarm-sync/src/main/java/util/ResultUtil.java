package src.main.java.util;
import java.util.Random;


public class ResultUtil {
	private static Random random = new Random(37);
	
	public static boolean getNextBoolean() {
		return random.nextBoolean();
	}
}
