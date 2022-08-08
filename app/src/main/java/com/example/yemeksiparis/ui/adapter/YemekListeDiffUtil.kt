package com.example.yemeksiparis.ui.adapter
import androidx.recyclerview.widget.DiffUtil

class YemekListeDiffUtil<Liste>(private val eskiListe:List<Liste>, private val yeniListe:List<Liste>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return eskiListe.size
    }

    override fun getNewListSize(): Int {
        return yeniListe.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return eskiListe[oldItemPosition] === yeniListe[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return eskiListe[oldItemPosition] == yeniListe[newItemPosition]
    }
}