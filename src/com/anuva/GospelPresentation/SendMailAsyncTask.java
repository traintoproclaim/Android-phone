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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SendMailAsyncTask extends AsyncTask<String, Void, String> {
	
	private AsyncTaskCompleteListener<String> callback;

	private ProgressDialog Dialog;
	private Context context1;
	private String subject,email,name,emailcc="";
	public SendMailAsyncTask(Context context,AsyncTaskCompleteListener<String> cb){
	    this.context1 = context;
	     Dialog = new ProgressDialog(context1);
	     this.callback = cb;
	}
	
	  
	  protected void onPreExecute() {  
		  Dialog.setMessage("Sending Request...");
		  Dialog.show();
	  } 
	@Override
	protected String doInBackground(String... arg) {			
		email = arg[0];
		name = arg[1];
		subject = arg[2];
		emailcc=arg[3];
		
		Log.i("email send", email);
		Log.i("name send", name);
		Log.i("subject send", subject);
		Log.i("emailcc send", emailcc);
		return sendMail();
	}
	protected void onPostExecute(String result) {
		
		try {
			Dialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		callback.onTaskComplete(result);
	}

	private String sendMail() {
		
		String is_sendMail = "false";

		//http://61.12.124.139/gospel/ReceiveUploads.php?Command=Sendmail&email=pankajthorat9@gmail.com&sub=3&name=pankaj
			
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


