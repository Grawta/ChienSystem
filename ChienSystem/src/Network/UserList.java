package Network;

import java.net.Socket;
import java.util.ArrayList;

public class UserList {
	private ArrayList<User> tabUser ;
	public UserList(){
		this.tabUser = null;
	}
	
	
	public void ajoutUser(String name, Socket socket){
		User user = new User(name,socket);
		tabUser.add(user);
	}
	
	public void removeUser(String name){
		int i=0;
		boolean supprime =false;
		while ((!(supprime))&&(i!=this.tabUser.size())) {
			if(name.equals(tabUser.get(i).getName())){
				tabUser.remove(i);
				supprime=true;
			}
		}
	}

}
