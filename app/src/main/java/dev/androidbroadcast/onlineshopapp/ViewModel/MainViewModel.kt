package dev.androidbroadcast.onlineshopapp.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.androidbroadcast.onlineshopapp.Model.SliderModel

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()

    val banner: MutableLiveData<List<SliderModel>> = _banner

    fun loadBanner() {

        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<SliderModel>()
                for (child in snapshot.children) {
                    val data = child.getValue(SliderModel::class.java)
                    if (data!=null) {
                        list.add(data)
                    }
                }
                _banner.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}