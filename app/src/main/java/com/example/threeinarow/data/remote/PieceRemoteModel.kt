package com.example.threeinarow.data.remote

import com.google.firebase.database.PropertyName

data class PieceRemoteModel(
    @get:PropertyName("id") @set:PropertyName("id") var id: String?,
    @get:PropertyName("horizontal") @set:PropertyName("horizontal") var horizontal: Int?,
    @get:PropertyName("vertical") @set:PropertyName("vertical") var vertical: Int?,
    @get:PropertyName("colour") @set:PropertyName("colour") var colour: String?,
    ){
    constructor() : this(null, null, null, null)
}