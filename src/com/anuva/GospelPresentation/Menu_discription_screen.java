package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu_discription_screen extends BaseActivity 
{
	private TextView textView;
	private Typeface tf1;
	private Button btn_end;
	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
        setContentView(R.layout.donation_screen);
        textView = (TextView) findViewById(R.id.tv1);
        btn_end=(Button)findViewById(R.id.btn_end);
        btn_end.setVisibility(View.INVISIBLE);
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
        textView.setTypeface(tf1);
        //textView.setTextColor(0x336666);
        textView.setTextColor(Color.parseColor("#336666"));
        String str = getIntent().getStringExtra("option");
        if(str!=null && str.equals("Copyright")){
        	 textView.setText(Html.fromHtml(
    	             "<b> Copyright ©2013 Train To Proclaim <br>All rights reserved</b> <br>" +
    	             "<p> Although protected by copyright, <br>" +
    	            		"  DUPLICATION is allowed and ENCOURAGED, <br>"+
    	            " providing nothing is changed.<br>"+
    	            		"Free copies can be downloaded from <br>" +
    	            		" Google Play.</p>"+
    	            " If you would like to suggest any changes or <br>"+
    	            		"translate the presentation into another language<br>" +
    	            		"then please contact Stuart Millar on <br>"+
    	            		"<a href=\"mailto:stu@traintoproclaim.com\">stu@traintoproclaim.com</a> "
    	    		));
        }
        	 
        	 else  if(str!=null && str.equals("ministry")){
            	 textView.setText(Html.fromHtml(
        	             "<b> About Train To Proclaim.</b> <br>" +
        	             "<p>  Train To Proclaim is committed to equipping <br>" +
        	            		"   and  empowering Christians to share the Gospel. <br> </p>"+
        	            "  <p> We provide training and free high<br>"+
        	            		" quality evangelism resources to Christians <br>  all around the world.</p>"+
        	            " <p> For more information on TTP, our<br>"+
        	            		" Vision, Resources and the Evangelism Training<br>" +
        	            		" we offer, go to <a href=\"http://www.traintoproclaim.com\">www.traintoproclaim.com</a><br><br></br>"+
        	            		" or email Stuart Millar on <a href=\"mailto:stu@traintoproclaim.com\">stu@traintoproclaim.com</a>"
        	    		));
        	 }
     
            	 else  if(str!=null && str.equals("Credits")){
                	 textView.setText(Html.fromHtml(
            	             "This presentation is based on the life of Jesus Christ<br>" +
            	             "  and is dedicated to His Glory! <br>" +
            	            		"   <p>Thank you to the many Christians that have helped  <br> " +
            	            		"to compile this presentation, your contributions are appreciated.</p>"+
            	            "  <p> Graphics by <br>Amanda Holmes - <a href=\"http://www.createmilk.com.au\">www.createmilk.com.au</a> <br>"+
            	            		"  App developed by <br>" +
            	            		"Anuva Technologies - <a href=\"http://www.anuvasoft.com/\">www.anuvasoft.com</a><br>"
            	    		));
          }
	   
	   textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Gospelin7.class);
		i.putExtra("Second_call", "true");
		startActivity(i);
		finish();
	}
}