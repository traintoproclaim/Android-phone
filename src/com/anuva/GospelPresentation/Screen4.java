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

public class Screen4 extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private int click_no=1;
	private ImageView iv_quest;
	private TextView tv1,tv2,tv_bottom;
	 private LinearLayout ll;
	    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        iv_quest=(ImageView)findViewById(R.id.iv_quest_mark);
		
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
     
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        tv_bottom.setText("");
       
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
				switch(click_no){
				case 1:
					iv_quest.setVisibility(View.VISIBLE);
					iv_quest.setBackgroundResource(R.anim.anim_quest);
					AnimationDrawable anim = (AnimationDrawable) iv_quest.getBackground();
					anim.start();
					tv2.setVisibility(View.VISIBLE);
			        tv_bottom.setText("Now do you know any human who is entirely perfect?");
					click_no++;
				    break;
				case 2:
					tv_bottom.setText("{No}. Neither do I.");
					click_no++;
				    break;
				case 3:
					Intent intent = new Intent(this,Screen5_heven_or_hell_no_third_place.class);
					startActivity(intent);
					finish();
				    break;	
				}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen3.class);
		i.putExtra("back", "yes");
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
