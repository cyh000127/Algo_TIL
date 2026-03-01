import java.util.*;
import java.io.*;

/**
 * 2490 윷놀이
 */
public class 백준_2490_윷놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int T = 0; T < 3; T++) {
			String str = br.readLine();

			int cnt = 0;
			for (int i = 0; i < 7; i += 2) {
				if (str.charAt(i) == '1') {
					cnt++;
				}
			}

			switch (cnt) {
			case 0:
				System.out.println('D');
				break;
			case 1:
				System.out.println('C');
				break;
			case 2:
				System.out.println('B');
				break;
			case 3:
				System.out.println('A');
				break;
			case 4:
				System.out.println('E');
				break;
			}
		}
	}
}