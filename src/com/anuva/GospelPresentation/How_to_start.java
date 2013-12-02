package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class How_to_start extends BaseActivity implements OnClickListener 
{
	private RelativeLayout rl ;
	private TextView tv1,tv2;
	private String str_intent;
    public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
        setContentView(R.layout.how_to_start);
        
        tv1 = (TextView)findViewById(R.id.tv_1);
        tv2 = (TextView)findViewById(R.id.tv_2);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_bold.ttf");
        tv1.setTypeface(tf1);
        tv2.setTypeface(tf);
        str_intent = getIntent().getStringExtra("skip_quiz");
        if(str_intent!=null && str_intent.equalsIgnoreCase("yes")){
        	tv1.setText("HOW TO START");
            tv2.setText(Html.fromHtml("Note: Don't show people this page!<br>"+
            		"Memorise one of the following ways to start:"+

    				"<p>\"Can you help me with something?<br> "+
    				"I’m doing something with church; it’s nothing weird, so you can just relax!<br>"+ 
    				"It's the latest thing... All we do is give you ...\"<br>"+
    				"<b>OR</b><br>"+
    				"\"Can I show you something I am doing with church?<br>"+  
    				"I would really appreciate getting your feedback on it.  It is ...\"<br>"+
    				"<b>OR</b><br>"+
    				"\"I have been wanting to talk with you about something <br>" +
    				"that is really important to me and I am a bit nervous about it.<br>"+ 
    				"Can I quickly show you something, it is ...\"</p>"));
    				        
        }
        
        else{
        	tv1.setText("QUIZ INTRODUCTION:");
            tv2.setText(Html.fromHtml("Note: Don't show people this page!<br>"+
            		" Please memorise the following ways to start:"+
    				"<p><b>For people who know you:</b><br> "+
    				"\"Can you help me with something? <br>" +
    				"I’m doing something with church; it’s nothing weird, <br>"+ 
    				" so you can just relax! <br>"+
    				" This is the latest thing. All we do is ask you ...\""+
    				"<p><b>For people who don’t know you:</b> <br>"+  
    				" \"Excuse me/Hello. Can you help me with something? <br>"+
    				"(Do you mind if I have a seat?) <br>"+
    				" My name is ...... I work with mainstream churches like Anglicans,<br>"+ 
    				" Baptists, Presbyterians... nothing weird, so you can just relax! <br>" +
    				" This is the latest thing. All we do is ask you ... \""));
    				        
        }
        
     }

	@Override
	public void onClick(View v) {
		 if(str_intent!=null && str_intent.equalsIgnoreCase("yes")){
			/*Intent i = new Intent();
			i.setClass(How_to_start.this, EnterAudienceName.class);
			i.putExtra("skip_quiz", "yes");
			startActivity(i);
			finish();*/
			 Intent i = new Intent();
			 i.setClass(How_to_start.this, Screen1.class);
			 startActivity(i);
			 finish();
		}
		 
		 else{
			 Intent i = new Intent();
        	 i.setClass(How_to_start.this, Quiz_extra_screen.class);
             startActivity(i);
             finish();
		 }
	}

	 @Override
		public void onBackPressed() {
			super.onBackPressed();
			 if(str_intent!=null && str_intent.equalsIgnoreCase("yes")){
				 Intent i = new Intent();
					i.setClass(How_to_start.this, EnterAudienceName.class);
					i.putExtra("skip_quiz", "yes");
					startActivity(i);
					finish();
			 }
			 else
			 {
				 startActivity(new Intent(this,EnterName.class));
					finish();
			 }
		}
}
