package com.anuva.GospelPresentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Screen14_before_jesus_came_earth extends BaseActivity implements OnClickListener {

	private RelativeLayout rl;
	private ImageView iv_heart,iv_perfect_rec,iv_people;
	private TextView tv_bottom,tv_center;
	private int click_no=1;
	private String str_name,str_gender;
	private LinearLayout ll;
    private Button btn;
    private String str_back ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen14_1);
        rl = (RelativeLayout)findViewById(R.id.rl_1);
        rl.setOnClickListener(this);
       
        iv_heart=(ImageView)findViewById(R.id.iv_heart);
        iv_people = (ImageView) findViewById(R.id.iv_people_group);
      
        iv_perfect_rec=(ImageView)findViewById(R.id.iv_perfect_rec);
        tv_center=(TextView)findViewById(R.id.tv_center);
        
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
		 str_back = getIntent().getStringExtra("back_14");
	        if(str_back!=null && str_back.equals("yes")){
	        	 tv_bottom.setText("So I have a plan! ");
	        	 click_no=4;
	        }
	        
	        else{
	        	tv_bottom.setText("Imagine this. Even before Jesus came down to earth God knew you");
	            
	            SharedPreferences myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
	            str_name = myPrefs.getString("audience_name", "YOU");
	            str_gender= myPrefs.getString("gender", "group");
	            tv_center.setText(Html.fromHtml(" I Love <br>"+ str_name) );
	        }
        
        
        
    }
	@Override
	public void onClick(View v) {
		if(v == rl){
			
			switch(click_no){
						
			case 1:
				tv_center.setVisibility(View.VISIBLE);
				iv_heart.setVisibility(View.VISIBLE);
				if(str_gender.equalsIgnoreCase("group")){
			        tv_bottom.setText(" and thought, I love "+ str_name+ ", I don’t want them to go to Hell.");
				}
				else if(str_gender.equalsIgnoreCase("male")){
			        tv_bottom.setText(" and thought, I love "+ str_name+ ", I don’t want him to go to Hell.");
				}
				else
			        tv_bottom.setText(" and thought, I love "+ str_name+ ", I don’t want her to go to Hell.");
		        	click_no++;
		        	break;
			
			case 2:
				if(str_gender.equalsIgnoreCase("group")){
					tv_bottom.setText("But they’ve broken My laws, so there must be a just punishment.");
				 }
				else if(str_gender.equalsIgnoreCase("male")){
					tv_bottom.setText("But he's broken My laws, so there must be a just punishment.");
				}
				else
					tv_bottom.setText("But she's broken My laws, so there must be a just punishment.");
					click_no++;
					break;
			case 3:
				 tv_center.setVisibility(View.INVISIBLE);
				 iv_heart.setVisibility(View.INVISIBLE);
				 tv_bottom.setText("So I have a plan! ");
				 click_no++;
				 break;
				 
			case 4:
				iv_heart.setVisibility(View.GONE);
				iv_people.setVisibility(View.INVISIBLE);
				iv_people.setBackgroundResource(R.anim.anim_jesus_zoon_in);
				iv_people.setVisibility(View.VISIBLE);
				tv_bottom.setText("I’ll send Jesus to earth,");
		        click_no++;
		        break;
		     
			case 5:
				iv_people.setVisibility(View.INVISIBLE);
				iv_people.setBackgroundResource(R.anim.anim_jesus_zoon_out);
				iv_people.setVisibility(View.VISIBLE);
				tv_bottom.setText("as a human being,");
		        click_no++;
				break;
				
			case 6:
				iv_perfect_rec.setVisibility(View.VISIBLE);
				iv_heart.setVisibility(View.INVISIBLE);
				tv_bottom.setText("to live a perfect life");
		        click_no++;
		        break;
			
	
			case 7:
				startActivity( new Intent(this, Screen14_2.class));
				finish();
				break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(str_back!=null && str_back.equals("yes")){
			  Intent i = new Intent(this, Screen14_before_jesus_came_earth.class);
		      startActivity(i);
			  finish();
		  }
		else{
			Intent i = new Intent(this,Screen13_jesus_soul_and_ours.class);
			startActivity(i);
			finish();
		}
		
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
