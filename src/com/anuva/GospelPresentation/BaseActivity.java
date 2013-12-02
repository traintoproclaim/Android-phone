package com.anuva.GospelPresentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity  extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	 
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu){
	     menu.add("Exit");
	     return true;
	    }
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item){
		 startActivity(new Intent (this,Gospelin7.class));
		 finish();
		 return super.onOptionsItemSelected(item);
	    }
 }
