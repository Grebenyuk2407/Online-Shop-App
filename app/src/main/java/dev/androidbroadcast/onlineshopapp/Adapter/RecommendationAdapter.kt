package dev.androidbroadcast.onlineshopapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.androidbroadcast.onlineshopapp.Model.ItemsModel
import dev.androidbroadcast.onlineshopapp.databinding.ViewholderRecommendBinding

class RecommendationAdapter(val items:MutableList<ItemsModel>):RecyclerView.Adapter<RecommendationAdapter.Viewholder>() {

    private var context:Context?=null

    class Viewholder(val binding:ViewholderRecommendBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        context=parent.context
        val binding= ViewholderRecommendBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
        holder.binding.ratingTxt.text=items[position].rating.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(RequestOptions.centerCropTransform())
            .into(holder.binding.pic)
    }

    override fun getItemCount(): Int = items.size
}