package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Quiz_extra_screen extends BaseActivity implements OnClickListener {

	private TextView tv_quiz_extra_screen;
	private Button btn_extra_screen;
	@Override	
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_extra_screen);
		tv_quiz_extra_screen = (TextView) findViewById(R.id.tv_quizExtraText);
	
		btn_extra_screen = (Button)findViewById(R.id.btn_extra_screen);
		Typeface tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
		 
		tv_quiz_extra_screen.setTypeface(tf1);		
		btn_extra_screen.setOnClickListener(this);
    
		SharedPreferences myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.putInt("result", 0);
		prefsEditor.putInt("quest_count", 0);
		prefsEditor.commit();
		
		
		tv_quiz_extra_screen.setText(Html.fromHtml(
				"10 quick questions <br>" +
				"Give you a score - analyse it <br>" +
				"Then for 6.5 minutes I'll give you<br>" +
				"the best definition you have ever <br>" +
				" heard of what a Christian is.<br>" +
				"Here's the first one ..."));
    }
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(Quiz_extra_screen.this,Quiz1.class);
		startActivity(i);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this,EnterName.class));
		finish();
	}
}
