package com.example.meinenotizen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Notizen (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,


    var title: String? = null,


    var dateTime: String? = null,


    var noteText: String? = null,


    var imgPath: String? = null,


    var webLink: String? = null,


    var color: String? = null

) {
    override fun toString(): String {
        return "$title : $dateTime"
    }
}

