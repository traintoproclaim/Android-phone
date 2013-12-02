package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz1 extends BaseActivity implements OnClickListener {

	private TextView question,text_quickQuiz,tv_qst_narration;
	private String option1,option2,option3,option4,option5;
	private Button next;
	private String[] arr_question,arr_que_narration;
	private int audienceNameScreenCount = 3;  //as question count starts from 0.to display this screen after 3rd question
	private RadioButton radioButton_1,radioButton_2,radioButton_3,radioButton_4,radioButton_5;
	private int result = 0;
	private int add_factor = 0;
	private int question_count = 0;
	private Button btn_next;
	private Typeface tf1,tf2;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor; 
	public static int quiz_text=1;
	private LinearLayout ll;
	private Button btn;
	
	@Override	
    public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);
		
		tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
		tf2 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio_rounded.ttf");
	/*	ll = (LinearLayout)findViewById(R.id.ll_bottom);
		btn = (Button)findViewById(R.id.btn_bottom);
		*/
		ll = (LinearLayout)findViewById(R.id.ll_bottom);
		btn = (Button)findViewById(R.id.btn_bottom);
		 if(quiz_text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		text_quickQuiz = (TextView) findViewById(R.id.tv_QuickQuiz);
		tv_qst_narration = (TextView) findViewById(R.id.tv_bottom);
		question = (TextView) findViewById(R.id.tv_quizQuestion);
		question.setTypeface(tf1);
		
		text_quickQuiz.setTypeface(tf2);
		text_quickQuiz.setText("Quiz");
		
		radioButton_1 = (RadioButton) findViewById(R.id.radio_button1);
		radioButton_2 = (RadioButton) findViewById(R.id.radio_button2);
		radioButton_3 = (RadioButton) findViewById(R.id.radio_button3);
		radioButton_4 = (RadioButton) findViewById(R.id.radio_button4);
		radioButton_5 = (RadioButton) findViewById(R.id.radio_button5);
		arr_question = getResources().getStringArray(R.array.quiz_Array);
		arr_que_narration = getResources().getStringArray(R.array.quiz_narrate_array);
		option1 = getResources().getString(R.string.option_1);
		option2 = getResources().getString(R.string.option_2);
		option3 = getResources().getString(R.string.option_3);
		option4 = getResources().getString(R.string.option_4);
		option5 = getResources().getString(R.string.option_5);
		
		next=(Button)findViewById(R.id.button_next);
		radioButton_1.setTypeface(tf1);
		radioButton_2.setTypeface(tf1);
		radioButton_3.setTypeface(tf1);
		radioButton_4.setTypeface(tf1);
		radioButton_5.setTypeface(tf1);
		
		radioButton_1.setText(option1);
		radioButton_2.setText(option2);
		radioButton_3.setText(option3);
		radioButton_4.setText(option4);
		radioButton_5.setText(option5);
		
		radioButton_1.setOnClickListener(this);
		radioButton_2.setOnClickListener(this);
		radioButton_3.setOnClickListener(this);
		radioButton_4.setOnClickListener(this);
		radioButton_5.setOnClickListener(this);
		
		next.setOnClickListener(this);
		
		myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		prefsEditor = myPrefs.edit();
		result = myPrefs.getInt("result", 0);
		question_count= myPrefs.getInt("quest_count", 0);
		
		
		if(question_count == 0){
			question.setText(arr_question[question_count]);
			tv_qst_narration.setTypeface(tf1);
			tv_qst_narration.setText(arr_que_narration[question_count]);
		}
		
		else{
			question.setText(arr_question[question_count]);
			tv_qst_narration.setTypeface(tf1);
			tv_qst_narration.setText(arr_que_narration[question_count]);
		}
		
    }
	
	@Override
	public void onClick(View v) {
		
		if(v==btn_next){
			Intent i = new Intent(Quiz1.this,Quiz1.class);
			startActivity(i);
			finish();
		}
		
		switch(v.getId())
		 {
		 case R.id.radio_button1:
			 radioButton_2.setChecked(false);
			 radioButton_3.setChecked(false);
			 radioButton_4.setChecked(false);
			 radioButton_5.setChecked(false);
			 add_factor = 1;
			 break;
		 case R.id.radio_button2:
			// array_quiz[option_count] = 2;
			 radioButton_1.setChecked(false);
			 radioButton_3.setChecked(false);
			 radioButton_4.setChecked(false);
			 radioButton_5.setChecked(false);
			 add_factor = 2;
				break;
		 case R.id.radio_button3:
			// array_quiz[option_count] = 3;
			 radioButton_2.setChecked(false);
			 radioButton_1.setChecked(false);
			 radioButton_4.setChecked(false);
			 radioButton_5.setChecked(false);
			 add_factor = 3;
				break;
		 case R.id.radio_button4:
			 radioButton_2.setChecked(false);
			 radioButton_3.setChecked(false);
			 radioButton_1.setChecked(false);
			 radioButton_5.setChecked(false);
			 add_factor = 4;
			 break;
			 
		 case R.id.radio_button5:
			// array_quiz[option_count] = 5;
			 radioButton_2.setChecked(false);
			 radioButton_3.setChecked(false);
			 radioButton_4.setChecked(false);
			 radioButton_1.setChecked(false);
			 add_factor = 5;
			 break;
		
		 }
	
		if(v == next) //for question next button
		 {
			 if(radioButton_1.isChecked() || radioButton_2.isChecked() ||
					 radioButton_3.isChecked() || radioButton_4.isChecked() ||
					 radioButton_5.isChecked())
			 {
				
				 question_count++;
				 prefsEditor.putInt("quest_count", question_count);
				 prefsEditor.commit();
				 
				 result = result+add_factor;
				// Toast.makeText(Quiz1.this,
				//			"Answer is : "+result, result).show();
				 prefsEditor.putInt("result", result);
				 prefsEditor.commit();
			 
			 if(question_count == arr_question.length) 
			 {
				 Intent i = new Intent();
			     i.setClass(Quiz1.this, Result.class);
			     startActivity(i);	
			     finish();
			 }	
			 else
			 {	
				 clearRadioButtons();
				 
				 if(question_count == audienceNameScreenCount){
					
					//question_count++;
					Intent i = new Intent(Quiz1.this,EnterAudienceName.class);
					startActivity(i);
					finish();
				 }
				 else{
					 question.setText(arr_question[question_count]);
					 tv_qst_narration.setTypeface(tf1);
					 tv_qst_narration.setText(arr_que_narration[question_count]);
				 }
    		  }
			 
			}
		   else
			 {
			   Toast.makeText(Quiz1.this,
						"Please answer the question", Toast.LENGTH_SHORT).show();
			 }
		 }

	}
	 public void clearRadioButtons(){
		 radioButton_1.setChecked(false);
		 radioButton_2.setChecked(false);
		 radioButton_3.setChecked(false);
		 radioButton_4.setChecked(false);
		 radioButton_5.setChecked(false);		 
	 }
	 
	 @Override
		public void onBackPressed() {
			super.onBackPressed();
			
			prefsEditor.putInt("quest_count", 0);
			prefsEditor.putInt("result", 0);
			prefsEditor.commit();
			startActivity(new Intent(this,Quiz_extra_screen.class));
			finish();
		}
	 
	 public void onTextClick(View v) {
			ll.setVisibility(View.INVISIBLE);
			btn.setVisibility(View.VISIBLE);
			quiz_text=1;
		}
		public void onTextButtonClick(View v) {
			ll.setVisibility(View.VISIBLE);
			btn.setVisibility(View.INVISIBLE);
			quiz_text=0;
		}
		
 }
