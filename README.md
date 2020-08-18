# openchargemap-android-library
This library makes integrating OpenChargeMap.org electric vehicle charging station POI's easy.

Charging stations model data is stored in an sqlite database for persistence and charging station
POI's are retrieved upon each call using the provided location information.

Simply use the provided OpenChargeMapViewModel for all your access to get
model data and POI's.

Use the OpenChargeMapViewModel as follows:
1) Create an instance of the ViewModel OpenChargeMapViewModelFactory with a reference to
your Android Application and optional the number of days to expire/refresh any database
persistant data. Database (model data) has a 10 day expiration date by default.

openChargeMapViewModel = ViewModelProviders.of(this, OpenChargeMapViewModelFactory(application)).get(OpenChargeMapViewModel::class.java)

2) Observer the ViewModel's dbInitialized property, which will indicated completion
of retreival and initialization of the database:

 fun setUpOpenChargeMapListeners() {
        progressBar.visibility = View.VISIBLE
        if (!openChargeMapViewModel.dbIntialized.hasObservers()) {
            openChargeMapViewModel.dbIntialized.observe(this, openChargeMapDBInitializedListener)
        }
    }

 3) Setup observers for model data updates (optional) and charging station poi's after db
 intialization. Calls to retrieve parts of the model data can be done at this time
 (example shows ChargeTypes and ConnectionTypes observers:

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

 openChargeMapViewModel.getPOIs(newLocation.latitude, newLocation.longitude)