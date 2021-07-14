package com.example.advancelock;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

	ImageView img;
	int curTag;
	String curImg;
	Drawable tmpDr;
	ImageView tmpIv;
	InputStream is;
	public static final String PASS = "PassFile";
	SharedPreferences sp;
	SharedPreferences.Editor edi;
	EditText email;

	Intent i;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sp = getSharedPreferences(PASS, 0);
        edi = sp.edit();

        i = new Intent(MainActivity.this, ScreenOnService.class);

        stopService(i);

    }

    //on Circls clicked
    public void onCircleClick(View v) throws IOException
    {
    	tmpIv = (ImageView) v;
    	curImg = tmpIv.getTag().toString();
    	if(curImg.equals("zero"))
    	{
    		is = getAssets().open("1.png");
    		tmpDr = Drawable.createFromStream(is, null);
    		tmpIv.setImageDrawable(tmpDr);
    		tmpIv.setTag(1);
    	}
    	else
    	{
    		curTag = (Integer) tmpIv.getTag();
    		curTag = (curTag % 7) + 1;
    		is = getAssets().open(Integer.toString(curTag) + ".png");
    		tmpDr = Drawable.createFromStream(is, null);
    		tmpIv.setImageDrawable(tmpDr);
    		tmpIv.setTag(curTag);
    	}
    }
    
    //on saving
    public void onSave(View v) throws Exception
    {
    	String strPatt = "   C1  C2\n\nC3        C4\n\n   C5  C6\n\nC1 - ";
    	int tmp = 0;
    	String tmpTag;
    	SharedPreferences.Editor spEdi = sp.edit();
    	tmpIv = (ImageView) findViewById(R.id.imgOne);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c1", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat(tmpTag + " Press");
    	}
    	tmpIv = (ImageView) findViewById(R.id.imgTwo);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c2", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat("\nC2 - " + tmpTag + " Press");
    	}
    	tmpIv = (ImageView) findViewById(R.id.imgThree);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c3", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat("\nC3 - " + tmpTag + " Press");
    	}
    	tmpIv = (ImageView) findViewById(R.id.imgFour);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c4", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat("\nC4 - " + tmpTag + " Press");
    	}
    	tmpIv = (ImageView) findViewById(R.id.imgFive);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c5", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat("\nC5 - " + tmpTag + " Press");
    	}
    	tmpIv = (ImageView) findViewById(R.id.imgSix);
    	if(!tmpIv.getTag().toString().equals("zero"))
    	{
    		tmpTag = tmpIv.getTag().toString();
    		spEdi.putString("c6", tmpTag);
    		tmp = tmp + 1;
    		strPatt = strPatt.concat("\nC6 - " + tmpTag + " Press");
    	}
    	if(tmp == 6)
    	{
    		spEdi.commit();

    		Toast.makeText(getApplicationContext(), "Pattern Saved", Toast.LENGTH_SHORT).show();
			startService(i);
    		
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(), "Pattern is very week, change the color of all circle", Toast.LENGTH_SHORT).show();
    	}
    	
    }
    
    //on going
    public void onClick(View v)
    {
    	Intent tmpInt = new Intent(this, LockScreenActivity.class);
    	startActivity(tmpInt);
    }
}
