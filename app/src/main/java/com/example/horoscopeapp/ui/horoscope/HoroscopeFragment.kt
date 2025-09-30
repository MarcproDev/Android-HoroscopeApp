package com.example.horoscopeapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscopeapp.databinding.FragmentHoroscopeBinding
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Virgo
import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.example.horoscopeapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    private val hororscopeViewModel by viewModels<HororscopeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initUI()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
          val type: HoroscopeModel =  when(it) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries ->HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                hororscopeViewModel.horoscope.collect {
                    horoscopeAdapter.updateList(it)
                    //cambios en horoscope
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}