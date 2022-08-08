package com.example.yemeksiparis.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.data.entity.SepetYemeklerCevap
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.data.entity.YemeklerCevap
import com.example.yemeksiparis.retrofit.SepetYemeklerDao
import com.example.yemeksiparis.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class YemeklerDaoRepository(var ydao:YemeklerDao) {
    var yemeklerListesi : MutableLiveData<List<Yemekler>>
    init {
        yemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun yemekAra(aramaKelimesi:String){
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                val filteredList = liste.filter { it.yemek_adi.lowercase().contains(aramaKelimesi.lowercase()) }
                yemeklerListesi.value = filteredList
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun yemekSirala(siralama:Int){
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                if (siralama == 1){
                    val descFilteredList = liste.sortedByDescending { it.yemek_fiyat }
                    yemeklerListesi.value = descFilteredList
                }else{
                    val ascFilteredList = liste.sortedByDescending { it.yemek_fiyat }.reversed()
                    yemeklerListesi.value = ascFilteredList
                }
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }
    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler

                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }


}