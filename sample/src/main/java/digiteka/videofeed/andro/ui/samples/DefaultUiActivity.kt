package digiteka.videofeed.andro.ui.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import digiteka.videofeed.andro.databinding.DefaultUiActivityBinding

class DefaultUiActivity : AppCompatActivity() {

	private lateinit var binding: DefaultUiActivityBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DefaultUiActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)
		//Nothing to do here, check the xml layout for more info
	}
}