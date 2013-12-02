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

public class Screen12_jesus_ad_bc extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_bc_ad,iv_christmas_ester;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen12);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        iv_bc_ad=(ImageView)findViewById(R.id.iv_bc_ad);
        iv_christmas_ester=(ImageView)findViewById(R.id.iv_christmas_ester);
        
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
        tv_bottom.setText("This is where Jesus comes in, the most significant person who has ever lived.");
       
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
		 switch(click_no){
			case 1:
				iv_bc_ad.setVisibility(View.VISIBLE);
		        tv_bottom.setText("He’s the one who split the timeline into BC & AD,");
		        click_no++;
		        break;
			
			case 2:
				iv_christmas_ester.setVisibility(View.VISIBLE);
		        tv_bottom.setText("Christmas and Easter are both based on His life.");
		        click_no++;
		        break;
			
			case 3:
				startActivity( new Intent(this, Screen13_jesus_soul_and_ours.class));
				finish();
				break;
		        
			
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen11_good_news.class);
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