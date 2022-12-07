package com.example.jobfinder.ui.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.jobfinder.R
import com.example.jobfinder.databinding.FragmentHomeBinding
import com.example.jobfinder.databinding.FragmentLogInBinding
import com.example.jobfinder.ui.home.HomeViewModel

class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val logInViewModel =
            ViewModelProvider(this).get(LogInViewModel::class.java)

        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLogin
        logInViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}