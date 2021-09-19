package com.example.food.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.food.data.model.Result

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var result:Result
)