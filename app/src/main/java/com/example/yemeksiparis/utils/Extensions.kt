package com.example.yemeksiparis.utils

import android.view.MenuItem
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.sayfaGecis(v:View,id:Int){
    findNavController(v).navigate(id)
}

fun Navigation.sayfaGecis(v:View,id:NavDirections){
    findNavController(v).navigate(id)
}

