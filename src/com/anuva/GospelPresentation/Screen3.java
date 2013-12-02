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

public class Screen3 extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_hell,iv_heven,iv_cross_bars,iv_circle,iv_cross,iv_body,iv_soul;
	private int click_no=1;
	private TextView tv_bottom;
	private LinearLayout ll;
	private Button btn;
	private String str_back ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        iv_cross_bars = (ImageView)findViewById(R.id.iv_arrows);
       
        iv_hell=(ImageView)findViewById(R.id.iv_hell);
        iv_heven=(ImageView)findViewById(R.id.iv_heven);
        iv_circle=(ImageView)findViewById(R.id.iv_circle);
        iv_circle.setBackgroundResource(R.anim.anim_circle_it);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_body=(ImageView)findViewById(R.id.iv_body);
        iv_soul=(ImageView)findViewById(R.id.iv_soul);
        iv_cross.setBackgroundResource(R.anim.anim_cross_it);
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		btn = (Button)findViewById(R.id.btn_bottom);
      
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv_bottom.setTypeface(tf1);
        
         str_back = getIntent().getStringExtra("back");
        if(str_back!=null && str_back.equals("yes")){
        	iv_body.setBackgroundResource(R.drawable.body_down8);
        	iv_circle.setBackgroundResource(R.drawable.circle5);
        	iv_soul.setVisibility(View.VISIBLE);
        	iv_circle.setVisibility(View.VISIBLE);
        	iv_cross_bars.setVisibility(View.VISIBLE);
        	iv_heven.setVisibility(View.VISIBLE);
        	iv_hell.setVisibility(View.VISIBLE);
			tv_bottom.setText(" in Hell ");
			click_no=7;
        }
        else{
        	 tv_bottom.setText("The Bible also says that all of us have a body ");
        }
      }

	@Override
	public void onClick(View v) {
		 
		switch(click_no){
		case 1:
			iv_soul.setVisibility(View.VISIBLE);
	        tv_bottom.setText("and a soul and at death your body is either buried");
	        click_no++;
	        break;
		
		case 2:
			iv_body.setVisibility(View.INVISIBLE);	
			iv_body.setBackgroundResource(R.anim.anim_body_down);
			iv_body.setVisibility(View.VISIBLE);
	        tv_bottom.setText(" or cremated.");
	        click_no++;
	        break;
	        
		case 3:
			iv_circle.setVisibility(View.VISIBLE);
	        tv_bottom.setText("But your soul, the real you,");
	        click_no++;
	        break;
	        
		case 4:
			iv_cross_bars.setVisibility(View.VISIBLE);
			tv_bottom.setText(" lives on forever either in");
			click_no++;
		    break;	
		    
		case 5:
			iv_heven.setVisibility(View.VISIBLE);
			tv_bottom.setText("Heaven or");
			click_no++;
		    break;	
		    
		case 6:
			iv_hell.setVisibility(View.VISIBLE);
			tv_bottom.setText(" in Hell ");
			click_no++;
			break;	
			
		case 7:
			iv_cross.setVisibility(View.VISIBLE);
			tv_bottom.setText("and there is no third place.");
			click_no++;
			break;	
			
		case 8:
		    Intent i = new Intent(this, Screen4.class);
	        startActivity(i);
		    finish();
			break;	
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back!=null && str_back.equals("yes")){
			  Intent i = new Intent(this, Screen3.class);
		      startActivity(i);
			  finish();
		  }
		  else{
			  Intent i = new Intent(this,Screen2.class);
			  startActivity(i);
			  finish();
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