package digiteka.videofeed.andro;

import android.app.Application;

import com.digiteka.newssnack.NewsSnack;
import com.digiteka.newssnack.core.config.DTKNSConfig;

import digiteka.videofeed.andro.logic.log.MyLogger;
import digiteka.videofeed.andro.logic.tracking.MyTracker;

public class SampleJavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DTKNSConfig config = new DTKNSConfig.Builder(BuildConfig.DIGITEKA_NEWSSNACK_MDTK)
                .build();

        //Then initialize the NewsSnack sdk
        NewsSnack.initialize(this, config);

        // (Optional) set a custom logger
        NewsSnack.Companion.setLogger(MyLogger.INSTANCE);

        // (Optional) add a custom tracker
        NewsSnack.Companion.addTracker(MyTracker.INSTANCE);
    }
}
