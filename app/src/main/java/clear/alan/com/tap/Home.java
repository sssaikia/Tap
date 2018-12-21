package clear.alan.com.tap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by Alan on 12/26/2016.
 */

public class Home extends AppCompatActivity {
    MediaPlayer startUp;
    boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        SharedPreferences q1=getSharedPreferences("checkBox1",Activity.MODE_PRIVATE);
        checked =q1.getBoolean("checkBox1", true);
        ((CheckBox)findViewById(R.id.soundCheeck)).setChecked(checked);

        ((CheckBox)findViewById(R.id.soundCheeck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences b1=getSharedPreferences("checkBox1", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=b1.edit();
                editor.putBoolean("checkBox1", b).apply();
                if (b){
                    if (startUp != null) {
                        startUp.release();
                    }
                    startUp = MediaPlayer.create(Home.this, R.raw.loop);
                    startUp.start();
                    startUp.setLooping(true);
                }else {
                    try {
                        startUp.stop();
                        startUp.release();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        hideSystemUI();

        if (checked) {
            if (startUp != null) {
                startUp.release();
            }
            startUp = MediaPlayer.create(this, R.raw.loop);
            startUp.start();
            startUp.setLooping(true);
        }


        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.pop);
       findViewById(R.id.startgame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startApp();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
       findViewById(R.id.leaderboardhome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(Home.this,TopPlayers.class));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

       }

    private void startApp(){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                try {
                    Thread.sleep(1500);
                    hideSystemUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    /*private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    hideSystemUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

    @Override
    protected void onPause() {
        if (startUp!=null){
            try {
                startUp.stop();startUp.release();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences q1=getSharedPreferences("checkBox1",Activity.MODE_PRIVATE);
        checked =q1.getBoolean("checkBox1", true);
        if (checked) {
            if (startUp != null) {
                startUp.release();
            }
            startUp = MediaPlayer.create(this, R.raw.loop);
            startUp.start();
            startUp.setLooping(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (startUp!=null){
            try {
                startUp.stop();startUp.release();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        super.onDestroy();
    }
}
