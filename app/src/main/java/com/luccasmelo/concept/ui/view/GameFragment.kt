package com.luccasmelo.concept.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luccasmelo.concept.R
import com.luccasmelo.concept.ui.base.BaseFragment
import com.luccasmelo.concept.data.repository.GamesRepository
//import com.luccasmelo.concept.databinding.GameFragmentBinding
import com.luccasmelo.concept.ui.view_model.GameViewModel
import com.luccasmelo.kotlinutils.log
import javax.inject.Inject

class GameFragment : BaseFragment() {


    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    //private lateinit var binding: GameFragmentBinding

    @Inject
    lateinit var gamesRepository:GamesRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        return inflater.inflate( R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bind()


    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }


    override fun bind() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.games.observe(this, Observer {games -> games.toString().log()})
        viewModel.create(gamesRepository)
        //binding.viewModel = viewModel
       // binding.setLifecycleOwner(this)
    }
}
