package testJUnit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import controler.ControlerGolbal;
import controler.EcritureBufferFichier;
import controler.LectureBufferFichier;
import controler.Login;
import network.ControlMessage;
import network.CreateTCPSocket;
import network.Message;
import network.NetworkCreateUDP;
import network.NetworkGlobal;
import network.NetworkListen;
import network.NetworkSendDisconnect;
import network.NetworkSendHello;
import network.SelfAddress;
import network.User;
import network.UserList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testJUnit {
	static ServerSocket serverSocket;
	static CreateTCPSocket tcpSocketServer;
	static CreateTCPSocket tcpSocketClient;
	static int port;
	static User user;
	static UserList userList;
	static Message message;
	static ControlMessage controlMessage;
	static NetworkGlobalExtend global ;
	static ControlerGolbalExtend controler ;
	static String login = Login.getLogin();
	
	


	/**
	 * 
	 * uniquement dans testJUnit
	 * @author nathan
	 *
	 */
	private class ControlerGolbalExtend extends ControlerGolbal {

		public void callRequest(String text, String pseudo) {
			try {
				this.sendRequest(text, pseudo);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private static class NetworkSendDisconnectExtend extends NetworkSendDisconnect {
		
		public static void envoieDisconnect(NetworkCreateUDP global) throws IOException{
			ControlMessage message = new ControlMessage("Jean", InetAddress.getLocalHost(), 25000, "bye");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = null;
			try {
				os = new ObjectOutputStream(outputStream);
				os.writeObject(message);
				byte[] buffer = outputStream.toByteArray();
				DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"),global.getServeur().getLocalPort());
				global.getServeur().send(sendPacket);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/***
	 * Uniquement dans testJUnit
	 * @author nathan
	 *
	 */
	
	protected class NetworkListenExtend extends NetworkListen {
		private boolean verifMessage;
		
		public NetworkListenExtend(NetworkCreateUDP serveur) throws IOException {
			super(serveur);
			this.verifMessage = false;
		}
		
		public void testReceiveJUnit(ControlMessage message) {		
			if(message.getData().equals("bye")){
				System.out.println("MEssage recu = BYe");
				this.setVerifMessage(true);
			}else{
				this.setVerifMessage(false);
			}
		}
		public boolean isVerifMessage() {
			return verifMessage;
		}
		public void setVerifMessage(boolean verifMessage) {
			this.verifMessage = verifMessage;
		}
	}

	private class NetworkGlobalExtend extends NetworkGlobal{
		private NetworkListenExtend listenExtend;
		private NetworkCreateUDP serveur ;
		public NetworkGlobalExtend() throws IOException{
			serveur = new NetworkCreateUDP(25000);
			NetworkSendHello hello = new NetworkSendHello(serveur);			
			this.setListen(new NetworkListenExtend(this.getServeur()));
			this.listenExtend.start();
		}

		private void setListen(NetworkListenExtend listen){
			this.listenExtend = listen;
		}
		public NetworkListenExtend getListenExtend(){
			return this.listenExtend;
		}
	}


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Login.setLogin("Nathan");
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

	@Test
	public void test06EcritureFichier(){
		try {
			EcritureBufferFichier.ecritureFichier("FichierTestJUnit", "test");
			String phrase = LectureBufferFichier.lectureFichier("FichierTestJUnit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	public void test07CreationMessage(){
		Message message = new Message(Message.DataType.Text, "phraseTest", "toto", Login.getLogin());
		Assert.assertTrue(message != null);
		Assert.assertTrue(message.getData().equals("phraseTest"));
	}

	@Test
	public void test08CreationControlMessage(){

		try {
			controlMessage = new ControlMessage(Login.getLogin(), InetAddress.getLocalHost(), 25000, "disconnect");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(controlMessage !=null);
		Assert.assertTrue(controlMessage.getData().equals("disconnect"));
	}

	@Test 
	public void test09NetworkGlobal(){
		try {
			global = new NetworkGlobalExtend();
			
			NetworkSendDisconnectExtend.envoieDisconnect(global.getServeur());
			Thread.sleep(100);
			boolean test = global.getListenExtend().isVerifMessage();
			System.out.println(test);
			Assert.assertTrue(test);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test10Controller(){
		controler = new ControlerGolbalExtend();

	}

	@Test
	public void test11SendRequest(){
		controler.callRequest("testToto","testUser");
	}

}
