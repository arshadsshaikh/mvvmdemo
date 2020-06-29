package com.demoexoplayer.homeactivity.model

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract  class EndlessRecyclerOnScrollListener(linearLayoutManager: LinearLayoutManager?) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5

    var firstVisibleItem = 0
    var visibleItemCount=0//private int visibleThreshold = 10;

    private var   totalItemCount=0




    private var current_page = 1

    private var mLinearLayoutManager: LinearLayoutManager? = linearLayoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.getChildCount();
        totalItemCount = mLinearLayoutManager!!.getItemCount();
        firstVisibleItem = mLinearLayoutManager!!.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
               loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
            <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            Log.i("Yaeye!", "end called");

            // Do something
            current_page++
            onLoadMore(current_page)

            loading = true;
        }
    }


//        visibleItemCount = mLinearLayoutManager.childCount
//        totalItemCount = mLinearLayoutManager!!.itemCount
//        firstVisibleItem = mLinearLayoutManager!!.findLastVisibleItemPosition() + 1
//
////        if (loading) {
////            if (firstVisibleItem == previousTotal) {
////                loading = false
////                previousTotal += 20
////            }
////        }
//     //   if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) { // End has been reached
//// Do something
//            current_page++
//            onLoadMore(current_page)
//            loading = true
//   //     }





    abstract fun onLoadMore(current_page: Int)




}