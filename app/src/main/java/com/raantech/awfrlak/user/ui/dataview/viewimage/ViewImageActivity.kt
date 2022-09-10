package com.raantech.awfrlak.user.ui.dataview.viewimage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.raantech.awfrlak.R
import com.raantech.awfrlak.user.data.common.Constants
import com.raantech.awfrlak.databinding.ActivityViewImageBinding
import com.raantech.awfrlak.user.ui.base.activity.BaseBindingActivity
import com.raantech.awfrlak.user.ui.base.bindingadapters.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewImageActivity : BaseBindingActivity<ActivityViewImageBinding>() {

    companion object {
        fun start(
            context: Activity,
            item: String,
            image: View
        ) {

            val p1: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(
                image,
                image.transitionName
            )
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                p1
            )
            val intent = Intent(context, ViewImageActivity::class.java)
            intent.putExtra(Constants.BundleData.IMAGE, item)
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_view_image,
            hasToolbar = true,
            hasBackButton = true,
            showBackArrow = true,
            hasTitle = false
        )
        supportPostponeEnterTransition()
        intent.getStringExtra(Constants.BundleData.IMAGE)?.let {
            binding?.imgPicture?.loadImage(it) {
                supportStartPostponedEnterTransition()
            }
        }
    }

}