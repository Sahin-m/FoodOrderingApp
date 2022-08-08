package com.example.yemeksiparis.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.databinding.CardSepetTasarimBinding
import com.example.yemeksiparis.ui.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.lang.Exception

class SepetAdapter(var mContext: Context,
                   var sepetListesi : List<SepetYemekler>,
                   var viewModel: SepetFragmentViewModel) :
    RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: CardSepetTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardSepetTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardSepetTasarimBinding.inflate(layoutInflater,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        try {
            val t = holder.tasarim
            val sepet = sepetListesi.get(position)
            t.textViewYemekAdi3.text = sepet.yemek_adi
            t.textViewYemekFiyat3.text = "${sepet.yemek_fiyat * sepet.yemek_siparis_adet}"

            viewModel.sepetiTopla(sepet.yemek_fiyat * sepet.yemek_siparis_adet)

            t.textViewSepetAdet.text = sepet.yemek_siparis_adet.toString()
            Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}").into(t.imageViewSepetResim)
            if(sepet.yemek_siparis_adet > 1){
                t.imageViewBtnSil.setImageResource(R.drawable.cikar_resim)
            }else{
                t.imageViewBtnSil.setImageResource(R.drawable.sil_resim)
            }

            t.imageViewBtnArttir.setOnClickListener {
                sepet.yemek_siparis_adet += 1
                t.textViewSepetAdet.text = sepet.yemek_siparis_adet.toString()

                //viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                //sepetListesi = emptyList()
                //notifyDataSetChanged()
                //viewModel.sepetToplam = 0

                viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                viewModel.sepetToplam = 0
                sepetListesi = emptyList()
                viewModel.sepetiGuncelle(sepet.yemek_adi, sepet.yemek_resim_adi, sepet.yemek_fiyat, sepet.yemek_siparis_adet, "MuhammedSahin")
                viewModel.sepetiYukle("MuhammedSahin")
            }
            t.imageViewBtnSil.setOnClickListener {
                if (sepet.yemek_siparis_adet > 1)
                {
                    sepet.yemek_siparis_adet -= 1

                    //viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                    //viewModel.sepetToplam = 0
                    //sepetListesi = emptyList()
                    //notifyDataSetChanged()

                    viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                    viewModel.sepetToplam = 0
                    sepetListesi = emptyList()
                    viewModel.sepetiGuncelle(sepet.yemek_adi, sepet.yemek_resim_adi, sepet.yemek_fiyat, sepet.yemek_siparis_adet, "MuhammedSahin")
                    viewModel.sepetiYukle("MuhammedSahin")
                    //viewModel.sepetToplam = 0

                }else{
                    Snackbar.make(it,"Bu ürünü silmek istediğinize emin misiniz ?",Snackbar.LENGTH_LONG)
                        .setAction("Evet",View.OnClickListener {
                            //viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                            //viewModel.sepetToplam = 0
                            //sepetListesi = emptyList()
                            //notifyDataSetChanged()

                            viewModel.sil(sepet.sepet_yemek_id,"MuhammedSahin")
                            viewModel.sepetToplam = 0
                            sepetListesi = emptyList()
                            viewModel.sepetiGuncelle(sepet.yemek_adi, sepet.yemek_resim_adi, sepet.yemek_fiyat, sepet.yemek_siparis_adet, "MuhammedSahin")
                            viewModel.sepetiYukle("MuhammedSahin")
                            //viewModel.sepetToplam = 0
                        })
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.RED)
                        .show()
                }
            }
        }catch (e:Exception){
        }

    }
    override fun getItemCount(): Int {
        if (sepetListesi.isEmpty()){
            //viewModel.sepetToplam = 0
        }
        return sepetListesi.size
    }

    fun sepetListeYukle(yeniListe : List<SepetYemekler>){
        val yemekListesiDiffUtil = YemekListeDiffUtil(sepetListesi,yeniListe)
        val diffUtilResult = DiffUtil.calculateDiff(yemekListesiDiffUtil)
        sepetListesi = yeniListe
        diffUtilResult.dispatchUpdatesTo(this)
    }
}