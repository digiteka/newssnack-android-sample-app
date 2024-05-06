package digiteka.videofeed.andro.ui.samples

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digiteka.videofeed.core.config.injection.DTKNSViewInjector
import com.digiteka.videofeed.core.rest.entities.zone.FallbackPlacementEntity
import digiteka.videofeed.andro.databinding.SampleFallbackViewBinding

class SampleInjector : DTKNSViewInjector {

	override fun hasViewAvailable(placement: FallbackPlacementEntity): Boolean {
		Log.i("SampleInjector", "hasViewAvailable - placement: $placement")
		return true
	}

	override fun buildView(placement: FallbackPlacementEntity, parent: ViewGroup): View? {
		val binding = SampleFallbackViewBinding.inflate(LayoutInflater.from(parent.context))
		return binding.root
	}

	override fun onViewCreated(view: View) {
		Log.i("SampleInjector", "onViewCreated - view: $view")
	}

	override fun onViewDestroyed(view: View) {
		Log.i("SampleInjector", "onViewDestroyed - view: $view")
	}

	override fun onViewVisibilityChanged(view: View, isVisible: Boolean) {
		Log.i("SampleInjector", "onViewVisibilityChanged - view: $view, isVisible: $isVisible")
	}
}