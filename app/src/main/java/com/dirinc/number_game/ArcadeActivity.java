package com.dirinc.number_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArcadeActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button button_arcade_go;
    private static final String SHARED_PREFS = "shared_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setColors();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade);

        if (android.os.Build.VERSION.SDK_INT>=19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        initializeButtons();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && android.os.Build.VERSION.SDK_INT>=19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.button_arcade_go:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    button_arcade_go.setBackgroundColor(getResources().getColor(R.color.button_background_pressed));
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    return true;
                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    button_arcade_go.setBackgroundColor(getResources().getColor(R.color.button_background));
                    startActivity(1);
                    return true;
                }
                return false;

            default:
                return false;
        }
    }

    public void initializeButtons() {
        button_arcade_go = (Button) findViewById(R.id.button_arcade_go);
        button_arcade_go.setOnTouchListener(this);
    }

    public void setColors() {
        Log.d("UI", "Setting Theme Color");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, 0);
        String unlockedColor = sharedPreferences.getString("color", "");

        switch (unlockedColor) {
            case "Purple":
                setTheme(R.style.Purple);
                break;
            case "Blue":
                setTheme(R.style.Blue);
                break;
            case "Teal":
                setTheme(R.style.Teal);
                break;
            case "Yellow":
                setTheme(R.style.Yellow);
                break;
            case "Orange":
                setTheme(R.style.Orange);
                break;
            case "Red":
                setTheme(R.style.Red);
                break;
            case "Pink":
                setTheme(R.style.Pink);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }
    }

    public void startActivity(int which) {
        Intent changeActivities;

        /*
         * 1 for Initial Countdown Activity
         */
        switch (which) {
            case 1:
                changeActivities = new Intent(this, ArcadeCountdownActivity.class);
                Log.d("ActivitySwitch", "Switching to Initial Countdown Activity");
                startActivity(changeActivities);
                break;
        }
    }
}
