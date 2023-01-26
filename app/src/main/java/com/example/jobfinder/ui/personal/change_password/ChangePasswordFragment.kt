package com.example.jobfinder.ui.personal.change_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentChangePasswordBinding
import com.example.jobfinder.databinding.FragmentPersonalBinding
import com.example.jobfinder.ui.personal.PersonalScreen
import com.example.jobfinder.ui.personal.PersonalViewModel
import com.example.jobfinder.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                JobFinderTheme {
                    ChangePasswordScreen(
                        onChangePasswordSuccess = {
                            findNavController().popBackStack()
                        },
                        navigateBack = {
                            findNavController().popBackStack()
                        }
                    )
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}