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

public class Screen23_we_must extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private int click_no=1;
	private ImageView iv_turn_away,iv_surrender;
	private TextView tv1,tv_bottom;
	private LinearLayout ll;
    private Button btn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen23);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        iv_turn_away=(ImageView)findViewById(R.id.iv_turn_away);
        iv_turn_away.setBackgroundResource(R.anim.anim_turn_away_small);
        iv_surrender=(ImageView)findViewById(R.id.iv_surrender);
        
        tv1 = (TextView)findViewById(R.id.tv1);
      
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        tv1.setTypeface(tf);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);

		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        tv_bottom.setText("Now can you remember the two things we must do to have this event?");
   
      
    
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
				case 1:					
			        tv_bottom.setText("We must make a commitment to ...");
					click_no++;
				    break;
				    
				case 2:
					tv_bottom.setText("... TURN from everything we know to be wrong and ... ");
					iv_turn_away.setVisibility(View.VISIBLE);
					click_no++;
					break;
				case 3:
					iv_surrender.setVisibility(View.VISIBLE);
					tv_bottom.setText("SURRENDER our lives to Jesus Christ.");
					click_no++;
				    break;
				
				case 4:
					Intent intent = new Intent(this,Screen24_heven.class);
					startActivity(intent);
					finish();
				    break;	
				}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen22_get_to_heven.class);
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

