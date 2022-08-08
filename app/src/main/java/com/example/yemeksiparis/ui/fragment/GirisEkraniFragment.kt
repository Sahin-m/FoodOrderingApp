package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.airbnb.lottie.LottieAnimationView
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentGirisEkraniBinding
import com.example.yemeksiparis.utils.sayfaGecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GirisEkraniFragment : Fragment() {
    private lateinit var tasarim : FragmentGirisEkraniBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = FragmentGirisEkraniBinding.inflate(inflater, container, false)

        tasarim.buttonAnasayfaGit.setOnClickListener {
            Navigation.sayfaGecis(it,R.id.actionAnasayfaGecis)
        }

        return tasarim.root
    }

}