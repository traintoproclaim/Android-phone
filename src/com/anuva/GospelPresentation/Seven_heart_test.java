package com.anuva.GospelPresentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Seven_heart_test extends BaseActivity   {

	private RadioGroup radio_group;
	private RadioButton rb_01,rb_02;
	private int quest_nos=1;
	private TextView tv_quest,tv_title;
	private Button btn_next;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_heart_test);
        radio_group = (RadioGroup)findViewById(R.id.radioGroup);
        
        tv_quest=(TextView)findViewById(R.id.textView1);
        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_quest.setMovementMethod( new ScrollingMovementMethod());
        
        rb_01=(RadioButton)findViewById(R.id.rb_dnt_belive);
        rb_02=(RadioButton)findViewById(R.id.rb_might_thought);
        
        rb_01.setText("I Agree");
        rb_02.setText("I Don't Agree");
       
        Setquestion();      
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio_bold.ttf");
        tv_title.setTypeface(tf1);
        
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/dominik.ttf");
        tv_quest.setTypeface(tf2);
        rb_01.setTypeface(tf2);
        rb_02.setTypeface(tf2);
        
        btn_next = (Button)findViewById(R.id.btn_next);
       
        btn_next.setVisibility(View.VISIBLE);
        btn_next.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(quest_nos!=5){
					if(rb_01.isChecked()){
						quest_nos++;
						Setquestion();      
						radio_group.clearCheck();
					}
					else if(rb_02.isChecked()){
						rb_02.setChecked(false);
						radio_group.clearCheck();
						Intent intent = new Intent(Seven_heart_test.this,Dont_agree_screen.class);
						startActivity(intent);
						finish();
					}
					else{
						Toast.makeText(Seven_heart_test.this, "Please choose any one option", Toast.LENGTH_SHORT).show();
					}
				}
				
				else {
					quest_nos++;
					Setquestion();      
					radio_group.clearCheck();
				}
			}
		});
    }

	@Override
	protected void onResume() {
		super.onResume();
		radio_group.clearCheck();
		quest_nos=1;
		Setquestion();      
		
	}

	private void Setquestion() {
		switch(quest_nos){
		
		case 1:
			tv_quest.setText("I admit that I have broken God's laws and God would be justified in punishing me.");
			break;
			
		case 2:
			tv_quest.setText("I am genuinely sorry for breaking God's laws and I sincerely desire to give up " +
					"the wrong ways of my past and live my life by God’s standards.");
			break;
			
		case 3:
			tv_quest.setText("I understand that Jesus wants to be my friend and to fulfill my deepest needs." +
					" If I become a Christian, He will commit Himself to lovingly and gently changing me into the person He wants me to be. I understand that real change will " +
					"only happen as I co-operate with Him. Change will sometimes be painful and at times I may fail. No matter how many times I fail, I'm going to keep living for Him.");
			break;
			
		case 4:
			tv_quest.setText("I acknowledge that Jesus as God, is loving and absolutely awesome. He is " +
					"the Supreme Ruler and Creator of the world. Because this is true, " +
					"I understand that He owns me and has the right, in love, to ask me to do " +
					"anything that is His will for me. I want to have a personal relationship with Him and follow Him with all my heart.");
			break;
			
		case 5:
			tv_quest.setText("What does it mean to \"follow Him with all your heart\"? It includes a new " +
					"attitude towards the Bible, the Church and the World. Remember that these are heart attitudes, not a list of things you have to do in order to get to Heaven!");
			rb_01.setVisibility(View.GONE);
	        rb_02.setVisibility(View.GONE);
			break;
			
		case 6:
			tv_title.setVisibility(View.VISIBLE);
			tv_title.setText("The Bible");
			rb_01.setVisibility(View.VISIBLE);
	        rb_02.setVisibility(View.VISIBLE);
			tv_quest.setText("I may not know what this book teaches yet, but I understand it is God's message to me. It shows me how He wants me to live. I want God to help me obey what it says with all my heart.");
			break;
			
		case 7:
			tv_title.setText("The Church");
			tv_quest.setText(" Being a Christian isn’t easy and I need the support of others. " +
					"I also need to learn from other Christians how to follow Jesus better. I will commit to being involved in a good church.");
			break;

		case 8:
			tv_title.setText("The World");
			tv_quest.setText("All people in the world have needs. I want to love people so that these needs, especially the spiritual, can be met.");
			break;
			
		case 9:
			Intent i = new Intent(this,Prayer.class);
			startActivity(i);
			finish();
			break;
		}
		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,Response.class));
		finish();
	}
 }
