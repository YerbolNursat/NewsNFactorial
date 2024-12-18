package kz.nfactorial.news.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.nfactorial.news.R
import kz.nfactorial.news.main.MainState

@Composable
fun SplashScreen(
    onEvent: (SplashEvent) -> Unit,
) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    top = 40.dp,
                    bottom = 44.dp
                )
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "title",
                textAlign = TextAlign.Center,
                color = Color(0xFF08080A),
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                lineHeight = 48.sp,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = "subtitle",
                textAlign = TextAlign.Center,
                color = Color(0xFF08080A),
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                lineHeight = 48.sp,
                modifier = Modifier.padding(top = 32.dp)
            )

            Image(
                painter = painterResource(R.drawable.news),
                contentDescription = "newsImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(32.dp))

            )

            Text(
                text = stringResource(R.string.open_your_knowledge_power),
                textAlign = TextAlign.Center,
                color = Color(0xFF08080A),
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                lineHeight = 48.sp,
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = stringResource(R.string.motivation_can_take_you_far_but_it_can_take_you_even_futher_if_you_first_find_your_vision),
                textAlign = TextAlign.Center,
                color = Color(0xFF828282),
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                lineHeight = 28.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .height(2.dp)
            )

            Spacer(Modifier.weight(1f))



            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                onClick = {
                    onEvent(SplashEvent.OnClickToMain(ActionArgs("We are from Splash Fragment")))
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = Color(0xFF08080A),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
                Text(
                    text = stringResource(R.string.get_started),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                )
            }
        }
}


@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
}