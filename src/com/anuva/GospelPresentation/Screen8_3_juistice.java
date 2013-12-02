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

public class Screen8_3_juistice extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	
	private ImageView iv_no_justice;
	private TextView tv_bottom;

	 private LinearLayout ll;
	    private Button btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_1);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
   
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        iv_no_justice = (ImageView)findViewById(R.id.iv_1);
        tv_bottom.setText("no JUSTICE. In the same way, God has to punish us for the laws we have broken, otherwise He’d be just like that judge.");
        tv_bottom.setTypeface(tf1);
        
       

       
    }
    
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			Intent intent = new Intent(this, Screen9_question_first.class);
			startActivity(intent);
			finish();
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen8_2_juistice_text.class);
		startActivity(i);
		finish();
	
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus){
			iv_no_justice.setBackgroundResource(R.anim.anim_no_justice);
			AnimationDrawable  anim = (AnimationDrawable) iv_no_justice.getBackground(); 
		      anim.start();
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