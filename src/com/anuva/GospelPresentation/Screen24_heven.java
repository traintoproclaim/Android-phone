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

public class Screen24_heven extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private int click_no=1;
	private ImageView iv_cross;
	private TextView tv_bottom;
	private LinearLayout ll;
    private Button btn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen24);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        iv_cross=(ImageView)findViewById(R.id.iv_cross);
        iv_cross.setBackgroundResource(R.anim.anim_close_it);
      
        
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
        tv_bottom.setText("If you have not turned and surrendered,");
        
        
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
				switch(click_no)
				{
				case 1:
					iv_cross.setVisibility(View.VISIBLE);
					tv_bottom.setText(" it is literally impossible to get into Heaven.");
					click_no++;
					break;
				case 2:
					iv_cross.setVisibility(View.INVISIBLE);
					iv_cross.setBackgroundResource(R.anim.anim_close_it2);
					iv_cross.setVisibility(View.VISIBLE);
			        tv_bottom.setText("According to the Bible, where is the only other place you can go? {Hell}.");
					click_no++;
				    break;
				
				case 3:
					tv_bottom.setText("God doesn’t want you or anyone to end up in Hell and there is no reason why anyone needs to.");
					click_no++;
				    break;
				
				case 4:
					Intent intent = new Intent(this,Response.class);
					startActivity(intent);
					finish();
				    break;	
				}
		}
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen23_we_must.class);
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

