package com.example.loodoscase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loodoscase.api.ApiService
import com.example.loodoscase.model.MovieInfoResponse
import com.example.loodoscase.util.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel(){

    var movieInfoResult: MutableLiveData<MovieInfoResponse> = MutableLiveData()
    var errorResult: MutableLiveData<String> = MutableLiveData()
    private val disposable: CompositeDisposable = CompositeDisposable()


    fun getMovieInfo(movieName: String) {
        disposable.add(
            ApiService()
                .apiService
                .getMovieInfo(movieName, Const.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.Response == Const.TRUE){
                        movieInfoResult.postValue(it)
                    }else {
                        errorResult.postValue(Const.FALSE)
                    }
                }, { e ->
                    Log.d("TAG", e.message)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}