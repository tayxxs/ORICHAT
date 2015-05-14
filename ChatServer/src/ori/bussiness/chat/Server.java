package ori.bussiness.chat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Server {
	// 新建用户信息
	public void newAccount(User user) {
		try {
			// create new file ".txt", with password saved in
			Integer account = new Integer(user.getAccount());
			File file = new File(".\\user\\", account.toString() + ".txt");
			boolean res = file.createNewFile();
			if (!res)
				System.out.println("new file create failed");

			OutputStream os = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeBytes(user.getPassword());
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Server() {
		ServerSocket ss = null;
		try {
			System.out.println("Poiiiiiiiiiiiii");
			ss = new ServerSocket(3333);
			// ss.setSoTimeout(20000);

			System.out.println("服务器已启动 in " + new Date());
			while (true) {
				Socket s = ss.accept();
				System.out.println("POI,连接上了！");
				// 接受客户端发来的信息
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				User u = (User) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(
						s.getOutputStream());
				if (u.getOperation().equals("login")) { // 登录
					// 获取相应用户文件并比较存储的密码和用户输入的密码
					Integer account = new Integer(u.getAccount());
					Path path = FileSystems.getDefault().getPath("./user",
							account.toString() + ".txt");
					BufferedReader reader = Files.newBufferedReader(path,
							StandardCharsets.UTF_8);
					String get = reader.readLine();
					if (get.equals(u.getPassword())) {
						boolean tem = true;
						System.out.println("[" + u.getAccount() + "]上线了!POI!");
						oos.writeObject(tem);
						//新建一个线程来保存这个socket
						Server2ClientThread s2c = new Server2ClientThread(s);
						ServerManager.addClientThread(u.getAccount(), s2c);
						s2c.start();

					} else {
						boolean tem = false;
						System.out.println("Password is not correct! Poi!");
						oos.writeObject(tem);
					}
					/*
					 * if(b){ //System.out.println("["+u.getNick()+"]上线了！");
					 * 
					 * //得到个人信息 //String user=udao.getUser(account);
					 * //返回一个成功登陆的信息包，并附带个人信息
					 * m.setType(ChatMessageType.SUCCESS); m.setContent(user);
					 * oos.writeObject(m); ServerConClientThread cct=new
					 * ServerConClientThread(s);//单开一个线程，让该线程与该客户端保持连接
					 * ManageServerConClient
					 * .addClientThread(u.getAccount(),cct);
					 * cct.start();//启动与该客户端通信的线程 }else{
					 * m.setType(ChatMessageType.FAIL); oos.writeObject(m); }
					 */
				} else if (u.getOperation().equals("register")) {
					newAccount(u);// 新建txt文件来保存密码
					if (true) {
						// 返回一个注册成功的信息包
						boolean tem = true;
						oos.writeObject(tem);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}
