package com.demoexoplayer.homeactivity.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demoexoplayer.homeactivity.R
import com.demoexoplayer.homeactivity.model.ApiResponse
import kotlinx.android.synthetic.main.article_layout_row.view.*

class ArticleAdapter(
    val context: Context,
    val articleList: MutableLiveData<ApiResponse>
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_layout_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        if ( articleList.value!!.size>1){

            return  articleList.value!!.size
        }
        else{

            return 0
        }



    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.userImageView)
            .load( articleList.value?.get(position)?.user?.get(0)?.avatar ?:"")
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.itemView.userImageView)




        holder.itemView.userNameTextView.text=
            articleList.value?.get(position)?.user?.get(0)?.name ?: ""

        holder.itemView.userDesignationTextView.text=
            articleList.value?.get(position)?.user?.get(0)?.designation ?: ""


        holder.itemView.articleContentTextView.text=
            articleList.value?.get(position)?.content?: ""

        if(articleList.value?.get(position)?.media?.isNotEmpty()!!){

            holder.itemView.articleTitleTextView.text=
                articleList.value?.get(position)?.media?.get(0)?.title ?: ""



            holder.itemView.articleUrlTextView.text=
                articleList.value?.get(position)?.media?.get(0)?.url ?: ""

            Glide.with(holder.itemView.articleImageView)
                .load( articleList.value?.get(position)?.media?.get(0)?.image ?:"")
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.itemView.articleImageView)

        }
        else{

            holder.itemView.articleTitleTextView.visibility=View.GONE
            holder.itemView.articleUrlTextView.visibility=View.GONE
            holder.itemView.articleImageView.visibility=View.GONE

        }






        holder.itemView.likesTextView.text=
            articleList.value?.get(position)?.likes.toString()+" Likes"

        holder.itemView.commentsTextView.text=
            articleList.value?.get(position)?.comments.toString()+" Comments"










    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}