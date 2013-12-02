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

public class Screen9_question_3_hate_yes extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_inner;
	private ImageView iv_guilty,iv_text_top,iv_text_murrderers;
	private TextView tv_bottom,tv_text_bottom;
	
	private int rl_click=0;
	private LinearLayout ll;
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen9_hate_yes);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl_inner = (RelativeLayout)findViewById(R.id.rl_inner);
        rl.setOnClickListener(this);
        
        iv_guilty=(ImageView)findViewById(R.id.iv_guilty);
        tv_text_bottom=(TextView)findViewById(R.id.tv_text_bottom);
        iv_text_top = (ImageView) findViewById(R.id.iv_text_top);
        iv_text_murrderers = (ImageView) findViewById(R.id.iv_text_murrderers);
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
		tv_bottom.setText("Me too. The Bible says if we’ve hated someone it’s the same as");
        tv_bottom.setTypeface(tf1);
        tv_text_bottom.setTypeface(tf1);
    }
    
	@Override
	public void onClick(View v){
		
	if(rl_click==0)
	{
		tv_text_bottom.setText("MURDERED");
		tv_bottom.setText("murdering them in our hearts.");
		//tv_bottom.setText("So by God’s standards we’re also MURDERERS");
		rl_click++;
	}
	else if(rl_click == 1)
	{	
		iv_text_top.setVisibility(View.GONE);
		rl_inner.setVisibility(View.GONE);
		tv_text_bottom.setVisibility(View.GONE);
		iv_text_murrderers.setVisibility(View.VISIBLE);
		
		tv_bottom.setText("So by God’s standards we’re also MURDERERS.");
		rl_click++;
	}
	else if(rl_click == 2)
	{	
		iv_guilty.setVisibility(View.VISIBLE);
		rl_click++;
	}
	else {
		Intent i = new Intent(this,Screen10_list_of_guilty.class);
		startActivity(i);
		//i.putExtra("back", "Screen9_question_3_hate_yes");
		finish();
	}
		
	}
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen9_question_3.class);
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
