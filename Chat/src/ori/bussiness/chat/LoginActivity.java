package ori.bussiness.chat;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	按下“注册”按钮执行此程序
	 */
	    public void register(View view){
	        Intent intent = new Intent(this,RegisterActivity.class);
	        startActivity(intent);

	    }
	/*
	按下“登陆”按钮执行此程序
	 */
	    public void login(View view){
	        //获取登陆界面的账户名
	        String tem = ((EditText)findViewById(R.id.chat_account)).getText().toString();
	        //检测EDITTEXT是否为空，因为Integer不能将空字符转为INT
	        if("".equals(tem)) {
	            Toast.makeText(this, "用户名不能为空！Poi！", Toast.LENGTH_SHORT).show();
	            return;
	        }
	        int account = Integer.parseInt(tem);
	        //获取密码
	        String password = ((EditText)findViewById(R.id.chat_password)).getText().toString();
	        isTrue(account, password);

	    }

	    public void isTrue(int a, String p) {
	        User user = new User(a,p);
	        user.setOperation("login");
	        boolean b = new ChatClient().sendLoginInfo(user);  //将User传至Server判断是否为注册用户
	        if(b) {
	            Intent intent = new Intent(this,MyActivity.class);
	            startActivity(intent);
	        }else {
	           Toast.makeText(this,"Fail To Connect! POI!",Toast.LENGTH_SHORT).show();
	        }

	    }
}
