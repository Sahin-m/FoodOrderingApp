package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.SepetYemekler
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparis.databinding.FragmentSepetBinding
import com.example.yemeksiparis.ui.adapter.YemeklerAdapter
import com.example.yemeksiparis.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yemeksiparis.ui.viewmodel.YemekDetayFragmentViewModel
import com.example.yemeksiparis.utils.sayfaGecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var tasarim : FragmentAnasayfaBinding
    private lateinit var yemeklerAdapter: YemeklerAdapter
    private lateinit var viewModel : AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = "Yemekler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        tasarim.imageViewSirala.setOnClickListener {
            val popup = PopupMenu(it.context,tasarim.imageViewSirala)
            popup.menuInflater.inflate(R.menu.popup_menu,popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_artan_fiyat -> {
                        viewModel.yrepo.yemekSirala(1)
                        true
                    }
                    R.id.action_azalan_fiyat -> {
                        viewModel.yrepo.yemekSirala(0)
                        true
                    }
                    else ->{
                        false
                    }
                }
            }
        }


        tasarim.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            yemeklerAdapter = YemeklerAdapter(requireContext(),it,viewModel)
            tasarim.yemeklerAdapter = yemeklerAdapter
            yemeklerAdapter.yemekListeYukle(it)
        }


        tasarim.bottomNavBar.setOnItemSelectedListener{
            when (it.itemId){
                R.id.action_anasayfa ->{
                    Navigation.sayfaGecis(tasarim.rv,R.id.anasayfaFragment)
                    true
                }
                R.id.action_favoriler ->{
                    false

                }
                R.id.action_sepet ->{
                    Navigation.sayfaGecis(tasarim.rv,R.id.sepetGecis)
                    true
                }
                else -> false
            }
        }

        return tasarim.root
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }
}


