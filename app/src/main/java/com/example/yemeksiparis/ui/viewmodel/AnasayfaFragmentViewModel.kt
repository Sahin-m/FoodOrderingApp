package com.example.yemeksiparis.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.data.repo.SepetDaoRepository
import com.example.yemeksiparis.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor (var yrepo : YemeklerDaoRepository, var srepo:SepetDaoRepository) : ViewModel() {


    val yemeklerListesi : MutableLiveData<List<Yemekler>>
    init{
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun ara(aramaKelimesi:String){
        yrepo.yemekAra(aramaKelimesi)
    }
    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
    }
    fun ekle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        Log.e("Sepete Ekle","$yemek_adi, $yemek_resim_adi, ${yemek_fiyat}, ${yemek_siparis_adet}, $kullanici_adi")
        srepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }
    fun sirala(siralama:Int) {
        yrepo.yemekSirala(siralama)
    }
}