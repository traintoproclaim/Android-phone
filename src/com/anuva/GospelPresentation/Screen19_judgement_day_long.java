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

public class Screen19_judgement_day_long extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_left,rl_right;
	private ImageView iv_hell_arrow;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen19_1);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        rl_left=(RelativeLayout)findViewById(R.id.rl_left);
        rl_right=(RelativeLayout)findViewById(R.id.rl_right);
        iv_hell_arrow=(ImageView)findViewById(R.id.iv_hell_arrow);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        tv_bottom.setText("He would look at you and say, “Welcome to Heaven, my beautiful child, I love you!” ");
    	
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			
			case 1:
				rl_left.setVisibility(View.INVISIBLE);
				rl_right.setVisibility(View.VISIBLE);
				iv_hell_arrow.setVisibility(View.VISIBLE);
		        tv_bottom.setText("And you will say, “But I broke your laws? Shouldn’t I be punished and go to Hell?");
		        click_no++;
		        break;
		        
			/*case 2:
				
		        tv_bottom.setText(" and God would welcome you into Heaven.");
		        click_no++;
		        break;
			
			case 3:
				
				rl_right.setVisibility(View.VISIBLE);
		        tv_bottom.setText("  But if you did not have this event, then you are not forgiven,");
		        click_no++;
		        break;
			
			case 4:
				iv_imperfect.setVisibility(View.VISIBLE);
				tv_bottom.setText(" have an imperfect record");
				click_no++;
			    break;
			    
			case 5:
				iv_hell_arrow.setVisibility(View.VISIBLE);
				tv_bottom.setText("and remember, God would have to be just and send you to Hell.");
				click_no++;
			    break;*/
			    			
			case 2:
				startActivity( new Intent(this, Screen19_2_jesus_gave_his_rec_long.class));
				finish();
				break;
		        
			
			}
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen18_2_turn_surrender.class);
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
