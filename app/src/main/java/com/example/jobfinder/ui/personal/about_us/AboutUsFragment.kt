package com.example.jobfinder.ui.personal.about_us

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentAboutUsBinding
import com.example.jobfinder.databinding.FragmentPersonalBinding
import com.example.jobfinder.ui.personal.PersonalScreen
import com.example.jobfinder.ui.personal.PersonalViewModel
import com.example.jobfinder.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("IntentReset")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(PersonalViewModel::class.java)

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                JobFinderTheme {
                    AboutUSScreen(
                        onNavigateToPhoneCall = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0336456170"))
                            startActivity(intent)
                        },
                        onNavigateToEmail = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:")
                                type = "*/*"
                                putExtra(Intent.EXTRA_EMAIL, "19522422@gm.uit.edu.vn")
                            }
//                            startActivity(intent)
                            startActivity(Intent.createChooser(intent,"Send Email Using: "))
                        },
                        onNavigateBack = { findNavController().popBackStack() }
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