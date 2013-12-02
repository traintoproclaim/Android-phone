package com.anuva.GospelPresentation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen21_where_you_will_go extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl3,rl2,rl_in_mid,rl_4;
	private Dialog dialog;	
	private TextView tv_bottom,tv_center;
	private int rl_click_count=0;
	private int same_screen=0;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen21);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl2 = (RelativeLayout)findViewById(R.id.rl_2);
        rl3 = (RelativeLayout)findViewById(R.id.rl_3);
        rl_4 = (RelativeLayout)findViewById(R.id.rl_4);
        rl_4.setVisibility(View.INVISIBLE);
        rl_in_mid = (RelativeLayout)findViewById(R.id.rl_in_mid);
        rl.setOnClickListener(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
 
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_center=(TextView)findViewById(R.id.tv1);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        tv_center.setTypeface(tf);
        
        rl2.setVisibility(View.INVISIBLE);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv_bottom.setTypeface(tf1);
       
        String str =getIntent().getStringExtra("second_call");
        if(str!=null){
        	rl3.setVisibility(View.INVISIBLE);
			rl2.setVisibility(View.VISIBLE);
	        tv_bottom.setText(" if you were to die right now, where would you go?");
			rl_click_count++;
        }
        else
        tv_bottom.setText("If everything I’ve told you today from the Bible is true,");
        
    }

	@Override
	public void onClick(View v) {

		switch(rl_click_count)
		{
		case 0:
			rl3.setVisibility(View.INVISIBLE);
			rl2.setVisibility(View.VISIBLE);
	        tv_bottom.setText(" if you were to die right now, where would you go?");
			rl_click_count++;
			break;
		case 1:
			tv_center.setVisibility(View.INVISIBLE);
			rl_in_mid.setVisibility(View.INVISIBLE);
			rl2.setVisibility(View.VISIBLE);
			//rl_4.setVisibility(View.INVISIBLE);
			dialog.setContentView(R.layout.alert_dailog_where_will_you_go);			
			//dialog.setTitle("Where will you go...");
			
	        dialog.getWindow().setGravity(Gravity.RIGHT);
	      //  dialog.getWindow().getAttributes().horizontalMargin = 0.2F;
	        //dialog.getWindow().set
	        
			RadioGroup radioGroup=(RadioGroup)dialog.findViewById(R.id.radioGroup1);
			//RadioButton rb_heven = (RadioButton) dialog.findViewById(R.id.rb_heven);
			// if button is clicked, close the custom dialog
			
			radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		    {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		            // checkedId is the RadioButton selected
		        	dialog.dismiss();
		        switch (checkedId) {
		        
		        case -1:
		            	break;
		            	
		            case R.id.rb_heven:
		            	Intent i1 = new Intent(Screen21_where_you_will_go.this,Have_you_turn_away_screen.class);
		            	startActivity(i1);
		            	finish();
		            	break;
	
		            case R.id.rb_not_sure:
		            	Intent i2 = new Intent(Screen21_where_you_will_go.this,Have_you_turn_away_screen.class);
		            	startActivity(i2);
		            	finish();		           
    		            break;
		              
		            case R.id.rb_hell:
		            	same_screen=1;
		            	 rl2.setVisibility(View.INVISIBLE);
		            	 rl_4.setVisibility(View.VISIBLE);
		            	 tv_center.setVisibility(View.VISIBLE);
		            	 tv_center.setText("Thanks for being honest. It would be a tragedy for you to end up in Hell.");
		            	 tv_bottom.setText("Thanks for being honest. It would be a tragedy for you to end up in Hell.");
		            	 rl_click_count=2;
		            	 break;
		            
		            case R.id.rb_other:
		            	same_screen=1;
		            	rl2.setVisibility(View.INVISIBLE);
		            	 rl_4.setVisibility(View.VISIBLE);
		            	 tv_center.setVisibility(View.VISIBLE);
		            	 tv_center.setText("I respect you believe something different, but IF this is true, where would you go?");
		            	 tv_bottom.setText("I respect you believe something different, but IF this is true, where would you go?");
		            	 rl_click_count=1;
		                 break;
		            }
		        }
		    });
 
			dialog.show();
			break;
			
		case 2:
			startActivity(new Intent(this,Screen22_get_to_heven.class));
			finish();
		}
	 }
	
	@Override
	public void onBackPressed() {
		
		if(same_screen==1){
			Intent i = new Intent(this,Screen21_where_you_will_go.class);
			i.putExtra("second_call", "yes");
			startActivity(i);
			finish();
		}
		else{
			super.onBackPressed();
			Intent i = new Intent(this,Screen20_die_right_now.class);
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

