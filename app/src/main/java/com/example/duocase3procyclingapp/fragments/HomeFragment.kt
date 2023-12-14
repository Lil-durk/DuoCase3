package com.example.duocase3procyclingapp.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.duocase3procyclingapp.databinding.FragmentHomeBinding
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding  // Initialize the binding variable

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level: Int = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            val scale: Int = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

            if (level != -1 && scale != -1) {
                val batteryPercentage = (level.toFloat() / scale.toFloat() * 100).toInt()
                updateBatteryPercentage(batteryPercentage)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Register the battery receiver
        requireActivity().registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unregister the battery receiver to avoid memory leaks
        requireActivity().unregisterReceiver(batteryReceiver)
    }

    private fun updateBatteryPercentage(percentage: Int) {
        // Update the TextView using view binding
        binding.batteryPercentageTextView.text = "Battery Percentage: $percentage%"
    }
}
