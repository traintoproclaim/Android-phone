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

public class Screen2 extends BaseActivity implements OnClickListener  {

	private RelativeLayout rl;
	private TextView tv_bottom;
	private int click_no=1;
	private ImageView iv_link,iv_perfect;
    private LinearLayout ll;
    private LinearLayout ll_bottom;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        iv_link=(ImageView)findViewById(R.id.iv_icon2);
        iv_perfect=(ImageView)findViewById(R.id.iv_icon5);
        ll=(LinearLayout)findViewById(R.id.ll);
        ll_bottom = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
       
		 if(Screen1.text==1){
			 ll_bottom.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("In the beginning, God ");
        
        
		 
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv_bottom.setTypeface(tf1);
    }

	@Override
	public void onClick(View v) {
		
		switch(click_no){
		case 1:
			iv_link.setVisibility(View.VISIBLE);
			iv_perfect.setVisibility(View.VISIBLE);
			ll.setVisibility(View.VISIBLE);

			tv_bottom.setText("made people perfect to have a friendship with Him.");
			click_no++;
			break;
		 
		case 2:
			Intent i = new Intent(this,Screen3.class);
			startActivity(i);
			finish();
			break;
		
		}
		
		
	}

	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen1.class);
		startActivity(i);
		finish();
	
	}
    
	public void onTextClick(View v) {
		
		ll_bottom.setVisibility(View.INVISIBLE);
		btn.setVisibility(View.VISIBLE);
		Screen1.text=1;
	}
	public void onTextButtonClick(View v) {
		ll_bottom.setVisibility(View.VISIBLE);
		btn.setVisibility(View.INVISIBLE);
		Screen1.text=0;
	}
}

