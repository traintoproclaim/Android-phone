package com.anuva.GospelPresentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Get_email_screen extends BaseActivity implements OnClickListener ,AsyncTaskCompleteListener<String> {

	private EditText et_email,et_name;
	private Button btn_send,btn_next;
	private SharedPreferences  pref;
	private SharedPreferences.Editor edit;
	private String email,name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_email_screen);
   
    	pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		edit = pref.edit();
		
		//name = pref.getString("audience_name", " ");
        et_email = (EditText)findViewById(R.id.et_email);
        et_name = (EditText)findViewById(R.id.et_name);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		if(v == btn_send){
			
				email = et_email.getText().toString();
				name = et_name.getText().toString();
				 if(email.length()==0 ){
					 Toast.makeText(this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
					 et_email.setText("");
				 }
				 else if(name.length()==0 ){
					 Toast.makeText(this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
					 
				 }
				 else if(!isEmailValid(email))
				 {
					 Toast.makeText(this, "Please provide valid  email address", Toast.LENGTH_SHORT).show();
					 et_email.setText("");
					
				 }
				 else{
					 if(checkInternetConnection()){
					 launchTask();
				 }
			
			 else{
				  
				 SharedPreferences myPrefs_1 = Get_email_screen.this.getSharedPreferences("gospel_offline_info", MODE_PRIVATE);
		    	 final Editor prefsEditor1 = myPrefs_1.edit();
		    	 int c = myPrefs_1.getInt("email_counter", 0);
		    	final int a=c+1;
		    	 if(a<=4){
		    		 
		    		 new AlertDialog.Builder(Get_email_screen.this)
				      .setMessage(getResources().getString(R.string.alert_text))
				      .setCancelable(true)
				      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
						    		 prefsEditor1.putInt("email_counter", a);
						    		 prefsEditor1.putString("email_"+a, email);
						    		 prefsEditor1.putString("name_"+a, name);
						    		 prefsEditor1.putInt("subject_"+a, 5);
							    	 prefsEditor1.commit();
							    	 
							    	 edit.putString("well_done", "Get_email_screen");
									 edit.commit();
										
									 Intent i = new Intent(Get_email_screen.this,Screen25_well_done.class);
									 startActivity(i);
									 finish();
									 
						    	 }
						    	
						    	 
							}
						).show();
		    	 }
		    	 else{
		    			alert1("", " Sorry.  You have already entered four email addresses so this cannot be sent.  Please make a note of this email address and add here when you are online.");

		    	 }
			 }
			}
		}
		else{
			edit.putString("well_done", "Get_email_screen");
			edit.commit();
			
			Intent i = new Intent(this,Screen25_well_done.class);
			startActivity(i);
			finish();
		}
		
	}

	
	 public void launchTask() {
		 SendMailAsyncTask a = new SendMailAsyncTask(Get_email_screen.this, this);
		 a.execute(email,name,"5","");
	    }
	
	 @Override
		public void onTaskComplete(String result) {
			if( result == "error"){
				alert("", "No Internet Connection!");		
			}
			else if(result == "false"){
				alert("", "Problem occured ");
			}
			else if(result == "true"){
				alert1("", " Mail sent successfully. Have a great day!");
			}
			Log.i("", "result : "+result);
		}
		//===========================================================================================
		private   void alert( String title, String message ){
		      new AlertDialog.Builder(this)
		      .setTitle( title )
		      .setMessage( message )
		      .setCancelable(true)
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
		      .setCancelable(true)
		      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						edit.putString("well_done", "Get_email_screen");
						edit.commit();
						
						Intent i = new Intent(Get_email_screen.this,Screen25_well_done.class);
						startActivity(i);
						finish();
					}
				}).show();
		}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
			Intent i = new Intent(this,Have_you_turn_away_screen.class);
			startActivity(i);
			finish();
	}
	
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}
	
	public   boolean checkInternetConnection() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    // test for connection
	    if (cm.getActiveNetworkInfo() != null
	            && cm.getActiveNetworkInfo().isAvailable()
	            && cm.getActiveNetworkInfo().isConnected()) {
	        return true;
	    } else {
	    	return false;
	    }
	}
}
