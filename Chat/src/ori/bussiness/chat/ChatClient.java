package ori.bussiness.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;




/**
 * Created by ori on 4/11/2015.
 */
public class ChatClient {
    public Socket socket;
    
    ChatClient(){
    	try {
    	  InetAddress address;
    	  address = InetAddress.getByName("10.0.2.2");
    	  Log.i("Socket", "C: Connecting...");
    	  
    	  socket = new Socket(address,3333);
    	  
    	  Log.i("Socket", "C: Connected");
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    }
    /*
    由于登陆和注册的结果都是要进入myActivity的，所以b获值true即可
     */
    public boolean sendLoginInfo(Object obj) {
    	
        //InetAddress address;
        boolean b = false;
        try {
            //连接SERVER
//            address = InetAddress.getByName("192.168.1.3");
//            socket = new Socket(address,3333);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(obj);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            b = (Boolean)ois.readObject();
            ois.close();
            oos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
    
    public boolean sendRegisterInfo(Object obj){
    	boolean b = false;
    	try{
    		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    		oos.writeObject(obj);
    		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    		b = (Boolean)ois.readObject();

    	}catch (IOException e){
    		e.printStackTrace();
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	return b;
    		
    
    }
}