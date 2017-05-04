package Network;

import java.net.Socket;
import java.util.ArrayList;

public class UserList {
	private ArrayList<User> tabUser;

	public UserList() {
		this.tabUser = new ArrayList<User>();
	}

	public void ajoutUser(String name, CreateTCPSocket socket) {
		User user = new User(name, socket);
		tabUser.add(user);
	}

	public void removeUser(String name) {
		int i = 0;
		boolean supprime = false;
		while ((!(supprime)) && (i != this.tabUser.size())) {
			if (name.equals(tabUser.get(i).getName())) {
				tabUser.remove(i);
				supprime = true;
			}
		}
	}

	public ArrayList<User> getTabUser() {
		return tabUser;
	}

	public User findUser(String pseudo) {
		User utilisateur = null;
		for (User user : this.tabUser) {
			if (user.getName().equals(pseudo)) {
				utilisateur = user;
				break;
			}
		}
		return utilisateur;
	}

	public void print() {
		for (User user : this.tabUser) {
			System.out.println(user.getName() + user.getSocket().getSocketCreer().getLocalPort());
		}
	}

	public boolean isEmpty() {
		int i = 0;
		for (User user : this.tabUser) {
			i++;
		}
		if (i == 0) {
			return true;
		} else {
			return false;
		}
	}
}
