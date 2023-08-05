package com.deny.myanimes.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.deny.myanimes.components.SearchBar
import com.deny.myanimes.data.models.AnimeModel
import com.deny.myanimes.data.models.DataModel
import com.deny.myanimes.data.viewmodels.MyAnimesViewModel
import com.deny.myanimes.navigation.DETAILS_NAV_TAG
import com.deny.myanimes.utils.Base64Custom
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(navController: NavController, animesViewModel: MyAnimesViewModel = koinViewModel()) {

    val dataExample = animesViewModel.animes.observeAsState()
    val searchQuery = remember { mutableStateOf("") }
    val animes = remember { mutableStateOf(AnimeModel()) }
    val page = remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        animesViewModel.getAnimes(page.value.toString())
    }

    dataExample.value?.let { value ->
        animes.value = value
    }

    animes.value.data?.let { list ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                SearchBar(
                    query = searchQuery.value,
                    onQueryChange = { newQuery ->
                        searchQuery.value = newQuery
                    },
                    onSearchClick = {
                        // Perform search here
                        animesViewModel.getAnimesByName(searchQuery.value)
                    }
                )
                LazyColumn {
                    items(list.toList()) { item ->
                        CardHome(navController = navController, item = item)
                    }
                }
            }
            FloatingActionButton(
                onClick = {
                    page.value++
                    animesViewModel.getAnimes(page.value.toString())
                },
                contentColor = Color.Red,
                modifier = Modifier
                    .padding(end = 24.dp, bottom = 24.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Filled.ArrowForward, "Localized description")
            }
            FloatingActionButton(
                onClick = {},
                contentColor = Color.Red,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = page.value.toString())
            }
            FloatingActionButton(
                onClick = {
                    if (page.value > 1) {
                        page.value--
                        animesViewModel.getAnimes(page.value.toString())
                    }
                },
                contentColor = Color.Red,
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 24.dp)
                    .align(Alignment.BottomStart)
            ) {
                Icon(Icons.Filled.ArrowBack, "Localized description")
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardHome(navController: NavController, item: DataModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (navController != null) {
                    val auxForNull = "1234"
                    navController.navigate(
                        route = DETAILS_NAV_TAG + "/${item.title}/${item.synopsis}/${item.trailer?.youtubeId ?: auxForNull}/${
                            Base64Custom().codificarBase64(
                                item.images?.jpg?.imageUrl.toString()
                            )
                        }"
                    )
                }
            }
    ) {
        GlideImage(
            model = item.images?.jpg?.imageUrl,
            contentDescription = "teste",
            modifier = Modifier.padding(8.dp),
        )
        Column {
            Text(
                text = item.title.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = item.synopsis.toString().substring(0, if (item.synopsis.toString().length >= 100) 100 else if (item.synopsis.toString().length >= 20) 20 else 0)+"...",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}