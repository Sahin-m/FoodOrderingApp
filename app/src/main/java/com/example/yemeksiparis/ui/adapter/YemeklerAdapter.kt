package com.example.yemeksiparis.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.databinding.CardYemekTasarimBinding
import com.example.yemeksiparis.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparis.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yemeksiparis.utils.sayfaGecis
import com.squareup.picasso.Picasso

class YemeklerAdapter(var mContex : Context,
                      var yemeklerListesi : List<Yemekler>,
                      var viewModel: AnasayfaFragmentViewModel) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    //public var yemekListesi = emptyList<Yemekler>()

    inner class CardTasarimTutucu(tasarim : CardYemekTasarimBinding ) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim : CardYemekTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContex)
        val tasarim = CardYemekTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val t = holder.tasarim
        val yemek = yemeklerListesi.get(position)
        var yemekSiparisAdet = 1
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}").into(t.imageViewYemek)
        t.textViewYemekAdi.text = yemek.yemek_adi
        t.textViewYemekFiyat.text = "${yemek.yemek_fiyat}"


        t.imageViewBtnArttir.setOnClickListener {
            yemekSiparisAdet += 1
            t.textViewSepetAdet.text = yemekSiparisAdet.toString()
        }
        t.imageViewBtnSil.setOnClickListener {
            if (yemekSiparisAdet > 1){
                yemekSiparisAdet -= 1
                t.textViewSepetAdet.text = yemekSiparisAdet.toString()
            }
        }

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.sayfaGecis(it,gecis)
        }
        t.buttonSepeteEkle.setOnClickListener {
            viewModel.ekle(yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat, yemekSiparisAdet, "MuhammedSahin")
        }
    }
    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    fun yemekListeYukle(yeniListe : List<Yemekler>){
        val yemekListesiDiffUtil = YemekListeDiffUtil(yemeklerListesi,yeniListe)
        val diffUtilResult = DiffUtil.calculateDiff(yemekListesiDiffUtil)
        yemeklerListesi = yeniListe
        diffUtilResult.dispatchUpdatesTo(this)
    }
}