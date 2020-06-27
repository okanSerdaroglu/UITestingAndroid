package com.example.espressouitesting.data.source

import com.example.espressouitesting.data.DummyMovies.INFINITY_WAR
import com.example.espressouitesting.data.DummyMovies.THE_RUNDOWN
import com.example.espressouitesting.data.Movie


class MoviesRemoteDataSource : MoviesDataSource {

    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, Movie>(2)

    init {
        addMovie(INFINITY_WAR)
        addMovie(THE_RUNDOWN)
    }

    override fun getMovie(movieId: Int): Movie? {
        return MOVIES_REMOTE_DATA[movieId]
    }

    private fun addMovie(movie: Movie) {
        MOVIES_REMOTE_DATA[movie.id] = movie
    }

    private fun addMovie(
        id: Int,
        title: String,
        image: String,
        description: String,
        directors: ArrayList<String>?,
        star_actors: ArrayList<String>?
    ) {
        val movie = Movie(
            id = id,
            title = title,
            image = image,
            description = description,
            directors = directors,
            star_actors = star_actors
        )
        MOVIES_REMOTE_DATA[id] = movie
    }


}