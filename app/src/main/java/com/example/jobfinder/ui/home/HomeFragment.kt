package com.example.jobfinder.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobfinder.R
import com.example.jobfinder.base.BaseFragment
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentHomeBinding
import com.example.jobfinder.ui.home.adapter.JobAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var homeViewModel: HomeViewModel
    private lateinit var jobAdapter: JobAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setUpSwipeRefresh()
        setUpToolbar()
        binding.addJobButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_addJobFragment3)
//            homeViewModel.addJob(
//                Job(
//                    name = "UI UX DESIGNER",
//                    description = "DESIGN WEB",
//                    company = "Apple",
//                    salary = "2000$",
//                    location = "USA",
//                    createDate = System.currentTimeMillis().toString(),
//                    isLock = false
//                )
//            )
        }
    }

    private fun setUpObserver() {
        homeViewModel.homeState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeState.Waiting -> {
                    setViewVisibilityWhenFetchingJob(View.GONE, View.GONE, View.GONE)
                    Log.i("phat ndt", "home state waiting")
                }
                is HomeState.Loading -> {
                    setViewVisibilityWhenFetchingJob(View.VISIBLE, View.GONE, View.GONE)
                }
                is HomeState.Success -> {
                    setViewVisibilityWhenFetchingJob(View.GONE, View.GONE, View.VISIBLE)
                    jobAdapter = JobAdapter(it.jobList)
                    binding.jobRcv.adapter = jobAdapter
                    Log.i("phat ndt", "home state success ${it.jobList.size}")
                }
                is HomeState.Error -> {
                    setViewVisibilityWhenFetchingJob(View.GONE, View.VISIBLE, View.GONE)
                    Log.i("phat ndt", "home state error ${it.error}")

                }
            }
        }
    }

    private fun setUpSwipeRefresh() {
        binding.homeSwipeRefresh.setOnRefreshListener {
            homeViewModel.fetchJob()
            binding.homeSwipeRefresh.isRefreshing = false
        }
    }

    private fun setUpToolbar() {
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchBtn -> {
                    setUpSearchView(it)
                    true;
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun setUpSearchView(menuItem: MenuItem) {
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { value ->
                    Log.i("phat ndt", value)
                    homeViewModel.searchJob(value)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let { value ->
                    Log.i("phat ndt", value)
                    homeViewModel.searchJob(value)
                }
                return true
            }

        })

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                Log.i("phat ndt", "expand search view")
                homeViewModel.refreshListJob()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                Log.i("phat ndt", "close search view")
                homeViewModel.refreshListJob()
                return true
            }
        })
    }

    private fun setViewVisibilityWhenFetchingJob(
        loadingView: Int,
        errorView: Int,
        jobRcvView: Int
    ) {
        binding.fetchJobLoading.visibility = loadingView
        binding.fetchJobError.visibility = errorView
        binding.jobRcv.visibility = jobRcvView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}