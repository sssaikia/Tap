package clear.alan.com.tap;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONObject;

//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Game extends AppCompatActivity {
    TextView textView, randomT, textView2, hiscr;
    Button button, button2, button3, button4, button5, button6;
    int check, score, hs, life = 3;
    String pname;
    ImageView life1, life2, life3;
    boolean pressed, firstTouch = true, reflectscore = true, checkBoxcheck;
    long countdownPeriod = 30000, conjugative5press = 1;
    CountDownTimer mCountdowntimer;
    MediaPlayer mpRight, mpWrong;
    /*
        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }*/
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        hideSystemUI();
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5433263595056427~6059179196");
        hiscr = findViewById(R.id.hiscr);
        textView = findViewById(R.id.text);
        randomT = findViewById(R.id.randomtext);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);

        AdView mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        loadInterstitial();

       /*  final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mAdView.bringToFront();
            }
        }, 5000);
*/

        SharedPreferences q1 = getSharedPreferences("sdlg", Activity.MODE_PRIVATE);
        final boolean checked = q1.getBoolean("sdlg", false);
        if (!checked) {
            startInfoDialog();
        }

        st();
        sc();
        chckhiscr();
        randomT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retry();
            }
        });
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.pop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 1) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 2) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 3) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 4) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 5) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                if (check == 6) {
                    right();
                } else {
                    wrong();
                }
                st();
                sc();
            }
        });


    }


    public void bTchd() {

        if (firstTouch) {
            //touch++;
            mCountdowntimer = new CountDownTimer(countdownPeriod, 1000) {

                public void onTick(long millisUntilFinished) {
                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //gameOn=true;
                    countdownPeriod = millisUntilFinished + 2000;
                }

                public void onFinish() {
                    //to=false;
                    SharedPreferences q1 = getSharedPreferences("key", Activity.MODE_PRIVATE);
                    hs = q1.getInt("key", 0);
                    //gameOn=false;
                    if (hs < score) {
                        SharedPreferences.Editor editor = q1.edit();
                        editor.putInt("key", score);
                        editor.apply();
                        editor.commit();
                        highScoreachieved();
                    } else {
                        // if (shouldShow==true){
                        textView.setText("Game Over");
                        gameOverDialog();

                        /*}
                    else{textView.setText("Press ready");}*/
                    }

                }

            }.start();
        }

    }

    public void sc() {
        int i1 = (int) (6 * Math.random()) + 1;
        switch (i1) {
            case 1: {

                randomT.setTextColor(Color.parseColor("#FF99CC00"));
                check = 6;
            }
            break;
            case 2: {

                randomT.setTextColor(Color.parseColor("#ff69b4"));
                check = 5;
            }
            break;
            case 3: {

                randomT.setTextColor(Color.parseColor("#FF33B5E5"));
                check = 3;
            }
            break;
            case 4: {

                randomT.setTextColor(Color.parseColor("#FFFF4444"));
                check = 2;
            }
            break;
            case 5: {

                randomT.setTextColor(Color.parseColor("#FFAA66CC"));
                check = 1;
            }
            break;
            case 6: {

                randomT.setTextColor(Color.parseColor("#ffff00"));
                check = 4;
            }
            break;
        }
    }

    public void st() {
        int i1 = (int) (6 * Math.random()) + 1;
        switch (i1) {
            case 1: {
                randomT.setText("RED");

            }
            break;
            case 2: {
                randomT.setText("BLUE");

            }
            break;
            case 3: {
                randomT.setText("YELLOW");

            }
            break;
            case 4: {
                randomT.setText("GREEN");

            }
            break;
            case 5: {
                randomT.setText("PINK");

            }
            break;
            case 6: {
                randomT.setText("PURPLE");

            }
            break;
        }
    }

    public void wrong() {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        randomT.startAnimation(animation);
        SharedPreferences r1 = getSharedPreferences("checkBox1", Activity.MODE_PRIVATE);
        final boolean checked = r1.getBoolean("checkBox1", true);
        if (checked) {
            if (mpWrong != null) {
                mpWrong.release();
            }

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(70);
            mpWrong = MediaPlayer.create(this, R.raw.wrong);
            mpWrong.start();
        }
        /*if (mpWrong.isPlaying()){
            mpWrong.stop();
            mpWrong.release();
            mpWrong.start();
        }else {
            mpWrong.start();
        }*/

        bTchd();
        conjugative5press = 0;
        firstTouch = false;
        switch (life) {
            case 3:
                life1.setImageDrawable(null);
                break;
            case 2:
                life2.setImageDrawable(null);
                break;
            case 1:
                life3.setImageDrawable(null);
                break;
        }
        life--;
        randomT.setBackgroundResource(R.drawable.wrong);
        new CountDownTimer(50, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                randomT.setBackgroundResource(R.drawable.rounded_corner);
            }
        }.start();
        if (life == 0) {
            mCountdowntimer.cancel();
            SharedPreferences q1 = getSharedPreferences("key", Activity.MODE_PRIVATE);
            hs = q1.getInt("key", 0);
            if (hs < score) {
                SharedPreferences.Editor editor = q1.edit();
                editor.putInt("key", score);
                editor.apply();
                editor.commit();
                highScoreachieved();
            } else {
                gameOverDialog();
            }
        }
    }

    public void right() {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        randomT.startAnimation(animation);
        SharedPreferences w1 = getSharedPreferences("checkBox1", Activity.MODE_PRIVATE);
        final boolean checked = w1.getBoolean("checkBox1", true);
        if (checked) {
            if (mpRight != null) {
                mpRight.release();
            }
            mpRight = MediaPlayer.create(this, R.raw.right);
            mpRight.start();
        }
        /*if (mpRight.isPlaying()){
            mpRight.stop();
            mpRight.release();
            mpRight.start();
        }else {
            mpRight.start();
        }*/

        conjugative5press++;
        bTchd();
        firstTouch = false;
        randomT.setBackgroundResource(R.drawable.right);
        new CountDownTimer(50, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                randomT.setBackgroundResource(R.drawable.rounded_corner);
            }
        }.start();

        if (reflectscore) {
            score++;
            textView2.setText("SCORE " + score);
        }

        if (conjugative5press % 5 == 0) {
            mCountdowntimer.cancel();
            firstTouch = true;
            //bTchd();
            //mCountdowntimer.start();
        }
    }

    public void startInfoDialog() {
        SharedPreferences q1 = getSharedPreferences("sdlg", Activity.MODE_PRIVATE);
        final boolean checked = q1.getBoolean("sdlg", false);
        View checkboxView = View.inflate(this, R.layout.checkbox, null);
        CheckBox checkBox = checkboxView.findViewById(R.id.checkbox);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.coolTheme);
        builder.setTitle("How to play?");
        builder.setView(checkboxView);

        checkBox.setChecked(checked);
        checkBox.setText("Do not show again.");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                SharedPreferences b1 = getSharedPreferences("sdlg", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = b1.edit();
                editor.putBoolean("sdlg", checked).apply();
            }
        });
        builder.setMessage("There are six buttons with six colour names.\n\n" +
                " On the box above, will appear the names of those colors.\n\n" +
                " Mind that, You have to select only the \"colour\" of the word. " +
                "\n\nYou have three lives and thirty seconds. \n\nLet's see how much you can score." +
                "" +
                "\n \n \n\n");
        builder.setNegativeButton("Let's play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferedName = getSharedPreferences("SavedName", Activity.MODE_PRIVATE);
                String chkNull = preferedName.getString("SavedName", "null");
                if (chkNull.equals("null") || chkNull.isEmpty()) {
                    inputName();
                }
            }
        });
        AlertDialog sdlg = builder.create();
        sdlg.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        sdlg.show();
        TextView textVieww1 = sdlg.findViewById(android.R.id.message);
        textVieww1.setTextSize(20);
    }

    public void gameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.scoreboard, null);
        builder.setView(view);
        TextView sooretext = view.findViewById(R.id.scoretext);
        TextView scoreval = view.findViewById(R.id.scorevalue);
        Button retry = view.findViewById(R.id.retrybutton);
        Button leaderboard = view.findViewById(R.id.leaderboard);
        Button exit = view.findViewById(R.id.exitbutton);
        sooretext.setText("GAME OVER");
        scoreval.setText("SCORE " + score);
        builder.setCancelable(false);
        final AlertDialog dlg = builder.create();
        dlg.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.pop);
        Button continu = view.findViewById(R.id.continu);
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                loadInterstitial();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dlg.dismiss();

                        continueGame();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        retry();
                        dlg.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dlg.dismiss();
                        startActivity(new Intent(Game.this, TopPlayers.class));
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                //todo open activity

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dlg.dismiss();
                        randomT.setText("Click here to start");
                        textView.setText("Ready");
                        reflectscore = false;
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        dlg.show();

    }

    public void retry() {
        life1.setImageResource(R.drawable.life);
        life2.setImageResource(R.drawable.life);
        life3.setImageResource(R.drawable.life);
        life = 3;
        if (mCountdowntimer != null) {
            mCountdowntimer.cancel();
        }
        st();
        sc();
        textView.setText("Let's go");
        firstTouch = true;
        reflectscore = true;
        countdownPeriod = 30000;
        conjugative5press = 0;
        score = 0;
        chckhiscr();
    }

    public void continueGame() {
        life3.setImageResource(R.drawable.life);
        life = 1;
        if (mCountdowntimer != null) {
            mCountdowntimer.cancel();
        }
        st();
        sc();
        //textView.setText("Let's go");
        firstTouch = true;
        reflectscore = true;
        countdownPeriod = 30000;
        conjugative5press = 0;
        //score = 0;
        chckhiscr();
    }

    public void chckhiscr() {
        SharedPreferences q1 = getSharedPreferences("key", Activity.MODE_PRIVATE);
        hs = q1.getInt("key", 0);
        hiscr.setText("Top record " + hs);
    }

    public void highScoreachieved() {
        SharedPreferences q1 = getSharedPreferences("key", Activity.MODE_PRIVATE);
        hs = q1.getInt("key", 0);
        //hs = (0 - hs);
        SharedPreferences preferedName = getSharedPreferences("SavedName", Activity.MODE_PRIVATE);
        String chkNull = preferedName.getString("SavedName", "null");

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://androidbug.ml/api/updatedata.php?name=" + chkNull + "&score=" + hs,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Game.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

        final AlertDialog.Builder b = new AlertDialog.Builder(this);

        View view = View.inflate(this, R.layout.scoreboard, null);
        b.setView(view);
        TextView sooretext = view.findViewById(R.id.scoretext);
        TextView scoreval = view.findViewById(R.id.scorevalue);
        Button retry = view.findViewById(R.id.retrybutton);
        Button leaderboard = view.findViewById(R.id.leaderboard);
        Button exit = view.findViewById(R.id.exitbutton);
        Button continu = view.findViewById(R.id.continu);

        sooretext.setText("High score");
        scoreval.setText("New record " + hs);
        final AlertDialog dd = b.create();
        dd.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.pop);
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                loadInterstitial();
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dd.dismiss();

                        continueGame();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        retry();
                        dd.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dd.dismiss();
                        startActivity(new Intent(Game.this, TopPlayers.class));
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                //todo open activity
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dd.dismiss();
                        randomT.setText("Click here to start");
                        textView.setText("Ready");
                        reflectscore = false;
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        /*b.setTitle("Yey!!!");
        b.setMessage("New record.\n"+));
        b.setPositiveButton("Cool, I want to play again.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                retry();
            }
        });*/

        dd.show();
    }

    public void inputName() {
        final AlertDialog.Builder name = new AlertDialog.Builder(this);
        name.setCancelable(false);
        View v = View.inflate(this, R.layout.entername, null);
        name.setView(v);
        final EditText input = v.findViewById(R.id.username);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        Button button = v.findViewById(R.id.saveUsername);
        final AlertDialog playername = name.create();
        playername.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.pop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pname = input.getText().toString();
                SharedPreferences preferedName = getSharedPreferences("SavedName", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferedName.edit();
                editor.putString("SavedName", pname);
                editor.apply();
                editor.commit();
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        playername.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        //playername.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        playername.show();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_nenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                aboutDiag();
                return true;
            case R.id.help:
                startInfoDialog();
                return true;
            case R.id.Exit:
                finish();
                return true;
            case R.id.settings:
                setting();
                return true;
            case R.id.hiscore:
                Intent i = new Intent(Game.this, TopPlayers.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                return true;
            case R.id.rtry:
                retry();
                return true;
            case R.id.share:
                Intent s = new Intent(android.content.Intent.ACTION_SEND);
                s.setType("text/plain");
                s.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
                s.putExtra(android.content.Intent.EXTRA_TEXT, "http://www.amazon.com/gp/mas/dl/andro" +
                        "id?p=clear.alan.com.tap");
                startActivity(Intent.createChooser(s, "Share via"));
            default:
                return super.onOptionsItemSelected(item);
            /*"http://slideme.org/app/clear.alan.com.tap"*/
        }

    }

    public void setting() {
        View checkboxView = View.inflate(this, R.layout.checkbox, null);
        CheckBox checkBox = checkboxView.findViewById(R.id.checkbox);
        AlertDialog.Builder setting = new AlertDialog.Builder(this, R.style.coolTheme);
        setting.setTitle("SETTINGS");
        setting.setMessage("Enable or disable features.");
        setting.setView(checkboxView);
        SharedPreferences q1 = getSharedPreferences("checkBox1", Activity.MODE_PRIVATE);
        final boolean checked = q1.getBoolean("checkBox1", true);
        checkBox.setChecked(checked);
        checkBox.setText("Sound Effects.");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                SharedPreferences b1 = getSharedPreferences("checkBox1", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = b1.edit();
                editor.putBoolean("checkBox1", checked).apply();
            }
        });
        AlertDialog abooutshow = setting.create();
        abooutshow.show();
    }

    public void aboutDiag() {
        AlertDialog.Builder about = new AlertDialog.Builder(this, R.style.coolTheme);
        SharedPreferences preferedName = getSharedPreferences("SavedName", Activity.MODE_PRIVATE);
        String chkNull = preferedName.getString("SavedName", "null");
        about.setTitle("Hello " + chkNull);
        //about.setIcon(R.drawable.index);
        about.setMessage("Tap the color is a brain training game,\n" +
                "where we challenge our brain to choose between color and words. \nPlay with friends and family," +
                "\nand find out who has faster response.\nEnjoy, and have fun.\n" +
                "\n\nversion 2.30\n    1.UI improvement\n    2.sound effects added \n\n\n -sstudio");

        AlertDialog abooutshow = about.create();
        abooutshow.show();
        TextView textVieww1 = abooutshow.findViewById(android.R.id.message);
        textVieww1.setTextSize(25);

    }

    @Override
    protected void onDestroy() {
        if (mCountdowntimer != null) {
            mCountdowntimer.cancel();
        }
        if (mpRight != null) {
            mpRight.release();
        }
        if (mpWrong != null) {
            mpWrong.release();
        }

            super.onDestroy();

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

    public void loadInterstitial() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5433263595056427/3223652658");
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


