package com.example.rssfeeder.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rssfeeder.Interface.ItemClickListener
import com.example.rssfeeder.Model.RSSObject
import com.example.rssfeeder.R

class feedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) , View.OnClickListener , View.OnLongClickListener
{

    var txtTitle:TextView
    var txtTime:TextView
    var txtContent:TextView

  private  var itemClickListener: ItemClickListener?=null

    init {
        txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
        txtTime = itemView.findViewById(R.id.txtTime) as TextView
        txtContent = itemView.findViewById(R.id.txtContent) as TextView

        itemView.setOnClickListener (this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
      itemClickListener!!.onClick(v , adapterPosition, false)
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v , adapterPosition, true)
    }

}

class feedAdapter(private val rssObject: RSSObject , private val mContext:Context) :RecyclerView.Adapter<feedViewHolder>()
{
    private val inflater:LayoutInflater
    init {
        inflater = LayoutInflater.from(mContext)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): feedViewHolder {
 val itemView = inflater.inflate(R.layout.row ,p0 , false)
return  feedViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return rssObject.items.size
    }

    override fun onBindViewHolder(holder: feedViewHolder, position: Int) {
       holder.txtTitle.text = rssObject.items[position].title
        holder.txtContent.text = rssObject.items[position].content
        holder.txtTime.text = rssObject.items[position].pubDate
        
        holder.setItemClickListener(ItemClickListener { view, position, isLongClick ->
            if(!isLongClick)
        })
    }

}