package com.enzoftware.composenews.ui.feed

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.enzoftware.composenews.domain.model.News
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

@Composable
fun NewsFeedContent(news: List<News>, paddingValues: PaddingValues) {
    LazyColumnFor(items = news) { item ->
        CardLayout(news = item)
    }
}

@Composable
fun CardLayout(news: News) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {

        Row {
            Box {
//                CoilImageWithCrossfade(
//                    request = ImageRequest.Builder(ContextAmbient.current).data(news.image)
//                        .transformations(CircleCropTransformation()).build(),
//                    modifier = Modifier.padding(16.dp),
//                    contentScale = ContentScale.FillHeight
//                )
            }
            CardBodyContent(news = news)
        }
    }
}

@Composable
fun CardBodyContent(news: News) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = news.title)
    }
}

@Composable
fun categoryColor(category: String): Color {
    return when (category) {
        "Politics" -> Color.Blue
        else -> Color.White
    }
}