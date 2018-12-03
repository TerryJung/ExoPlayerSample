package example.terry.com.exoplayersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button assetBtn = (Button) findViewById(R.id.asset_button);
        assetBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asset_button:
                Intent assetIntent = new Intent(this, AssetActivity.class);
                startActivity(assetIntent);
                break;
        }
    }
}
