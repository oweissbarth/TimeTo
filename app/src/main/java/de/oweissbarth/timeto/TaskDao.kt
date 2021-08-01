package de.oweissbarth.timeto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Query("DELETE FROM task_table WHERE id=:id")
    suspend fun delete(id: Int)
}