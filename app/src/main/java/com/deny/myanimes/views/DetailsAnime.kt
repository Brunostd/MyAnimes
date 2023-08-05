package com.deny.myanimes.views

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.deny.myanimes.components.YoutubeScreen2
import com.deny.myanimes.navigation.DETAILS_ARGUMENT_KEY1
import com.deny.myanimes.navigation.DETAILS_ARGUMENT_KEY2
import com.deny.myanimes.navigation.DETAILS_ARGUMENT_KEY3
import com.deny.myanimes.navigation.DETAILS_ARGUMENT_KEY4
import com.deny.myanimes.utils.Base64Custom

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsAnime(navController: NavController, arguments: Bundle?) {

    val title = arguments?.getString(DETAILS_ARGUMENT_KEY1)
    val synopsis = arguments?.getString(DETAILS_ARGUMENT_KEY2)
    val youtubeId = arguments?.getString(DETAILS_ARGUMENT_KEY3)
    val image = arguments?.getString(DETAILS_ARGUMENT_KEY4)


    when {
        title != null && synopsis != null && youtubeId != null && image != null -> {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "arrowBack"
                    )
                    Text(
                        text = "VOLTAR",
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    GlideImage(
                        model = Base64Custom().decodificarBase64(image),
                        contentDescription = "teste",
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentHeight()
                    )
                }
                YoutubeScreen2(videoId = youtubeId)
                Text(
                    text = "Synopsis",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Text(
                    text = synopsis,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailsAnimePreview() {
    DetailsAnime(navController = rememberNavController(), arguments = null)
}