package ChienSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureBufferFichier {
	/*
	 * Lecture d'une seule ligne d'un fichier public String lectureUneLigne
	 * (BufferedReader reader){ String line =null; try { line =
	 * reader.readLine(); } catch (IOException e) { e.printStackTrace(); }
	 * return line; }
	 */

	public static String[] lectureBuffer(BufferedReader reader) {
		String[] fichierLu = new String[] {};
		String line;
		int i = 0;
		try {
			while ((line = reader.readLine()) != null) {
				fichierLu[i] = reader.readLine();
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fichierLu;
	}

	public static String[] lectureFichier(String nomFichier) {
		String [] tab =new String[1024];
		int i = 0;
		try {
			File f = new File(nomFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				while (line != null) {
					tab[i] = line;
					line = br.readLine();
					i++;
				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}
		return tab;
	}

}
