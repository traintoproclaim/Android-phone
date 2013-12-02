package com.anuva.GospelPresentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class Response_second_screen extends BaseActivity implements OnClickListener, AsyncTaskCompleteListener<String>  {
	
	private TextView tv_message;
	private String response;
    private Button btn_next1,btn_send,btn_end;
    private EditText et_email,et_name;
    private String email,name,subject;
    private SharedPreferences  pref;
    private SharedPreferences.Editor edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responce_second_screen);
        
        pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		edit = pref.edit();
        tv_message=(TextView)findViewById(R.id.tv_message);
        response = getIntent().getStringExtra("selection");
        
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/dominik.ttf");
        tv_message.setTypeface(tf2);
        
        btn_next1=(Button)findViewById(R.id.btn_next);
        btn_end=(Button)findViewById(R.id.btn_end);
        btn_send=(Button)findViewById(R.id.btn_send);
        et_email=(EditText)findViewById(R.id.et_email);
        et_name=(EditText)findViewById(R.id.et_name);
      
        btn_next1.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        
        if(response==null){
        	response="";
        }
        if(response.equals("dnt_belive")){
        	tv_message.setText("Thank you for your time today, I do hope you found it " +
        			"interesting. I would encourage you to give it some more thought, eternity is a long time and you really want to make sure you get this right.");
        	et_email.setVisibility(View.GONE);
        	et_name.setVisibility(View.GONE);
        	btn_send.setVisibility(View.GONE);
        	btn_end.setVisibility(View.VISIBLE);
        	btn_end.setBackgroundResource(R.drawable.theendbtn);
        	
        }
        else if(response.equals("might_thought")){
        	btn_end.setVisibility(View.VISIBLE);
        	tv_message.setText("Thank you for that. Would you like to receive a " +
        			"one off email with links to a website that has answers to questions" +
        			" like ‘How do you know if the Bible is true?’ and ‘What about the other religions’? ");
           
            subject="1";
            
        }
        
        else if(response.equals("explore_futher")){

        	btn_end.setVisibility(View.VISIBLE);
        	tv_message.setText("That’s great! Do you have someone you " +
        			"know that can help you with this? Can I help? Would you like to receive " +
        			"a one off email with a link to a free e-book that explains a bit more about this? ");
        	
        	subject="2";
        }
        
        else if(response.equals("surrender_now")){
        	tv_message.setText("Excellent! I am happy to help you with this! Turning and" +
        			" Surrendering your life to Jesus is a massive commitment that involves a" +
        			" heart attitude change. Let’s go through 7 heart attitudes to check that you are ready:");
        
        	btn_next1.setVisibility(View.VISIBLE);
        	et_email.setVisibility(View.GONE);
        	et_name.setVisibility(View.GONE);
        	btn_send.setVisibility(View.GONE);
        }
    }
	@Override
	public void onClick(View v) {
		if(v==btn_next1){
			Intent i1 = new Intent(this,Seven_heart_test.class);
			startActivity(i1);
			finish();
		}
		else if(v==btn_send){

				email= et_email.getText().toString();
				name= et_name.getText().toString();
				if(email.length()==0){
					 Toast.makeText(this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
				}
				else if(name.length()==0){
					 Toast.makeText(this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
				}
				else if(!isEmailValid(email)){
					 et_email.setText("");
					 Toast.makeText(this, "Please provide valid  email address", Toast.LENGTH_SHORT).show();
				}
				else{
					if(checkInternetConnection()){
						launchTask();
					}
					
			 else{
					 SharedPreferences myPrefs_1 = Response_second_screen.this.getSharedPreferences("gospel_offline_info", MODE_PRIVATE);
			    	 final Editor prefsEditor1 = myPrefs_1.edit();
			    	 int c = myPrefs_1.getInt("email_counter", 0);
			    	 final int a=c+1;
			    	 if(a<=4){
			    		 
			    		 new AlertDialog.Builder(Response_second_screen.this)
					      .setMessage(getResources().getString(R.string.alert_text))
					      .setCancelable(true)
					      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
							    		 prefsEditor1.putInt("email_counter", a);
							    		 prefsEditor1.putString("email_"+a, email);
							    		 prefsEditor1.putString("name_"+a, name);
							    		 prefsEditor1.putInt("subject_"+a,  Integer.parseInt(subject));
								    	 prefsEditor1.commit();
								    	 
								    	 edit.putString("well_done", "Response");
											edit.commit();
											Intent i = new Intent(Response_second_screen.this,Screen25_well_done.class);
											startActivity(i);
											finish();
							    	 }
								}
							).show();
			    		
					}
			    	 else{
			    			alert1("", " Sorry. You have already entered four email addresses so this cannot be sent. Please make a note of this email address and add here when you are online.");
			    	 }
			 }
				}
			}
		else{
			//btn is end
			edit.putString("well_done", "Response");
			edit.commit();
			Intent i = new Intent(this,Screen25_well_done.class);
			startActivity(i);
			finish();
		}
	}
	 public void launchTask() {
		 SendMailAsyncTask a = new SendMailAsyncTask(Response_second_screen.this, this);
		 a.execute(email,name,subject,"");
	    }
	 
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,Response.class));
		finish();
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
		private   void alert1( String title, String message ){
		      new AlertDialog.Builder(this)
		      .setTitle( title )
		      .setMessage( message)
		      .setCancelable(false)
		      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						edit.putString("well_done", "Response");
						edit.commit();
						Intent i = new Intent(Response_second_screen.this,Screen25_well_done.class);
						startActivity(i);
						finish();
					}
				}).show();
		}
}
