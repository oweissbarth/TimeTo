package de.oweissbarth.timeto

import android.util.Log
import androidx.lifecycle.*
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel : ViewModel() {
    private val db: Realm by lazy {
        Realm.getDefaultInstance()
    }

    val allTasks: LiveRealmResults<Task> = LiveRealmResults<Task>(getResults = {
           db.where<Task>().findAll()

    }, closeRealm = {

        db?.close()
    })//db.where<Task>().findAll().asLiveData()

    fun insert(task: Task) {
        db.executeTransaction {
            it.insert(task)
        }
    }

    fun delete(taskID: String) {
        db.executeTransaction {
            Log.d("TaskViewModel", "Deleting task $taskID")
            it.where(Task::class.java).equalTo("id", taskID).findFirst()?.deleteFromRealm()
        }
    }

    fun getById(id: String): Task?{
       return db.where(Task::class.java).equalTo("id", id).findFirst()
    }

    override fun onCleared() {
        db.close()
        super.onCleared()
    }
}

class TaskViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel() as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}