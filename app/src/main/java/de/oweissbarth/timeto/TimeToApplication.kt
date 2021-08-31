package de.oweissbarth.timeto

import android.app.Application
import io.realm.RealmConfiguration
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TimeToApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(){
        super.onCreate()


        Realm.init(this)

        // Creating our db with custom properties

        val config = RealmConfiguration.Builder()
            .name("test.db")
            .schemaVersion(1)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }


}