
// Tero Paananen 2011
// tepaanan@gmail.com
// FINLAND

package com.tero;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class MyAndroidPrjActivity extends Activity {

	private MainView mainView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Screen to landscape
        // Press Ctrl + F11 to change emulator orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        // No title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // First and the only view
        mainView = new MainView(this);
        setContentView(mainView);
                
    }
}