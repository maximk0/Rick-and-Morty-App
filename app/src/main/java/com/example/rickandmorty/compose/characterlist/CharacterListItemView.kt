package com.example.rickandmorty.compose.characterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.compose.theme.Gray120
import com.example.rickandmorty.compose.theme.Gray80
import com.example.rickandmorty.compose.theme.Gray900
import com.example.rickandmorty.compose.theme.PurpleGrey80
import com.example.rickandmorty.data.network.models.Result
import com.example.rickandmorty.viewmodels.ChatactersModel
import com.example.rickandmorty.compose.utils.CharacterImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    viewModel: ChatactersModel =  hiltViewModel(),
    character: Result,
    onCharacterItemClicked: (Result) -> Unit,
) {
    Card(
        onClick = {
            onCharacterItemClicked(character)
            viewModel.character = character
                  },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Gray900),
        modifier = Modifier
            .padding(5.dp)
            .height(150.dp)
    ) {
        val statusColor = when (character.status) {
            "Alive" -> Color.Green
            "Dead" -> Color.Red
            else -> Color.Gray
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CharacterImage(
                data = character.image,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = character.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    color = PurpleGrey80
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                        contentDescription = "Status image",
                        colorFilter = ColorFilter.tint(color = statusColor)
                    )
                    InfoText(
                        text = "${character.status} - ${character.species}",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Text(
                    text = "Last location:",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray120
                )
                InfoText(character.location.name)
            }
        }
    }
}

@Composable
fun InfoText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Gray80
    )
}
