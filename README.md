


> Written with [StackEdit](https://stackedit.io/).
>

## openchargemap_android_library

## This library makes integrating OpenChargeMap.org (https://openchargemap.org/site) electric vehicle charging station POI's easy with your mapping application.




**This library retrieves two sets of data:**
 1. Core model data that can be used for presenting option lists to the
   user.
 2. Charging station POI's - with details of charging stations for
   each matching POI.

 Charging stations model data is stored in an sqlite3 database for persistence and charging station POI's are retrieved upon each call using the provided location information. Simply use the provided **OpenChargeMapViewModel** for all your access to get  model data and POI's.


**Use the OpenChargeMapViewModel as follows:**
Create an instance of the ViewModel OpenChargeMapViewModelFactory with a reference to your Android Application and  the number of days to expire/refresh the core data database data.  Database expiration/refresh will occur after 10 days by default.

    openChargeMapViewModel = ViewModelProviders
      .of(this,OpenChargeMapViewModelFactory(application))
      .get(OpenChargeMapViewModel::class.java)

Observe the ViewModel's dbInitialized property, which will indicate completion of retrieval and initialization of the database:

    fun setUpOpenChargeMapListeners() {
       if (!openChargeMapViewModel.dbIntialized.hasObservers()) {
         openChargeMapViewModel.dbIntialized.observe(this,openChargeMapDBInitializedListener)
       }
    }

Setup observers for model data updates (optional - they can also be directly retrieved from the OpenChargeMapViewModel)  and charging
station poi's after db intialization. Calls to retrieve parts of the
   model data can be done at this time    (example shows ChargeTypes and
   ConnectionTypes observers, but more model data is available):

       val openChargeMapDBInitializedListener = Observer<Boolean> {
             if (it) {
                 openChargeMapViewModel.getChargeTypes()
                 openChargeMapViewModel.getConnectionTypes()
                 openChargeMapViewModel.chargeTypes.observe(this, openChargeMapChargerTypesObserver)
                 openChargeMapViewModel.connectionTypes.observe(this, openChargeMapConnectionTypesObserver)
                 openChargeMapViewModel.pois.observe(this, openChargeMapPOIObserver)
             }
         }



Observer POI's:

    val openChargeMapPOIObserver = Observer<Optional<List<PoiItem>>> {
    ...
    }

Call the POI function when POI data is required:


     openChargeMapViewModel.getPOIs(latitude, longitude)
To optionally render map markers from POI instances, a helper class MapMarker is available:

    val mapMarkers = MapMarkers(context)
    val mapIcon : Bitmap = mapMarkers.getIconForPOI(poi)

getIconForPOI returns a Bitmaps to be used for the your map charging station icons. These images were borrowed from the Open Charge Map app [https://play.google.com/store/apps/details?id=com.webprofusion.openchargemap&hl=en_US along with the
logic to determine which icon to return.

Integration:
Currently a SNAPSHOT of this library is available in JitPack.io.
Documention: https://jitpack.io/#dinzdale/openchargemap_android_library/-SNAPSHOT

root build.gradle  :

    allprojects {
          repositories {
             ...
             maven { url 'https://jitpack.io' }
          }
       }


app build.gradle

    dependencies {
       implementation 'com.github.dinzdale:openchargemap_android_library:-SNAPSHOT'
    }


release versions - obfuscating  (minifying/shrinking), add the following to your proguard configuration file (ie proguard-custom-rules.pro)

    -keep public class com.gmjacobs.productions.openchargemap.** {*;}


**Some of the technologies leveraged to make this library possible are as follows:**


 - Retrofit api library: https://square.github.io/retrofit/ (with Kotlin
   coroutines bindings)
 - Kotlin
 - Android Room - database creation and table/entities definition (with Kotlin coroutines bindings).
 - Kotlin Coroutines - streamlining asynch handling.
 - ModelViews - extracting data from UI components.

This library is functional and is available as is. It has been made as open source for further collaboration of improvements and enhancements.

Further credits and licensing information to be added.

Please forward any questions to:
dinzdale:
gmjacobs61@gmail.com