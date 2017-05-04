package testJUnit;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Network.CreateTCPSocket;
import Network.SelfAddress;
import Network.User;
import Network.UserList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testJUnit {
	static ServerSocket serverSocket;
	static CreateTCPSocket tcpSocketServer;
	static CreateTCPSocket tcpSocketClient;
	static int port;
	static User user;
	static UserList userList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Assert.assertTrue(1 == 1);
	}

	@Test
	public void test01CreationServerSocketTCP() {

		try {
			serverSocket = new ServerSocket(0);
			port = this.serverSocket.getLocalPort();
			tcpSocketServer = new CreateTCPSocket(serverSocket);
			tcpSocketServer.start();
			Assert.assertTrue(tcpSocketServer != null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void test02CreationClientSocketTCP() {
		try {
			tcpSocketClient = new CreateTCPSocket(SelfAddress.getLocalHostLANAddress(), port);
			tcpSocketClient.start();
			Assert.assertTrue(tcpSocketClient != null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void test03CreationUser() {
		user = new User("TestUser", tcpSocketClient);
		Assert.assertTrue(user != null);
	}

	@Test
	public void test04CreationUserList() {
		userList = new UserList();
		userList.ajoutUser("testUser", tcpSocketClient);
		String name = this.userList.getTabUser().get(0).getName();
		Assert.assertFalse(name.equals(null));
	}

	@Test
	public void test05SuppressionUserEtIsEmpty() {
		userList.removeUser("testUser");
		Assert.assertTrue(this.userList.isEmpty());
	}
	

}
