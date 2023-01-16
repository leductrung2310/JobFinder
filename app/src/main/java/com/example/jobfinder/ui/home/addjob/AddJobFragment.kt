package com.example.jobfinder.ui.home.addjob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentAddJobBinding

class AddJobFragment : Fragment() {
    private var _binding : FragmentAddJobBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.action_addJobFragment3_to_navigation_home)
        }
    }

}