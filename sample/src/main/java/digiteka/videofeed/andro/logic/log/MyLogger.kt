package digiteka.videofeed.andro.logic.log

import android.util.Log
import com.digiteka.videofeed.core.log.DTKNSLogger

object MyLogger : DTKNSLogger {

	private const val TAG = "Digiteka"
	override fun debug(message: String) {
		Log.d(TAG, message)
	}

	override fun info(message: String) {
		Log.i(TAG, message)
	}

	override fun warning(message: String, throwable: Throwable?) {
		Log.w(TAG, message, throwable)
	}

	override fun error(message: String, throwable: Throwable?) {
		Log.e(TAG, message, throwable)
	}

}