package oye.cuento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class capsula8 extends AppCompatActivity {

    private VideoView videoView;
    private int position = 0;
    public Intent intent;
    private MediaController mediaController;
    Integer count = 0;
    MediaPlayer c2mediaPlayer;
    MediaPlayer c2mediaPlayer2;
    Boolean band, prueba = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula8);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.videoView);
        c2mediaPlayer = MediaPlayer.create(this, R.raw.llorando);
        c2mediaPlayer2 = MediaPlayer.create(this, R.raw.lohicistebien);
        intent = new Intent(this, capsula9.class);


// Set the media controller buttons
        if (mediaController == null) {
            mediaController = new MediaController(capsula8.this);

            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(videoView);
            // Set MediaController for VideoView
            videoView.setMediaController(mediaController)

            ;/*
            repetir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.resume();
                }
            });*/
        }

        try {
            // ID of video file.
            int id = this.getRawResIdByName("cap4");
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();

        // When the video file ready for playback.
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();

                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startActivity(intent);
//                try {
//
//                    //intent = new Intent(getApplicationContext(), capsula6.class);
//                    //startActivity(intent);
//                    hilo = new MyTask();
//                    hilo.execute(band);
//                }finally {
//                    finish();
//                }

            }
        });



    }

    void kill_activity(){finish();}

    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }




}

