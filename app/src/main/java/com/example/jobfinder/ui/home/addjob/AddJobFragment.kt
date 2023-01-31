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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentAddJobBinding
import com.example.jobfinder.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddJobFragment : Fragment() {
    private var _binding: FragmentAddJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private var isTelecommuting: Int = 0;
    private var isLogo: Int = 0;
    private var isQuestion: Int = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.action_addJobFragment3_to_navigation_home)
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
                val job = Job(
                    title = binding.title.text.toString(),
                    location = binding.location.text.toString(),
                    company_profile = binding.companyProfile.text.toString(),
                    company_email = binding.companyEmail.text.toString(),
                    requirements = binding.companyEmail.text.toString(),
                    telecommuting = isTelecommuting.toBoolean(),
                    has_company_logo = isLogo.toBoolean(),
                    has_questions = isQuestion.toBoolean(),
                    employee_type = binding.employeeType.selectedItem.toString(),
                    required_experience = binding.requiredExperience.selectedItem.toString(),
                    required_education = binding.requiredEducation.selectedItem.toString(),
                    industry = binding.industrySpinner.selectedItem.toString(),
                    function = binding.functionSpinner.selectedItem.toString(),
                    salary_range = binding.salaryRange.text.toString(),
                    report_count = 0,
                    created_date = System.currentTimeMillis().toString(),
                    is_fake = false,
                )
                homeViewModel.addJob(job)
            } else {
                val job = 2;
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
        binding.telecommuting.setOnCheckedChangeListener { radioGroup, i ->
            isTelecommuting = if (i == R.id.yesTelecommuting) {
                1
            } else {
                0
            }
        }

        binding.logo.setOnCheckedChangeListener { radioGroup, i ->
            isLogo = if (i == R.id.yesLogo) {
                1
            } else {
                0
            }
        }

        binding.question.setOnCheckedChangeListener { radioGroup, i ->
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