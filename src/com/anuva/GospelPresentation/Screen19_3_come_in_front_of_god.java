package com.anuva.GospelPresentation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen19_3_come_in_front_of_god  extends BaseActivity implements OnClickListener {

	private RelativeLayout rl,rl_comment;
	private ImageView iv_earth,iv_top,iv_picture,iv_church,iv_picture1;
	private TextView tv_bottom,tv_speech;
	private int click_no=1;
	private String presenter_name,formattedDate;
	private SharedPreferences myPrefs;
	private LinearLayout ll;
    private Button btn;
    private String str_back ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen19_3);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        myPrefs= this.getSharedPreferences("gospel_pref",MODE_PRIVATE);
        
        presenter_name = myPrefs.getString("user_name", "....");
        SharedPreferences .Editor edit = myPrefs.edit();
        edit.putString("back_screen20", "Screen19_3_come_in_front_of_god");
        edit.commit();
        
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		formattedDate = df.format(c.getTime());
        
		tv_speech=(TextView)findViewById(R.id.tv_speech);
        iv_earth=(ImageView)findViewById(R.id.iv_world);
        iv_top=(ImageView)findViewById(R.id.iv_top);
        iv_picture=(ImageView)findViewById(R.id.iv_picture);
        iv_picture1=(ImageView)findViewById(R.id.iv_picture1);
        rl_comment=(RelativeLayout)findViewById(R.id.rl_comment);
        iv_earth.setBackgroundResource(R.anim.anim_to_earth);
        iv_church = (ImageView)findViewById(R.id.iv_church);
		
        Typeface tf1 = Typeface.createFromAsset(this.getAssets(),
                "fonts/opificio.ttf");
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        tv_bottom.setTypeface(tf1);
        
        ll = (LinearLayout)findViewById(R.id.ll_bottom);
		 btn = (Button)findViewById(R.id.btn_bottom);
  
		 if(Screen1.text==1){
			 ll.setVisibility(View.INVISIBLE);
			 btn.setVisibility(View.VISIBLE);
		 }
		 str_back = getIntent().getStringExtra("back_19");
	        if(str_back!=null && str_back.equals("yes")){
	        	iv_earth.setBackgroundResource(R.drawable.earth6);
	        	iv_top.setVisibility(View.VISIBLE);
	        	tv_bottom.setText("Firstly, I died on the cross for you.");
	        	click_no=5;
	        }
	        
	        else{
	        	 tv_bottom.setText("You would come before God at judgment and Jesus would say ");
	             
	             Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
	                     "fonts/opificio_rounded.ttf");
	             tv_speech.setTypeface(tf2);
	        }
       
    }

    @Override
	public void onClick(View v) {
		if(v == rl){
			switch(click_no){
			case 1:
				rl_comment.setVisibility(View.VISIBLE);
				tv_speech.setText(Html.fromHtml("I\'m sorry,<br> but I can\'t let you <br> into Heaven"));
		        tv_bottom.setText("\"I\'m sorry, but I can\'t let you into Heaven.");
		        click_no++;
		        break;
		        
			case 2:
				tv_speech.setText(Html.fromHtml("You don’t have a perfect record,<br> I have to be just <br> and send you to Hell."));
		        tv_bottom.setText(" You don’t have a perfect record, I have to be just and send you to Hell.");
		        click_no++;
		        break;
			
			case 3:
				tv_speech.setText(Html.fromHtml("I loved you so much <br> I tried at least six ways <br> to get through to you."));
				tv_bottom.setText(" I loved you so much I tried at least six ways to get through to you. ");
				click_no++;
				break;
				
			case 4:
				rl_comment.setVisibility(View.INVISIBLE);
				iv_top.setVisibility(View.VISIBLE);
		        tv_bottom.setText("Firstly, I died on the cross for you.");
		        click_no++;
		        break;
		        
			case 5:
				SharedPreferences myPrefs1 = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
		        String image = myPrefs1.getString("presenter_image", "nothing");
		        if(!(image.equalsIgnoreCase("nothing"))){
		    	   Log.i("image not null", "null");
		    	   byte[] b = Base64.decode(image, Base64.DEFAULT);
			       InputStream is = new ByteArrayInputStream(b);
			       Bitmap b1 = BitmapFactory.decodeStream(is);
			       iv_picture1.setImageBitmap(b1);
			       iv_picture1.setVisibility(View.VISIBLE);
			     
			       int  finalwidht = iv_picture.getMeasuredWidth();
			       RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(finalwidht, finalwidht);
			       layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			       iv_picture1.setLayoutParams(layoutParams);
		         }
		        else{
		        	iv_picture.setVisibility(View.VISIBLE);
		        }
				iv_top.setVisibility(View.INVISIBLE);
				
		        tv_bottom.setText("  Secondly I sent " + presenter_name +" on "+ formattedDate+" to tell you why I died on the cross and how you could be forgiven.");
		        click_no++;
		        break;
		        
			case 6:
				iv_picture.setVisibility(View.INVISIBLE);
				iv_picture1.setVisibility(View.INVISIBLE);
				iv_top.setVisibility(View.VISIBLE);
				iv_top.setBackgroundResource(R.drawable.consince);
		        tv_bottom.setText("Thirdly, I gave you a conscience so that you would know right from wrong.");
		        click_no++;
		        break;
		        
			case 7:
				iv_top.setVisibility(View.INVISIBLE);
				iv_church.setVisibility(View.VISIBLE);
		        tv_bottom.setText("Fourthly, there were some good churches in the area where you lived.");
		        click_no++;
		        break;
			case 8:
			
		        tv_bottom.setText("You could have found " +
		        		"out about Me at one of them.");
		        click_no++;
		        break;
			
			case 9:
				startActivity( new Intent(this, Screen19_3_2_come_in_front_of_god.class));
				finish();
				break;
    			}
			}
		}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) 
	{
		super.onWindowFocusChanged(hasFocus);
		 if(str_back!=null && str_back.equals("yes")){
			 
		 }
		 else{
			    AnimationDrawable yourAnimation = 
			        (AnimationDrawable) iv_earth.getBackground();
			    if(hasFocus) {
			    	yourAnimation.start();
			    } else {
			    	yourAnimation.stop();
			    }
		 }
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back!=null && str_back.equals("yes")){
			  Intent i = new Intent(this, Screen19_3_come_in_front_of_god.class);
		      startActivity(i);
			  finish();
		  }
		else{
			Intent i = new Intent(this,Screen19_2_jesus_gave_his_rec_long.class);
			startActivity(i);
			finish();
		}
		
	}
	public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
    }
	public void onTextClick(View v) {
		ll.setVisibility(View.INVISIBLE);
		btn.setVisibility(View.VISIBLE);
		Screen1.text=1;
	}
	public void onTextButtonClick(View v) {
		ll.setVisibility(View.VISIBLE);
		btn.setVisibility(View.INVISIBLE);
		Screen1.text=0;
	}
}