package com.luccasmelo.concept.ui.view_model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.luccasmelo.concept.data.model.GameContainer
import com.luccasmelo.concept.data.repository.GamesRepository
import com.luccasmelo.kotlinutils.logE
import com.luccasmelo.kotlinutils.showConfirm
import com.luccasmelo.kotlinutils.showError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GameViewModel : ViewModel() {
    @Inject
    lateinit var gamesRepository: GamesRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable



    val games = MutableLiveData<ArrayList<GameContainer>>()

    val refreshing = MutableLiveData<Boolean>()


    fun create() {
        if (games.value == null) {
            games.postValue(ArrayList())
            load()
        }
    }

    fun load(reload: Boolean = false) {
        val disposable = this.gamesRepository.getNextGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    run {
                        "load".logE()
                        refreshing.postValue(false)

                        val arrayListFinal = ArrayList<GameContainer>()
                        val arrayListGames = ArrayList(response.top)

                        if (games.value != null && !reload)
                            arrayListFinal concat games.value!!
                        arrayListFinal concat arrayListGames
                        games.postValue(arrayListFinal)
                    }

                }, { error ->
                    error.localizedMessage.logE()
                })
        compositeDisposable.addAll(disposable)
    }

    fun reload() {
        "reload".logE()
        this.gamesRepository.reload()
        load(true)

    }

    override fun onCleared() {
        "onCleared".logE()
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }


}

infix fun ArrayList<GameContainer>.concat(arrayList: ArrayList<GameContainer>) = this.addAll(arrayList)



