package com.example.yemeksiparis.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparis.data.entity.CRUDcevap
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.data.entity.SepetYemeklerCevap
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.retrofit.SepetYemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SepetDaoRepository(var sdao: SepetYemeklerDao) {


    var sepetListesi : MutableLiveData<List<SepetYemekler>>
    init {
        sepetListesi = MutableLiveData()
    }

    fun sepetiGetir() : MutableLiveData<List<SepetYemekler>>{
        return  sepetListesi
    }


    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
          sdao.sepeteKaydet(yemek_adi,yemek_resim_adi,yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object:Callback<CRUDcevap>{
                override fun onResponse(call: Call<CRUDcevap>?, response: Response<CRUDcevap>) {
                    val basari = response.body().success
                    val mesaj = response.body().message
                    Log.e("Sepete Ekle","$basari - $mesaj")
                }
                override fun onFailure(call: Call<CRUDcevap>?, t: Throwable?) {}
            })
    }


    fun tumSepetiAl(kullanici_adi:String){
        sdao.tumSepetiGetir(kullanici_adi).enqueue(object: Callback<SepetYemeklerCevap> {
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val liste = response.body().sepetYemekler
                sepetListesi.value = liste

            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {
                val emptyList:List<SepetYemekler> = emptyList()
                sepetListesi.value = emptyList
            }
        })
    }

    fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi: String){
        sdao.sepettekiYemekSil(sepet_yemek_id,kullanici_adi).enqueue(object : Callback<CRUDcevap>{
            override fun onResponse(call: Call<CRUDcevap>?, response: Response<CRUDcevap>) {

                tumSepetiAl(kullanici_adi)
            }
            override fun onFailure(call: Call<CRUDcevap>?, t: Throwable?) {}
        })
    }
}