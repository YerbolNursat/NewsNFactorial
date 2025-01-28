package kz.nfactorial.news.presentation.splashXml

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.nfactorial.news.presentation.splashXml.NewsHorizontalAdapter.NewsHorizontalViewHolder
import kz.nfactorial.news.R


class NewsHorizontalAdapter : RecyclerView.Adapter<NewsHorizontalViewHolder>() {

    private val items = mutableListOf<NewsHorizontalItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsHorizontalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)

        return NewsHorizontalViewHolder(view)
    }


    override fun onBindViewHolder(holder: NewsHorizontalViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun addNewElement(item: NewsHorizontalItem){
        items.add(item)
        notifyDataSetChanged()
    }

    inner class NewsHorizontalViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val titleTv = view.findViewById<TextView>(R.id.title_tv)
        val subTitleTv = view.findViewById<TextView>(R.id.subTitle_tv)


        fun onBind(
            item: NewsHorizontalItem
        ) {
            titleTv.text = item.title
            subTitleTv.text = item.subTitle
        }
    }

    data class NewsHorizontalItem(
        val title: String,
        val subTitle: String
    )
}