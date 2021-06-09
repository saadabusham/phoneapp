package com.technzone.baseapp.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel(){


    var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

}