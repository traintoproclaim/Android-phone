package com.anuva.GospelPresentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Screen25_well_done extends BaseActivity implements OnClickListener {

	private TextView tv_top,tv_1,tv_2;
	private Button btn_next;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen25);
        
        
        tv_top=(TextView)findViewById(R.id.tv_top);
        tv_1=(TextView)findViewById(R.id.tv_1);
        tv_2=(TextView)findViewById(R.id.tv_2);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_bold.ttf");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        tv_top.setText(Html.fromHtml("THE END<br>" +
        		"Have a great Day!"));
        
        tv_top.setTypeface(tf1);
        tv_1.setTypeface(tf);
        tv_2.setTypeface(tf);
        
        btn_next=(Button)findViewById(R.id.btn);
        btn_next.setOnClickListener(this);
        
        tv_2.setText(Html.fromHtml(" Well done!" +"</br>" +
        " Time to upload your data encouraging "+"</br>"+
        		"other Christians around the world!"));
    }

	@Override
	public void onClick(View v) {
		
		SharedPreferences myPrefs_1 = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
		String login_name = myPrefs_1.getString("login_name", "");
		Log.i("login_name in well done screen", login_name);
		if(login_name.length()>0){
			
			SharedPreferences  pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
			SharedPreferences.Editor edit = pref.edit();
				
			edit.putString("back_data", "Screen25_well_done");
			edit.commit();
			
			Intent i = new Intent(this, Data_entry_screen.class);
			startActivity(i);
			finish();
		}
		else{
			alert("","Whooops ... you have not yet logged in so you can't go any further.  Please do so on the next screen. You only need to do this once!");
		}
		
	}
	
	@Override
	public void onBackPressed() {
		SharedPreferences pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		// Log.i("pref.getString (well_done)", pref.getString("well_done", ""));
		 if( pref.getString("well_done", "").equalsIgnoreCase("Get_email_screen")){
				Intent i = new Intent(this,Get_email_screen.class);
				startActivity(i);
				finish();
			 }
		 else  if( pref.getString("well_done", " ").equalsIgnoreCase("Prayer")){
				Intent i = new Intent(this,Prayer.class);
				startActivity(i);
				finish();
			 }
		   else if( pref.getString("well_done", " ").equalsIgnoreCase("Dont_agree_screen")){
				Intent i = new Intent(this,Dont_agree_screen.class);
				startActivity(i);
				finish();
			 }
		   else{
			   super.onBackPressed();
				startActivity(new Intent(this,Response.class));
				finish();
		    }
       }
	/*public void onTextClick(View v) {
		ll.setVisibility(View.INVISIBLE);
		btn.setVisibility(View.VISIBLE);
	}
	public void onTextButtonClick(View v) {
		ll.setVisibility(View.VISIBLE);
		btn.setVisibility(View.INVISIBLE);
		Screen1.text=0;
	}
	*/
	private   void alert( String title, String message ){
	      new AlertDialog.Builder(this)
	      //.setTitle( title )
	      .setMessage( message )
	      .setCancelable(true)
	      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					SharedPreferences  pref = Screen25_well_done.this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
					SharedPreferences.Editor edit = pref.edit();
						
					edit.putString("back_login", "Screen25_well_done");
					edit.commit();
					
					Intent i = new Intent(Screen25_well_done.this, Login_screen.class);
					startActivity(i);
					finish();
				}
			}).show();
	}
  }
