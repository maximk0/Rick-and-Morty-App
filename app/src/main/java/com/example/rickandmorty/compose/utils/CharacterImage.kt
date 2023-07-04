package com.example.rickandmorty.compose.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmorty.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    if (data == null)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = contentDescription,
            modifier = modifier,
            alignment = Alignment.Center,
            contentScale = contentScale
        )
    else
        GlideImage(
            model = data,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
}