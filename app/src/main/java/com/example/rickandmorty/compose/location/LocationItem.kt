package com.example.rickandmorty.compose.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.compose.theme.Gray120
import com.example.rickandmorty.compose.theme.Gray900
import com.example.rickandmorty.compose.theme.PurpleGrey80
import com.example.rickandmorty.compose.characterlist.InfoText
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
                InfoText("Type: ${location.type}")
                InfoText("Dimension: ${location.dimension}")
                Text(
                    text = "Created: ${location.created}",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray120
                )
            }
        }
    }
}
