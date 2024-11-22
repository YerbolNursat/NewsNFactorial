package kz.nfactorial.news.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun ColumnView(item: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(R.drawable.news),
            contentDescription = "newsImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(86.dp)
                .clip(RoundedCornerShape(32.dp))
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(vertical = 2.dp, horizontal = 4.dp),
                text = "Entrepeneur",
                color = Color.LightGray,
                fontSize = 11.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "How to promote business\n" +
                        "right away in instagram",
                color = Color.Black,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600)
            )
            Text(
                text = item.name,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 11.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(500)
            )
        }
    }

}