package com.anuva.GospelPresentation;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_screen extends BaseActivity implements OnClickListener {

	private EditText et_email,et_password;
	private Button btn_send,btn_registration;
	private SharedPreferences  pref;
    private SharedPreferences.Editor prefsEditor1;
    private String email,password;
    private TextView tv1;
    private String country="",Count="0";
    boolean menu_option;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
              
        et_email = (EditText)findViewById(R.id.et_email);
        et_password = (EditText)findViewById(R.id.et_password);
        tv1 = (TextView)findViewById(R.id.tv1);
        
        pref = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
		prefsEditor1 = pref.edit();
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
		
        if(getIntent().getStringExtra("menu_option")!=null){
        
        
        	menu_option=true;
        	tv1.setVisibility(View.VISIBLE);
        	tv1.setTypeface(tf);
        	String str_name = pref.getString("login_name", "");
        	if(str_name.length()>0){
        		tv1.setText("You are logged in as "+str_name);
        	}
        }
        if(getIntent().getStringExtra("data_entry")!=null){
        	
        	tv1.setVisibility(View.VISIBLE);
        	tv1.setTypeface(tf);
        	String str_name = pref.getString("login_name", "");
        	if(str_name.length()>0){
        		tv1.setText("You are logged in as "+str_name);
        	}
        }
        
        btn_send = (Button)findViewById(R.id.btn_submit);
        btn_send.setOnClickListener(this);
        btn_registration = (Button)findViewById(R.id.btn_registor);
        btn_registration.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		 
		  if(v==btn_send){
			  if(checkInternetConnection()){
				   email = et_email.getText().toString();
				password = et_password.getText().toString();
					 if(email.length()==0){
						 Toast.makeText(this, "Please provide Email Address", Toast.LENGTH_SHORT).show();
						 et_email.setText("");
					 }
					 
					 else if(password.length()==0) {
						 Toast.makeText(this, "Please provide Valid Email Address", Toast.LENGTH_SHORT).show();
						 et_email.setText("");
						 et_password.setText("");
					 }
					 else{
						new AsyncTaskClass().execute();
					 }
			    }
			  
			  else{
				  alert("", "Please Check Intenet Connection");
			  }
		 }
		
		  else {
			  if(checkInternetConnection()){
				  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.traintoproclaim.com/index.php?option=com_wrapper&Itemid=64"));
				  startActivity(browserIntent);
			  }
			  else{
				  alert("", "Please Check Intenet Connection");
			  }
		  }
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(menu_option){
			Intent i = new Intent(Login_screen.this,Gospelin7.class);
			startActivity(i);
			finish();
		}
		else{
			SharedPreferences pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
			
			 if( pref.getString("back_login", "Screen25_well_done").equalsIgnoreCase("Screen25_well_done")){
					 Intent i = new Intent(this,Screen25_well_done.class);
					 startActivity(i);
					 finish();
				 }
			 else if( pref.getString("back_login", "Screen25_well_done").equalsIgnoreCase("Data_entry_screen")){
				 Intent i = new Intent(this,Data_entry_screen.class);
				 startActivity(i);
				 finish();
			 }
			   else{
				Intent i = new Intent(this,Get_email_screen.class);
				startActivity(i);
				finish();
			    }
		}
	}
	// #############################################################################
	
	private String Login() {
		
		String is_login = "false";

		String url = "http://html.traintoproclaim.com/gospel/ReceiveUploads.php";
		
		Log.i("Url ", ""+url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postmethod = new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			
		nameValuePair.add(new BasicNameValuePair("Command","Login"));
		nameValuePair.add(new BasicNameValuePair("user",email));
		nameValuePair.add(new BasicNameValuePair("pwd",password));
		try {
			postmethod.setEntity(new UrlEncodedFormEntity(nameValuePair));
			String response = httpclient.execute(postmethod,responseHandler);	
			
			Log.i("", "response ++++++++++: "+response);
			JSONObject response_obj=new JSONObject(response);
			
			String response_string = response_obj.getString("Response");
			//String response_country =response_obj.optJSONObject("Country");
			
			
           if(response_string.equalsIgnoreCase("success")){
        	   Count = response_obj.getString("Count");
        		
    			prefsEditor1.commit();
				is_login="true";
				
				String response_country = response_obj.getString("Country");
				if(response_country!=null){
					country = response_country;
				}
			}
           
			
			else if(response_string.equalsIgnoreCase("error")){
				
				is_login="wrong_psw";
			}
			else{
				is_login="false";
			}
	      }
		
		catch (SocketException se) {
	    	Log.i("SOKETTTTTTT", "exception");
	    	is_login="error";
	    	se.printStackTrace();
	   }

		catch (Exception e) {
				e.printStackTrace();
			}
		return is_login;
	}


	private class AsyncTaskClass extends AsyncTask<String, Void, String> {
		  private ProgressDialog Dialog = new ProgressDialog(Login_screen.this);
		  protected void onPreExecute() {  
			  Dialog.setMessage("Loading...");
			  Dialog.show();
			
		  } 
		@Override
		protected String doInBackground(String... arg) {			
			return Login();
		}
		protected void onPostExecute(String result) {
			
			try {
				Dialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if( result == "error"){
				alert("", "Error Internet Connection Can't be made to Server");		
			}
			else if(result == "false"){
				alert("", "Problem occured while Login");
			}
			else if(result == "wrong_psw"){
				alert("", "Incorrect Username or Password ");
				 et_email.setText("");
				 et_password.setText("");
			}
			else if(result == "true"){
				alert1("", "Login Successful!");
			}
				
			Log.i("", "result : "+result);			
		}
	}
	//===========================================================================================
	private   void alert( String title, String message ){
	      new AlertDialog.Builder(this)
	      .setTitle( title )
	      .setMessage( message )
	      .setCancelable(false)
	      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).show();
	}

	private   void alert1( String title, String message ){
	      new AlertDialog.Builder(this)
	      .setTitle( title )
	      .setMessage( message )
	      .setCancelable(false)
	      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					prefsEditor1.putString("login_name",email);
					prefsEditor1.putString("login_country",country);
					prefsEditor1.putString("people_count", Count);
					prefsEditor1.commit();
					String login_name = pref.getString("login_name", "");
					Log.i("login_name in LOGIN screen", login_name);
					
					if(menu_option){
						Intent i = new Intent(Login_screen.this,Gospelin7.class);
						startActivity(i);
						finish();
					}
					
					else{
						Intent i = new Intent(Login_screen.this,Data_entry_screen.class);
						startActivity(i);
						finish();
					}
				}
			}).show();
	}
	
	public   boolean checkInternetConnection() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (cm.getActiveNetworkInfo() != null
	            && cm.getActiveNetworkInfo().isAvailable()
	            && cm.getActiveNetworkInfo().isConnected()) {
	    	
	        return true;
	    } else {
	        
	    	return false;
	    }
	}
}
