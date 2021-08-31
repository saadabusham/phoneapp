package com.raantech.awfrlak.utils.extensions

import androidx.databinding.BindingAdapter
import com.raantech.awfrlak.R
import com.raantech.awfrlak.common.CommonEnums
import com.raantech.awfrlak.utils.LocaleUtil
import com.skydoves.powerspinner.PowerSpinnerView
import com.skydoves.powerspinner.SpinnerGravity

@BindingAdapter("arrow_gravity")
fun PowerSpinnerView.setArrowGravity(boolean: Boolean) {
   this.arrowGravity = if(LocaleUtil.getLanguage() == CommonEnums.Languages.Arabic.value) SpinnerGravity.START else SpinnerGravity.END
   this.arrowTint = resources.getColor(R.color.circle_image_stroke_color)
}

