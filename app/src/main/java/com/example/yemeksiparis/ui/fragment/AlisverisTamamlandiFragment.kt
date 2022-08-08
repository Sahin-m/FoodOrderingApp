package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentAlisverisTamamlandiBinding
import com.example.yemeksiparis.utils.sayfaGecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlisverisTamamlandiFragment : Fragment() {
    private lateinit var tasarim : FragmentAlisverisTamamlandiBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = FragmentAlisverisTamamlandiBinding.inflate(inflater, container, false)
        tasarim.buttonAnasayfa.setOnClickListener {
            Navigation.sayfaGecis(it,R.id.actionAlisverisToAnasayfaGecis)
        }
        return tasarim.root
    }
}