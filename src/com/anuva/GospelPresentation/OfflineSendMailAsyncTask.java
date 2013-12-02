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

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class OfflineSendMailAsyncTask extends AsyncTask<String, Void, String> {
	
	private AsyncTaskCompleteListener<String> callback;

	private String subject,email,name,emailcc="";
	public OfflineSendMailAsyncTask(Context context,AsyncTaskCompleteListener<String> cb){
	   
	     this.callback = cb;
	}
	
	  
	  protected void onPreExecute() {  
		  
	  } 
	@Override
	protected String doInBackground(String... arg) {			
		email = arg[0];
		name = arg[1];
		subject = arg[2];
		emailcc=arg[3];
		return sendMail();
	}
	protected void onPostExecute(String result) {
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		callback.onTaskComplete(result);
		
					
		
	}

	private String sendMail() {
		
		String is_sendMail = "false";

		String url = "http://html.traintoproclaim.com/gospel/ReceiveUploads.php";
		
		Log.i("Url ", ""+url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postmethod = new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			
		nameValuePair.add(new BasicNameValuePair("Command","Sendmail"));
		nameValuePair.add(new BasicNameValuePair("email",email));
		nameValuePair.add(new BasicNameValuePair("ccemail",emailcc));
		nameValuePair.add(new BasicNameValuePair("sub",subject));
		nameValuePair.add(new BasicNameValuePair("name",name));
		try {
			postmethod.setEntity(new UrlEncodedFormEntity(nameValuePair));
			String response = httpclient.execute(postmethod,responseHandler);	
			
			Log.i("", "response ++++++++++: "+response);
	      
			JSONObject response_obj=new JSONObject(response);
			
			String response_string = response_obj.getString("Response");
		
			if(response_string.equalsIgnoreCase("Success")){
				
				is_sendMail="true";
			}
			else{
				is_sendMail="false";
			}
		 
	      }
		
		catch (SocketException se) {
	    	Log.i("SOKETTTTTTT", "exception");
	    	is_sendMail="error";
	    	se.printStackTrace();
	   }

		catch (Exception e) {
				e.printStackTrace();
			}
		return is_sendMail;
	}
}


