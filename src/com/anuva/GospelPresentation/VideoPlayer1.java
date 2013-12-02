package com.anuva.GospelPresentation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer1 extends Activity
{
	private VideoView mVideoView;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor; 
	
	@Override
    public void onCreate(Bundle iBundle){
		
		super.onCreate(iBundle);
		setContentView(R.layout.videoplayer);
		mVideoView = (VideoView) this.findViewById(R.id.video); 
		mVideoView.setMediaController(new MediaController(this));
		//String path = "android.resource://" + getPackageName() + "/" + R.raw.notification;
		String path = "android.resource://" + getPackageName() + "/" + R.raw.g7_480;

		//String path ="";
		mVideoView.setVideoURI(Uri.parse(path));
		
		myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
		prefsEditor = myPrefs.edit();
		
		mVideoView.setOnPreparedListener(new OnPreparedListener()
         {
           public void onPrepared(MediaPlayer mp) {
        	   mVideoView.requestFocus();
        	   mVideoView.start();
            }   
         });
		
		mVideoView.setOnCompletionListener(new OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent main = new Intent(VideoPlayer1.this, Response.class);
                prefsEditor.putString("video_back", "yes");
                prefsEditor.commit();
                VideoPlayer1.this.startActivity(main);
                VideoPlayer1.this.finish();
            }
        });
	}
	 @Override
     public void onBackPressed() {
             super.onBackPressed();
             startActivity(new Intent(this,Gospelin7.class));
             finish();
    }
  }
