package view;



import java.io.*;

import javax.swing.*;

import controler.LectureBufferFichier;

public class ThreadsReceive extends Thread{
	
	private ImagePanelA image ;
	private BufferedWriter writer; 
	private JTextArea textArea; 

	 public ThreadsReceive( ImagePanelA image,JTextArea textArea){			
		 	this.image = image;
			this.textArea = textArea; 
		  }

		  public void run(){
			  String line; 
		    while (true){ 
		    	
		    	if(this.image.getUserTextField().getSelectedValue() !=null){
		    		try {
						line = (LectureBufferFichier.lectureFichier((String) this.image.getUserTextField().getSelectedValue())) ;
						
							this.textArea.setText("");
							this.textArea.setText(line);	
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
