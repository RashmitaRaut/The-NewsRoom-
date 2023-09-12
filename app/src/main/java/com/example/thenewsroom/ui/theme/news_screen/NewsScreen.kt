package com.example.thenewsroom.ui.theme.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thenewsroom.domain.model.Article
import com.example.thenewsroom.ui.theme.component.CategoryTabRow
import com.example.thenewsroom.ui.theme.component.NewsArticleCard
import com.example.thenewsroom.ui.theme.component.NewsScreenTopBar
import com.example.thenewsroom.ui.theme.component.RetryContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent : (NewsScreenEvent) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Entertainment"
    )


    val pagerState = rememberPagerState(pageCount = {categories.size})
    val coroutineScope = rememberCoroutineScope()



    LaunchedEffect(key1 = pagerState){
        snapshotFlow { pagerState.currentPage}.collect{ page ->
            onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[page]))
        }
    }


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { NewsScreenTopBar(
            scrollBehavior = scrollBehavior,
            onSearchIconClicked = {}) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categories = categories,
                onTabSelected = { index ->
                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                }
            )

            HorizontalPager(
                state = pagerState
            ) {
                NewsArticlesList(state =  state,
                    onCardClicked = {},
                    onRetry = {
                        onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                    }
                )
            }

        }
    }
}

// state = Any value that can change during the usage of app
// event = All the possible actions user can perform

@Composable
fun NewsArticlesList(
    state: NewsScreenState,
    onCardClicked : (Article) -> Unit,
    onRetry: () -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.articles) { article ->
            NewsArticleCard(
                article = article,
                onCardClicked = onCardClicked
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
    ){
        if (state.isLoading){
             CircularProgressIndicator()
        }

        if (state.error != null){
            RetryContent(
                error = state.error ,
                onRetry = onRetry)
        }
    }

}