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

public class Screen22_get_to_heven extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl3;
	private int click_no=1;
	private ImageView iv_perfert_rec,iv_per_text,iv_cross,iv_jesus_you,iv_eternity,iv_two;
	private TextView tv_bottom;
	private LinearLayout ll;
    private Button btn;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen22);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl3 = (RelativeLayout)findViewById(R.id.rl_3);
        rl.setOnClickListener(this);
        iv_perfert_rec=(ImageView)findViewById(R.id.iv_perfect_record);
        iv_per_text=(ImageView)findViewById(R.id.iv_perfect_text);
        iv_eternity = (ImageView)findViewById(R.id.iv_eternity);
        iv_jesus_you=(ImageView)findViewById(R.id.iv_jesus_and_you);
        iv_jesus_you.setBackgroundResource(R.anim.anim_soul_tranfer_you);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_two=(ImageView)findViewById(R.id.iv_two);
        iv_cross.setBackgroundResource(R.anim.anim_cross_it);
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        iv_perfert_rec.setBackgroundResource(R.anim.anim_perfect_record);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
 
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        iv_eternity.setBackgroundResource(R.anim.anim_eternity);
       
        tv_bottom.setText("We’re talking about all of eternity here,");
        
        
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
				switch(click_no)
				{
				
				case 1:
					iv_eternity.setVisibility(View.INVISIBLE);					
					iv_eternity.setBackgroundResource(R.drawable.eternity_sec);
					iv_eternity.setVisibility(View.VISIBLE);
					tv_bottom.setText(" not just a mere billion years, So let’s do a quick review.");
					click_no++;
					break;
					
				case 2:
					iv_eternity.setVisibility(View.INVISIBLE);
					rl3.setVisibility(View.INVISIBLE);
			        tv_bottom.setText("According to the Bible, what kind of record must we have to get into Heaven?");
					click_no++;
				    break;
				case 3:
					iv_perfert_rec.setVisibility(View.VISIBLE);
					tv_bottom.setText(" {Perfect}. ");
					click_no++;
					break;
				case 4:
					tv_bottom.setText("Now, there are only two ways to get a perfect record.");
					click_no++;
				    break;
				    
				case 5:
					iv_per_text.setVisibility(View.VISIBLE);
					tv_bottom.setText("One is to be a perfect person,");
					click_no++;
				    break;
				    
				   
				case 6:
					iv_cross.setVisibility(View.VISIBLE);
					tv_bottom.setText(" and we’ve all blown this one.");
					click_no++;
				    break;
				    
				case 7:
					iv_per_text.setVisibility(View.GONE);
					iv_cross.setVisibility(View.GONE);
					iv_two.setVisibility(View.VISIBLE);
					iv_jesus_you.setVisibility(View.VISIBLE);
					tv_bottom.setText(" The only other way is to have Jesus give us His perfect record when we are completely forgiven.");
					click_no++;
				    break;
				    
				case 8:
					/*SharedPreferences pref = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
					SharedPreferences.Editor edit = pref.edit();
					
					edit.putString("back_Screen23", "Screen22_get_to_heven");
					edit.commit();
					*/
					Intent intent = new Intent(this,Screen23_we_must.class);
					startActivity(intent);
					finish();
				    break;	
				}
		}
	}
	
	@Override
	public
	void onWindowFocusChanged(boolean hasFocus) {
	    if (hasFocus  ) {
	    	// iv_eternity.setVisibility(View.VISIBLE);
	    	 AnimationDrawable anim = (AnimationDrawable) iv_eternity.getBackground();
				anim.start();
	    }
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen21_where_you_will_go.class);
		i.putExtra("second_call", "yes");
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

