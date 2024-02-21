package digiteka.videofeed.andro

import android.app.Application
import com.digiteka.newssnack.NewsSnack
import com.digiteka.newssnack.core.config.DTKNSConfig
import digiteka.videofeed.andro.logic.log.MyLogger
import digiteka.videofeed.andro.logic.tracking.MyTracker

class SampleApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		//Create the DTKNSConfig
		val dtknsConfig: DTKNSConfig = DTKNSConfig.Builder(
			mdtk = BuildConfig.DIGITEKA_NEWSSNACK_MDTK
		)
			.build()

		//Then initialize the NewsSnack sdk
		NewsSnack.initialize(applicationContext = this, config = dtknsConfig)

		// (Optional) set a custom logger
		NewsSnack.setLogger(MyLogger)

		// (Optional) add a custom tracker
		NewsSnack.addTracker(MyTracker)
	}
}