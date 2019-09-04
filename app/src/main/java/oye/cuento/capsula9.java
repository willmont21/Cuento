package oye.cuento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class capsula9 extends AppCompatActivity {

    MediaPlayer c2mediaPlayer;
    MediaPlayer c2mediaPlayer2;
    MediaPlayer c2mediaPlayer3;
    Intent intent;
    Integer count = 0;
    Boolean band, prueba = false;
    ImageView img;
    MyTask hilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula9);

        ImageView img = (ImageView) findViewById(R.id.imageView);

        c2mediaPlayer = MediaPlayer.create(this, R.raw.encuentraamamaloba);
        c2mediaPlayer2 = MediaPlayer.create(this, R.raw.wolfderecha);
        c2mediaPlayer3 = MediaPlayer.create(this, R.raw.encontrastemamaloba);
        intent = new Intent(this, capsula10.class);
        count = 0;

        hilo = new MyTask();
        hilo.execute(band);
    }

    public void sonido (View view){
        Log.i("imagen", "Hasta aqui llego");
        prueba = false;
        hilo.cancel(prueba);

        hilo.onPostExecute("resultado");

        
    }

    class MyTask extends AsyncTask<Boolean, Integer, String> {
        @Override
        protected String doInBackground(Boolean... params) {
            while (band = true) {
                c2mediaPlayer2.start();
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
            detener(c2mediaPlayer2);

            for (count = 0; count <= 2; count++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            c2mediaPlayer.start();

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
