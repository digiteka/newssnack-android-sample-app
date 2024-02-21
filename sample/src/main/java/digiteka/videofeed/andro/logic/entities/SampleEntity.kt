package digiteka.videofeed.andro.logic.entities

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.annotation.FontRes

data class SampleEntity<A : Activity>(
	val label: String,
	val activityClass: Class<A>,
	@ColorRes val primaryColor: Int,
	@ColorRes val secondaryColor: Int,
	@FontRes val font: Int? = null,
)