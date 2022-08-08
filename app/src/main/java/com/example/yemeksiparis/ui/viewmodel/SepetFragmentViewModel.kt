package com.example.yemeksiparis.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.data.repo.SepetDaoRepository
import com.example.yemeksiparis.data.repo.YemeklerDaoRepository
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetFragmentViewModel @Inject constructor (var srepo : SepetDaoRepository) : ViewModel() {
    val sepetListesi : MutableLiveData<List<SepetYemekler>>

    var sepetToplam = 0
    init {
        sepetiYukle("MuhammedSahin")
        sepetListesi = srepo.sepetiGetir()
    }
    fun sepetiYukle(kullanici_adi:String){
        srepo.tumSepetiAl(kullanici_adi)
    }
    fun sil(sepet_yemek_id:Int,kullanici_adi: String){
        srepo.sepetYemekSil(sepet_yemek_id,kullanici_adi)
    }
    fun sepetiGuncelle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        srepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun sepetiTopla(fiyat:Int):Int{
        sepetToplam += fiyat
        return sepetToplam
    }
}