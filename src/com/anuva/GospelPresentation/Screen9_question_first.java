package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen9_question_first extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_text_top, iv_text_bottom,iv_guilty;
	private ImageButton ib_yes,ib_no;
	private TextView tv_bottom;
	private LinearLayout ll_yes_no;
	private int rl_click=0;
	private LinearLayout ll;
    private Button btn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen9);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setClickable(false);
        
        //iv_text_top = (ImageView)findViewById(R.id.iv_text);
        ib_yes=(ImageButton)findViewById(R.id.ib_yes);
       
        if(ib_yes.isFocused()){
        	Log.i("yes buttonn", "is focused");
        }
        
        ib_no=(ImageButton)findViewById(R.id.ib_no);
        ll_yes_no=(LinearLayout)findViewById(R.id.ll_yes_no);
        ib_yes.setOnClickListener(this);
        ib_no.setOnClickListener(this);
        
        iv_text_bottom=(ImageView)findViewById(R.id.iv_text_bottom);
        iv_text_top =(ImageView)findViewById(R.id.iv_text);
        iv_guilty=(ImageView)findViewById(R.id.iv_guilty);
        iv_guilty.setBackgroundResource(R.anim.anim_guilty);
        
        
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");

        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("For example I’ve told a lie before. Have you?");
        tv_bottom.setTypeface(tf1);
        
       
    }
    
	@Override
	public void onClick(View v) {
	
		if(v==ib_yes){
		//	ib_yes.setBackgroundResource(R.drawable.yes_btn_selected);
			ll_yes_no.setVisibility(View.GONE);
			iv_text_top.setVisibility(View.GONE);
			
			rl.setClickable(true);
			iv_text_bottom.setVisibility(View.VISIBLE);
			tv_bottom.setText("Technically that makes us both LIARS.");
			rl.setOnClickListener( new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(rl_click==0){
						iv_guilty.setVisibility(View.VISIBLE);
						rl_click++;
					}
					else if(rl_click==1){
						 Intent i = new Intent(Screen9_question_first.this,Screen9_question_2.class);
						 startActivity(i);
						 finish();
					}
				}
			});
		}
		else if(v==ib_no){
			ib_no.setBackgroundResource(R.drawable.no_btn_selected);
			rl.setClickable(true);
			ll_yes_no.setVisibility(View.GONE);
			iv_text_top.setVisibility(View.GONE);
			iv_text_bottom.setVisibility(View.VISIBLE);
			tv_bottom.setText("Really? Never in your life? Not even a ‘white lie’? Everyone has at some stage!");
			
			 rl.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(rl_click==0){
							iv_guilty.setVisibility(View.VISIBLE);
							rl_click++;
						}
						else if(rl_click==1){
							 Intent i = new Intent(Screen9_question_first.this,Screen9_question_2.class);
							 startActivity(i);
							 finish();
							
						}
					}
				});
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen8_3_juistice.class);
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
