package Controler;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Network.User;
import Network.UserList;

public class UpdateListUser {
	
	public static void miseAJour(DefaultListModel<String> listModel, UserList tabUser){
		ArrayList<User> tableauUser = tabUser.getTabUser();
		listModel.clear();
		for (int j = 0; j <tableauUser.size(); j++) {
			User user = tableauUser.get(j);
			String name = user.getName();
			if(!(listModel.contains(name))){
				listModel.addElement(name);
			}	
		}
	}
	

}
