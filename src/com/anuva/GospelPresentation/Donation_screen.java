package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Donation_screen  extends BaseActivity implements OnClickListener
{
	
	private TextView textView;
	private Button btn_end;
	private Typeface tf1;

	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
        setContentView(R.layout.donation_screen);
        textView = (TextView) findViewById(R.id.tv1);
        btn_end =(Button)findViewById(R.id.btn_end);
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
        textView.setTypeface(tf1);
        btn_end.setOnClickListener(this);

        
        	btn_end.setBackgroundResource(R.drawable.endbtn);
        	 textView.setText(Html.fromHtml(
    	             "Have you considered giving a donation to <br>" +
    	             "Train To Proclaim to help them develop more App’s? <br>"+
    	   	         "<p>Go to <a href=\"http://www.traintoproclaim.com\">www.traintoproclaim.com</a> to donate, <br>" +
    	             "for more quality evangelism resources and lots of free downloads!</p>"));
       
        
	    textView.setMovementMethod(LinkMovementMethod.getInstance());
	  
	}

	@Override
	public void onClick(View arg0) {
		
			Intent i = new Intent(this,Gospelin7.class);
			startActivity(i);
			finish();
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,congratulation_screen.class));
		finish();
	}
}
