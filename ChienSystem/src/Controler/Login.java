package Controler;

import java.io.BufferedReader;
import java.io.IOException;

public class Login {

	private static String login;

	public Login(String pseudo ) throws IOException {
		this.login = pseudo;
	}

	private String lecturePseudo(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		return line;
	}

	public static String getLogin() {
		return login;
	}

}
