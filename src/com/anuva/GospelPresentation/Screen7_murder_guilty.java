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

public class Screen7_murder_guilty extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private int click_no=1;
	private ImageView iv_speech_right,iv_guilty;

	private TextView tv_bottom;
	private LinearLayout ll;
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen7);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        
        iv_speech_right = (ImageView)findViewById(R.id.iv_speech_right);
        iv_guilty = (ImageView)findViewById(R.id.iv_guilty);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
    
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("Then after a huge police hunt they catch the murderer and bring him to court and he pleads");
        tv_bottom.setTypeface(tf1);
        
    }
    
	@Override
	public void onClick(View v) {
		if(v == rl){
		 switch (click_no) {
			case 1:
				iv_guilty.setVisibility(View.VISIBLE);
				tv_bottom.setText("guilty.");
				click_no++;
				break;
			case 2:
				   tv_bottom.setText("But to everyones’ horror the Judge says ");
				   click_no++;
					break;
			case 3:
				iv_speech_right.setVisibility(View.VISIBLE);
				tv_bottom.setText("\"I’m a loving judge, I’m just going to let you off\" ");
				click_no++;
				break;
			
			case 4:
				Intent intent = new Intent(this, Screen8_1_juistice.class);
				startActivity(intent);
				finish();
				break;
				
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen6_1_god_love_us.class);
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
