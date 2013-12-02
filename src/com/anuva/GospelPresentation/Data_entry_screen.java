package com.anuva.GospelPresentation;

import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import android.annotation.SuppressLint;
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
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class Data_entry_screen extends FragmentActivity implements OnClickListener, OnItemSelectedListener {

	private Button btn_send;
	private TextView tv_1,tv_login_name,tv_change_login,et_date;
	private SharedPreferences pref;
	private SharedPreferences.Editor edit_pref1;
	private Spinner spinner_number1,spinner_country1,spinner_number2,spinner_country2,spinner_number3,spinner_country3;
	private String country1 ="",country2="",country3="" ;
	private String no1="0",no2="0",no3="0";
	private ArrayList<String> arrayList;
	private String[] country = {"Afghanistan", "Albania", "Algeria", "America",
			"Andorra", "Angola", "Anguilla", "Antigua and Barbuda",
			"Argentina", "Armenia", "Aruba", "Australia", "Austria",
			"Azerbaijan", "Azores", "Bahamas", "Bahrain", "Bangladesh",
			"Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda",
			"Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil",
			"British Virgin Islan", "Brunei Darussalam", "Bulgaria",
			"Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada",
			"Cape Verde", "Cayman Islands", "Central African Repu", "Chad",
			"Chile", "China", "Colombia", "Comoros", "Congo", "Cook Islands",
			"Corsica", "Costa Rica", "Cote d''Ivoire (Ivory", "Croatia",
			"Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
			"Dominican Republic", "Ecuador", "Egypt", "El Salvador", "England",
			"Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
			"Falkland Islands", "Faroe Islands", "Fiji", "Finland",
			"France (inc. Monaco)", "French Guiana", "French Polynesia",
			"Gabon", "Gambia", "Georgia, Republic of", "Germany", "Ghana",
			"Gibraltar", "Great Britain and No", "Greece", "Greenland",
			"Grenada", "Guadeloupe", "Guatemala", "Guinea", "Guinea Bissau",
			"Guyana", "Haiti", "Holland", "Honduras", "Hong Kong", "Hungary",
			"Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
			"Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan",
			"Kenya", "Kiribati", "Korea(North)", "Korea(South)", "Kuwait",
			"Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao",
			"Macedonia", "Madagascar", "Madeira Islands", "Malawi", "Malaysia",
			"Maldives", "Mali", "Malta", "Marshall Islands", "Martinique",
			"Mauritania", "Mauritius", "Mexico", "Moldova", "Mongolia",
			"Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
			"Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
			"New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria",
			"Norway", "Noumea", "Nuie", "Oman", "Pakistan", "Panama",
			"Papua New Guinea", "Paraguay", "Peru", "Philippines",
			"Pitcairn Island", "Poland", "Portugal", "Qatar", "Republic of",
			"Reunion", "Romania", "Russia", "Rwanda", "Saint Helena",
			"Saint Lucia", "Saint Pierre and Miq", "Saint Vincent and th",
			"Samoa", "San Marino", "SaoTome and Principe", "Saudi Arabia",
			"Scotland", "Senegal", "Serbia Monteneg(Yugo", "Seychelles",
			"Sierra Leone", "Singapore", "Slovak Republic", "Slovenia",
			"Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka",
			"St.Kitts and Nevis", "Sudan", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tahiti", "Taiwan", "Tajikistan",
			"Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago",
			"Trist and aCunha", "Tunisia", "Turkey", "Turkmenistan",
			"Turks and Caicos Isl", "Tuvalu", "Uganda", "Ukraine",
			"United Arab Emirates", "United States", "Uruguay", "Uzbekistan",
			"Vanuatu", "Vatican City", "Venezuela", "Vietnam",
			"Wallis and Futuna Is", "Western Samoa", "Yemen", "Zaire",
			"Zambia", "Zimbabwe", };
	
	private String[] numbers = {"0","1","2","3","4","5","6","7","8","9","10"};
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.data_entry_screen);
	     
	        pref = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
	        edit_pref1 = pref.edit();
	    	String str_name = pref.getString("login_name", "");
	    	String str_country = pref.getString("login_country", "");
	    	
	    	arrayList= new ArrayList<String>(Arrays.asList(country));
	    	if(str_country.length()>0 && !(str_country.equals("Afghanistan"))){
	    		arrayList.add(0, str_country);
	    	}
	    	else{
	    		//arrayList.remove(0);
	    	}
	    	
	        btn_send = (Button)findViewById(R.id.btn_send);
	        spinner_number1 = (Spinner)findViewById(R.id.spinner1);
	        spinner_country1 = (Spinner)findViewById(R.id.sp_country1);
	        spinner_number2 = (Spinner)findViewById(R.id.spinner2);
	        spinner_country2 = (Spinner)findViewById(R.id.sp_country2);
	        spinner_number3 = (Spinner)findViewById(R.id.spinner3);
	        spinner_country3 = (Spinner)findViewById(R.id.sp_country3);
	        et_date =(TextView)findViewById(R.id.et_date);

	        tv_login_name =(TextView)findViewById(R.id.tv_login_name);
	        tv_change_login =(TextView)findViewById(R.id.tv_change_login);
	        tv_1 =(TextView)findViewById(R.id.tv1);
	        
	        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                 "fonts/opificio_rounded.ttf");
	        
	        tv_1.setTypeface(tf);
	        tv_login_name.setTypeface(tf);
	        tv_change_login.setTypeface(tf);
	        tv_login_name.setText(" "+str_name);
	        tv_change_login.setText(Html.fromHtml("<i><u>Change User? </u></i>"));
	        tv_change_login.setOnClickListener(this);
	        
	        btn_send.setOnClickListener(this);

	        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");//dd/MM/yyyy
	        Date now = new Date();
	        String strDate = sdfDate.format(now);
	      
        	et_date.setText(strDate);
        	
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
 	        		android.R.layout.simple_spinner_item, numbers);
 	        	dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);	
 	        
	        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
	 	        		android.R.layout.simple_spinner_item, arrayList);
	 	        	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
	 	        	 
	      	spinner_number1.setAdapter(dataAdapter);
	      	spinner_number1.setOnItemSelectedListener(this);
	      	spinner_country1.setAdapter(dataAdapter1);
	      	spinner_country1.setOnItemSelectedListener(this);
	      	
	      	spinner_number2.setAdapter(dataAdapter);
	      	spinner_number2.setOnItemSelectedListener(this);
	      	spinner_country2.setAdapter(dataAdapter1);
	      	spinner_country2.setOnItemSelectedListener(this);
	      	
	      	spinner_number3.setAdapter(dataAdapter);
	      	spinner_number3.setOnItemSelectedListener(this);
	      	spinner_country3.setAdapter(dataAdapter1);
	      	spinner_country3.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	{
		Spinner spinner = (Spinner) parent;
	     if(spinner.getId() == R.id.spinner1)
	     {
	    	no1= (String) parent.getItemAtPosition(pos);  
	     }
	     else if(spinner.getId() == R.id.spinner2) {
	    	 no2= (String) parent.getItemAtPosition(pos);  
	     }
	     else if(spinner.getId() == R.id.spinner3){
	    	 
	    	 no3= (String) parent.getItemAtPosition(pos);  
	     }
	     else if(spinner.getId() == R.id.sp_country1)
	     {
	         country1 = (String) parent.getItemAtPosition(pos);
	     }
	     else if(spinner.getId() == R.id.sp_country2)
	     {
	    	 country2 = (String) parent.getItemAtPosition(pos);
	     }
	     else if(spinner.getId() == R.id.sp_country3)
	     {
	    	 country3 = (String) parent.getItemAtPosition(pos);
	     }

	}

	public void onNothingSelected(AdapterView<?> arg0){
	    
	}
	
	@Override
	public void onClick(View v) {
		if(v == tv_change_login){
			SharedPreferences  pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
			SharedPreferences.Editor edit = pref.edit();
				
			edit.putString("back_login", "Data_entry_screen");
			edit.commit();
			
	        Intent i = new Intent(Data_entry_screen.this,Login_screen.class);
	        i.putExtra("data_entry", "yes");
	        startActivity(i);
	        finish();
		}
		
		 if(v == btn_send)
		{
			if(no1.equals("0") && no2.equals("0") && no3.equals("0")){
				 startActivity(new Intent(Data_entry_screen.this,congratulation_screen.class));
				 finish();
			}
			else{
				
			if(checkInternetConnection()){
				new AsyncTaskClass().execute();
			}
			else{
				 SharedPreferences myPrefs_1 = Data_entry_screen.this.getSharedPreferences("gospel_offline_info", MODE_PRIVATE);
		    	 final Editor prefsEditor1 = myPrefs_1.edit();
		    	 int c = myPrefs_1.getInt("data_counter", 0);
		    	 final int a=c+1;
		    	 if(a<=4){
		    		 new AlertDialog.Builder(Data_entry_screen.this)
				      .setMessage("Please note:   You are currently offline so this info will be stored until you go back online and then it will be sent. You can only store a maximum of four entries while offline.")
				      .setCancelable(false)
				      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								String str_name = pref.getString("login_name", "");
									 prefsEditor1.putInt("data_counter", a);
							    	 prefsEditor1.putString("user_name", str_name);
							    	 prefsEditor1.putString("country_name1_"+a, country1);
							    	 prefsEditor1.putString("country_name2_"+a, country2);
							    	 prefsEditor1.putString("country_name3_"+a, country3);
							    	 prefsEditor1.putString("country_nos1_"+a, no1);
							    	 prefsEditor1.putString("country_nos2_"+a, no2);
							    	 prefsEditor1.putString("country_nos3_"+a, no3);
							    	 prefsEditor1.commit();
							    	 startActivity(new Intent(Data_entry_screen.this,congratulation_screen.class));
									 finish();
							}
						}).show();
		    	 }
				
		    	 else{
		    			alert1("", " Sorry.  You have already entered four entries so this cannot be sent.  Please make a note of this email address and add here when you are online.");

		    	 }
			}
			}
		}
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu){
	     menu.add("Exit");
	     return true;
	    }
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		 startActivity(new Intent (this,Gospelin7.class));
		 finish();
		 return super.onOptionsItemSelected(item);
	    }
	 
	 @Override
		public void onBackPressed() {
			super.onBackPressed();
			SharedPreferences  pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
			String str = pref.getString("back_data", "");
			
			if(str.equalsIgnoreCase("Screen25_well_done")){
				startActivity(new Intent(this,Screen25_well_done.class));
				finish();
			}
			else{
				startActivity(new Intent(this,Screen25_well_done.class));
				finish();
			}
		}
//#############################################################################
		
		private String dataEnter() {
			
			String is_login = "false";
			String str_name = pref.getString("login_name", "");
			String url = "http://html.traintoproclaim.com/gospel/ReceiveUploads.php";
			
			Log.i("Url ", ""+url);
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost postmethod = new HttpPost(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
				
			Log.i("country_nos1", no1);
			Log.i("country_nos2", no2);
			Log.i("country_nos3", no3);
			
			nameValuePair.add(new BasicNameValuePair("Command","Countrycount"));
			nameValuePair.add(new BasicNameValuePair("user_name",str_name));
			nameValuePair.add(new BasicNameValuePair("country_name1",country1));
			nameValuePair.add(new BasicNameValuePair("country_nos1",no1));
			nameValuePair.add(new BasicNameValuePair("country_name2",country2));
			nameValuePair.add(new BasicNameValuePair("country_nos2",no2));
			nameValuePair.add(new BasicNameValuePair("country_name3",country3));
			nameValuePair.add(new BasicNameValuePair("country_nos3",no3));
			
			try {
				postmethod.setEntity(new UrlEncodedFormEntity(nameValuePair));
				String response = httpclient.execute(postmethod,responseHandler);	
				
				Log.i("", "response ++++++++++: "+response);
				JSONObject response_obj=new JSONObject(response);
				
				String response_string = response_obj.getString("Response");
			
				if(response_string.equalsIgnoreCase("success")){
					String Count = response_obj.getString("Count");
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
			  private ProgressDialog Dialog = new ProgressDialog(Data_entry_screen.this);
			  protected void onPreExecute() {  
				  Dialog.setMessage("Loading...");
				  Dialog.setCancelable(false);
				  Dialog.show();
				  
			  } 
			@Override
			protected String doInBackground(String... arg) {			
				return dataEnter();
			}
			protected void onPostExecute(String result) {
				
				try {
					Dialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if( result == "error"){
					alert("", "No Internet Connection!");		
				}
				else if(result == "false"){
					alert("", "Problem occured!");
				}
				
				else if(result == "true"){
					alert1("Thank you!", "Your results have been entered. Go to www.traintoproclaim.com to see your results and what is happening around the world.");
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
						startActivity(new Intent(Data_entry_screen.this,congratulation_screen.class));
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
