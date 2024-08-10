package dev.androidbroadcast.onlineshopapp.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.project1762.Helper.ManagmentCart
import dev.androidbroadcast.onlineshopapp.Adapter.ColorAdapter
import dev.androidbroadcast.onlineshopapp.Adapter.SliderAdapter
import dev.androidbroadcast.onlineshopapp.Model.ItemsModel
import dev.androidbroadcast.onlineshopapp.Model.SliderModel
import dev.androidbroadcast.onlineshopapp.R
import dev.androidbroadcast.onlineshopapp.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private val numberOrder = 1
    private lateinit var managementCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart=ManagmentCart(this)

        getBundle()
        Banners()
        initColorList()
    }

    private fun initColorList() {
        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

    }

    private fun Banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl ){
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = false
        binding.slider.clipChildren = false
        binding.slider.offscreenPageLimit = 1
        binding.slider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositionPageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.slider.setPageTransformer(compositionPageTransformer)
        if (sliderItems.size>1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$"+item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managementCart.insertItem(item)
        }

        binding.backBtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {

        }
    }
}