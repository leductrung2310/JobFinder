package com.example.jobfinder.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.jobfinder.R
import com.example.jobfinder.base.BaseFragment
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.databinding.FragmentHomeBinding
import com.example.jobfinder.ui.home.adapter.JobAdapter
import com.example.jobfinder.ui.home.adapter.OnClickListener
import com.example.jobfinder.ui.home.adapter.OnLongClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty


@AndroidEntryPoint
class HomeFragment : BaseFragment(), OnClickListener, OnLongClickListener {
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
                    jobAdapter = JobAdapter(it.jobList, this, this)
                    binding.jobRcv.adapter = jobAdapter
                    Log.i("phat ndt", "home state success ${it.jobList.size}")
                }
                is HomeState.Error -> {
                    setViewVisibilityWhenFetchingJob(View.GONE, View.VISIBLE, View.GONE)
                    Log.i("phat ndt", "home state error ${it.error}")

                }
            }
        }

        homeViewModel.reportJob.observe(viewLifecycleOwner) {
            when (it) {
                is ReportJobState.Waiting -> {
                }
                is ReportJobState.Loading -> {
                }
                is ReportJobState.Success -> {
                    Toasty.success(
                        requireContext(),
                        "Report this job successfully",
                        Toast.LENGTH_LONG,
                        true
                    ).show();
                    homeViewModel.fetchJob()
                }
                is ReportJobState.Error -> {
                    Toasty.error(
                        requireContext(),
                        "Some thing wrong when reporting this job!",
                        Toast.LENGTH_SHORT,
                        true
                    ).show();
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

    private fun openReportDialog(job: Job) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Report jobs")
            .setMessage("Do you want to report this job?")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                Firebase.auth.currentUser?.let {
                    if (job.report_count?.contains(it.uid) == true) {
                        Toasty.info(
                            requireContext(),
                            "You have reported this job!",
                            Toast.LENGTH_SHORT,
                            true,
                        ).show();
                    } else {
                        val reportCount = job.report_count?.toMutableList()
                        reportCount?.add(it.uid)
                        homeViewModel.reportJob(job.copy(report_count = reportCount))

                    }
                }
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(job: Job) {
        Toasty.info(
            requireContext(),
            "Waiting to add job!",
            Toast.LENGTH_SHORT,
            true,
        ).show();
    }

    override fun onLongClick(job: Job) {
        openReportDialog(job)
    }
}