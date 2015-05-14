package ori.bussiness.chat;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

	public void cancel(View view) {
		startActivity(new Intent(this, LoginActivity.class));
	}

	/*
	 * 实现注册新用户
	 */
	public void registerToServer(View view) {
		User user;
		// 获取用户输入的用户名
		String tem = ((EditText) findViewById(R.id.user_input)).getText()
				.toString();
		if ("".equals(tem)) {
			Toast.makeText(this, "用户名不能为空！Poi！", Toast.LENGTH_SHORT).show();
			return;
		}
		int account = Integer.parseInt(tem);
		// 获取密码并判断是否一致
		String psw = ((EditText) findViewById(R.id.psw_input)).getText()
				.toString();
		String pswC = ((EditText) findViewById(R.id.pswC_input)).getText()
				.toString();
		if (psw.equals(pswC)) {
			user = new User(account, psw);
			user.setOperation("register");

			try {
				Log.v("aaa", "bbb");
				boolean b = new ChatClient().sendRegisterInfo(user);
				Log.v("aaa", "cccc");

				if (b) {
					startActivity(new Intent(this, MyActivity.class));
					Toast.makeText(this, "注册成功! Poi!", Toast.LENGTH_SHORT)
							.show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "两次密码不一致.Poi!", Toast.LENGTH_SHORT).show();
			// 下面可以添加清空EditText框中的内容
		}

	}
}
