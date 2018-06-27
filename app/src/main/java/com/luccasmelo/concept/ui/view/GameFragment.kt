package com.luccasmelo.concept.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luccasmelo.concept.R
import com.luccasmelo.concept.ui.base.BaseFragment
import com.luccasmelo.concept.ui.adapter.GameAdapter
import com.luccasmelo.concept.ui.view_model.GameViewModel
import com.luccasmelo.kotlinutils.EndlessScrollListener
import com.luccasmelo.kotlinutils.logE
import android.widget.ImageView
import com.luccasmelo.concept.data.model.Game
import com.luccasmelo.concept.data.model.GameContainer
import com.luccasmelo.concept.databinding.FragmentGameBinding
import com.luccasmelo.concept.utils.Constants


class GameFragment : BaseFragment() {


    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        if (savedInstanceState == null)
            bind()



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun bind() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)


        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        activityComponent.inject(viewModel)

        viewModel.create()
        setupAdapter(viewModel.games.value)
        setupListerner()

        viewModel.games.observe(this, Observer { games ->
            run {
                games?.let {
                    "gamesChange ${it.size}".logE()
                        gameAdapter.update(it)
                    setupListerner()


                }
            }
        })




    }

    private lateinit var gameAdapter: GameAdapter

    private fun setupAdapter(games: ArrayList<GameContainer>?) {

        gameAdapter = GameAdapter(games ?: ArrayList())
        binding.rcvGames.adapter = gameAdapter
        binding.swipeLayout.isRefreshing = false
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvGames.layoutManager = linearLayoutManager

    }

    private fun setupListerner(){
        binding.rcvGames.addOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore() {
                "onLoadMore".logE()
                viewModel.load()
            }
        })

        gameAdapter.itemClickListener = { itemView, pos ->
            run {
                "onItemClick".logE()
                val image = itemView.findViewById<ImageView>(R.id.image)
                val game = viewModel.games.value!![pos].game

                openVideosActivity(image, game)
            }
        }
    }

    private fun openVideosActivity(imageView: ImageView, game: Game) {


        val intent = Intent(activity, GameVideoActivity::class.java)
        intent.putExtra(Constants.GAME_URL_EXTRA, game.box.url)
        intent.putExtra(Constants.GAME_NAME_EXTRA, game.name)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                imageView,
                Constants.SHARED_ELEMENT_ID)
        startActivity(intent, options.toBundle())


    }
}


