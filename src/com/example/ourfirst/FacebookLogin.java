package com.example.ourfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookLogin extends Activity {
	
	Facebook facebook = new Facebook("431893480184932");
	public static String TAG = "Facebook";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);

		facebook.authorize(this, new DialogListener() {
			// @Override
			public void onComplete(Bundle values) {
				Intent intent = new Intent(FacebookLogin.this,TabsActivity.class);
				Log.d(TAG, "onComplete");
				startActivity(intent);
			}

			// @Override
			public void onFacebookError(FacebookError error) {
				Intent intent = new Intent(FacebookLogin.this,WelcomeScreen.class);
				Log.d(TAG, "onFacebookError");
				startActivity(intent);
			}

			// @Override
			public void onError(DialogError e) {
				Log.d(TAG, "onError");
				Intent intent = new Intent(FacebookLogin.this,WelcomeScreen.class);
				startActivity(intent);
			}

			// @Override
			public void onCancel() {
				Log.d(TAG, "onCancel");
				Intent intent = new Intent(FacebookLogin.this,WelcomeScreen.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		facebook.authorizeCallback(requestCode, resultCode, data);
	}

}
