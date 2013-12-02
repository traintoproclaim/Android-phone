package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen8_2_juistice_text extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private TextView tv_bottom,tv1;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_text);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
       
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv1=(TextView)findViewById(R.id.tv1);
       
        
        tv1.setText(Html.fromHtml( "WHEN SOMEONE HAS BROKEN " +  "<br />"  
                 + "THE LAW"  + "<br />" + 
                 "THERE MUST BE A PUNISHMENT" + "<br />" +
                 " OTHERWISE THERE IS ..."
                 ));
        
       
       // tv1.setGravity(Gravity.CENTER);
        
       // View positiveButton = findViewById(R.id.positiveButton);
      
        
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_rounded.ttf");
        tv1.setTypeface(tf);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
   
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setText("That’s because when someone has broken the law there must be a punishment, otherwise there is ...");
        tv_bottom.setTypeface(tf1);
        
       
    }
    
	@Override
	public void onClick(View v) {
		if(v == rl){
			Intent intent = new Intent(this, Screen8_3_juistice.class);
			startActivity(intent);
			finish();
		}
	}
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		Intent i = new Intent(this,Screen8_1_juistice.class);
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
