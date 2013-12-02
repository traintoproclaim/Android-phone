package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Screen9_question_3 extends BaseActivity implements OnClickListener {

	private ImageView iv_text_top;
	private ImageButton ib_yes,ib_no;
	private TextView tv_bottom;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;
	 private LinearLayout ll;
	    private Button btn;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen9);
        
        myPrefs = this.getSharedPreferences("gospel_pref",MODE_PRIVATE);
        prefsEditor = myPrefs.edit(); 
        
        iv_text_top = (ImageView)findViewById(R.id.iv_text);
        ib_yes=(ImageButton)findViewById(R.id.ib_yes);
        ib_no=(ImageButton)findViewById(R.id.ib_no);
        ib_yes.setOnClickListener(this);
        ib_no.setOnClickListener(this);
        iv_text_top.setBackgroundResource(R.drawable.hated);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");

        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
   
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("Have you ever felt hatred toward someone; even for a moment?");
        tv_bottom.setTypeface(tf1);
        
        
    }
    
	@Override
	public void onClick(View v) {
	
		if(v==ib_yes){
			 
			 prefsEditor.putString("is_hate", "yes");
		     prefsEditor.commit();
		    
			 Intent i = new Intent(Screen9_question_3.this,Screen9_question_3_hate_yes.class);
			 startActivity(i);
			 finish();
		
		}
		else if(v==ib_no){
			prefsEditor.putString("is_hate", "no");
		    prefsEditor.commit();
		    Intent i = new Intent(this,Screen9_question_4.class);
			startActivity(i);
			finish();
		}
	}
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen9_question_2.class);
		startActivity(i);
		finish();
	
	}
	public void onTextClick(View v) {
		ll.setVisibility(View.INVISIBLE);
		btn.setVisibility(View.VISIBLE);
		Screen1.text=1;
	}
	public void onTextButtonClick(View v) {
		ll.setVisibility(View.VISIBLE);
		btn.setVisibility(View.INVISIBLE);
		Screen1.text=0;
	}
}