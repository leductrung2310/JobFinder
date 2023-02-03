package com.example.jobfinder.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobfinder.R
import com.example.jobfinder.data.model.Job
import java.util.*

class JobAdapter(
    private val jobs: List<Job>,
    private val onLongClickListener: OnLongClickListener,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job = jobs[position]
        holder.title.text = job.title
        holder.salaryRange.text = job.employee_type
        holder.companyName.text = job.company_profile
        holder.location.text = job.location
        val calendar = Calendar.getInstance()
        calendar.time = Date(job.created_date?.toLong() ?: (System.currentTimeMillis() / 1000))
        holder.jobDay.text = getDayName(calendar.get(Calendar.DAY_OF_WEEK))
        holder.jobDate.text = calendar.get(Calendar.DATE).toString()
        holder.jobMonth.text = getMonthName(calendar.get(Calendar.MONTH))
        holder.jobYear.text = calendar.get(Calendar.YEAR).toString()

        holder.jobCard.setOnClickListener {
            onClickListener.onClick(job)
        }

        holder.jobCard.setOnLongClickListener {
            onLongClickListener.onLongClick(job)
            true
        }
    }

    private fun getDayName(value: Int): String {
        return when (value) {
            Calendar.SUNDAY -> "Sun"
            Calendar.MONDAY -> "Mon"
            Calendar.TUESDAY -> "Tue"
            Calendar.WEDNESDAY -> "Wed"
            Calendar.THURSDAY -> "Thu"
            Calendar.FRIDAY -> "Fri"
            Calendar.SATURDAY -> "Sat"
            else -> "###"
        }
    }

    private fun getMonthName(value: Int): String {
        return when (value) {
            Calendar.JANUARY -> "Jan"
            Calendar.FEBRUARY -> "Feb"
            Calendar.MARCH -> "Mar"
            Calendar.APRIL -> "Apr"
            Calendar.MAY -> "May"
            Calendar.JUNE -> "Jun"
            Calendar.JULY -> "Jul"
            Calendar.AUGUST -> "Aug"
            Calendar.SEPTEMBER -> "Sep"
            Calendar.OCTOBER -> "Oct"
            Calendar.NOVEMBER -> "Nov"
            Calendar.DECEMBER -> "Dec"
            else -> "###"
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jobCard: CardView = view.findViewById(R.id.jobCardView)
        val title: AppCompatTextView = view.findViewById(R.id.jobTitle)
        val salaryRange: AppCompatTextView = view.findViewById(R.id.salaryRange)
        val companyName: AppCompatTextView = view.findViewById(R.id.companyName)
        val location: AppCompatTextView = view.findViewById(R.id.location)
        val jobDay: AppCompatTextView = view.findViewById(R.id.jobDay)
        val jobDate: AppCompatTextView = view.findViewById(R.id.jobDate)
        val jobMonth: AppCompatTextView = view.findViewById(R.id.jobMonth)
        val jobYear: AppCompatTextView = view.findViewById(R.id.jobYear)
    }
}

interface OnLongClickListener {
    fun onLongClick(job: Job)
}

interface OnClickListener{
    fun onClick(job: Job)
}