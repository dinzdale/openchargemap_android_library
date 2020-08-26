



> Written with [StackEdit](https://stackedit.io/).
>

## openchargemap_android_library

## This library makes integrating OpenChargeMap.org (https://openchargemap.org/site) electric vehicle charging station POI's easy with your mapping application.





## **The library retrieves two sets of data:**

 1. Core reference model data that can be used for presenting option lists to the user.
 2. Charging station POI's - with details of charging stations for
   each matching POI.


## Architecure
OCM reference data is persisted in an sqlite3 database and is retrieved and populated via the OCM "reference" api. Reference data expires and refreshes the database every 10 days, but can be overridden by the application.
Charging station POI's, containing detailed information about each charging station, are retrieved using the OCM "poi" api and specifies the desired location coordinates.

An **OpenChargeMapViewModel** ViewModel is made available to the application to act as the sole component for retrieving OCM reference data and making poi queries. Database management and api calls are all handled by the library, but can also be accesed by the application if required.
Reference data for the application session can be filtered as needed and stored for the session in the OpenChargeMapViewModel.

**OpenChargeMapViewModel usage:**
Use of of the OpenChargeMapViewModel requires observing the dbIntialized property initially for database initialization followed by observing the paramsFetched property, indicating completion of loading the OpenChargeMapViewModel with the specified reference data.

Applications create an instance of an OpenChargeMapViewModel by using the OpenChargeMapViewModelFactory,  with a reference to your  Application and optionally the number of days to refresh the database.  Initial installation of your application will build the database and persist until it expires.

    openChargeMapViewModel = ViewModelProviders
      .of(this,OpenChargeMapViewModelFactory(application))
      .get(OpenChargeMapViewModel::class.java)

After creation of the ViewModel, the application should observe the dbInitialized property. The dbInitialed will invoke the observer when the database has been populated (either upon an initial install, database expiration/refresh or just indicate ready to proceed) :

    fun setUpOpenChargeMapListeners() {
        progressBar.visibility = View.VISIBLE
      if (!openChargeMapViewModel.dbIntialized.hasObservers()) {
            openChargeMapViewModel.dbIntialized.observe(this, openChargeMapDBInitializedListener)
        }
    }

During execution of the dbInitialization observer, the application can indicate which reference data to load the OpenChargeMapViewModel with, by either requesting all of the reference data for a type or filtering the reference data by names. The following example pulls all the possible ChargeTypes and StatusTypes while requesting partial matches by name for ConnectionTypes, Countries, Operators and UsageTypes.

    val openChargeMapDBInitializedListener = Observer<Boolean> {
      if (it) {

            openChargeMapViewModel.getChargeTypes()
            openChargeMapViewModel.getStatusTypes()

            openChargeMapViewModel.getConnectionTypesByName("chademo", "J1772", "CCS")
            openChargeMapViewModel.getCountriesByName(
                "united states",
                "mexico",
                "canada",
                "puerto rico",
                "israel",
                "france"
      )
            openChargeMapViewModel.getOperatorsByName("blink", "chargepoint")
            openChargeMapViewModel.getUsageTypesByName("public")
            openChargeMapViewModel.pois.observe(this, openChargeMapPOIObserver)
        }
    }
Note above, at this time, an observer for POI data retrieval can also be registered with the poi property.

Example POI observer:

    val openChargeMapPOIObserver = Observer<Optional<List<PoiItem>>> {
    ...
    }

After the desired reference data has been requested by the application as above, the application can now get notified of reference data loading completion into the  OpenChargeMapViewModel by observing the paramsFetched property. When this observer is invoked, calls to retrieve POI data can be made as the parameter reference data is now available in the OpenChargeMapViewModel. The API requires a list of ids for those reference data types to pass to the OCM api. Helper get***IDs functions can be found in the ViewModel for this purpose. This sample function retrieves POIs with a location object specifying the required coordinates for the poi call:

    fun relocateOpenChargeMapPins(newLocation: Location, clearPins: Boolean) {
        if (clearPins) {
            // clear OCM pins here
        }
        //setUpOpenChargeMapListeners()

        // wait for all params to get fetched into model
      openChargeMapViewModel.paramsFetched.observe(this, Observer {
      if (it) {
                openChargeMapViewModel.getPOIs(
                    newLocation.latitude,
                    newLocation.longitude,
                    countryIDs = openChargeMapViewModel.getCountryIDs(),
                    operatorIDs = openChargeMapViewModel.getOperatorIDs(),
                    connectionTypeIDs = openChargeMapViewModel.getConnectionTypeIDs(),
                    usageTypeIDs = openChargeMapViewModel.getUsageTypeIDs(),
                    statusTypeIDs = openChargeMapViewModel.getStatusTypeIDs(),
                    maxResults = 100)
            }
        })

    }

Results of the POI queries will be returned to the applications poi observer, the openChargeMapPOIObserver in the example above.
To optionally render map markers from POI instances, a helper class MapMarker is available and can be used to render OCM pins to your map within this observer.

    val mapMarkers = MapMarkers(context)
    val mapIcon : Bitmap = mapMarkers.getIconForPOI(poi)

The above getIconForPOI returns a Bitmaps to be used for the your map charging station icons. These images were borrowed from the Open Charge Map app [https://play.google.com/store/apps/details?id=com.webprofusion.openchargemap&hl=en_US along with the
logic to determine which icon to return.

**Integration**:
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


**Technologies employed by this are as follows:**

 - Kotlin
 - Retrofit network library: https://square.github.io/retrofit/ (with Kotlin
   coroutines bindings)
 - Room - database creation and table/entities definition (with Kotlin coroutine bindings).
 - Kotlin Coroutines - streamlining async handling.
 - ModelViews - abstracting data from UI components.

This library is functional and is available as is. It has been made as open source for further collaboration of improvements and enhancements.

Further credits and licensing information to be added.

Please forward any questions to:
dinzdale:
gmjacobs61@gmail.com
