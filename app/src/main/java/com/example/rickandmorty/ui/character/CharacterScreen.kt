package com.example.rickandmorty.ui.character

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.Gray120
import com.example.rickandmorty.ui.theme.Gray1200
import com.example.rickandmorty.ui.theme.Gray80
import com.example.rickandmorty.ui.theme.Gray900
import com.example.rickandmorty.viewmodels.CharactersViewModel
import com.example.rickandmorty.ui.commonUi.CharacterImage

const val TAG = "CharacterScreen"
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterScreen(
    viewModel: CharactersViewModel,
    onBackClick: () -> Unit,
    id: String
) {
    viewModel.getCharacter(id)
     val character by viewModel.character.collectAsState()
    Log.d(TAG, "character: ${character.toString()}")
    val episodes by viewModel.listOfEpisodes.collectAsState(listOf())
    LaunchedEffect(key1 = true) {

        viewModel.getEpisodes(character?.episode)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray1200)
    ) {
        Scaffold(
            backgroundColor = Gray1200,
            topBar = {
                CharacterTopBar(
                    onUpClick = onBackClick,
                    text = character?.name ?: stringResource(id = R.string.character)
                )
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, start = 30.dp, end = 30.dp)
            ) {
                item {
                    CharacterImage(
                        data = character?.image,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = character?.name ?: stringResource(id = R.string.empty_string),
                        lineHeight = 35.sp,
                        fontSize = 35.sp,
                        color = Gray80,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Divider(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        color = Gray80,
                        thickness = 1.dp
                    )

                    GrayDetailText(stringResource(id = R.string.live_status))
                    InfoDetailText("${character?.status}")
                    GrayDetailText(stringResource(id = R.string.species_gender))
                    InfoDetailText("${character?.species} (${character?.gender})")
                    GrayDetailText(stringResource(id = R.string.last_location))
                    InfoDetailText(character?.location?.name ?: stringResource(id = R.string.empty_string))

                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        text = stringResource(id = R.string.episodes),
                        fontSize = 35.sp,
                        color = Gray80,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                itemsIndexed(episodes) { _, item ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Gray900),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, top = 10.dp)
                    ) {

                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    fontSize = 20.sp,
                                    text = item.name,
                                    color = Gray80,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .requiredWidth(200.dp)
                                )

                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    text = item.episode,
                                    color = Gray120
                                )
                            }

                            Row {
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    text = item.airDate,
                                    color = Gray120
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun CharacterTopBar(
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    TopAppBar(
        title = {
            Text(text = text)
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = null
                )
            }
        },
    )
}

@Composable
fun InfoDetailText(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        color = Gray80
    )
}

@Composable
fun GrayDetailText(title: String) {
    Text(
        modifier = Modifier.padding(top = 30.dp),
        text = title,
        fontSize = 20.sp,
        color = Gray120
    )
}
