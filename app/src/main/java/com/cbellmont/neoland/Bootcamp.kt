package com.cbellmont.neoland

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Campus::class,
        parentColumns = arrayOf("uId"),
        childColumns = arrayOf("campusId"),
        onDelete = ForeignKey.CASCADE)
))


data class Bootcamp (
    var name: String,
    var about: String,
    var campusId: Int
){
    @PrimaryKey(autoGenerate = true)
    var uId = 0
}
