package com.ori.lukai.chat;


import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Server {

	public void newAccount(User user) {
		try {
			Integer account = new Integer(user.getAccount());
			String a = account.toString();
			File file = new File(".\\user\\"+a+".txt");
			boolean res = file.createNewFile();
			if(!res) System.out.println("failed");
			
			OutputStream os = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeBytes(user.getPassword());
			dos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Server(){
		ServerSocket ss = null;
		try {
                        System.out.println("Poiiiiiiiiiiiii");
			ss=new ServerSocket(5469);
			System.out.println("������������ in "+new Date());
			while(true){
				Socket s=ss.accept();
				//���ܿͻ��˷�������Ϣ
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User) ois.readObject();

				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			      	if(u.getOperation().equals("login")){ //��¼
				        //��ȡ��Ӧ�û��ļ����Ƚϴ洢��������û����������
					Integer account = new Integer(u.getAccount());
					Path path = FileSystems.getDefault().getPath(".\\user", account.toString()+".txt");
					BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
					String get = reader.readLine();
					if(get.equals(u.getPassword())){
						boolean tem = true;
						System.out.println("["+u.getAccount()+"]������!POI!");
						oos.writeObject(tem);
					}else {
						boolean tem = false;
						System.out.println("Password is not correct! Poi!");
						oos.writeObject(tem);
					}
		        	/*
					if(b){
						//System.out.println("["+u.getNick()+"]�����ˣ�");
						
                                                                                                //�õ�������Ϣ
						//String user=udao.getUser(account);
						//����һ���ɹ���½����Ϣ����������������Ϣ
						m.setType(ChatMessageType.SUCCESS);
						m.setContent(user);
						oos.writeObject(m);
						ServerConClientThread cct=new ServerConClientThread(s);//����һ���̣߳��ø��߳���ÿͻ��˱�������
						ManageServerConClient.addClientThread(u.getAccount(),cct);
						cct.start();//������ÿͻ���ͨ�ŵ��߳�
					}else{
						m.setType(ChatMessageType.FAIL);
						oos.writeObject(m);
					}*/
			       	}else if(u.getOperation().equals("register")){	
					newAccount(u);//�½�txt�ļ�����������
					if(true){
		        		//����һ��ע��ɹ�����Ϣ��
						boolean tem = true;
						oos.writeObject(tem);
		        	        }
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args){
		new Server();
	}
}
