package com.demoexoplayer.homeactivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoexoplayer.homeactivity.adapters.ArticleAdapter
import com.demoexoplayer.homeactivity.model.EndlessRecyclerOnScrollListener
import com.demoexoplayer.homeactivity.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var  articleAdapter: ArticleAdapter
    private var current_page=1
    private var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)


        var url= "blogs?page=$current_page&limit=10"

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeViewModel.getData(url)
        articlesRecyclerView.layoutManager=
            linearLayoutManager


        observeArticleData()

        articleAdapter= ArticleAdapter(this,homeViewModel.articleList)

        endlessRecyclerOnScrollListener =
            object : EndlessRecyclerOnScrollListener(linearLayoutManager) {
                override fun onLoadMore(currentpage: Int) {

                    Log.e("load","more")

                   if ( homeViewModel.isLoadMore){

                       loadMoreData(currentpage)
                   }
//

//
                }


            }


        articlesRecyclerView.addOnScrollListener(endlessRecyclerOnScrollListener as EndlessRecyclerOnScrollListener)


    }

    fun loadMoreData(currentpage: Int) {

        var url= "blogs?page=$currentpage&limit=10"

        homeViewModel.getData(url)

    }


   private fun observeArticleData(){

        homeViewModel.articleList.observe(this, Observer {
            Toast.makeText(
                this,
                " String:" ,
                Toast.LENGTH_LONG
            ).show()

            if(homeViewModel.isLoadMore){
                articlesRecyclerView.adapter=articleAdapter

            }






        })



    }









}



