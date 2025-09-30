package com.example.horoscopeapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopeapp.databinding.ItemHoroscopeBinding
import com.example.horoscopeapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)} )

        }
    }

   private fun startRotationAnimation(view: View, newLambda:()->Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            withEndAction { newLambda() }
            rotationYBy(360f)

            start()
        }

    }
}