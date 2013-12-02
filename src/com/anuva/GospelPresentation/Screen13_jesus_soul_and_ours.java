package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen13_jesus_soul_and_ours extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_circle_jesus,iv_circle_all_of_us,iv_perfert,iv_imperfect;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen13);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        iv_perfert=(ImageView)findViewById(R.id.iv_perfect);
        iv_imperfect=(ImageView)findViewById(R.id.iv_imperfect);
        
        iv_circle_jesus=(ImageView)findViewById(R.id.iv_circle_jesus);
        iv_circle_jesus.setBackgroundResource(R.anim.anim_circle_red);
        iv_circle_all_of_us=(ImageView)findViewById(R.id.iv_circle_ours);
        iv_circle_all_of_us.setBackgroundResource(R.anim.anim_circle_red);
        
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
        tv_bottom.setText("What do you notice is the difference between  ");
    
       
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			case 1:
				iv_circle_jesus.setVisibility(View.VISIBLE);
		        tv_bottom.setText("Jesus’ soul");
		        click_no++;
		        break;
			
			case 2:
				iv_circle_all_of_us.setVisibility(View.VISIBLE);
		        tv_bottom.setText("and ours?");
		        click_no++;
		        break;
			
			case 3:
				iv_imperfect.setVisibility(View.VISIBLE);
				tv_bottom.setText("Well, ours is imperfect ");
				click_no++;
		        break;
			
			case 4:
				//iv_perfert.setVisibility(View.VISIBLE);
				tv_bottom.setText("but Jesus' is ");
				click_no++;
		        break;
		    
			case 5:
				iv_perfert.setVisibility(View.VISIBLE);
				tv_bottom.setText("{PERFECT}.");
				click_no++;
		        break;
		        
			case 6:
				startActivity( new Intent(this, Screen14_before_jesus_came_earth.class));
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen12_jesus_ad_bc.class);
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
