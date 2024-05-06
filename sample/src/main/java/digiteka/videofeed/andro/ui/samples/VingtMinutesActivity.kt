package digiteka.videofeed.andro.ui.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digiteka.videofeed.core.config.ui.DTKNSUiConfig
import com.digiteka.videofeed.ui.VideoFeedFragment
import digiteka.videofeed.andro.R
import digiteka.videofeed.andro.databinding.VingtMinutesActivityBinding

class VingtMinutesActivity : AppCompatActivity() {

	private lateinit var binding: VingtMinutesActivityBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = VingtMinutesActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val uiConfig: DTKNSUiConfig = DTKNSUiConfig.Builder()
			.setPlayIcon(R.drawable.ic_vm_play)
			.setPauseIcon(R.drawable.ic_vm_pause)
			.setTitleFont(R.font.source_serif)
			.setForcedDarkMode(true)
			.setDescriptionFont(R.font.oswald)
			.setZoneFont(R.font.arial)

			.setDeactivateAds(false)
			.setTagParams { zoneName, adId ->
				mapOf(
					"category" to "$zoneName _ $adId",
					"sub_category" to "news"
				)
			}

			.build()

		binding.vingtMinutesFragmentContainerView.getFragment<VideoFeedFragment>().apply {
			setInjector(SampleInjector())
			setUiConfig(uiConfig)
		}

	}
}