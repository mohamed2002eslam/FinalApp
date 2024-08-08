package com.example.bottomnav.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bottomnav.fullImageURL
import com.example.bottomnav.viewModel.MoviesViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(modifier: Modifier=Modifier, viewModel: MoviesViewModel= hiltViewModel()){
    val moviesState by viewModel.moviesState

    LazyColumn(modifier = modifier
        .padding(8.dp)) {
        items(moviesState?.results?: emptyList()){movie->
            Column(modifier = Modifier
                .padding(8.dp)
                .fillParentMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
                .border(BorderStroke(1.dp, color = Color.Black), RoundedCornerShape(8.dp))
                .padding(bottom = 8.dp)
            ){
                GlideImage(modifier = Modifier

                    .fillMaxWidth()
                    .height(250.dp),model = movie.posterPath.fullImageURL(), contentDescription =null,
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    text = movie.title ?: "TV-Name",
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = movie.overview ?: "overview",
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

            }

        }
    }
}
@Composable
@Preview
fun preHome(){
    HomeScreen()
}