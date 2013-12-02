package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen18_turn_surrender extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_turn,iv_turn_text,iv_heart,iv_text_center,iv_surrender,iv_surrender_text,iv_globe;
	private TextView tv_bottom,tv_top,tv_list;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    private String str_back ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen18);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       // rl_2 =(RelativeLayout)findViewById(R.id.rl_2);
        iv_turn=(ImageView)findViewById(R.id.iv_turn_around);
        iv_turn_text=(ImageView)findViewById(R.id.iv_turn_text);
        iv_text_center = (ImageView) findViewById(R.id.iv_text_center);
        iv_surrender=(ImageView)findViewById(R.id.iv_surrender);
        iv_surrender_text=(ImageView)findViewById(R.id.iv_surrender_text);
        iv_globe=(ImageView)findViewById(R.id.iv_globe);
        tv_list=(TextView)findViewById(R.id.tv_list);
        tv_top=(TextView)findViewById(R.id.tv_top);
        iv_heart=(ImageView)findViewById(R.id.iv_heart);
        tv_list.setText(Html.fromHtml( "Lying " +  "<br/>"  
                + "Cheating"  + "<br/>" + 
                "Stealing" + "<br/>" +
                " Sex outside of<br> Marriage" +"</br>" 
                ));
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        tv_top.setTypeface(tf);
        tv_list.setTypeface(tf);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 str_back = getIntent().getStringExtra("back_18");
	        if(str_back!=null && str_back.equals("yes")){
	        	iv_heart.setVisibility(View.VISIBLE);
	        	tv_bottom.setText(" Rather, it’s a change of heart; a sincere desire to live life God’s way.");
	        	click_no=6;
	        }
	        
	        else
	        	tv_bottom.setText("There are two things we must do to have this event.");
    
    	
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			case 1:				
		        tv_bottom.setText("When we are genuinely sorry for breaking God’s laws, we must");
		        click_no++;
		        break;
			case 2:				
				iv_turn.setVisibility(View.VISIBLE);
				iv_turn_text.setVisibility(View.VISIBLE);
				tv_bottom.setText("make a commitment to TURN away from things like");
		        click_no++;
		        break;   
			
			case 3:
				tv_list.setVisibility(View.VISIBLE);
		        tv_bottom.setText(" Lying, cheating, stealing, sex outside of marriage etc ...");
		        click_no++;
		        break;
			
			case 4:
		        tv_bottom.setText(" We’ll never be a perfect person, that’s impossible.");
		        click_no++;
		        break;
			
			case 5:	
				tv_list.setVisibility(View.INVISIBLE);
				iv_heart.setVisibility(View.VISIBLE);
				tv_bottom.setText(" Rather, it’s a change of heart; a sincere desire to live life God’s way.");
				click_no++;
				break;
			case 6:		
				iv_turn.setVisibility(View.INVISIBLE);
				iv_heart.setVisibility(View.INVISIBLE);
				iv_turn.setBackgroundResource(R.anim.anim_turn_away);
				iv_turn.setVisibility(View.VISIBLE);	
				tv_bottom.setText(" It’s like doing a u-turn with your life.");
				click_no++;
				break;
				
			case 7:
				iv_globe.setVisibility(View.INVISIBLE);
				iv_text_center.setVisibility(View.INVISIBLE);
				iv_turn.setVisibility(View.VISIBLE);
				iv_turn_text.setVisibility(View.VISIBLE);
				iv_surrender.setVisibility(View.VISIBLE);
				iv_surrender_text.setVisibility(View.VISIBLE);
				tv_bottom.setText("We must also SURRENDER our lives to Jesus.");
				click_no++;
			    break;
				
			case 8:
				startActivity( new Intent(this, Screen18_2_turn_surrender.class));
				finish();
				break;
		
			
				
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back!=null && str_back.equals("yes")){
			  Intent i = new Intent(this, Screen18_turn_surrender.class);
		      startActivity(i);
			  finish();
		  }
		else{
			Intent i = new Intent(this,Screen17_3_2_major_events.class);
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

