package com.luccasmelo.concept.ui.view_model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.luccasmelo.concept.data.model.Game
import com.luccasmelo.concept.data.repository.GamesRepository
import com.luccasmelo.kotlinutils.log
import com.luccasmelo.kotlinutils.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class GameViewModel : ViewModel() {
    lateinit var gamesRepository: GamesRepository

    fun create(gamesRepository: GamesRepository) {
        this.gamesRepository = gamesRepository
        val compositeDisposable = CompositeDisposable()
        val disposable = this.gamesRepository.getNextGames()
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ games ->
                    this.games.postValue(games)
                }, {
                    it.logE()
                }, {
                    "Complete".log()
                })

        compositeDisposable.add(disposable)
        compositeDisposable.dispose()
    }

    val games = MutableLiveData<List<Game>>()

}
