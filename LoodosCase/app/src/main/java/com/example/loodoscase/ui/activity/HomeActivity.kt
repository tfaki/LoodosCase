package com.example.loodoscase.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loodoscase.R
import com.example.loodoscase.core.BaseActivity
import com.example.loodoscase.helper.AlertPattern
import com.example.loodoscase.helper.AlertTypeState
import com.example.loodoscase.helper.hideKeyboard
import com.example.loodoscase.model.MovieInfoResponse
import com.example.loodoscase.ui.adapter.MovieInfoAdapter
import com.example.loodoscase.ui.interfaces.ItemClickListener
import com.example.loodoscase.ui.viewmodel.HomeViewModel
import com.example.loodoscase.util.Const
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private var movieInfoList = ArrayList<MovieInfoResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = HomeViewModel()
        rvMovieInfo.layoutManager = LinearLayoutManager(this)

        homeViewModel.movieInfoResult.observe(this, Observer {
            hideLoading()
            movieInfoList.clear()
            movieInfoList.add(it)
            rvMovieInfo.adapter = MovieInfoAdapter(movieInfoList, object : ItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    val intent = Intent(this@HomeActivity, MovieDetailActivity::class.java)
                    intent.putExtra(Const.MOVIE_INFO_RESPONSE, movieInfoList[position])
                    val transition = getString(R.string.transition)
                    val options: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@HomeActivity,
                            v,
                            transition
                        )
                    ActivityCompat.startActivity(this@HomeActivity, intent, options.toBundle())
                }
            })
        })

        homeViewModel.errorResult.observe(this, Observer {
            hideLoading()
            AlertPattern.Builder(this)
                .alertType(AlertTypeState.WARNING)
                .alertMessage(getString(R.string.no_result))
                .build()
        })


        btnFindMovie.setOnClickListener {
            val movieName = etFindMovie.text.toString()
            if (movieName.isNullOrEmpty()) {
                AlertPattern.Builder(this)
                    .alertType(AlertTypeState.WARNING)
                    .alertMessage(getString(R.string.should_movie_name))
                    .build()
            } else {
                showLoading()
                hideKeyboard()
                homeViewModel.getMovieInfo(movieName)
            }
        }

    }

}
