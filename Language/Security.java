package Language;

import java.util.Random;

public class Security {
	public static class Initialize{
		public static int newCode(int Bounds) {
			int code = new Random().nextInt(Bounds);
			return code;
		}
		public static int newCode() {
			int code = new Random().nextInt(1000);
			return code;
		}
	}
	public static boolean Verify(int initializeCode,String input) {
		int codeInput = Integer.valueOf(input);
		if(initializeCode == codeInput) {
			return true;
		}
		else {
			return false;
		}
	}
}