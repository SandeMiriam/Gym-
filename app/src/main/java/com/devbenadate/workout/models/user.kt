package com.devbenadate.workout.models

import com.google.gson.annotations.SerializedName

data class user(
@SerializedName("first_name") var first_name: String,
@SerializedName("last_name") var last_name: String,
@SerializedName("phone_number") var phone_number: String,
var email: String,
@SerializedName("user_id") var user_id: String,

)

