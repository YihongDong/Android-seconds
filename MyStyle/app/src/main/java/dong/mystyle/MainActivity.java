package dong.mystyle;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.*;
import java.util.Timer;

public class MainActivity extends Activity {
    private TabHost tabHost;
    private RoundProgressBar mRoundProgressBar;
    private int progress = 0;
    private Button button_start, button_pause, button_stop, button_lap, button_resume;
    private ListView listView_in_stopwatch;
    private ArrayAdapter<String> adapter_in_stopwatch;
    private int click_count = 0;
    private java.util.Timer timer = new Timer();
    private TimerTask timerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
        initClock();
        initButtons();
        listView_in_stopwatch = (ListView) findViewById(R.id.listview_in_stopwatch);
        adapter_in_stopwatch = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        listView_in_stopwatch.setAdapter(adapter_in_stopwatch);
    }

    public void initClock() {
        mRoundProgressBar=new RoundProgressBar(MainActivity.this);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
        mRoundProgressBar.setProgress(0);


    }


    private void startTimer() {
        if (timerTask == null) {
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    progress++;
                    mRoundProgressBar.setProgress(progress);}


            };
            timer.schedule(timerTask, 10, 10);
        }
    }

    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask=null;
        }


    }

    private void initTab() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("BLANK").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tabTime2").setIndicator("BLANK").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tabTime3").setIndicator("秒表").setContent(R.id.tab3));
        tabHost.addTab(tabHost.newTabSpec("tabTime4").setIndicator("BLANK").setContent(R.id.tab4));

    }

    ;
    private void initButtons(){

    button_lap=(Button) findViewById(R.id.button4);
        button_start=(Button) findViewById(R.id.button1);
        button_pause=(Button) findViewById(R.id.button2);
        button_resume=(Button) findViewById(R.id.button2_plus);
        button_stop=(Button)findViewById(R.id.button3);
        button_resume.setVisibility(View.GONE);
    button_lap.setOnClickListener(new View.OnClickListener()

                                  {
                                      public void onClick(View v) {
                                          click_count++;
                                          adapter_in_stopwatch.insert(String.format("%d.                 %d.%d.%d",
                                                  click_count, mRoundProgressBar.getMin(), mRoundProgressBar.getSec(), mRoundProgressBar.getMilisec()), 0);
                                      }
                                  }

    );



    button_start.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        startTimer();
            button_pause.setVisibility(View.VISIBLE);
            button_resume.setVisibility(View.GONE);
    }

    }

    );



    button_pause.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        stopTimer();
        button_pause.setVisibility(View.GONE);
        button_resume.setVisibility(View.VISIBLE);
    }
    }

    );

button_stop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        stopTimer();
        progress=0;
        adapter_in_stopwatch.clear();
        mRoundProgressBar.setProgress(progress);
        button_resume.setVisibility(View.GONE);
    button_pause.setVisibility(View.VISIBLE);}
});
    button_resume.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        button_resume.setVisibility(View.GONE);
        button_pause.setVisibility(View.VISIBLE);
        startTimer();
    }
    }

        );
    }

    }




