package com.example.rickandmorty.ui.commonUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.rickandmorty.R

@Composable
fun ErrorColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    loadStateError: LoadState.Error,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        loadStateError.error.localizedMessage?.let {
            Text(
                text = stringResource(id = R.string.error, it),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
        Button(onClick = { onClick() }, modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
