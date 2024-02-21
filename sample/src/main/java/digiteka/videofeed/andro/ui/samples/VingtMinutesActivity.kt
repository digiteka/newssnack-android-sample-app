package digiteka.videofeed.andro.ui.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digiteka.newssnack.core.config.ui.DTKNSUiConfig
import com.digiteka.newssnack.ui.NewsSnackFragment
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
			.setDescriptionFont(R.font.oswald)
			.setZoneFont(R.font.arial)
			.build()

		binding.vingtMinutesFragmentContainerView.getFragment<NewsSnackFragment>()
			.setUiConfig(uiConfig)

	}
}