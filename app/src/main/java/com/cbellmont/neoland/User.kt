package com.cbellmont.neoland

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var gender: String,
    var email: String,
    @Embedded
    var name: Name,
    @Embedded
    var picture: Picture
) {
    @PrimaryKey(autoGenerate = true)
    var uId = 0
}

data class Name(
    var first: String,
    var last: String
)

data class Picture(
    var large: String,
    var medium: String,
    var thumbnail: String
)




