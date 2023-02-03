package com.example.jobfinder.ui.home.addjob

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentAddJobBinding
import com.example.jobfinder.ui.home.AddJobState
import com.example.jobfinder.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class AddJobFragment : Fragment() {
    private var _binding: FragmentAddJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private var isTelecommuting: Int = 1
    private var isLogo: Int = 1
    private var isQuestion: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.action_addJobFragment3_to_navigation_home)
        }

        homeViewModel.isEnableAddJobButton.observe(viewLifecycleOwner) {
            binding.addJobButton.isEnabled = it
        }

        homeViewModel.addJobState.observe(viewLifecycleOwner) {
            when (it) {
                is AddJobState.Waiting -> {
                }
                is AddJobState.Loading -> {
                    Toasty.info(
                        requireContext(),
                        "Waiting to add job!",
                        Toast.LENGTH_SHORT,
                        true,
                    ).show()
                }
                is AddJobState.Success -> {
                    Toasty.success(
                        requireContext(),
                        "Add job successfully",
                        Toast.LENGTH_LONG,
                        true
                    ).show()
                    findNavController().popBackStack()
                    homeViewModel.setAddJobState()
                }
                is AddJobState.Error -> {
                    Toasty.error(
                        requireContext(),
                        "Some thing wrong when add job!",
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }
            }
        }

        setUpEventButton()
        setUpRequiredEducationSpinner()
        setUpRequiredExperienceSpinner()
        setUpEmployeeTypeSpinner()
        setUpIndustrySpinner()
        setUpFunctionSpinner()
        setUpRadioButton()
    }

    private fun setUpEventButton() {
        binding.addJobButton.setOnClickListener {
            if (binding.title.text?.isNotEmpty() == true &&
                binding.location.text?.isNotEmpty() == true &&
                binding.companyProfile.text?.isNotEmpty() == true &&
                binding.companyEmail.text?.isNotEmpty() == true &&
                binding.requirements.text?.isNotEmpty() == true &&
                binding.salaryRange.text?.isNotEmpty() == true
            ) {
                binding.addJobButton.isEnabled = false
                val job = Job(
                    title = binding.title.text.toString(),
                    location = binding.location.text.toString(),
                    company_profile = binding.companyProfile.text.toString(),
                    company_email = binding.companyEmail.text.toString(),
                    requirements = binding.requirements.text.toString(),
                    telecommuting = isTelecommuting.toBoolean(),
                    has_company_logo = isLogo.toBoolean(),
                    has_questions = isQuestion.toBoolean(),
                    employee_type = binding.employeeType.selectedItem.toString(),
                    required_experience = binding.requiredExperience.selectedItem.toString(),
                    required_education = binding.requiredEducation.selectedItem.toString(),
                    industry = binding.industrySpinner.selectedItem.toString(),
                    function = binding.functionSpinner.selectedItem.toString(),
                    description = binding.salaryRange.text.toString(),
                    report_count = emptyList(),
                    created_date = System.currentTimeMillis().toString(),
                    is_fake = false,
                )
                homeViewModel.checkFakeJob(job)
            } else {
                Toasty.info(
                    requireContext(),
                    "Please fill full information!",
                    Toast.LENGTH_SHORT,
                    true,
                ).show()
            }
        }

    }

    private fun setUpRequiredEducationSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.required_education, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.requiredEducation.adapter = it
        }

        binding.requiredEducation.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Log.i("add job", p0.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpRequiredExperienceSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.required_experience, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.requiredExperience.adapter = it
        }

        binding.requiredExperience.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Log.i("add job", p0.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpEmployeeTypeSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.employee_type, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.employeeType.adapter = it
        }

        binding.employeeType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Log.i("add job", p0.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpIndustrySpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.industry, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.industrySpinner.adapter = it
        }

        binding.industrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Log.i("add job", p0.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpFunctionSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.function, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.functionSpinner.adapter = it
        }

        binding.functionSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Log.i("add job", p0.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpRadioButton() {
        binding.telecommuting.setOnCheckedChangeListener { _, i ->
            isTelecommuting = if (i == R.id.yesTelecommuting) {
                1
            } else {
                0
            }
        }

        binding.logo.setOnCheckedChangeListener { _, i ->
            isLogo = if (i == R.id.yesLogo) {
                1
            } else {
                0
            }
        }

        binding.question.setOnCheckedChangeListener { _, i ->
            isQuestion = if (i == R.id.yesLogo) {
                1
            } else {
                0
            }
        }
    }
}

fun Int.toBoolean(): Boolean {
    return this == 1
}

fun Boolean.toYesNo(): String {
    if (this) {
        return "Yes"
    }
    return "No"
}