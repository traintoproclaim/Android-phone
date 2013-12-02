package com.anuva.GospelPresentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dont_agree_screen  extends BaseActivity implements OnClickListener,AsyncTaskCompleteListener<String>{

	private TextView tv_message;
	private EditText et_email,et_name;
	private String email,name;
	private Button btn_send,btn_end;
	private SharedPreferences  pref;
	private SharedPreferences.Editor edit;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prayer);
       
        tv_message=(TextView)findViewById(R.id.tv_lable_1);
       
        pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		edit = pref.edit();
		
        tv_message.setTextColor(Color.parseColor("#1A1A1A"));
        tv_message.setText("To surrender to Jesus you must agree with this. I would like " +
        		"to send you a one off email that has a link to a free e-book that explains " +
        		"this some more. This will give you time to think about this decision and " +
        		"when you are ready it explains how you can surrender your life to Jesus.");
       
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/dominik.ttf");
                       
        tv_message.setTypeface(tf2);
        
        et_email = (EditText)findViewById(R.id.et_email);
        et_name = (EditText)findViewById(R.id.et_name);
        btn_send = (Button)findViewById(R.id.btn_send);
        
        btn_end=(Button)findViewById(R.id.btn_end);
        btn_end.setVisibility(View.VISIBLE);
        btn_send.setVisibility(View.VISIBLE);
        et_email.setVisibility(View.VISIBLE);
        et_name.setVisibility(View.VISIBLE);
        
        btn_send.setOnClickListener(this);
        btn_end.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		 
		  if(v==btn_send){
				
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
					
					 SharedPreferences myPrefs_1 = Dont_agree_screen.this.getSharedPreferences("gospel_offline_info", MODE_PRIVATE);
			    	 final Editor prefsEditor1 = myPrefs_1.edit();
			    	 int c = myPrefs_1.getInt("email_counter", 0);
			    	 final int a=c+1;
			    	 if(a<=4){
			    		 
			    		 new AlertDialog.Builder(Dont_agree_screen.this)
					      .setMessage(getResources().getString(R.string.alert_text))
					      .setCancelable(true)
					      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
							    		 prefsEditor1.putInt("email_counter", a);
							    		 prefsEditor1.putString("email_"+a, email);
							    		 prefsEditor1.putString("name_"+a, name);
							    		 prefsEditor1.putInt("subject_"+a, 4);
								    	 prefsEditor1.commit();
								    	 
								    		edit.putString("well_done", "Dont_agree_screen");
											edit.commit();
												
											Intent i = new Intent(Dont_agree_screen.this,Screen25_well_done.class);
											startActivity(i);
											finish();
										 
							    	 }
							    	
							    	 
								}
							).show();
			    	 }
			    	 else{
			    			alert1("", " Sorry.  You have already entered 4 email addresses so this cannot be sent.  Please make a note of this email address and add here when you are online.");

			    	 }
					
				  }
		  }
			 }
		  else if(v== btn_end){
			 
			 edit.putString("well_done", "Dont_agree_screen");
			 edit.commit();
				
			 Intent i = new Intent(this,Screen25_well_done.class);
			 startActivity(i);
			 finish();
		  }
	}
	
	 public void launchTask() {
		 SendMailAsyncTask a = new SendMailAsyncTask(Dont_agree_screen.this, this);
		 a.execute(email,name,"4","");
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
				alert1("", "Mail sent successfully. Have a great day!");
								
			}
			Log.i("", "result : "+result);
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
						edit.putString("well_done", "Dont_agree_screen");
						edit.commit();
							
						Intent i = new Intent(Dont_agree_screen.this,Screen25_well_done.class);
						startActivity(i);
						finish();
					}
				}).show();
		}
		
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,Seven_heart_test.class));
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
	    if (cm.getActiveNetworkInfo() != null
	            && cm.getActiveNetworkInfo().isAvailable()
	            && cm.getActiveNetworkInfo().isConnected()) {
	        return true;
	    } else {
	        
	    	return false;
	    }
	}
}
