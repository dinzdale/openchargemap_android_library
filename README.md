# openchargemap_android_library
This library makes integrating OpenChargeMap.org (https://openchargemap.org/site) electric vehicle charging station POI's easy with your
mapping application.

The library handles retrieval of two sets of data:
a) Core model data that can be used for presenting option lists to the user
b) Charging station POI's - with details of charging stations for each matching POI.

Charging stations model data is stored in an sqlite3 database for persistence and charging station
POI's are retrieved upon each call using the provided location information.

Simply use the provided OpenChargeMapViewModel for all your access to get
model data and POI's.

Use the OpenChargeMapViewModel as follows:
1) Create an instance of the ViewModel OpenChargeMapViewModelFactory with a reference to
your Android Application and optionally the number of days to expire/refresh the core data database data.
Database has a 10 day expiration date by default, but can be overriden by the application.

openChargeMapViewModel = ViewModelProviders.of(this, OpenChargeMapViewModelFactory(application)).get(OpenChargeMapViewModel::class.java)

2) Observer the ViewModel's dbInitialized property, which will indicate completion
of retreival and initialization of the database:

 fun setUpOpenChargeMapListeners() {
        if (!openChargeMapViewModel.dbIntialized.hasObservers()) {
            openChargeMapViewModel.dbIntialized.observe(this, openChargeMapDBInitializedListener)
        }
    }

 3) Setup observers for model data updates (optional - they can also be directly retrieved from the OpenChargeMapViewModel)
 and charging station poi's after db ntialization. Calls to retrieve parts of the model data can be done at this time
 (example shows ChargeTypes and ConnectionTypes observers, but more model data is available:

   val openChargeMapDBInitializedListener = Observer<Boolean> {
         if (it) {
             openChargeMapViewModel.getChargeTypes()
             openChargeMapViewModel.getConnectionTypes()
             openChargeMapViewModel.chargeTypes.observe(this, openChargeMapChargerTypesObserver)
             openChargeMapViewModel.connectionTypes.observe(this, openChargeMapConnectionTypesObserver)
             openChargeMapViewModel.pois.observe(this, openChargeMapPOIObserver)
         }
     }

4) Observer POI's:
val openChargeMapPOIObserver = Observer<Optional<List<PoiItem>>> {
...
}

4) Call the POI function when POI data is required:

 openChargeMapViewModel.getPOIs(latitude, longitude)

5) To optionally render map markers from POI instances, a helper class MapMarker is available:

  val mapMarkers = MapMarkers(context)

where mapMarkers.getIconForPOI(poi) can be called to return a default matching Bitmap icons for that POI
to render on the map. These icons were borrowed from the Open Charge Map by Webprofusion, along with the
logic to determine which icon to return.

Integration:
Currently a SNAPSHOT of this library is available in JitPack.io.
Documention: https://jitpack.io/#dinzdale/openchargemap_android_library/-SNAPSHOT

1) gradle, root gradle.build
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

2) app gradle.build
dependencies {
	        implementation 'com.github.dinzdale:openchargemap_android_library:-SNAPSHOT'
}

Some of the technologies leveraged to make this library possible are as follows, and
can be used to learn how to use Android's latest architecture components:

Retrofit api library: https://square.github.io/retrofit/ (with Kotlin coroutines bindings)
Kotlin
Android Room - database creation and table/entities definition (with Kotlin coroutines bindings).
Kotlin Coroutines - streamlining asynch handling.
ModelViews - extracting data from UI components.

This library is functional; works as is; is a work in progress and made available as open source.
Further credits and licensing information to be added.

Please forward any questions to:
dinzdale:
gmjacobs61@gmail.com

