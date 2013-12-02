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

public class Screen17_3_2_major_events extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_cross,iv_rec_list,iv_event_line,iv_event_text;
	private TextView tv_bottom;
	private int click_no=1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen17);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
    	//iv_soul_transfer = (ImageView) findViewById(R.id.iv_soul_transfer);
        iv_event_line=(ImageView)findViewById(R.id.iv_major_event_line);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_cross.setBackgroundResource(R.anim.anim_cross_it);
        iv_event_text=(ImageView)findViewById(R.id.iv_major_events_text);
        iv_event_line.setBackgroundResource(R.drawable.event_forgiven_1);
        iv_event_line.setVisibility(View.VISIBLE);
        iv_rec_list=(ImageView)findViewById(R.id.iv_rec_list);
        
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
        iv_event_text.setVisibility(View.GONE);
        //iv_soul_transfer.setVisibility(View.INVISIBLE);
		iv_rec_list.setVisibility(View.VISIBLE);
		tv_bottom.setText("Now we do not have this event by being christened, ");
    	
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
			/*case 1:
				iv_event_line.setVisibility(View.VISIBLE);
		        tv_bottom.setText("between birth");
		        click_no++;
		        break;
		        
			case 2:
				iv_event_line.setBackgroundResource(R.drawable.event_death);
		        tv_bottom.setText("and death,");
		        click_no++;
		        break;
			
			case 3:
				iv_event_line.setBackgroundResource(R.drawable.event_forgiven_1);
		        tv_bottom.setText("When we are completely FORGIVEN for breaking God’s laws.");
		        click_no++;
		        break;
			
			case 4:
				iv_event_text.setVisibility(View.GONE);
				iv_soul_transfer.setVisibility(View.VISIBLE);
				tv_bottom.setText("The only way to be forgiven is through Jesus, by receiving");
				click_no++;
			    break;
			    
			case 5:
				// show soul transfer animation
				iv_soul_transfer.setVisibility(View.INVISIBLE);
				iv_soul_transfer
				.setBackgroundResource(R.anim.anim_soul_tranfer_small);
				iv_soul_transfer.setVisibility(View.VISIBLE);
				tv_bottom.setText(" His perfect record when we become a real Christian.");
				click_no++;
				break;*/
				
			//from here
			/*case 1:
				iv_soul_transfer.setVisibility(View.INVISIBLE);
				iv_rec_list.setVisibility(View.VISIBLE);
				tv_bottom.setText("Now we do not have this event by being christened, ");
				click_no++;
			    break;*/
		 
			case 1:
				tv_bottom.setText("baptised, confirmed, praying, going to church, believing God exists.");
				click_no++;
			    break;
			
			case 2:
				iv_cross.setVisibility(View.VISIBLE);
				tv_bottom.setText("These are all good things but none of these will give us a perfect record.");
				click_no++;
			    break;
			
			case 3:
				startActivity( new Intent(this, Screen18_turn_surrender.class));
				finish();
				break;
			}
		}
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen17_3_major_events.class);
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
