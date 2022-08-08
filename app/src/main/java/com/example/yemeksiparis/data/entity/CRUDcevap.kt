package com.example.yemeksiparis.data.entity

import com.google.gson.annotations.SerializedName

data class CRUDcevap(@SerializedName("success") var success:Int,
                     @SerializedName("message") var message:String) {
}