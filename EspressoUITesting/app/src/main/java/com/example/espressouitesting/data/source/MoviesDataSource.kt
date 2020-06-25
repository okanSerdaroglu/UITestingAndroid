package com.example.espressouitesting.data.source

import com.example.espressouitesting.data.Movie


interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}