package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen10_list_of_guilty extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private int click_no=1;
	private FrameLayout fl_thieves,fl_haters,fl_blasphemy,fl_idolatry;
	private TextView tv_bottom;
	private int back;
	private SharedPreferences myPrefs;
	private LinearLayout ll;
	private Button btn;
	private String str1="",str2="",str3="",str4="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen10);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        myPrefs= this.getSharedPreferences("gospel_pref",MODE_PRIVATE);
        
        
        fl_haters = (FrameLayout)findViewById(R.id.fl_muderes);
        fl_thieves = (FrameLayout)findViewById(R.id.fl_thievs);
        
        fl_blasphemy = (FrameLayout)findViewById(R.id.fl_basphemy);
        fl_idolatry = (FrameLayout)findViewById(R.id.fl_idolatry);
        
        if(myPrefs.getString("is_stolen", "no").equalsIgnoreCase("yes")){
        	fl_thieves.setVisibility(View.VISIBLE);
        	str1= ", thieves";
        }
        
        if(myPrefs.getString("is_hate", "no").equalsIgnoreCase("yes")){
        	fl_haters.setVisibility(View.VISIBLE);
        	str2 = ", murderers";
        	back=1;
        }
        else if(myPrefs.getString("is_blasphemy", "no").equalsIgnoreCase("yes")) {
        	fl_blasphemy.setVisibility(View.VISIBLE);
        	str3 = ", blasphemers";
        	back=2;
        }
        else if(myPrefs.getString("is_idolatry", "no").equalsIgnoreCase("yes")) {
        	fl_idolatry.setVisibility(View.VISIBLE);
        	str4 = ", idolaters";
        	back=3;
        }
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
    
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("So by God’s Standards we are liars"+str1+str2 +str3+str4+" ...");
        tv_bottom.setTypeface(tf1);
        
        
    }
    
	@Override
	public void onClick(View v) {
		if(v == rl){
		 switch (click_no) {
			case 1:
			    tv_bottom.setText("Obviously we shouldn’t be allowed into a perfect Heaven; we deserve punishment in Hell. That’s the bad news. ");
				click_no++;
				break;
			
			case 2:
				Intent intent = new Intent(this, Screen11_good_news.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i;
		Log.i("Back_screen 10", ""+back);
		switch(back){
	
		case 1:
			i = new Intent(this,Screen9_question_3_hate_yes.class);
			startActivity(i);
			finish();
			break;
	
		case 2:
			i = new Intent(this,Screen9_question_4.class);
			startActivity(i);
			finish();
			break;
			
		case 3:
			i = new Intent(this,Screen9_question_5.class);
			startActivity(i);
			finish();
			break;
		default:
			i = new Intent(this,Screen9_question_5.class);
			startActivity(i);
			finish();
			break;
	}
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
