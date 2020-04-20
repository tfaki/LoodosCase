package com.example.loodoscase.ui.activity

import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.loodoscase.R
import com.example.loodoscase.core.BaseActivity
import com.example.loodoscase.helper.load
import com.example.loodoscase.model.MovieInfoResponse
import com.example.loodoscase.util.Const
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var movieInfoResponse: MovieInfoResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupUI()

        backContainer.setOnClickListener { onBackPressed() }

    }

    private fun setupUI() {

        movieInfoResponse =
            intent.getSerializableExtra(Const.MOVIE_INFO_RESPONSE) as MovieInfoResponse

        ivPoster.load(movieInfoResponse.Poster)
        tvTitle.text = "Title : ${movieInfoResponse.Title}"
        tvYear.text = "Year : ${movieInfoResponse.Year}"
        tvRunTime.text = "Runtime : ${movieInfoResponse.Runtime}"
        tvGenre.text = "Genre : ${movieInfoResponse.Genre}"
        tvDirector.text = "Director : ${movieInfoResponse.Director}"
        tvWriter.text = "Writer : ${movieInfoResponse.Writer}"
        tvActors.text = "Actors : ${movieInfoResponse.Actors}"
        tvAwards.text = "Awards : ${movieInfoResponse.Awards}"
        tvPlot.text = "Plot : ${movieInfoResponse.Plot}"
        tvLanguage.text = "Language : ${movieInfoResponse.Language}"
        tvProduction.text = "Production : ${movieInfoResponse.Production}"
    }


    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this)
    }

}