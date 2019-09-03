package oye.cuento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class capsula1 extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Intent intent;
    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsula1);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.instrucciones);
        mediaPlayer = MediaPlayer.create(this, R.raw.ponatencion);
        intent = new Intent(this, capsula2.class);
        count = 0;
        new MyTask().execute(2);

    }

    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            startActivity(intent);
            Log.i("intent", "correcto");
        }
        @Override
        protected void onPreExecute() {
            mediaPlayer.start();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("Sonido", ""+count);
        }
    }
}
