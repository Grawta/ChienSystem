package View;



import java.io.*;

import javax.swing.*;

import Controler.LectureBufferFichier;

public class ThreadsReceive extends Thread{
	
	private ImagePanelA image ;
	private BufferedWriter writer; 
	private JTextArea textArea; 

	 public ThreadsReceive( ImagePanelA image,JTextArea textArea){			
		 	this.image = image;
			this.textArea = textArea; 
		  }

		  public void run(){
			  String [] line; 
		    while (true){ 
		    	
		    	// readLine reads the line... then throws it away !!! so keep it before testing:!!:
		    	if(this.image.getUserTextField().getSelectedValue() !=null){
		    		line = (LectureBufferFichier.lectureFichier((String) this.image.getUserTextField().getSelectedValue())) ;
					for (int i = 0; i < line.length; i++) {
						this.textArea.setText("");
						this.textArea.setText(line[i]);	
					} 	
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
					
		    }
		  }
}
