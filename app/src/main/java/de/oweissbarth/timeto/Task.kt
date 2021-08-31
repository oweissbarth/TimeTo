package de.oweissbarth.timeto

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.time.DateTimeException
import java.time.Instant
import java.util.*

open class Task(
    var name: String? = null,
    var start: Date? = null,
    var week: Int? = null,
    var day: Int? = null,
    var hour: Int? = null,
    var minute: Int? = null,
    var second: Int? = null,
    var actions: RealmList<String> = RealmList<String>(),
    @PrimaryKey var id: String = UUID.randomUUID().toString()
): RealmObject() {

    constructor(name: String) : this(name, Date.from(Instant.now()), 0, 0,0,0,0)
}
