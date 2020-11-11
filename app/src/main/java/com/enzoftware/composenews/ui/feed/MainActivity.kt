package com.enzoftware.composenews.ui.feed

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.enzoftware.composenews.domain.model.News
import com.enzoftware.composenews.ui.styles.ComposeNewsTheme
import com.enzoftware.composenews.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNewsTheme {
                NewsFeedScreen(newsViewModel = newsViewModel)
            }
        }
    }
}


@Composable
fun NewsFeedScreen(newsViewModel: NewsViewModel) {
    val observeState = newsViewModel.uiViewStateObservable.observeAsState()
    val uiState = observeState.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ComposeNews") })
        },
        bodyContent = {
            when (uiState) {
                is ViewState.Success -> {
                    NewsFeedContent(news = uiState.result as List<News>, paddingValues = it)
                }
                is ViewState.Error -> {
                    ErrorContent()
                }
                is ViewState.Loading -> {
                    LoadingContent()
                }
            }
        }
    )
}

@Composable
fun LoadingContent() {
    Row(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, children = {
            Text(text = "Something went wrong! :(")
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNewsTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Compose News") }) },
            bodyContent = {
                LoadingContent()
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ComposeNewsTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Compose news") })
            },
            bodyContent = {
                ErrorContent()
            }
        )
    }
}
