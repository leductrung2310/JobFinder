package com.example.jobfinder.ui.authentication.forgot_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentForgotPasswordBinding
import com.example.jobfinder.databinding.FragmentLogInBinding
import com.example.jobfinder.ui.authentication.login.LogInScreen
import com.example.jobfinder.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                JobFinderTheme {
                    ForgotPasswordScreen()
                }
            }
        }

        binding.topBar.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                JobFinderTheme {
                    ForgotPasswordTopBar(
                        onNavigateBack = { findNavController().popBackStack() }
                    )
                }
            }
        }
        return root
    }
}