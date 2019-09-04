package oye.cuento;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.Toast;

public class capsula2 extends AppCompatActivity {


    MediaPlayer mediaPlayer, mediaPlayer2;
    Intent intent;
    Integer count = 0;
    Boolean band, prueba = false;
    ImageView img;
    MyTask hilo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula2);


        img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.abajo);
        mediaPlayer = MediaPlayer.create(this, R.raw.deslizaabajo);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.lohicistebien);
        intent = new Intent(this, capsula3.class);
        count = 0;

        hilo = new MyTask();
        hilo.execute(band);
        //new MyTask2().execute(band);
        img.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastTop), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastRight), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastLeft), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastBottom), Toast.LENGTH_SHORT).show();
                prueba = false;
                hilo.cancel(prueba);

                hilo.onPostExecute("resultado");
            }

        });

    }

    class MyTask extends AsyncTask<Boolean, Integer, String> {
        @Override
        protected String doInBackground(Boolean... params) {
            while (band = true) {
                mediaPlayer.start();
                for (count = 0; count <= 8; count++) {
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
            detener(mediaPlayer);
            mediaPlayer2.start();
            for (count = 0; count <= 2; count++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            detener(mediaPlayer2);
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


