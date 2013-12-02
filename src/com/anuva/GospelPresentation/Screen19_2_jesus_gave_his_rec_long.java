package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen19_2_jesus_gave_his_rec_long extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_jesus_cross,iv_jesus_you,iv_top,iv_cross;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen19_2);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        
        iv_jesus_cross=(ImageView)findViewById(R.id.iv_jesus_cross);
        iv_jesus_you=(ImageView)findViewById(R.id.iv_jesus_and_you);
        iv_jesus_you.setBackgroundResource(R.anim.anim_soul_tranfer_you);
        iv_top=(ImageView)findViewById(R.id.iv_time_line);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_jesus_you.setBackgroundResource(R.anim.anim_soul_tranfer_you);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        iv_cross.setBackgroundResource(R.anim.anim_cross_it);
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        tv_bottom.setText("Smiling, Jesus would say, \"I took the punishment for you, when I died on the cross,\" ");
    
    	
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			
			case 1:
				
				iv_jesus_you.setVisibility(View.VISIBLE);
		        tv_bottom.setText("\"I gave you my perfect record when you turned and surrendered to Me. Welcome into Heaven!\"");
		        click_no++;
		        break;
		        
			case 2:
				
		        tv_bottom.setText(" Now that’s amazing. Jesus has done something for us we could never do for ourselves. That’s why He’s so incredible!");
		        click_no++;
		        break;
			
			case 3:
				iv_jesus_cross.setVisibility(View.INVISIBLE);
				iv_jesus_you.setVisibility(View.INVISIBLE);
				iv_cross.setVisibility(View.VISIBLE);
				iv_top.setVisibility(View.VISIBLE);
		        tv_bottom.setText("  But if you did not have this event, this is what it would be like ");
		        click_no++;
		        break;
			
			case 4:
				iv_top.setBackgroundResource(R.drawable.timeline_whole1);
				
				tv_bottom.setText("  for you at death.");
				click_no++;
			    break;
			    
			case 5:
				//iv_hell_arrow.setVisibility(View.VISIBLE);
				startActivity( new Intent(this, Screen19_3_come_in_front_of_god.class));
			    finish();
				break;
			
			}
		}
	}
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen19_judgement_day_long.class);
		startActivity(i);
		finish();
	
	}
	public void onWindowFocusChanged (boolean hasFocus) 
	{
	    super.onWindowFocusChanged(hasFocus);
	    AnimationDrawable frameAnimation = 
	        (AnimationDrawable) iv_cross.getBackground();
	    if(hasFocus) {
	        frameAnimation.start();
	    } else {
	        frameAnimation.stop();
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
