package com.example.meinenotizen.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Notizen(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String,
    var noteText: String,
    var imgPath: String,
    var dateTime: String,
    var webLink: String,
    var color: String,

    ) {
    override fun toString(): String {
        return "$title : $dateTime"
    }
}
