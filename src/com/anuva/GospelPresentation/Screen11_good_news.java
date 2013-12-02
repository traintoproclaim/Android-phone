package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen11_good_news extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private TextView tv1,tv_bottom;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setBackgroundResource(R.drawable.goodnews);
        rl.setOnClickListener(this);
       
        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setVisibility(View.GONE);
        
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
        tv_bottom.setText(" But the good news is that God doesn’t want anyone to go there.");
       
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			startActivity(new Intent(Screen11_good_news.this,Screen12_jesus_ad_bc.class));
			finish();
		}
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen10_list_of_guilty.class);
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
