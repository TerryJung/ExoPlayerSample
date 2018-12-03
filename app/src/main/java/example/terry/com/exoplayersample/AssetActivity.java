package example.terry.com.exoplayersample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.AssetDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;

public class AssetActivity extends AppCompatActivity {
    private static final String VIDEO_CONTENT_URI = "file:///android_asset/asset_sample.mp4";

    private SimpleExoPlayer exoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        PlayerView pv = findViewById(R.id.player_view);
        prepareExoPlayerFromFileUri(Uri.parse(VIDEO_CONTENT_URI));
        pv.setPlayer(exoPlayer);

    }

    private void prepareExoPlayerFromFileUri(Uri uri) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(), new DefaultLoadControl());

        DataSpec dataSpec = new DataSpec(uri);
        final AssetDataSource assetDataSource = new AssetDataSource(this);

        try {
            assetDataSource.open(dataSpec);
        } catch (AssetDataSource.AssetDataSourceException e) {
            e.printStackTrace();
        }


        DataSource.Factory factory = new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return assetDataSource;
            }
        };
        MediaSource videoSource = new ExtractorMediaSource(assetDataSource.getUri(),
                factory, new DefaultExtractorsFactory(), null, null);

        exoPlayer.prepare(videoSource);
    }

}
