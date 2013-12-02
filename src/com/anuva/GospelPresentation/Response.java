package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Response extends BaseActivity implements  OnCheckedChangeListener {

	private RadioGroup radio_group;
	private RadioButton rb1,rb2,rb3,rb4;
    private TextView tv_top;
    private String str_back;
    private SharedPreferences myPrefs;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response);
        
        myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
        
        str_back = myPrefs.getString("video_back","no");
        tv_top=(TextView)findViewById(R.id.textView1);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_bold.ttf");
        
        tv_top.setTypeface(tf1);
        radio_group = (RadioGroup)findViewById(R.id.radioGroup);
        rb1=(RadioButton)findViewById(R.id.rb_dnt_belive);
        rb2=(RadioButton)findViewById(R.id.rb_might_thought);
        rb3=(RadioButton)findViewById(R.id.rb_explore_futher);
        rb4=(RadioButton)findViewById(R.id.rb_surrender_now);
        
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
       
		
		
        rb1.setTypeface(tf2);
        rb2.setTypeface(tf2);
        rb3.setTypeface(tf2);
        rb4.setTypeface(tf2);
        
        radio_group.setOnCheckedChangeListener(this);
        Button btn_send = (Button)findViewById(R.id.btn_ok);
        btn_send.setVisibility(View.INVISIBLE);
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch(checkedId){
		
			case R.id.rb_dnt_belive:
				Intent i = new Intent(this,Response_second_screen.class);
				i.putExtra("selection", "dnt_belive");
				startActivity(i);
				finish();
				break;
				
			case R.id.rb_might_thought:
				Intent i1 = new Intent(this,Response_second_screen.class);
				i1.putExtra("selection", "might_thought");
				startActivity(i1);
				finish();
				break;
				
			case R.id.rb_explore_futher:
				Intent i2 = new Intent(this,Response_second_screen.class);
				i2.putExtra("selection", "explore_futher");
				startActivity(i2);
				finish();
				break;
				
			case R.id.rb_surrender_now:
				Intent i3 = new Intent(this,Response_second_screen.class);
				i3.putExtra("selection", "surrender_now");
				startActivity(i3);
				finish();
				break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		radio_group.clearCheck();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back.equals("yes")){
			Intent i = new Intent(this,Gospelin7.class);
			startActivity(i);
			finish();
		}
		else{
			Intent i = new Intent(this,Screen24_heven.class);
			startActivity(i);
			finish();
		}
	}
	
 }
