package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen1 extends BaseActivity implements OnClickListener {
	private RelativeLayout rl;
	private int click_no=1;
	private TextView tv1,tv2,tv3,tv4,tv5_1,tv5_2,tv6,tv_bottom;
	public static int text=1;
	private LinearLayout ll;
	private Button btn;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        
        tv1 = (TextView)findViewById(R.id.tv_lable_1);
        tv2 = (TextView)findViewById(R.id.tv_lable_2);
        tv3 = (TextView)findViewById(R.id.tv_lable_3);
        tv4 = (TextView)findViewById(R.id.tv_lable_4);
        tv5_1 = (TextView)findViewById(R.id.tv_lable_5_1);
        tv5_2 = (TextView)findViewById(R.id.tv_lable_5_2);
        tv6 = (TextView)findViewById(R.id.tv_lable_6);
 
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		btn = (Button)findViewById(R.id.btn_bottom);
      
		if(Screen1.text==1 || Quiz1.quiz_text==1){
			 Screen1.text=1;
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		}
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
 
        SharedPreferences pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
        if( pref.getString("back_screen1", "").equalsIgnoreCase("EnterName")){
        	 tv_bottom.setText("A summary of the message of the entire Bible in 6.5 minutes. It would be great to see what you think of it");
		}
        else{
        	tv1.setVisibility(View.VISIBLE);
			tv_bottom.setText("The Bible says that God, the Creator of the universe, is ...");
			click_no++;
        }
        
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv_bottom.setTypeface(tf1);
        
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv6.setTypeface(tf);
        tv5_1.setTypeface(tf);
        tv5_2.setTypeface(tf);
    }
    
	@Override
	public void onClick(View v) {
	 if(v == rl){
		switch(click_no){
		
		case 1:
			tv1.setVisibility(View.VISIBLE);
			tv_bottom.setText("The Bible says that God, the Creator of the universe, is ... ");
			click_no++;
		    break;	
		    
		case 2:
			tv2.setVisibility(View.VISIBLE);
			tv_bottom.setText("holy");
			click_no++;
		    break;	
		    
		case 3:
			tv3.setVisibility(View.VISIBLE);
			tv_bottom.setText("Heaven is ...");
			click_no++;
			break;
			
		case 4:
			tv4.setVisibility(View.VISIBLE);
			tv_bottom.setText("holy");
			click_no++;
			break;
			
		case 5:
			tv5_1.setVisibility(View.VISIBLE);
			tv5_2.setVisibility(View.VISIBLE);
			tv_bottom.setText("and holy simply means");
			click_no++;
		    break;	
		  
		case 6: 
			tv6.setVisibility(View.VISIBLE);
			tv_bottom.setText("PERFECT.");
			click_no++;
			break;
			
		case 7:
			Intent i = new Intent(this,Screen2.class);
			startActivity(i);
			finish();
			break;	
		}
	 }
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,EnterName.class);
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
