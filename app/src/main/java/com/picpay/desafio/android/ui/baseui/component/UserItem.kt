package com.picpay.desafio.android.ui.baseui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.baseui.theme.PicPayTheme

private val imageModifier = Modifier
    .padding(start = 16.dp)
    .clip(CircleShape)
    .size(52.dp)

@Composable
fun AsyncUserItem(
    name: String,
    username: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeHolderResource: Int = R.drawable.ic_round_account_circle
) {
    UserItem(
        name = name,
        username = username,
        image = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(placeHolderResource),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
        },
        modifier = modifier
    )
}

@Composable
private fun UserItem(
    name: String,
    username: String,
    image: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(MaterialTheme.colors.background)
    ) {
        image()
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = username,
                fontSize = 14.sp,
                color = Color(0x80FFFFFF)
            )
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun Preview() {
    PicPayTheme {
        AsyncUserItem(
            "Armandinho",
            "Armando Barraco",
            ""
        )
    }
}