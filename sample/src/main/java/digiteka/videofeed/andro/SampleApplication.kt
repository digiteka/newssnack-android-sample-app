package digiteka.videofeed.andro

import android.app.Application
import com.digiteka.videofeed.VideoFeed
import com.digiteka.videofeed.core.config.DTKNSConfig
import digiteka.videofeed.andro.logic.log.MyLogger
import digiteka.videofeed.andro.logic.tracking.MyTracker

class SampleApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		//Create the DTKNSConfig
		val dtknsConfig: DTKNSConfig = DTKNSConfig.Builder(
			mdtk = BuildConfig.DIGITEKA_VIDEOFEED_MDTK
		)
			.build()

		//Then initialize the NewsSnack sdk
		VideoFeed.initialize(applicationContext = this, config = dtknsConfig)

		// (Optional) set a custom logger
		VideoFeed.setLogger(MyLogger)

		// (Optional) add a custom tracker
		VideoFeed.addTracker(MyTracker)
	}
}