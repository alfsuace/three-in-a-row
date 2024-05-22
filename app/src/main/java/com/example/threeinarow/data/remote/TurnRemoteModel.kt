package com.example.threeinarow.data.remote

import com.google.firebase.database.PropertyName

class TurnRemoteModel(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("turn") @set:PropertyName("turn") var turn: String = ""
)