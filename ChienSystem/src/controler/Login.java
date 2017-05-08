package controler;

import java.io.BufferedReader;
import java.io.IOException;

public class Login {

	private static String login;


	private String lecturePseudo(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		return line;
	}

	public static String getLogin() {
		return login;
	}
	public static void setLogin(String pseudo){
		login =pseudo;
	}

}
