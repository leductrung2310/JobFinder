package com.example.jobfinder.ui.home.addjob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentAddJobBinding
import com.example.jobfinder.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddJobFragment : Fragment() {
    private var _binding : FragmentAddJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {        homeViewModel =
        ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.action_addJobFragment3_to_navigation_home)
            homeViewModel.addJob(Job(company = "hello"))
        }
    }

}