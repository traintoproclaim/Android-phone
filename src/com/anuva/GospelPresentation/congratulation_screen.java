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
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;




public class congratulation_screen  extends BaseActivity implements OnClickListener
{
	private TextView textView,tv_top,tv_count;
	private Button btn_end;
	private	Typeface tf1;
	private	String count="0";
	private SharedPreferences myPrefs_1;
	private Editor prefsEditor1;
	 
	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
        setContentView(R.layout.donation_screen);
        textView = (TextView) findViewById(R.id.tv1);
        tv_top = (TextView) findViewById(R.id.tv_top);
        tv_count = (TextView) findViewById(R.id.tv_count);
        btn_end =(Button)findViewById(R.id.btn_end);
        tv_top.setVisibility(View.VISIBLE);
        tv_count.setVisibility(View.VISIBLE);
        
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
        textView.setTypeface(tf1);
        tv_top.setTypeface(tf1);
        tv_count.setTypeface(tf1);
        
        btn_end.setOnClickListener(this);
        myPrefs_1 = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
 		prefsEditor1 = myPrefs_1.edit();
 		
        if(checkInternetConnection()){
	    	 new AsyncTaskClass().execute();
	    }
        else{
     		count = myPrefs_1.getString("people_count", "0");
        }
       	
		SpannableStringBuilder text = new SpannableStringBuilder(count);  
		text.setSpan(new AbsoluteSizeSpan(20), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
		
		  tv_top.setText(Html.fromHtml("<b><font color=\"#336666\"> CONGRATULATIONS!</font></b>"+
		             "<br/><br/>The number of people you have shared the G7 with is now" ));
		  tv_count.setText(Html.fromHtml("<b>"+count+"</b>"));
		  textView.setText(Html.fromHtml(
	             "<p>You will only know once in Heaven the effect that you are having - keep it up! <br/>"+
	   	         "<p>For more statistics go to <a href=\"http://www.traintoproclaim.com\">www.traintoproclaim.com</a> and log in. <br/>" +
	   	         "<br/>" +
	             "God bless you!</p>"));
        
	    textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public void onClick(View arg0) {
		 if(checkInternetConnection()){
			 Intent i = new Intent(this,Donation_screen.class);
			 startActivity(i);
			 finish();
		 }
		 else{
			 alert("","This number will update when you are next online");
		 }
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,Data_entry_screen.class));
		finish();
	}
	
	private String Getcount() {
		
		String is_login = "true";

		String url = "http://html.traintoproclaim.com/gospel/ReceiveUploads.php";
		/*SharedPreferences pref = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
		SharedPreferences.Editor editot = pref.edit();*/
		String str_name = myPrefs_1.getString("login_name", "");
		Log.i("Login_name in congrats", str_name);
		if(str_name.length()<=0){
			return "false";
		}
		Log.i("Url ", ""+url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postmethod = new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			
		nameValuePair.add(new BasicNameValuePair("Command","Totalcount"));
		nameValuePair.add(new BasicNameValuePair("user",str_name));
		
		try {
			postmethod.setEntity(new UrlEncodedFormEntity(nameValuePair));
			String response = httpclient.execute(postmethod,responseHandler);	
			
			Log.i("", "response in congrats "+response);
			JSONObject response_obj=new JSONObject(response);
			
			String count1 = response_obj.getString("Response");
			
			prefsEditor1.putString("people_count", count1);
			prefsEditor1.commit();
			count = myPrefs_1.getString("people_count", "0");
		
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
		  private ProgressDialog Dialog = new ProgressDialog(congratulation_screen.this);
		  protected void onPreExecute() {  
			  Dialog.setMessage("Loading...");
			  Dialog.setCancelable(false);
			  Dialog.show();
			
		  } 
		@Override
		protected String doInBackground(String... arg) {			
			return Getcount();
		}
		protected void onPostExecute(String result) {
			
			try {
				Dialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(result == "false"){
				
			}
			else {
				Log.i("count = "+count, ".lenght = "+count.length());
				if(count.length()==0 || count==null)
					count="0";
				 tv_count.setText(Html.fromHtml("<b>"+count+"</b>"));
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
					
					Intent i = new Intent(congratulation_screen.this,Donation_screen.class);
					startActivity(i);
					finish();
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
