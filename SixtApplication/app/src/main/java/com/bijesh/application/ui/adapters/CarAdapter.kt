package com.bijesh.application.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bijesh.application.R
import com.bijesh.application.models.CarsItem
import com.bumptech.glide.Glide

class CarAdapter : RecyclerView.Adapter<CarViewHolder>() {

    var carList = mutableListOf<CarsItem>()
    fun setCars(users: List<CarsItem>) {
        this.carList = users.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        Glide.with(holder.itemView.context).load(car.carImageUrl).placeholder(R.drawable.car_place_holder)
            .fitCenter().centerCrop().into(holder.carImage)
        holder.carName.text = car.modelName
        holder.carInfo1.text = car.series
        holder.carInfo2.text = car.make
    }
}

class CarViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val carName : TextView = view.findViewById(R.id.card_view_image_title)
    val carImage : ImageView = view.findViewById(R.id.card_view_image)
    val carInfo1 : TextView = view.findViewById(R.id.info1)
    var carInfo2 : TextView = view.findViewById(R.id.info2)

}