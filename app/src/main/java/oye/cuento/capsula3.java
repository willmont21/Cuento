package oye.cuento;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.Toast;

public class capsula3 extends AppCompatActivity {

    MediaPlayer mediaPlayer, mediaPlayer2;
    Intent intent;
    Integer count = 0;
    Boolean band, prueba = false;
    ImageView img;
    MyTask hilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula3);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.continuar);

        mediaPlayer = MediaPlayer.create(this, R.raw.deslizaabajo);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.lohicistebien);
        intent = new Intent(this, capsula3.class);
        count = 0;

        hilo = new MyTask();
        hilo.execute(band);

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
                mediaPlayer.stop();


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
                    break;
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            mediaPlayer2.start();
            for (count = 0; count <= 2; count++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }

}
