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


public class Screen5_heven_or_hell_no_third_place  extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_cross,iv_perfect_rec_1,iv_arrow,
	iv_heven,iv_hell,iv_all_of_us,iv_hell_arrow;
	private int click_no=1;
	private TextView tv_bottom;
	private LinearLayout ll;
	private Button btn;
	private String str_back ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen5);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        iv_heven= (ImageView)findViewById(R.id.iv_heven);
        iv_hell= (ImageView)findViewById(R.id.iv_hell);
   
        iv_all_of_us= (ImageView)findViewById(R.id.iv_all_of_us);
        iv_hell_arrow= (ImageView)findViewById(R.id.iv_hell_arrow);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_cross.setBackgroundResource(R.anim.anim_cross_it);

        iv_arrow=(ImageView)findViewById(R.id.iv_arrow);
        iv_perfect_rec_1=(ImageView)findViewById(R.id.iv_perfect_rec_1);
        
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
        
        str_back = getIntent().getStringExtra("back_5");
        if(str_back!=null && str_back.equals("yes")){
        	click_no=5;
        	iv_arrow.setVisibility(View.VISIBLE);
        	iv_heven.setVisibility(View.VISIBLE);
        	iv_hell.setVisibility(View.VISIBLE);
        	iv_perfect_rec_1.setVisibility(View.VISIBLE);
        	//iv_cross.setVisibility(View.VISIBLE);
			tv_bottom.setText("and to get to Heaven we must have a perfect record,");

        }
        else
        tv_bottom.setText("So we have a problem.");
        
        
    }

	@Override
	public void onClick(View v) {
		switch(click_no){
		case 1:
			iv_arrow.setVisibility(View.VISIBLE);
	        tv_bottom.setText("If there are only two places our souls can go to at death;");
	        click_no++;
	        break;	
	        
		case 2:
			
			iv_heven.setVisibility(View.VISIBLE);
			tv_bottom.setText("either Heaven");
			click_no++;
		    break;	
		    
		case 3:
			iv_hell.setVisibility(View.VISIBLE);
			tv_bottom.setText("or Hell");
			click_no++;
		    break;	
			
		case 4:
			iv_perfect_rec_1.setVisibility(View.VISIBLE);
			tv_bottom.setText("and to get to Heaven we must have a perfect record,");
			click_no++;
		    break;	
		    
		case 5:
			iv_cross.setVisibility(View.VISIBLE);
			tv_bottom.setText(" and no one has one,");
			click_no++;
			break;	
			
		case 6:
			iv_arrow.setVisibility(View.INVISIBLE);
			iv_heven.setVisibility(View.INVISIBLE);
			iv_hell.setVisibility(View.INVISIBLE);
			iv_perfect_rec_1.setVisibility(View.INVISIBLE);
			iv_cross.setVisibility(View.INVISIBLE);
			tv_bottom.setText("then logically it would seem we are " );
			click_no++;
			break;
			
		case 7:
			iv_all_of_us.setVisibility(View.VISIBLE);
	        tv_bottom.setText("all headed for ... {HELL}.");
	        click_no++;
	        break;	
	        
		case 8:
			iv_hell_arrow.setVisibility(View.VISIBLE);
	        tv_bottom.setText("You may be thinking, “This doesn’t sound right.”");
	        click_no++;
	        break;
	        
		case 9:
			startActivity(new Intent(this,Screen6_1_god_love_us.class));
		    finish();
			break;	
		/*case 10:
			
			startActivity(new Intent(this,Screen5_all_to_hell.class));
			finish();
			break;*/
		}
	 }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back!=null && str_back.equals("yes")){
			  Intent i = new Intent(this, Screen5_heven_or_hell_no_third_place.class);
		      startActivity(i);
			  finish();
		  }
		else{
			Intent i = new Intent(this,Screen4.class);
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
