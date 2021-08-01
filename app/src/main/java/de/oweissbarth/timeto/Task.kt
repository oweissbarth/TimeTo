package de.oweissbarth.timeto

import androidx.room.*
import java.time.OffsetDateTime

@Entity(tableName="task_table")
data class Task(
    @PrimaryKey(autoGenerate=true) val id: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="start") val start: OffsetDateTime,
    @ColumnInfo(name="week") val week: Int,
    @ColumnInfo(name="day") val day: Int,
    @ColumnInfo(name="hour") val hour: Int,
    @ColumnInfo(name="minute") val minute: Int,
    @ColumnInfo(name="second") val second: Int
){
    constructor(name: String): this(0,name, OffsetDateTime.now(), 0,0,0,0,30)
}


/*@Entity(tableName="action_table")
data class Action(
    @PrimaryKey(autoGenerate=true) val id: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="order") val order: Int
)


data class TaskWithActions(
    @Embedded
    val task: Task,

    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val actions: List<Action>
)*/
