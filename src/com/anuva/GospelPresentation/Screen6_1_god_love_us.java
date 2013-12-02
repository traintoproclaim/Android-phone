package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen6_1_god_love_us extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_text;
	private FrameLayout fl_1;
	private int click_no=1;
	private ImageView iv_heart,iv_couple,iv_cross_hairs;
	private TextView tv_bottom,tv_1,tv_2,tv_3;
	private LinearLayout ll;
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        fl_1 = (FrameLayout)findViewById(R.id.fl_1);
        
        rl_text =(RelativeLayout)findViewById(R.id.rl_text);
        tv_1 = (TextView)findViewById(R.id.tv_text1);
        tv_2 = (TextView)findViewById(R.id.tv_text2);
        tv_3 = (TextView)findViewById(R.id.tv_text3);
        iv_cross_hairs = (ImageView)findViewById(R.id.iv_cross_hairs);
        iv_heart = (ImageView)findViewById(R.id.iv_heart);
        iv_couple = (ImageView)findViewById(R.id.iv_couple);
        
       // iv_text.setVisibility(View.VISIBLE);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
    
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("I thought God loves us.");
        tv_bottom.setTypeface(tf1);
        tv_1.setTypeface(tf1);
        tv_2.setTypeface(tf1);
        tv_3.setTypeface(tf1);
        
      
    }
    
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch (click_no) {

			case 1:
				tv_bottom.setText("Well, there is more to this, let me explain.");
				click_no++;
				break;

			case 2:
				   rl_text.setVisibility(View.GONE);
				   iv_cross_hairs.setVisibility(View.INVISIBLE);
				   fl_1.setVisibility(View.VISIBLE);
				   tv_bottom.setText("Who is someone you really love & care about?");
				   click_no++;
				   break;
					
			case 3:
				iv_heart.setVisibility(View.INVISIBLE);
				iv_couple.setVisibility(View.INVISIBLE);
				iv_heart.setBackgroundResource(R.anim.anim_heart_blood);
				iv_heart.setVisibility(View.VISIBLE);
				tv_bottom.setText("Now this would be terrible, but imagine someone murdered the person you love.");
				click_no++;
				break;
					
			case 4:
				Intent intent = new Intent(this,
				Screen7_murder_guilty.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen5_heven_or_hell_no_third_place.class);
		i.putExtra("back_5", "yes");
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