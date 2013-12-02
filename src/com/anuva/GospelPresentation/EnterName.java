package com.anuva.GospelPresentation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EnterName extends BaseActivity implements OnClickListener 
{
	private EditText userName;
	private String userNameText,str_enterUserNameText;
	private RadioButton rb_skipQuiz,rb_goQuiz;
	private Button btn_next;
	private Typeface tf1;
	private TextView tv_enterUserName;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;
	//public int screen_width = getScreenWidth(context);
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Quiz1.quiz_text =0;
        
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
        tv_enterUserName = (TextView) findViewById(R.id.tv_enterUserName);
        Resources res = getResources();
        str_enterUserNameText = res.getString(R.string.text_enterUserName);
        tv_enterUserName.setTypeface(tf1);
        tv_enterUserName.setText(str_enterUserNameText);
        userName = (EditText) findViewById(R.id.edit_Text_UserName);
        btn_next = (Button) findViewById(R.id.button_next_enterName);
        
        rb_goQuiz = (RadioButton) findViewById(R.id.rb_goQuiz);
        rb_skipQuiz = (RadioButton) findViewById(R.id.rb_skipQuiz);
        rb_goQuiz.setTypeface(tf1);
        rb_goQuiz.setText(res.getString(R.string.take_quiz));
        rb_skipQuiz.setTypeface(tf1);
        rb_skipQuiz.setText(res.getString(R.string.Skip_quiz));
        rb_skipQuiz.setOnClickListener(this);
        rb_goQuiz.setOnClickListener(this);
        btn_next.setOnClickListener(this);
       
        myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
        prefsEditor = myPrefs.edit();
        userNameText="";
      
    }

   
    @Override
	public void onClick(View v)
	{
    	userNameText = (userName.getText().toString()).trim();

		 if(userNameText.equalsIgnoreCase(""))
			 userNameText = "...";

		 switch(v.getId())
		 {
		 case R.id.rb_goQuiz:
			 rb_skipQuiz.setChecked(false);   
       	     break;
       	     
		 case R.id.rb_skipQuiz:
			 rb_goQuiz.setChecked(false);   
       	  	 break;
       	  	 
		 case R.id.button_next_enterName:
			 if(rb_skipQuiz.isChecked())
			 {
				//Put user name into shared Preference
				prefsEditor.putString("user_name", userNameText);
				prefsEditor.commit();

				Intent i = new Intent();
				i.setClass(EnterName.this, EnterAudienceName.class);
				i.putExtra("skip_quiz", "yes");
				startActivity(i);
				finish();
				
				/*Intent i = new Intent();
				i.setClass(EnterName.this, How_to_start.class);
				i.putExtra("skip_quiz", "yes");
				startActivity(i);
				finish();*/
				
			 }
			 else if (rb_goQuiz.isChecked()) {
				 prefsEditor.putString("user_name", userNameText);
				 prefsEditor.commit();

				 Intent i = new Intent();
					i.setClass(EnterName.this, How_to_start.class);
					i.putExtra("skip_quiz", "no");
					startActivity(i);
					finish();
			  } 
			 else {
				 Toast.makeText(EnterName.this,
							"Please select option.", Toast.LENGTH_SHORT).show();
			 }
			 break;
		 }
	}
    @Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,InstructionScreen.class));
		finish();
	}
 }
