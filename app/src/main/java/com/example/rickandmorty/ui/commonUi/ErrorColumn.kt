package com.example.rickandmorty.ui.commonUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
            Text(text = stringResource(id = R.string.error, it))
        }
        Button(onClick = { onClick() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
