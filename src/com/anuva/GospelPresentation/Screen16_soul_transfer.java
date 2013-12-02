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

public class Screen16_soul_transfer extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_soul_transfer, iv_soul_transfer1;
	private TextView tv_bottom;
	private int click_no = 1;
	private LinearLayout ll;
    private Button btn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen16);
		rl = (RelativeLayout) findViewById(R.id.rl_1);
		rl.setOnClickListener(this);

		iv_soul_transfer = (ImageView) findViewById(R.id.iv_soul_transfer);
		iv_soul_transfer1 = (ImageView) findViewById(R.id.iv_soul_transfer1);

		Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
				"fonts/opificio.ttf");
		ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
   
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		tv_bottom = (TextView) findViewById(R.id.tv_bottom);
		tv_bottom.setTypeface(tf1);
		tv_bottom
				.setText("Because Jesus was punished for the laws we have broken, it is now possible to receive");

		
	}

	@Override
	public void onClick(View v) {
		if (v == rl) {
			switch (click_no) {

			case 1:
				iv_soul_transfer1
						.setBackgroundResource(R.anim.anim_soul_tranfer);
				iv_soul_transfer1.setVisibility(View.VISIBLE);
				iv_soul_transfer.setVisibility(View.INVISIBLE);
				tv_bottom
						.setText("a perfect record and make it into Heaven.");
				click_no++;
				break;

			case 2:
				tv_bottom.setText("But how do we do this?");
				click_no++;
				break;

			case 3:
				startActivity(new Intent(this, Screen17_3_major_events.class));
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Screen14_2.class);
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
