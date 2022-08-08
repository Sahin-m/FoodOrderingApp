package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.databinding.CardBosSepetBinding
import com.example.yemeksiparis.databinding.FragmentSepetBinding
import com.example.yemeksiparis.ui.adapter.SepetAdapter
import com.example.yemeksiparis.ui.adapter.YemeklerAdapter
import com.example.yemeksiparis.ui.viewmodel.SepetFragmentViewModel
import com.example.yemeksiparis.utils.sayfaGecis
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var tasarim : FragmentSepetBinding
    private lateinit var sepetAdapter: SepetAdapter
    private lateinit var viewModel : SepetFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetToolbarBaslik = "Sepet"
        tasarim.sepetFragment = this
        tasarim.rv.layoutManager = LinearLayoutManager(requireContext())



        viewModel.sepetListesi.observe(viewLifecycleOwner){
            sepetAdapter = SepetAdapter(requireContext(),it,viewModel)
            tasarim.textViewToplamFiyat.text = "${viewModel.sepetToplam} ₺"
            viewModel.sepetToplam = 0
            tasarim.sepetAdapter = sepetAdapter
            sepetAdapter.sepetListeYukle(it)
        }



        tasarim.buttonAlisverisTamam.setOnClickListener {
            Toast.makeText(requireContext(),"Alışverişiniz Tamamlandı Toplam Ücret ${viewModel.sepetToplam}",Toast.LENGTH_SHORT)
                .show()
            Navigation.sayfaGecis(it,R.id.actionAlisverisTamamGecis)
            var i = 0

            viewModel.sepetListesi.observe(viewLifecycleOwner){
                while (i > it.size ){
                    viewModel.sil(it.get(i).sepet_yemek_id,"MuhammedSahin")
                }
            }
        }
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle("MuhammedSahin")
        viewModel.sepetToplam = 0
    }
}

