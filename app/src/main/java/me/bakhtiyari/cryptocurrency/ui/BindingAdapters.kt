package me.bakhtiyari.cryptocurrency.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import me.bakhtiyari.cryptocurrency.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun loadImageUrl(view: ImageView, imgUrl: String?) {
        if (imgUrl.isNullOrEmpty())
            return
        Glide.with(view.context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.background_gradiant)
                .error(R.drawable.ic_image_broken)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
        ).load(imgUrl).into(view)
    }

    @JvmStatic
    @BindingAdapter("changeVisibility")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}