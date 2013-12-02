package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen19_3_2_come_in_front_of_god  extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_comment;
	private ImageView iv_earth,iv_top,iv_jesus;
	private TextView tv_bottom,tv_speech;
	private int click_no=1;
	private SharedPreferences myPrefs;
	private LinearLayout ll;
    private Button btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen19_3);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        myPrefs= this.getSharedPreferences("gospel_pref",MODE_PRIVATE);
        SharedPreferences .Editor edit = myPrefs.edit();
        edit.putString("back_screen20", "Screen19_3_come_in_front_of_god");
        edit.commit();
        
		tv_speech=(TextView)findViewById(R.id.tv_speech);
        iv_earth=(ImageView)findViewById(R.id.iv_world);
        iv_top=(ImageView)findViewById(R.id.iv_top);
       
        rl_comment=(RelativeLayout)findViewById(R.id.rl_comment);
        iv_earth.setBackgroundResource(R.drawable.earth6);
        iv_jesus = (ImageView)findViewById(R.id.iv_jesus);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        iv_top.setBackgroundResource(R.anim.anim_globe);
		iv_top.setVisibility(View.VISIBLE);
		tv_bottom.setText("Fifthly I created a world so beautiful that it was impossible not to know that I was there.");

      
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        tv_speech.setTypeface(tf2);
    }

    @Override
	public void onClick(View v) {
		if(v == rl){
			switch(click_no){
			
			case 1:
				iv_top.setVisibility(View.INVISIBLE);
				iv_jesus.setVisibility(View.VISIBLE);
		        tv_bottom.setText("And finally, I rose from the dead to prove that I was God and that everything I said in the Bible was true.");
		        click_no++;
		        break;
			
			case 2:
				iv_jesus.setVisibility(View.INVISIBLE);
				iv_jesus.setBackgroundResource(R.anim.anim_jesus_white_icon);
				iv_jesus.setVisibility(View.VISIBLE);
		        tv_bottom.setText("Yet, you just did nothing.");
		        click_no++;
		        break;  
		        
			case 3:
				rl_comment.setVisibility(View.VISIBLE);
				tv_speech.setText(Html.fromHtml("I\'m sorry,<br>  I can\'t let you into Heaven"));
		        tv_bottom.setText("I’m sorry I can’t let you into Heaven");
		        click_no++;
		        break;   
		      
			case 4:
				 tv_speech.setText(Html.fromHtml("I have to send you,<br> to Hell."));
				 tv_bottom.setText("I have to send you to Hell.");
			     click_no++;
			     break;  
		
			case 5:
				 rl_comment.setVisibility(View.INVISIBLE);
				 iv_earth.setVisibility(View.INVISIBLE);
				 iv_earth.setBackgroundResource(R.anim.anim_to_down);
				 iv_earth.setVisibility(View.VISIBLE);
				 tv_bottom.setText(" ");
			     click_no++;
			     break;
			     
			case 6:
				startActivity( new Intent(this, Screen20_die_right_now.class));
				finish();
				break;
    			}
			}
		}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) 
	{
	    super.onWindowFocusChanged(hasFocus);
	    AnimationDrawable yourAnimation = 
	        (AnimationDrawable) iv_top.getBackground();
	    if(hasFocus) {
	    	yourAnimation.start();
	    } else {
	    	yourAnimation.stop();
	    }
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen19_3_come_in_front_of_god.class);
		i.putExtra("back_19", "yes");
		startActivity(i);
		finish();
	}
	public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
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