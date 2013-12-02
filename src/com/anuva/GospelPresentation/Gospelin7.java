package com.anuva.GospelPresentation;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Gospelin7 extends Activity implements OnClickListener 
{
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public static Bitmap Image_bit;
	private Button buttonPlayVideo,buttonManual,btn_menu,btn_ok,btn_crop;
	private Dialog dialog;	
	private int in_image_view =0;
	
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;
	private static final int PICK_IMAGE_GALLERY = 1;
	private static final int CROP_FROM_CAMERA = 2;
	private Uri image_Uri;
	protected int item=-1;;
	private SharedPreferences myPrefs_1;
	private Editor prefsEditor1;
   
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);
        buttonPlayVideo = (Button) findViewById(R.id.button_playVideo1);
        buttonManual = (Button) findViewById(R.id.button_manual1);
        
        buttonPlayVideo.setOnClickListener(this);
        buttonManual.setOnClickListener(this);
        
        
        btn_menu= (Button)findViewById(R.id.button_menu);
        btn_menu.setOnClickListener(this);
        
        try {
			myPrefs_1 = this.getSharedPreferences("gospel_persistence", MODE_PRIVATE);
			prefsEditor1 = myPrefs_1.edit();
			myPrefs = this.getSharedPreferences("gospel_pref", MODE_PRIVATE);
			prefsEditor = myPrefs.edit();
		}
        catch(OutOfMemoryError e){
        	
        	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setMessage("No Sufficient Space available to run the application.");
		        builder .setCancelable(false);
			     builder .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
				            finish();
						}
					}).show();
        }
        catch (Exception e) {
			
			e.printStackTrace();
		}
         
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
         String s= getIntent().getStringExtra("Second_call");
         in_image_view =0;
         if(s==null){
        	 prefsEditor.clear();
    		 prefsEditor.commit();
         }
         prefsEditor.putString("version", "short");
		 prefsEditor.commit();
		 Screen1.text=0;
    }
    
    @Override
	public void onClick(View v)
	{

    	if(v == buttonPlayVideo)
    	{
    		Intent i = new Intent();
        	i.setClass(Gospelin7.this, VideoPlayer1.class);
            startActivity(i);
            finish();
    	}

    	else if(v == buttonManual)
    	{
    		String image = myPrefs.getString("version", "nothing");
		    Log.i("version before activity called", image);
		        
			Intent i = new Intent();
        	i.setClass(Gospelin7.this, InstructionScreen.class);
            startActivity(i);
            finish();
    	}
    	
    	else if(v == btn_menu)
    	{
    		
    		dialog.setContentView(R.layout.alert_dailog_menu);			
			dialog.setTitle("Menu Option");
			
	        dialog.getWindow().setGravity(Gravity.CENTER);
	        
			RadioGroup radioGroup=(RadioGroup)dialog.findViewById(R.id.radioGroup1);
			//RadioButton rb_heven = (RadioButton) dialog.findViewById(R.id.rb_heven);
			
			radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		    {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		            // checkedId is the RadioButton selected
		        	dialog.dismiss();
		        	switch (checkedId) {
		            case -1:
		            	break;
		            	
		            case R.id.rb_long_version:
		            	prefsEditor.putString("version", "long");
    	 				prefsEditor.commit();
    	   		    	break;
	
		            case R.id.rb_short_version:
		            	 prefsEditor.putString("version", "short");
   	 				  	 prefsEditor.commit();
   	   		    	     break;
		              
		            case R.id.rb_photo:
		            	Intent intent = new Intent();
  			            intent.setType("image/*");
  			            intent.setAction(Intent.ACTION_GET_CONTENT);
  			            startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_IMAGE_GALLERY);
  			    	    break;
		            	 //check this if any problem in alert box		           
		            
		            case R.id.rb_ministry:
		            	 Intent i = new Intent(Gospelin7.this,Menu_discription_screen.class);
   			    	     i.putExtra("option", "ministry");
   			    	     startActivity(i);
   			    	     finish();
   			    	     break;
   			    	     
		            case R.id.rb_credits:
		            	 Intent i1 = new Intent(Gospelin7.this,Menu_discription_screen.class);
  			    	     i1.putExtra("option", "Credits");
  			    	     startActivity(i1);
  			    	     finish();
  			    	     break;
  			    	     
		            case R.id.rb_copyright:
		            	 Intent i2 = new Intent(Gospelin7.this,Menu_discription_screen.class);
  			    	     i2.putExtra("option", "Copyright");
  			    	     startActivity(i2);
  			    	     finish();
  			    	     break;
   			    	     
		            case R.id.rb_login_details:
		            	 Intent i3 = new Intent(Gospelin7.this,Login_screen.class);
 			    	     i3.putExtra("menu_option", "true");
 			    	     startActivity(i3);
 			    	     finish();
 			    	     break;
 			    	     
		           /* case R.id.rb_close:
			    	     break;*/
		            }
		        }
		    });
 
			dialog.show();
    
    	}
    	
    	else if(v == btn_ok)
    	{
    		btn_ok.setClickable(false);
    		 
   			  
    		String str = encodeTobase64(Image_bit);
    		prefsEditor1.putString("presenter_image", str);
    		prefsEditor1.commit();
		      
    		
    		
    		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setMessage("Image Set Successfully.");
		        builder .setCancelable(false);
			     builder .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							//Intent i = new Intent();
							//i.putExtra("Second_call", "yes");
				        	//i.setClass(Gospelin7.this, Gospelin7.class);
				        	
				            //startActivity(i);
				            //finish();
						}
					}).show();
			}
    	
    	else if(v == btn_crop){
			doCrop();
    	}
	}

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("In OnActivityResult========================================");
		if(data!=null){
		if(requestCode== PICK_IMAGE_GALLERY){
			image_Uri = data.getData();
			 
	        try {
				Image_bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_Uri);
			
	        } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        in_image_view=1;
			
	        doCrop();
	        
	        /* setContentView(R.layout.image_view);
			iv_picture = (ImageView) findViewById(R.id.iv_picture);
			iv_picture.setImageBitmap(Image_bit);
			btn_ok = (Button) findViewById(R.id.btn_ok);
	        btn_crop = (Button) findViewById(R.id.btn_crop);
	        btn_ok.setOnClickListener(this);
	        btn_crop.setOnClickListener(this);*/
	       
		}
		
		else if(requestCode==CROP_FROM_CAMERA){
			if(data!=null){
	        	 Bundle extras = data.getExtras();
	        	 if (extras != null){
	        		 
	        		 Image_bit = extras.getParcelable("data");
	        	//	 iv_picture.setImageBitmap(Image_bit);
	        		 
	        		 String str = encodeTobase64(Image_bit);
	         		 prefsEditor1.putString("presenter_image", str);
	         		 prefsEditor1.commit();
	     		      
	         		
	         		
	         		  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	     		      builder.setMessage("Image Set Successfully.");
	     		      builder .setCancelable(false);
	     			  builder .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	     						@Override
	     						public void onClick(DialogInterface dialog, int which) {
	     						
	     						}
	     					}).show();
	        	 }
	         }
		  }
		
		}
	}
    
    
    private void doCrop() {
    	Log.i("PickLogoOrPicture","In Do Crop========");
		final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();
    	
    	Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        
        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        
        int size = list.size();
    	Log.i("PickLogoOrPicture","Crop options count======== "+size);

    	
    	
        if (size == 0) {	        
        	//Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        	//alert("", "Sorry! Can not find image crop app");
            return;
        } else {
        	intent.setData(image_Uri);
            
           /* intent.putExtra("outputX", 320);
            intent.putExtra("outputY", 160);
            intent.putExtra("aspectX", 2);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);*/
        	
        	 intent.putExtra("outputX", 200);
             intent.putExtra("outputY", 200);
             intent.putExtra("aspectX", 1);
             intent.putExtra("aspectY", 1);
             intent.putExtra("scale", true);
             intent.putExtra("return-data", true);
            
        	if (size == 1) {
        		Intent i 		= new Intent(intent);
	        	ResolveInfo res	= list.get(0);
	        	
	        	i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
	        	startActivityForResult(i, CROP_FROM_CAMERA);
        	} 
        	else {
		        for (ResolveInfo res : list) {
		        	final CropOption co = new CropOption();
		        	
		        	co.title 	= getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
		        	co.icon		= getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
		        	co.appIntent= new Intent(intent);
		        	co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
		            cropOptions.add(co);
		        }
	        
		        CropOptionAdapter adapter = new CropOptionAdapter(getApplicationContext(), cropOptions);
		        
		        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("Crop Image");
		        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
		            public void onClick( DialogInterface dialog, int item ) {
		                startActivityForResult( cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
		            }
		        });
	        
		        builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
		            @Override
		            public void onCancel( DialogInterface dialog ) {
		               
		                if (image_Uri != null ) {
		                    getContentResolver().delete(image_Uri, null, null );
		                    image_Uri = null;
		                }
		            }
		        } );
		        
		        AlertDialog alert = builder.create();
		        alert.show();
        	}
        }
	}
    
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        String imageEncoded ="";
       try{
    	   immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
           byte[] b = baos.toByteArray();
         imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
       } catch(Exception e){
            e.printStackTrace();
        }catch(OutOfMemoryError e){
        	 immagex.compress(Bitmap.CompressFormat.JPEG, 30, baos);
             byte[] b = baos.toByteArray();
             imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
          
            Log.e("EWN", "Out of memory error catched");
        }
        Log.e("LOOK", imageEncoded);
    //    Dialog.dismiss();
        return imageEncoded;
    }
    /*public static Bitmap decodeBase64(String input) 
    {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
    }*/
   @Override
	public void onBackPressed() {
		super.onBackPressed();
		//startActivity(new Intent(this,Response.class));
		if(in_image_view==1){
			Intent i = new Intent();
			i.putExtra("Second_call", "yes");
        	i.setClass(Gospelin7.this, Gospelin7.class);
        	
            startActivity(i);
            finish();
		}
		else{
			finish();
		}
		
	}
   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
    menu.add("Exit");
    
    return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
	
	 finish();
	 return super.onOptionsItemSelected(item);
   }
}
