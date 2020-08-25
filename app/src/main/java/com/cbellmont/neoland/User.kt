package com.cbellmont.neoland

import androidx.room.*

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Bootcamp::class,
        parentColumns = arrayOf("uId"),
        childColumns = arrayOf("bootcampId"),
        onDelete = ForeignKey.CASCADE)
))


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
    var bootcampId: Int? = null
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




