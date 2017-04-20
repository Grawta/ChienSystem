package ChienSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;

public class MainTest {

	public static void main(String[] args) throws IOException {
		FileReader fra2b = null;
		FileReader frb2a = null;
		FileWriter fwa2b = null;
		FileWriter fwb2a = null;
		
		
		try {
			fwa2b = new FileWriter("a2b");
			fwb2a = new FileWriter("b2a");
			fra2b = new FileReader("a2b");
			frb2a = new FileReader("b2a");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader bra2b = new BufferedReader(fra2b);
		BufferedReader brb2a = new BufferedReader(frb2a);
		BufferedWriter bwa2b = new BufferedWriter(fwa2b);
		BufferedWriter bwb2a = new BufferedWriter(fwb2a);
		
		BackGroundImage a2b = new BackGroundImage();
		
		EcritureBufferFichier.ecritureFichier(fwb2a,"toto");
		EcritureBufferFichier.ecritureFichier(fwb2a,"TUTTUTUT");
		
		String [] toto = LectureBufferFichier.lectureFichier("b2a");
		NetworkGlobal global = new NetworkGlobal();

	}

	
}


