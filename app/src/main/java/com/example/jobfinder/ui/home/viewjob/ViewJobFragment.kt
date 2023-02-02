package com.example.jobfinder.ui.home.viewjob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentAddJobBinding
import com.example.jobfinder.databinding.FragmentViewJobBinding
import com.example.jobfinder.ui.home.HomeViewModel
import com.example.jobfinder.ui.home.addjob.toYesNo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewJobFragment : Fragment() {
    private var _binding: FragmentViewJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        _binding = FragmentViewJobBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.action_viewJobFragment_to_navigation_home)
        }
        homeViewModel.selectedJob.observe(viewLifecycleOwner) {
            binding.jobName.text = it.title
            binding.jobLocation.text = it.location
            binding.jobCompanyProfile.text = it.company_profile
            binding.jobCompanyEmail.text = it.company_email
            binding.jobRequirements.text = it.requirements
            binding.jobSalaryRange.text = it.salary_range
            binding.jobTelecommuting.text = it.telecommuting?.toYesNo()
            binding.jobLogo.text = it.has_company_logo?.toYesNo()
            binding.jobQuestion.text = it.has_questions?.toYesNo()
            binding.jobEmployeeType.text = it.employee_type
            binding.jobExperience.text = it.required_experience
            binding.jobEducation.text = it.required_education
            binding.jobIndustry.text = it.industry
            binding.jobFunction.text = it.function
        }
    }
}