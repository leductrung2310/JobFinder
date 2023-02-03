package com.example.jobfinder.ui.home.viewjob

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentAddJobBinding
import com.example.jobfinder.databinding.FragmentViewJobBinding
import com.example.jobfinder.ui.home.HomeViewModel
import com.example.jobfinder.ui.home.addjob.toYesNo
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

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

    @SuppressLint("IntentReset")
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
            binding.jobSalaryRange.text = it.description
            binding.jobTelecommuting.text = it.telecommuting?.toYesNo()
            binding.jobLogo.text = it.has_company_logo?.toYesNo()
            binding.jobQuestion.text = it.has_questions?.toYesNo()
            binding.jobEmployeeType.text = it.employee_type
            binding.jobExperience.text = it.required_experience
            binding.jobEducation.text = it.required_education
            binding.jobIndustry.text = it.industry
            binding.jobFunction.text = it.function
            if (it.is_fake == true) {
                binding.layoutAlter.visibility = View.VISIBLE
            } else {
                binding.layoutAlter.visibility = View.GONE
            }
        }


        binding.applyJobButton.setOnClickListener {
            if (homeViewModel.selectedJob.value?.is_fake == true && homeViewModel.selectedJob.value?.report_count?.size!! >= 5) {
                openReportDialog()
            }
            else {
                openEmail()
            }

        }
    }

    private fun openReportDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Apply for jobs")
            .setMessage("This job is predicted fake. Do you want to apply for this job?")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                openEmail()
                dialog.dismiss()
            }
            .show()
    }

    private fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_BCC, binding.jobCompanyEmail.text)
            putExtra(Intent.EXTRA_SUBJECT, "Apply for ${binding.jobName.text}")
        }
        if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
            startActivity(Intent.createChooser(intent,"Send Email Using: "))
        }
    }
}