package com.tarapogancev.pushapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("imageSource")
fun bindImage(imgView: ImageView, imgUrl: LiveData<Int>) {
    imgView.setImageResource(imgUrl.value!!)
}
