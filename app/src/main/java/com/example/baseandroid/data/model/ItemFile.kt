package com.example.baseandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
//import kotlinx.android.parcel.Parcelize

//@Parcelize
@Entity(tableName = "DocumentReaders")
data class ItemFile (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val type: String?,
    val pathName: String?,
    val image: Int?,
    val name: String?,
    val time: String?,
    val size: Double?,
    var isSelect: Boolean,
    val title: String,
    var isPreSelect: Boolean,
    var isFavourite: String?,
    var isOpen: String?
) /*: Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
}*/