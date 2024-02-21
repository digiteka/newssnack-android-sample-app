# Application de démonstration du SDK VideoFeed de Digiteka

[![en](https://img.shields.io/badge/lang-en-red.svg)](ReadMe.md)
[![fr](https://img.shields.io/badge/lang-fr-blue.svg)](ReadMe.fr.md)

Cette application de démonstration montre comment intégrer et utiliser la librairie NewsSnack de Digiteka.
La librairie NewsSnack de Digiteka fourni un composant interactif pour afficher des listes de contenus médias, organisés par catégories.

# Installation

Ajouter la dépendance à votre fichier `build.gradle`:

``` kotlin    
dependencies {    
  implementation("com.digiteka.android:newssnack:1.0.0")
}   
```   

# Utilisation

Dans la méthode `onCreate` de votre classe `Application`, si vous n'en avez pas, créez-en une, et ajoutez-le code suivant pour initialiser la librairie :

``` kotlin  
//Créer la configuration DTKNSConfig 
val dtknsConfig: DTKNSConfig = DTKNSConfig.Builder(mdtk = "my_mdtk_key_here")    
  .build() 
//Puis initialiser la librairie NewsSnack 
NewsSnack.initialize(applicationContext = this, config = dtknsConfig)   
```

## Logger

Il est possible de définir un logger personalisé pour récupérer les logs de la librairie. Le logger doit implémenter l'interface `DTKNSLogger`.

``` kotlin  
import android.util.Log 
import com.digiteka.newssnack.core.log.DTKNSLogger    

object MyLogger : DTKNSLogger {    
    
  private const val TAG = "MyAppLogger" 
  override fun debug(message: String) { Log.d(TAG, message) }      
  override fun info(message: String) { Log.i(TAG, message) }    
  override fun warning(message: String, throwable: Throwable?) { Log.w(TAG, message, throwable) }    
  override fun error(message: String, throwable: Throwable?) { Log.e(TAG, message, throwable) }

}   
```   

Puis passer le logger à la librairie :

``` kotlin  
NewsSnack.setLogger(logger = MyLogger)   
```   

## Tracking

Pour traquer les évènnements provenant de la librairie, il est possible de passer un tracker personalisé. Le tracker doit implémenter l'interface `DTKNSTracker`.

``` kotlin
import com.digiteka.newssnack.logic.tracking.DTKNSTracker
import com.digiteka.newssnack.logic.tracking.TrackingEvent

object MyTracker: DTKNSTracker {
  override fun trackEvent(event: TrackingEvent) {
        // Track the event
    }
}
```   

Puis passer le tracker à la librairie :

``` kotlin
NewsSnack.setTracker(tracker = MyTracker)
```

## Afficher le NewsSnackFragment

Le NewsSnackFragment étend la classe android Fragment, vous pouvez le gérer comme n'importe quel autre fragment.
Dans l'exemple suivant, il est inséré avec un `FragmentContainerView` dans un layout XML.

``` xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/defaultUiActivityContainerView"
    android:name="com.digiteka.newssnack.ui.NewsSnackFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />   
```

**Recommendations**

- Le `NewsSnackFragment` a des comportements de déroulement vertical et horizontal, il n'est donc pas recommandé de l'insérer dans un layout possédant du déroulement, telle qu'une ScrollView ou qu'une
  RecyclerView.

### Personnaliser l'UI

Par défaut, le NewsSnackFragment utilisera le mode d'UI du système.
L'interface peut être personnalisée en passant une instance de `DTKNSUiConfig` au NewsSnackFragment.

``` kotlin    
val uiConfig: DTKNSUiConfig = DTKNSUiConfig.Builder()    
  .setForcedDarkMode(true) // Forece l'affichage en mode sombre ou clair. Par défaut, la librairie utilisera le mode d'UI du système.
  .setPlayIcon(R.drawable.ic_vm_play) // Définit l'icône play pour le lecteur video. 
  .setPauseIcon(R.drawable.ic_vm_pause) // Définit l'icône pause pour le lecteur video.
  .setTitleFont(R.font.source_serif) // Définit la police d'écriture pour le titre du panneau d'information.
  .setDescriptionFont(R.font.arial) // Défini la police d'écriture pour la description du panneau d'information et les puces de catégories.
  .build()   
 
// Puis passer la configuration à l'instance du NewsSnackFragment affichée
findViewById<FragmentContainerView>(R.id.vingtMinutesFragmentContainerView)    
  .getFragment<NewsSnackFragment>()
  .setUiConfig(uiConfig)   
```   

## Erreurs et Logs

| Type          | Code d'erreur | Niveau   | Message                                                                                                                                    | Cause                                                                                                                                             |
|---------------|---------------|----------|--------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| Configuration | DTKNS_CONF_1  | Critical | Mdtk must not be null, empty or blank. Please provide a valid Api Key.                                                                     | mdtk nul ou vide                                                                                                                                  |  
| Configuration | DTKNS_CONF_2  | Error    | No data were provided for your Api key (mdtk), please check your Api Key is valid, and you do provide data for it in the digiteka console. | Le tableau data est vide ou aucune zone ne contient de vidéo                                                                                      |  
| Configuration | DTKNS_CONF_3  | Error    | NewsSnack sdk has not yet been initialized. Please call `NewsSnack.initialize` first.                                                      | NewSnack.shared.initialize ou NewsSnack.initialize n'ont pas encore été appelé                                                                    |  
| Configuration | DTKNS_CONF_4  | Warning  | No video available in zone                                                                                                                 | Aucune vidéo disponible dans la zone                                                                                                              |  
| Network       |               | Info     | Network connection re-established                                                                                                          | La connexion au réseau a été \(r\)établie                                                                                                         |  
| Network       | DTKNS_NET_1   | Warning  | Network connection lost.                                                                                                                   | La connexion au réseau a été perdue                                                                                                               |  
| Network       | DTKNS_NET_2   | Warning  | Network connection unavailable.                                                                                                            | La connexion au réseau est indisponible lors d'une requête réseau                                                                                 |  
| Data          | DTKNS_DATA_1  | Warning  | DataIntegrity error \<ClassName>: \<short message describing why>                                                                          | L'une des données requises du modèle envoyé par le serveur est invalide.                                                                          |  
| Data          |               | Info     | Can't load image from url \<url>                                                                                                           | L'image placeholder n'a pas pu être chargée                                                                                                       |
| SDK           | DTKNS_SDK_1   | Critical | Can't convert URL from string \<string>                                                                                                    | La conversion de l'url string en URL remonte une erreur. Veuillez contacter le support si cela arrive                                             |
| SDK           | DTKNS_SDK_2   | Error    | Failed to communicate with server                                                                                                          | La réponse du serveur était invalide, ou la connexion au serveur à échouée (p.e. timeout). Veuillez contacter le support si le problème persiste. |

# Application de démonstration

Vous pouvez tester l'application de démonstration en utilisant le mdtk : `01472001`.

Pour ce faire, ajoutez votre mdtk et votre auth token jitpack digiteka au fichier `local.properties` du projet comme suit :    
``` properties
DIGITEKA_VIDEOFEED_MDTK=01472001
DIGITEKA_VIDEOFEED_SECRET=your_digiteka_jitpack_auth_token_here
```
