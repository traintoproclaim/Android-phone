package com.anuva.GospelPresentation;



import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class Result extends BaseActivity implements OnClickListener
{
	private TextView tv_yourScoreIs,tv_quickQuiz;
	private TextView tv_resultNarration1,tv_resultNarration2;
	private TextView tv_result1,tv_result2,tv_result3,tv_result4,tv_result5;
	
	private ImageView  iv_bible;
	private int quiz_result, click_count=0;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;
	private Button btn_next,btn_next1;
	private TableLayout tl;
	private ImageView iv_1,iv_2,iv_3,iv_4,iv_6;
	private LinearLayout ll;
	private Button btn;
	
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		 myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		 prefsEditor = myPrefs.edit();
		 quiz_result = myPrefs.getInt("result", 0);
		 Log.i("quiz_result **", ""+quiz_result);
		 tl=(TableLayout)findViewById(R.id.tablelayout1);
	     iv_bible = (ImageView)findViewById(R.id.iv_bible1);
		 btn_next1=(Button)findViewById(R.id.button_next);

		 iv_1 =(ImageView)findViewById(R.id.iv_1);
		 iv_2 =(ImageView)findViewById(R.id.iv_2);
		 iv_3 =(ImageView)findViewById(R.id.iv_3);
		 iv_4 =(ImageView)findViewById(R.id.iv_4);
		 iv_6 =(ImageView)findViewById(R.id.iv_6);
		
		 ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
		 if(Quiz1.quiz_text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
			
		 Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
	                "fonts/opificio.ttf");
		 Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
	                "fonts/opificio_rounded.ttf"); 
		 Typeface tf3 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio_bold.ttf");
		 
		tv_yourScoreIs = (TextView) findViewById(R.id.tv_youScoreIs);
		tv_quickQuiz = (TextView) findViewById(R.id.tv_quickQuiz);
		tv_quickQuiz.setTypeface(tf2);
		tv_yourScoreIs.setTypeface(tf3);
		
		tv_resultNarration1 = (TextView) findViewById(R.id.tv_bottom);
		tv_resultNarration2 = (TextView) findViewById(R.id.result_narration2);
		tv_resultNarration2.setTypeface(tf1);
		tv_result1 = (TextView) findViewById(R.id.TV_result1);
		tv_result2 = (TextView) findViewById(R.id.TV_result2);
		tv_result3 = (TextView) findViewById(R.id.TV_result3);
		tv_result4 = (TextView) findViewById(R.id.TV_result4);
		tv_result5 = (TextView) findViewById(R.id.TV_result5);
		
		tv_result1.setTypeface(tf2);
		tv_result2.setTypeface(tf2);
		tv_result3.setTypeface(tf2);
		tv_result4.setTypeface(tf2);
		tv_result5.setTypeface(tf2);
		tv_resultNarration1.setTypeface(tf1);		
		tv_resultNarration1.setText("You scored"+" "+quiz_result);
		
		tv_yourScoreIs.setText(Html.fromHtml("Your score is : <b>" +quiz_result+"</b>"));
			
		btn_next = (Button) findViewById(R.id.button_presentation);
		btn_next.setOnClickListener(this);

		resetScore();
    }
	
	public void resetScore()
	{
		 prefsEditor.putInt("result", 0);
	}

	@Override
	public void onClick(View arg0) 
	{
		
		if(arg0 == btn_next)
		{
			if(click_count==0){
			
				if(quiz_result >= 49 && quiz_result <= 50){
					iv_1.setBackgroundResource(R.anim.anim_score_circle);
					tv_resultNarration1.setText("According to this you are an ANGEL! Wow!");
					click_count=5;
				}
			    	
				else {
					iv_1.setBackgroundResource(R.anim.anim_score_cross);
					
					tv_resultNarration1.setText("49-50 is Angelic, so you are not an angel ...");
					click_count=1;
				}
				iv_1.setVisibility(View.VISIBLE);
				
			}
				else if(click_count==1){
					
					if(quiz_result >= 46 && quiz_result <= 48)
				    {
						iv_2.setBackgroundResource(R.anim.anim_score_circle);
						tv_resultNarration1.setText("According to this you are Saint ... Wow!");
						click_count=5;
				    }
					
					else
					{
						iv_2.setBackgroundResource(R.anim.anim_score_cross);

						tv_resultNarration1.setText("46-48 is Saintly, so you are not Saint ...");
						click_count=2;
					}
					iv_2.setVisibility(View.VISIBLE);
				}
			
				else if(click_count==2){
					
					if(quiz_result >= 18 && quiz_result <= 45)
				    	{
						iv_3.setBackgroundResource(R.anim.anim_score_circle);
						tv_resultNarration1.setText("According to this you are a Good person" +
								" [At least you are not struggling or seek help! ]");
						click_count=5;
				    	}
					
					else
					{
						iv_3.setBackgroundResource(R.anim.anim_score_cross);
						tv_resultNarration1.setText("You have scored under the good rating.");
						click_count=3;
					}
					iv_3.setVisibility(View.VISIBLE);
				}
			
				else if(click_count==3){
					
					if(quiz_result >= 12 && quiz_result <= 17)
				    	{
						iv_4.setBackgroundResource(R.anim.anim_score_circle);
						click_count=5;
						tv_resultNarration1.setText("But you have been a lot more honest" +
								" than most people. Thank you");
				    	}
					
					else {
						iv_4.setBackgroundResource(R.anim.anim_score_cross);
						tv_resultNarration1.setText("And under struggling, which leaves us with the last rating ...");
						click_count=4;
					}
					iv_4.setVisibility(View.VISIBLE);
				}
		        
             else if(click_count==4){
            	 iv_6.setBackgroundResource(R.anim.anim_score_circle);
            	 iv_6.setVisibility(View.VISIBLE);
				 tv_resultNarration1.setText("Seek help. You have being very honest, thank you for that...");
				 click_count=5;
             }
			
             else if(click_count==5){
             	 tv_resultNarration2.setText(Html.fromHtml(  "The best definition <br> you have ever heard <br> of what a Christian is ...")); 
            	 tl.setVisibility(View.INVISIBLE);
            	 tv_resultNarration2.setVisibility(View.VISIBLE);
            	 click_count=6;
            	 tv_resultNarration1.setText("Now like I said before I will give you the best definition you " +
            	 		"have ever heard of what a Christian is ...");
              }
             
             else if(click_count==6){
            	 tv_resultNarration2.setVisibility(View.INVISIBLE);
            	 btn_next.setVisibility(View.INVISIBLE);
                 iv_bible.setVisibility(View.VISIBLE);
                 btn_next1.setVisibility(View.VISIBLE);
        		 btn_next1.setOnClickListener(this);
        		 tv_resultNarration1.setText("This is also a summary of the message of the entire " +
             	 		"Bible, in 6.5 minutes. It will be great to see what you think of it.");
     
            }
		}
		
		else if(arg0 == btn_next1){
			 prefsEditor.putString("back_screen1", "Result");
		     prefsEditor.commit();
			 Intent i = new Intent();
     	     i.setClass(Result.this, Screen1.class);
             startActivity(i);
             finish();
		}
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,Quiz_extra_screen.class));
		finish();
	}
	
	 public void onTextClick(View v) {
			ll.setVisibility(View.INVISIBLE);
			btn.setVisibility(View.VISIBLE);
			Quiz1.quiz_text=1;
		}
		public void onTextButtonClick(View v) {
			ll.setVisibility(View.VISIBLE);
			btn.setVisibility(View.INVISIBLE);
			Quiz1.quiz_text=0;
		}
		
}

