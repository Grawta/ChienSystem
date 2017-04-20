package ChienSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureBufferFichier {
	/*prend un string et l'écrit dans un buffer en flushant le buffer aprs ecriture*/
	public static void ecritureBuffer(BufferedWriter writer,String phrase){
		try {
			writer.write(phrase+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ecritureFichier(FileWriter writer,String phrase){
		BufferedWriter bufferWriter = new BufferedWriter(writer);
		try {
			bufferWriter.write(phrase+"\n");
			bufferWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
