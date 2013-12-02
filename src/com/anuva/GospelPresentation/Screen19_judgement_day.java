package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen19_judgement_day extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_center19;
	private ImageView iv_perfect,iv_hell_arrow,iv_cross,iv_heven_arrow,iv_body;
	private ImageView iv_forgiven;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen19);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        
        rl_center19 = (RelativeLayout)findViewById(R.id.rl_center_19);
      //  rl_center19_top = (RelativeLayout)findViewById(R.id.rl_center_19_top);
        iv_forgiven = (ImageView)findViewById(R.id.iv_forgiven);
        iv_perfect=(ImageView)findViewById(R.id.iv_perfect);
        iv_hell_arrow=(ImageView)findViewById(R.id.iv_hell_arrow);
        iv_heven_arrow=(ImageView)findViewById(R.id.iv_heven_arrow);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_body=(ImageView)findViewById(R.id.iv_body);
        
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
        
        tv_bottom.setText("If you did have this event sometime in your life, then you are forgiven, ");
        
        SharedPreferences pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
        SharedPreferences .Editor edit = pref.edit();
        edit.putString("back_screen20", "Screen19_judgement_day");
        edit.commit();
        
        
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			
			case 1:
				iv_perfect.setVisibility(View.VISIBLE);
		        tv_bottom.setText("have a perfect record.");
		        click_no++;
		        break;
		        
			case 2:
				iv_heven_arrow.setVisibility(View.VISIBLE);
		        tv_bottom.setText(" and God would welcome you into Heaven.");
		        click_no++;
		        break;
			
			case 3:
				rl_center19.setVisibility(View.VISIBLE);
				iv_forgiven.setBackgroundResource(R.drawable.event_forgiven);
				iv_perfect.setVisibility(View.INVISIBLE);
				iv_heven_arrow.setVisibility(View.INVISIBLE);
				iv_cross.setBackgroundResource(R.anim.anim_cross_it);
				iv_cross.setVisibility(View.VISIBLE);
		        tv_bottom.setText("  But if you did not have this event, then you are not forgiven,");
		        click_no++;
		        break;
			
			case 4:
				iv_body.setBackgroundResource(R.drawable.body_gray_1);
				rl_center19.setVisibility(View.VISIBLE);
				iv_perfect.setBackgroundResource(R.drawable.page_13_imperfect);
				iv_perfect.setVisibility(View.VISIBLE);				
				tv_bottom.setText(" have an imperfect record and remember,");
				click_no++;
			    break;
			    
			case 5:
				rl_center19.setVisibility(View.VISIBLE);
				iv_hell_arrow.setVisibility(View.VISIBLE);
				tv_bottom.setText("God would have to be just and send you to Hell.");
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
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen18_2_turn_surrender.class);
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


