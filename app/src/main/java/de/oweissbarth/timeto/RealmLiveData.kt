package de.oweissbarth.timeto

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import io.realm.*

@MainThread
class LiveRealmResults<T : RealmModel>(private val getResults: () -> RealmResults<T>,
                                       private val closeRealm: () -> Unit) : LiveData<List<T>>() {

    private val listener = OrderedRealmCollectionChangeListener<RealmResults<T>> {
            results, _ ->
        this@LiveRealmResults.value = results
    }
    private var results: RealmResults<T>? = null

    private var realm: Realm? = null

    override fun onActive() {
        super.onActive()

        results = getResults()

        if (results?.isValid == true) {
            results?.addChangeListener(listener)
        }
        if (results?.isLoaded == true) {
            value = results
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (results?.isValid == true) {
            results?.removeChangeListener(listener)
        }
    }
}

