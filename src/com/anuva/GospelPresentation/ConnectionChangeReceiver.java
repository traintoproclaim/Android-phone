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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

 public class ConnectionChangeReceiver extends BroadcastReceiver implements AsyncTaskCompleteListener<String>{
	 private SharedPreferences myPrefs_1;
	 private Editor prefsEditor1;
	 private String str_name;
	 private SharedPreferences  pref;
	 private SharedPreferences.Editor edit_pref1;
	 @Override
	 public void onReceive( Context context, Intent intent )
	  {
		 if (context.getContentResolver() == null) {
	         
	         return;
	        }
		 
	    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
	    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
	  //  NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE );
	    
	    //=======================================================
	    
	    // to do also check for data traffic.
	   /* if (mobNetInfo.getType() == ConnectivityManager.TYPE_WIFI) 
	    	Toast.makeText( context, "Have Wifi Connection", Toast.LENGTH_SHORT ).show();
        else
        	Toast.makeText( context, "Dont Have Wifi Connection", Toast.LENGTH_SHORT ).show();*/

	    if ( activeNetInfo != null ){
	      //Toast.makeText( context, "Active Network Type : " + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT ).show();
	       myPrefs_1 = context.getSharedPreferences("gospel_offline_info", 0);
	       prefsEditor1 = myPrefs_1.edit();
	    	
		  pref = context.getSharedPreferences("gospel_persistence", 0);
		   edit_pref1=pref.edit();
		   str_name = pref.getString("login_name", "");
	       int c = myPrefs_1.getInt("email_counter", 0);
		    if(c>0){
		    	Log.i("Sending mail", "fgvfdvgfdgf");
		    	
		    	for(int i=1;i<=c;i++){
		    		Log.i("Sending mail", "for "+i);
		    		
		    		OfflineSendMailAsyncTask a = new OfflineSendMailAsyncTask(context, this);
		    		String email = myPrefs_1.getString("email_"+i, "");
		    		String ccemail = myPrefs_1.getString("ccemail_"+i, "");
		    		String name =myPrefs_1.getString("name_"+i, "");
		    		int sub = myPrefs_1.getInt("subject_"+i, 2);
		    		a.execute(email,name,""+sub,ccemail);
		    	  }
		     }
		    prefsEditor1.putInt("email_counter", 0);
			prefsEditor1.commit();
			 
		    int d = myPrefs_1.getInt("data_counter", 0);
	    	if(d>0){
	    		Log.i("Sending data", "fojkjdfs");
	    		for(int i=1;i<=d;i++){
		    		Log.i("Sending data", "for "+i);
		    		
		    		String country1 = myPrefs_1.getString("country_name1_"+i,"");
		    		String country2 = myPrefs_1.getString("country_name2_"+i,"");
		    		String country3 = myPrefs_1.getString("country_name3_"+i,"");
		    		String country_nos1 = myPrefs_1.getString("country_nos1_"+i,"");
		    		String country_nos2 = myPrefs_1.getString("country_nos2_"+i,"");
		    		String country_nos3 = myPrefs_1.getString("country_nos3_"+i,"");
		    		
		    		 new AsyncTaskClass().execute(country1,country2,country3,
		    				 country_nos1,country_nos2,country_nos3);
	    		}
	    	}
	    	
	    	 prefsEditor1.putInt("data_counter", 0);
			 prefsEditor1.commit();
	    	 //prefsEditor1.clear();
	    	 //prefsEditor1.commit();
	    }
	    
	   /* if( mobNetInfo != null ){
	      Toast.makeText( context, "Mobile Network Type : " + mobNetInfo.getTypeName(), Toast.LENGTH_SHORT ).show();
	    }*/
	    
	    return;
	  }

	@Override
	public void onTaskComplete(String result) {
		
	}
	private String dataEnter(String country1,String country2,String country3,
			String country_nos1,String country_nos2,String country_nos3) {
		
		String is_login = "false";
		Log.i("str_name  gtggt ", ""+str_name);
		String url = "http://html.traintoproclaim.com/gospel/ReceiveUploads.php";
		
		Log.i("country1 ", "b "+country1);
		Log.i("country2 ", "b "+country2);
		Log.i("country3 ", "b "+country3);
		Log.i("country_nos1 ", "b "+country_nos1);
		Log.i("country_nos2 ", "b "+country_nos2);
		Log.i("country_nos3 ", "b "+country_nos3);
		
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postmethod = new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		
		nameValuePair.add(new BasicNameValuePair("Command","Countrycount"));
		nameValuePair.add(new BasicNameValuePair("user_name",str_name));
		nameValuePair.add(new BasicNameValuePair("country_name1",country1));
		nameValuePair.add(new BasicNameValuePair("country_nos1",country_nos1));
		nameValuePair.add(new BasicNameValuePair("country_name2",country_nos2));
		nameValuePair.add(new BasicNameValuePair("country_nos2",country2));
		nameValuePair.add(new BasicNameValuePair("country_name3",country3));
		nameValuePair.add(new BasicNameValuePair("country_nos3",country_nos3));
		
		try {
			postmethod.setEntity(new UrlEncodedFormEntity(nameValuePair));
			String response = httpclient.execute(postmethod,responseHandler);	
			
			Log.i("", "response ++++++++++: "+response);
			JSONObject response_obj=new JSONObject(response);
			String response_string = response_obj.getString("Response");
		
			if(response_string.equalsIgnoreCase("success")){
				String Count = response_obj.getString("Count");
				Log.i("Count in connection", "countttt "+Count);
				edit_pref1.putString("people_count", Count);
				edit_pref1.commit();
				is_login="true";
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
		 
		@Override
		protected String doInBackground(String... arg) {			
			return dataEnter(arg[0],arg[1],arg[2],arg[3],arg[4],arg[5]);
		}
		protected void onPostExecute(String result) {
			
			if( result == "error"){
				
			}
			
			else if(result == "false"){
				
			}
			
			else if(result == "true"){
				 prefsEditor1.putInt("Data_entery", 0);
				 prefsEditor1.commit();
			}
				
			Log.i("", "result upload data : "+result);			
		}
	}
 }
