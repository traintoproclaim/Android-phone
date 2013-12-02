package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Screen14_2 extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_white_jesus_icon,iv_people,iv_cross1,iv_heart_big;
	private TextView tv_bottom;
	private int click_no=1;
	private String str_gender;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen14_1);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
       
        iv_people = (ImageView) findViewById(R.id.iv_people_group);
        iv_white_jesus_icon=(ImageView)findViewById(R.id.iv_jesus);
       // iv_perfect_rec=(ImageView)findViewById(R.id.iv_perfect_rec);
        iv_cross1=(ImageView)findViewById(R.id.iv_cross_1);
        iv_heart_big=(ImageView)findViewById(R.id.iv_heart_big);
        
      // tv_center=(TextView)findViewById(R.id.tv_center);
        
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
        iv_cross1.setVisibility(View.VISIBLE);
        iv_people.setBackgroundResource(R.drawable.jesus17);
       
        SharedPreferences myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
       
        str_gender= myPrefs.getString("gender", "group");
        if(str_gender.equalsIgnoreCase("group")){
			    tv_bottom.setText("and to die a cruel, painful death on a cross to take their punishment for them.");
		}
		else if(str_gender.equalsIgnoreCase("male")){
			    tv_bottom.setText("and to die a cruel, painful death on a cross to take his punishment for him.");
		}
		else
			    tv_bottom.setText("and to die a cruel, painful death on a cross to take her punishment for her.");
		
     //   tv_center.setText(Html.fromHtml(" I Love <br>"+ str_name) );
        
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			case 1:
				tv_bottom.setText("And He did!");
				click_no++;
			    break;
				
			case 2:
				tv_bottom.setText("So Jesus died ");
				click_no++;
			    break;
			    
			case 3:
				iv_cross1.setVisibility(View.INVISIBLE);
				iv_white_jesus_icon.setVisibility(View.VISIBLE);
				tv_bottom.setText("and also rose from the dead,");
				click_no++;
			    break;
			    
			case 4:
				iv_white_jesus_icon.setBackgroundResource(R.anim.anim_jesus_white_icon);
				AnimationDrawable  anim = (AnimationDrawable) iv_white_jesus_icon.getBackground(); 
			    anim.start();
				tv_bottom.setText(" proving He is God.");
				click_no++;
			    break;
			    
			case 5:
				iv_white_jesus_icon.setVisibility(View.INVISIBLE);
				iv_people.setVisibility(View.INVISIBLE);
				iv_heart_big.setBackgroundResource(R.anim.anim_heart_zoom_in);
				iv_heart_big.setVisibility(View.VISIBLE);
				tv_bottom.setText("Out of His amazing love for us, Jesus did something that we could never do for ourselves.");
				click_no++;
			    break;
				
			case 6:
				startActivity( new Intent(this, Screen16_soul_transfer.class));
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen14_before_jesus_came_earth.class);
		i.putExtra("back_14", "yes");
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
