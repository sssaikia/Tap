package clear.alan.com.tap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TopPlayers extends AppCompatActivity {

    RequestQueue queue;
    RecyclerView recyclerView;
    List<Score> list;
    ScoreAdapter adapteRR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_player);
        hideSystemUI();
        recyclerView= findViewById(R.id.recycler);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        queue= Volley.newRequestQueue(this);
        JsonRequest request = new JsonArrayRequest(Request.Method.GET, "http://androidbug.ml/api/tapplayer.php", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                list.add(new Score(jsonObject.getString("name"),Long.parseLong((String) jsonObject.get("score"))));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapteRR=new ScoreAdapter(list);
                        recyclerView.setAdapter(adapteRR);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);








        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
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
}
