package de.oweissbarth.timeto

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TimeToApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TaskDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}