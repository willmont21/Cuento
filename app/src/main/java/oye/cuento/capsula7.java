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

public class capsula7 extends AppCompatActivity {

    private VideoView videoView;
    private int position = 0;
    public Intent intent;
    private MediaController mediaController;
    Integer count = 0;
    MediaPlayer c2mediaPlayer;
    MediaPlayer c2mediaPlayer2;
    Boolean band, prueba = false;
    MyTask hilo;
    TextView swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula7);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.videoView);
        c2mediaPlayer = MediaPlayer.create(this, R.raw.llorando);
        c2mediaPlayer2 = MediaPlayer.create(this, R.raw.lohicistebien);
        swipe=(TextView) findViewById(R.id.textView);
        intent = new Intent(this, capsula8.class);


// Set the media controller buttons
        if (mediaController == null) {
            mediaController = new MediaController(capsula7.this);

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
            int id = this.getRawResIdByName("cap3");
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
                // repetir.setVisibility(View.VISIBLE);
                //cont++;
                hilo = new MyTask();
                hilo.execute(band);
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

        swipe.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastTop), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastRight), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastLeft), Toast.LENGTH_SHORT).show();
                prueba = false;
                hilo.cancel(prueba);

                hilo.onPostExecute("resultado");
            }
            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastBottom), Toast.LENGTH_SHORT).show();

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



    class MyTask extends AsyncTask<Boolean, Integer, String> {
        @Override
        protected String doInBackground(Boolean... params) {
            while (band = true) {
                c2mediaPlayer.start();
                for (count = 0; count <= 9; count++) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (isCancelled())
                    band = true;
            }
            return "Task Completed.";
        }

        @Override
        protected void onPostExecute(String result) {
            detener(c2mediaPlayer);
            c2mediaPlayer2.start();
            for (count = 0; count <= 2; count++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            detener(c2mediaPlayer2);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        public void detener (MediaPlayer mediaPlayer){
            if (mediaPlayer!= null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer= null;
            }
        }
    }
}

