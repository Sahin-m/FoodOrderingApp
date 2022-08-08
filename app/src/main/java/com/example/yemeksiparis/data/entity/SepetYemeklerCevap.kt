package com.example.yemeksiparis.data.entity

import com.google.gson.annotations.SerializedName

data class SepetYemeklerCevap(@SerializedName("sepet_yemekler") var sepetYemekler:List<SepetYemekler>,
                              @SerializedName("success") var success:Int) {
}