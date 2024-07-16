# Digiteka VideoFeed SDK

[![en](https://img.shields.io/badge/lang-en-red.svg)](ReadMe.md)
[![fr](https://img.shields.io/badge/lang-fr-blue.svg)](ReadMe.fr.md)

Digiteka VideoFeed library provides an interactive view to display lists of media content, sorted by categories.

# Installation

Simply add the dependency to your application `build.gradle` file:

``` kotlin    
dependencies {    
    implementation("com.github.digiteka:newssnack-android:1.0.3")
}   
```   

And add the jitpack repository to your project `settings.gradle.kt` file:

``` kotlin
dependencyResolutionManagement {
    maven{
        url = uri("https://jitpack.io")
    }
}
```

# Usage

Then in the `onCreate` of your `Application` class, if you don't have one, create one, and add the following code to initialize the sdk:

``` kotlin  
//Create the DTKNSConfig 
val dtknsConfig: DTKNSConfig = DTKNSConfig.Builder(mdtk = "my_mdtk_key_here")    
    .build() 
//Then initialize the VideoFeed sdk 
VideoFeed.initialize(applicationContext = this, config = dtknsConfig)   
```

## Logger

Optionally, you can set a custom logger in order to retrieve logs from the sdk. The logger must implement the `DTKNSLogger` interface.

``` kotlin  
import android.util.Log 
import com.digiteka.videofeed.core.log.DTKNSLogger    

object MyLogger : DTKNSLogger {    
    
    private const val TAG = "MyAppLogger" 
    override fun debug(message: String) { Log.d(TAG, message) }      
    override fun info(message: String) { Log.i(TAG, message) }    
    override fun warning(message: String, throwable: Throwable?) { Log.w(TAG, message, throwable) }    
    override fun error(message: String, throwable: Throwable?) { Log.e(TAG, message, throwable) }
 
}   
```   

Then pass the logger:

``` kotlin  
VideoFeed.setLogger(logger = MyLogger)   
```   

## Tracking

Optionally, you can set a custom tracker in order to track events from the sdk. The tracker must implement the `DTKNSTracker` interface.

``` kotlin
import com.digiteka.videofeed.logic.tracking.DTKNSTracker
import com.digiteka.videofeed.logic.tracking.TrackingEvent

object MyTracker: DTKNSTracker {
  override fun trackEvent(event: TrackingEvent) {
        // Track the event
    }
}
```   

Then pass the tracker:

``` kotlin
VideoFeed.setTracker(tracker = MyTracker)
```

## Displaying the VideoFeedFragment

The VideoFeedFragment extends the android Fragment class, you can manage it like any other fragment.
Here, the example is done in the XML view.

``` xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/defaultUiActivityContainerView"
    android:name="com.digiteka.videofeed.ui.VideoFeedFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />    
```

**Recommendations**

- The `VideoFeedFragment` has vertical and horizontal scroll capabilities, thus it's not recommended to put it inside a scrollable view, like a ScrollView or a RecyclerView.

### Customizing the UI

By default, VideoFeedFragment will use system  
The UI can be customized by passing a `DTKNSUiConfig` instance to the VideoFeedFragment.

``` kotlin    
val uiConfig: DTKNSUiConfig = DTKNSUiConfig.Builder()    
    .setForcedDarkMode(true) // Forces the dark mode or light mode. By default the sdk will use the current system uiMode. 
    .setPlayIcon(R.drawable.ic_vm_play) // Sets the play icon on the video player 
    .setPauseIcon(R.drawable.ic_vm_pause) // Sets the pause icon on the video player 
    .setTitleFont(R.font.source_serif) // Sets the font for the title of the info panel 
    .setZoneFont(R.font.oswald) // Sets the font for the zone tag
    .setDescriptionFont(R.font.arial) // Sets the font for the description in the info panel and the Categories tag's    
	.setDeactivateAds(false) // Disables the ads and ads fallback
	.setTagParams { zoneName, adId -> // defines the tagParams for a given zoneName and adId
        mapOf(
            "category" to "$zoneName _ $adId",
            "sub_category" to "news"
        )
    }
    .build()    
 
// Then pass the uiConfig to the fragment 
findViewById<FragmentContainerView>(R.id.vingtMinutesFragmentContainerView)    
    .getFragment<VideoFeedFragment>()
    .setUiConfig(uiConfig)   
```   

### Customizing the ad fallback

VideoFeed provides an interface `DTKNSViewInjector` in order to customize a fallback view when no ad is available for an ad placement.
The fallback view is not displayed when an error occurs while loading an ad.

``` kotlin
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

```

Then you just need to provide it to the `VideoFeedFragment`:

``` kotlin
findViewById<FragmentContainerView>(R.id.vingtMinutesFragmentContainerView)
	.getFragment<VideoFeedFragment>()
	.setInjector(SampleInjector())

```

## Errors and Logs

| Type          | Error Code   | Level    | Message                                                                                                                                    | Cause                                                                                                                       |
|---------------|--------------|----------|--------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Configuration | DTKNS_CONF_1 | Critical | Mdtk must not be null, empty or blank. Please provide a valid Api Key.                                                                     | mdtk is null or empty                                                                                                       |  
| Configuration | DTKNS_CONF_2 | Error    | No data were provided for your Api key (mdtk), please check your Api Key is valid, and you do provide data for it in the digiteka console. | The mdtk is not valid, or no video has been configured in the digiteka console                                              |  
| Configuration | DTKNS_CONF_3 | Error    | VideoFeed sdk has not yet been initialized. Please call `VideoFeed.initialize` first.                                                      | `VideoFeed.initialize` has not been called yet                                                                              |  
| Configuration | DTKNS_CONF_4 | Warning  | No video available in zone                                                                                                                 | No video is available for this zone                                                                                         |  
| Network       |              | Info     | Network connection re-established                                                                                                          | Network connection was lost and has been re-established                                                                     |  
| Network       | DTKNS_NET_1  | Warning  | Network connection lost.                                                                                                                   | Lost network connection                                                                                                     |  
| Network       | DTKNS_NET_2  | Warning  | Network connection unavailable.                                                                                                            | Failed to connect to network                                                                                                |  
| Data          | DTKNS_DATA_1 | Warning  | DataIntegrity error \<ClassName>: \<short message describing why>                                                                          | Required data was not provided by the server.                                                                               |  
| Data          |              | Info     | Can't load image from url \<url>                                                                                                           | The placeholder image url wasn't valid or failed to load                                                                    |
| SDK           | DTKNS_SDK_1  | Critical | Can't convert URL from string \<string>                                                                                                    | Built server url was not valid. Please contact support.                                                                     |
| SDK           | DTKNS_SDK_2  | Error    | Failed to communicate with server                                                                                                          | Server response was invalid, or connection failed (timeout). Contact support if it occurs too frequently or systematically. |

# Sample app

You can test the Sample app using the mdtk : `01472001`.

Add your digiteka VideoFeed mdtk key to the project's `local.properties` like following:    
```DIGITEKA_VIDEOFEED_MDTK=01472001```