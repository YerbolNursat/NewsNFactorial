package kz.nfactorial.news.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.nfactorial.news.R

@Composable
internal fun RowView(item: NewsItem) {
    Column(modifier = Modifier.padding(24.dp)) {
        Image(
            painter = painterResource(R.drawable.news),
            contentDescription = "newsImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(130.dp)
                .height(164.dp)
                .clip(RoundedCornerShape(32.dp))
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "12 Articles",
            color = Color.LightGray,
            fontSize = 14.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(500)
        )
        Text(
            text = item.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(600)
        )
    }
}