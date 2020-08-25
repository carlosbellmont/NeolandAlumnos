package com.cbellmont.neoland

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Campus (
    var name: String,
    var foto: Int
){
    @PrimaryKey(autoGenerate = true)
    var uId = 0
}






