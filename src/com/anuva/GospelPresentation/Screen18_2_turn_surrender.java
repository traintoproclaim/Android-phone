package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Screen18_2_turn_surrender extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_2;
	private ImageView iv_globe;
	private TextView tv_bottom,tv_top;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen18);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        rl_2 =(RelativeLayout)findViewById(R.id.rl_2);
//        iv_turn=(ImageView)findViewById(R.id.iv_turn_around);
//        iv_turn_text=(ImageView)findViewById(R.id.iv_turn_text);
//        iv_surrender=(ImageView)findViewById(R.id.iv_surrender);
//        iv_surrender_text=(ImageView)findViewById(R.id.iv_surrender_text);
        iv_globe=(ImageView)findViewById(R.id.iv_globe);
        tv_top=(TextView)findViewById(R.id.tv_top);
       
        
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
        
       // iv_globe.setVisibility(View.INVISIBLE);
		//iv_text_center.setVisibility(View.INVISIBLE);
		/*iv_turn.setVisibility(View.VISIBLE);
		iv_turn_text.setVisibility(View.VISIBLE);
		iv_surrender.setVisibility(View.VISIBLE);
		iv_surrender_text.setVisibility(View.VISIBLE);
		tv_bottom.setText("We must also SURRENDER our lives to Jesus.");
    */
        tv_top.setVisibility(View.INVISIBLE);
		rl_2.setVisibility(View.INVISIBLE);
		//iv_globe.setBackgroundResource(R.anim.anim_globe);	
		iv_globe.setVisibility(View.VISIBLE);
		tv_bottom.setText("Because if Jesus made you and the entire universe");
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			/*case 1:				
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
				break;*/
				 // from here next screen
				
			/*case 1:
				iv_globe.setVisibility(View.INVISIBLE);
				iv_text_center.setVisibility(View.INVISIBLE);
				iv_turn.setVisibility(View.VISIBLE);
				iv_turn_text.setVisibility(View.VISIBLE);
				iv_surrender.setVisibility(View.VISIBLE);
				iv_surrender_text.setVisibility(View.VISIBLE);
				tv_bottom.setText("We must also SURRENDER our lives to Jesus.");
				click_no++;
			    break;*/
			   
			/*case 1:
				tv_top.setVisibility(View.INVISIBLE);
				rl_2.setVisibility(View.INVISIBLE);
				iv_globe.setBackgroundResource(R.anim.anim_globe);	
				iv_globe.setVisibility(View.VISIBLE);
				tv_bottom.setText("Because if Jesus made you and the entire universe");
				click_no++;
			    break;
*/			    
			case 1:
				iv_globe.setBackgroundResource(R.drawable.jesus_in_heart);
				tv_bottom.setText(" He deserves to be in the centre of your life.");
				click_no++;
			    break;
			    
			case 2:
				iv_globe.setBackgroundResource(R.drawable.surrender_icon);
				tv_bottom.setText(" Surrendering is making Jesus the number one priority of our lives.");
				click_no++;
			    break;
			    
			case 3:
				iv_globe.setVisibility(View.INVISIBLE);
				iv_globe.setBackgroundResource(R.drawable.event_death_circle);
				iv_globe.setVisibility(View.VISIBLE);
				tv_bottom.setText("Now, say you die and come before God at Judgment.");
				click_no++;
			    break;
			
			case 4:
				SharedPreferences myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
				String version =  myPrefs.getString("version", "");
				if(version.equals("short")){
					startActivity( new Intent(this, Screen19_judgement_day.class));
					finish();

				}
				else{
					startActivity( new Intent(this, Screen19_judgement_day_long.class));
					finish();
				}
			}
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen18_turn_surrender.class);
		i.putExtra("back_18", "yes");
		startActivity(i);
		finish();
	
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus){
			iv_globe.setBackgroundResource(R.anim.anim_globe);	
			//iv_no_justice.setBackgroundResource(R.anim.anim_no_justice);
			AnimationDrawable  anim = (AnimationDrawable) iv_globe.getBackground(); 
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

