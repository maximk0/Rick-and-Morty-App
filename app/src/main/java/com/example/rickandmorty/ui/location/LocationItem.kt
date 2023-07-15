package com.example.rickandmorty.ui.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.Gray120
import com.example.rickandmorty.ui.theme.Gray900
import com.example.rickandmorty.ui.theme.PurpleGrey80
import com.example.rickandmorty.ui.charactersList.InfoText
import com.example.rickandmorty.data.network.models.LocationsDto

@Composable
fun LocationItem(location: LocationsDto) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Gray900),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = location.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = PurpleGrey80
                )
                InfoText( stringResource(id = R.string.type, location.type))
                InfoText( stringResource(id = R.string.dimension, location.dimension))
                Text(
                    text =  stringResource(id = R.string.created, location.created),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray120
                )
            }
        }
    }
}
