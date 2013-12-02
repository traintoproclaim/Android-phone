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

public class InstructionScreen extends BaseActivity  implements OnClickListener 
{
	private Button btn_go;
	private Typeface tf1,tf2;
	private TextView tv_instruction,tv_instruction_title;
	public void onCreate(Bundle instructionSavedInstance)
	{
		super.onCreate(instructionSavedInstance);
        setContentView(R.layout.instruction);
        tv_instruction = (TextView) findViewById(R.id.tv_instruction);
        tv_instruction_title = (TextView) findViewById(R.id.tv_instrucation_title);
       
        tf1 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio.ttf");
		tf2 = Typeface.createFromAsset(this.getAssets(),"fonts/opificio_bold.ttf");
        btn_go = (Button) findViewById(R.id.btn_instruction_go);
        tv_instruction_title.setTypeface(tf2);
        tv_instruction.setTypeface(tf1);
        
        tv_instruction.setText(Html.fromHtml("Read the words at the bottom and tap the screen  <br>" +
        									 "when you get to the second to last word<br>" +
        									 "so that you can seamlessly flow onto<br> " +
        									 "the words on the next screen.<br>" +
        									 "Please practice until you have a good flow.<br>" +
        									 "When you know the words tap on them and they disappear.<br>"+
        									 "Tap on the text symbol to get the words back."+
        									 "<p>Go to <a href=\"http://www.traintoproclaim.com\">www.traintoproclaim.com</a> for <br>"+
        									 " a video tutorial on how to use this App."
        				));
        tv_instruction.setMovementMethod(LinkMovementMethod.getInstance());
        btn_go.setOnClickListener(this);
	}
	
	 @Override
		public void onClick(View v)
		{
		 	if(v == btn_go)
		 	{
		 		 Intent i = new Intent();
		         i.setClass(InstructionScreen.this, EnterName.class);
	             startActivity(i);
	             finish();
		 	}
		}
	 
	 @Override
		public void onBackPressed() {
			super.onBackPressed();
			startActivity(new Intent(this,Gospelin7.class));
			finish();
		}
 }
