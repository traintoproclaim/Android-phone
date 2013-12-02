package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Have_you_turn_away_screen extends BaseActivity implements OnClickListener {

	private Button btn_yes,btn_no;
	private TextView tv_bottom,tv_label;
	private LinearLayout ll;
    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.have_you_turn_away);
        
        btn_yes = (Button)findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
        btn_no = (Button)findViewById(R.id.btn_no);
        btn_no.setOnClickListener(this);
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		btn = (Button)findViewById(R.id.btn_bottom);

		if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_label=(TextView)findViewById(R.id.tv_lable);
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
        	                "fonts/opificio.ttf");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_bold.ttf");
        tv_label.setTypeface(tf);
        tv_bottom.setTypeface(tf1);
        tv_bottom.setText("  \"So have you \'Turned\' and \'Surrendered\' your life to Jesus?\" ");
    
    }

	@Override
	public void onClick(View v) {
		if(v== btn_yes){
		  Intent i = new Intent(this,Get_email_screen.class);
		  startActivity(i);
		  finish();
		}
		
		else if(v== btn_no){
			startActivity(new Intent(this,Screen22_get_to_heven.class));
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i1 = new Intent(Have_you_turn_away_screen.this,Screen21_where_you_will_go.class);
		i1.putExtra("second_call", "yes");
		startActivity(i1);
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
