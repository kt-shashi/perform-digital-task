package com.shashi.performdigitaltask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shashi.performdigitaltask.data.RecyclerViewData

class MainActivity : AppCompatActivity() {

    private lateinit var itemData: ArrayList<RecyclerViewData>
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setupRecyclerVIew()

    }

    //Initialize views
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    // Setup recycler view
    private fun setupRecyclerVIew() {

        itemData = ArrayList()
        mAdapter = ItemAdapter()

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        itemData = getData()
        mAdapter.updateList(itemData)

    }

    // Provide data for each view (View Type 1/2/3)
    private fun getData(): ArrayList<RecyclerViewData> {

        val tempList: ArrayList<RecyclerViewData> = ArrayList()

        for (i in 1..13) {
            val data: RecyclerViewData = when (i) {
                7 -> RecyclerViewData("View\nType\n2")
                10 -> RecyclerViewData("View Type 3")
                else -> RecyclerViewData("View\nType\n1")
            }
            tempList.add(data)
        }

        return tempList
    }

}