package umn.ac.id.muhammadakmalalhaqi_18087_uts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MusicPlay extends AppCompatActivity {
    private ImageView ivplay,ivpause,ivprev,ivnext,ivcircle,ivheadphone;
    private TextView tvElapsedTimeLabel, tvRemainingTimeLabel,tvSongTitle;
    private SeekBar skplay;
    MediaPlayer mp;
    boolean started=false;
    int totaltime;
    int remainingTime = 0;
    int songPosition;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        ivplay = (ImageView) findViewById(R.id.ivplay);
        ivpause = (ImageView) findViewById(R.id.ivpause);
        ivprev = (ImageView) findViewById(R.id.ivprev);
        ivnext = (ImageView) findViewById(R.id.ivnext);
        ivcircle = (ImageView) findViewById(R.id.ivcircle);
        ivheadphone = (ImageView) findViewById(R.id.ivheadphone);
        tvSongTitle = (TextView) findViewById(R.id.song_title);
        tvElapsedTimeLabel = (TextView) findViewById(R.id.tvElapsedTimeLabel);
        tvRemainingTimeLabel = (TextView) findViewById(R.id.tvRemainingTimeLabel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int songID = getIntent().getIntExtra("songID",0);
        songPosition = getIntent().getIntExtra("position",0);
        String songTitle = getIntent().getStringExtra("songTitle");
        final List<Song> songs = (List<Song>) getIntent().getSerializableExtra("object_song");
        System.out.println("SONGS : " + songs.size());
        mp = MediaPlayer.create(getApplicationContext(), songID);

        tvSongTitle.setText(songTitle);
        totaltime = mp.getDuration();
        skplay = (SeekBar) findViewById(R.id.skplay);
        skplay.setMax(totaltime);
        skplay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp.seekTo(progress);
                    skplay.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message message = new Message();
                        message.what = mp.getCurrentPosition();
//
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

        ivplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivplay.setVisibility(View.GONE);
                ivpause.setVisibility(View.VISIBLE);
                mp.seekTo(remainingTime);
                mp.start();
                started = true;
            }
        });
        ivpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivplay.setVisibility(View.VISIBLE);
                ivpause.setVisibility(View.GONE);
                remainingTime = mp.getCurrentPosition();
                mp.pause();
                started = false;
            }
        });
        ivnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                songPosition++;
                mp = MediaPlayer.create(MusicPlay.this, songs.get(songPosition).getSongID());
                mp.start();
                tvSongTitle.setText(songs.get(songPosition).getSongTitle());
                totaltime = mp.getDuration();
//                skplay = (SeekBar) findViewById(R.id.skplay);
                skplay.setMax(totaltime);
            }
        });
        ivprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                songPosition--;
                mp = MediaPlayer.create(MusicPlay.this, songs.get(songPosition).getSongID());
                mp.start();
                tvSongTitle.setText(songs.get(songPosition).getSongTitle());
                totaltime = mp.getDuration();
//                skplay = (SeekBar) findViewById(R.id.skplay);
                skplay.setMax(totaltime);
            }
        });
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        super.onBackPressed();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentPosition = msg.what;
            skplay.setProgress(currentPosition);
            String timelabel = "";
            String timelabelTime = "";
            int time = totaltime-currentPosition;
            int min = time / 1000 / 60;
            int sec = time / 1000 % 60;
            if (currentPosition!=0){
                Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                ivcircle.startAnimation(aniRotate);
            }

            timelabel = min + ":";
            if (sec<10){
                timelabel+="0";
            }
            timelabel+=sec;

            int time2 = currentPosition;
            int min2 = time2 / 1000 / 60;
            int sec2 = time2 / 1000 % 60;

            timelabelTime = min2 + ":";
            if (sec2<10){
                timelabelTime+="0";
            }
            timelabelTime+=sec2;

            tvRemainingTimeLabel.setText(timelabel);
            tvElapsedTimeLabel.setText(timelabelTime);
            super.handleMessage(msg);

        }
    };


}
