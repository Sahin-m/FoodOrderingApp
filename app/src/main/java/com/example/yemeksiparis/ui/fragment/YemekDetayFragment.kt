package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparis.ui.viewmodel.YemekDetayFragmentViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var tasarim : FragmentYemekDetayBinding
    private lateinit var viewModel : YemekDetayFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay , container, false)
        tasarim.yemekDetayFragment = this
        tasarim.yemekDetayToolbarBaslik = "Yemek Detay"
        var adet = 1
        tasarim.textViewAdet.text = adet.toString()

        val bundle : YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        tasarim.yemekNesnesi = gelenYemek
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}").into(tasarim.imageViewYemekResim)
        tasarim.imageBtnArttir.setOnClickListener {
            if (adet > 0){
                adet += 1
            }
            tasarim.textViewAdet.text = adet.toString()
        }
        tasarim.imageBtnAzalt.setOnClickListener {
            if (adet > 1){
                adet -= 1
            }
            tasarim.textViewAdet.text = adet.toString()
        }
        tasarim.buttonSepeteEkle2.setOnClickListener {

            viewModel.ekle(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat, adet, "MuhammedSahin")

        }
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }
}