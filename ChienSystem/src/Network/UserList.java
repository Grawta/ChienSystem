package Network;

import java.net.Socket;
import java.util.ArrayList;

public class UserList {
	private ArrayList<User> tabUser ;
	


	public UserList(){
		this.tabUser = null;
	}
	
	
	public void ajoutUser(String name, CreateTCPSocket socket){
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
	public ArrayList<User> getTabUser() {
		return tabUser;
	}
	
	public User findUser(String pseudo){
		User user = null;
		
		
		return user;
	}

}
