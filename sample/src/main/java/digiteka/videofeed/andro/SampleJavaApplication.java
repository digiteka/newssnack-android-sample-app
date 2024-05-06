package digiteka.videofeed.andro;

import android.app.Application;

import com.digiteka.videofeed.VideoFeed;
import com.digiteka.videofeed.core.config.DTKNSConfig;

import digiteka.videofeed.andro.logic.log.MyLogger;
import digiteka.videofeed.andro.logic.tracking.MyTracker;

public class SampleJavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DTKNSConfig config = new DTKNSConfig.Builder(BuildConfig.DIGITEKA_VIDEOFEED_MDTK)
                .build();

        //Then initialize the NewsSnack sdk
        VideoFeed.initialize(this, config);

        // (Optional) set a custom logger
        VideoFeed.Companion.setLogger(MyLogger.INSTANCE);

        // (Optional) add a custom tracker
        VideoFeed.Companion.addTracker(MyTracker.INSTANCE);
    }
}
