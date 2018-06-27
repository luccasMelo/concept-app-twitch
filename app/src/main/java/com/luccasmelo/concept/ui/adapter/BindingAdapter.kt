package com.luccasmelo.concept.ui.adapter

import android.databinding.BindingAdapter
import android.databinding.InverseBindingListener
import android.databinding.adapters.ListenerUtil
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.luccasmelo.concept.R
import com.luccasmelo.concept.utils.Constants
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {

    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.color.primary)

    Glide.with(imageView.context)

            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

}

@BindingAdapter("icon")
fun setIcon(imageView: ImageView, iconIdentifaier: String?) {
    val iconicsDrawable = IconicsDrawable(imageView.context)
    imageView.background =
            if (iconIdentifaier == Constants.ICON_VIEWER) iconicsDrawable.icon(GoogleMaterial.Icon.gmd_visibility)
            else {
                iconicsDrawable.icon(GoogleMaterial.Icon.gmd_computer)
            }
                    .color(Color.GRAY)
                    .sizeDp(20)
}

@BindingAdapter(value = ["onRefreshListener", "refreshingAttrChanged"], requireAll = false)
fun setOnRefreshListener(view: SwipeRefreshLayout,
                         listener: SwipeRefreshLayout.OnRefreshListener?,
                         refreshingAttrChanged: InverseBindingListener?) {

    val newValue = SwipeRefreshLayout.OnRefreshListener {
        if (listener != null) {
            refreshingAttrChanged?.onChange()
            listener.onRefresh()
        }
    }

    val oldValue = ListenerUtil.trackListener<SwipeRefreshLayout.OnRefreshListener>(view, newValue, R.id.onRefreshListener)
    if (oldValue != null) {
        view.setOnRefreshListener(null)
    }
    view.setOnRefreshListener(newValue)
}

@BindingAdapter("android:refreshing")
fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
    if (refreshing != view.isRefreshing) {
        view.isRefreshing = refreshing
    }
}