package aymanzeine.featuretrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void faceSearch(View v){
        Intent intent = new Intent(this, FaceDetection.class);
        startActivity(intent);
    }

    public void photoSearch(View v)
    {
        Intent intent = new Intent(this, DetectingActivity.class);
        startActivity(intent);
    }

    public void voice(View v)
    {
        Intent intent = new Intent(this, VoiceRecognitionActivity.class);
        startActivity(intent);
    }

    public void camera(View v)
    {
        Intent intent = new Intent(this, AndroidCameraApi.class);
        startActivity(intent);
    }

    public void eyes(View v)
    {
        Intent intent = new Intent(this, EyeTrackActivity.class);
        startActivity(intent);
    }


}
