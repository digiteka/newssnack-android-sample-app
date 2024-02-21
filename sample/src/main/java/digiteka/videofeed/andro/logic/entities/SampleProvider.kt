package digiteka.videofeed.andro.logic.entities

import digiteka.videofeed.andro.R
import digiteka.videofeed.andro.ui.samples.DefaultUiActivity
import digiteka.videofeed.andro.ui.samples.VingtMinutesActivity

object SampleProvider {

	fun getSamples(): List<SampleEntity<*>> {
		return listOf(
			SampleEntity(
				label = "Default UI Example",
				activityClass = DefaultUiActivity::class.java,
				R.color.primary,
				R.color.secondary,
			),
			SampleEntity(
				label = "Vingt Minutes UI Example",
				activityClass = VingtMinutesActivity::class.java,
				R.color.vm_primary,
				R.color.white,
				R.font.source_serif
			)
		)
	}
}