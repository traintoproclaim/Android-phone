package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EnterAudienceName extends BaseActivity implements OnClickListener
{
	private RadioButton rb_male,rb_female,rb_group;
	private String str_enterYourName;
	private Button btn_next;
	private String audienceName, skip_quiz;
	private TextView tv_audienceName;
	private EditText et_audienceName;
	private Typeface tf1;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;

	public void onCreate(Bundle savedInstance){
		
		super.onCreate(savedInstance);
        setContentView(R.layout.audiencename);
        et_audienceName = (EditText) findViewById(R.id.et_audienceName);
        tv_audienceName = (TextView) findViewById(R.id.tv_audienceName);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        btn_next = (Button) findViewById(R.id.btn_audience_screen);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_group = (RadioButton) findViewById(R.id.rb_group);
        
        tv_audienceName.setTypeface(tf1);
             
        str_enterYourName = getResources().getString(R.string.text_audienceName);
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
        rb_male.setTypeface(tf1);
        rb_female.setTypeface(tf1);
        rb_group.setTypeface(tf1);
        tv_audienceName.setText(str_enterYourName);
        
        btn_next.setOnClickListener(this);
        rb_male.setOnClickListener(this);
		rb_female.setOnClickListener(this);
		rb_group.setOnClickListener(this);
		skip_quiz= getIntent().getStringExtra("skip_quiz");
		
		myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
	    prefsEditor = myPrefs.edit();
	        
	}

	@Override
	public void onClick(View v) 
	{
	  switch(v.getId())
	  {
		case R.id.rb_male:
			rb_female.setChecked(false);
			rb_group.setChecked(false);
			 break;
			 
		 case R.id.rb_female:
			 rb_male.setChecked(false);
			 rb_group.setChecked(false);
			 break;
			 
		 case R.id.rb_group:
			 rb_male.setChecked(false);
			 rb_female.setChecked(false);
			 break;
			 
		 case R.id.btn_audience_screen:
			 audienceName = (et_audienceName.getText().toString()).trim();
			 
			 if(!audienceName.equalsIgnoreCase("") && (rb_male.isChecked() ||
					 rb_female.isChecked() ||
					 rb_group.isChecked()))
			   {
				 prefsEditor.putString("audience_name", audienceName);
				 if(rb_male.isChecked()){
					 prefsEditor.putString("gender", "male");
				 }
				 else if(rb_female.isChecked()){
					 prefsEditor.putString("gender", "female");
				 }
				 else{
					 prefsEditor.putString("gender", "group");
				 }
				 
				 if(skip_quiz!=null && skip_quiz.equals("yes")){
					 
					 Intent i = new Intent();
					 i.setClass(EnterAudienceName.this, How_to_start.class);
					 i.putExtra("skip_quiz", "yes");
					 startActivity(i);
					 finish();
					 prefsEditor.putString("back_screen1", "EnterName");
					 prefsEditor.commit();
					  
				 }
				 else{
					 prefsEditor.putInt("quest_count", 3);
					 prefsEditor.commit();
					 Intent i = new Intent(EnterAudienceName.this,Quiz1.class);
					 startActivity(i);
					 finish();
				 }
			}

			 else {
				 Toast.makeText(EnterAudienceName.this,
				 "Please enter Audience name and Gender", Toast.LENGTH_SHORT).show();
			 }
			 break;
		 }
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		 if(skip_quiz!=null && skip_quiz.equals("yes")){
			 startActivity(new Intent(this,EnterName.class));
			 finish();
		 }
		 else{
			 prefsEditor.putInt("quest_count", 2);
			 prefsEditor.commit();
			 startActivity(new Intent(this,Quiz1.class));
			 finish();
		 }
	  }
  }
