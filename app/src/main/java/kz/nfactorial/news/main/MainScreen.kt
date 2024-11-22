package kz.nfactorial.news.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.nfactorial.news.R

data class NewsItem(
    val name: String
)

@Composable
fun MainScreen(text: String) {
    var list by remember { mutableStateOf<List<NewsItem>>(listOf(NewsItem("BBC NEWS"))) }

    var inputText = remember { mutableStateOf("") }
    var checked = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(top = 56.dp)
        ) {
            item { Text(text) }
            item { Switch(checked = checked.value, onCheckedChange = { checked.value = it }) }
            item { SearchView(inputText) }
            item { TopView(list) }
            item { TopView(list) }
            item { TopView(list) }
            item { BottomViewHeader() }
            item { BottomViewHeader() }
            item { BottomViewHeader() }
            item { BottomViewHeader() }
            list.forEachIndexed { i, _ ->
                item { ColumnView(list[i]) }
            }
//            items(list.size) { key -> }
        }

        FloatingActionButton(
            onClick = {
                if (inputText.value.isNotEmpty())
                    list = list + NewsItem(inputText.value)
            },
            modifier = Modifier
                .padding(24.dp)
                .size(64.dp)
                .background(color = Color.Red, shape = CircleShape)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "add content",
                modifier = Modifier.size(24.dp)
            )
        }

    }

}

@Composable
private fun SearchView(inputText: MutableState<String>) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = inputText.value,
        onValueChange = { inputText.value = it },
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .padding(all = 16.dp)
            .onFocusChanged { isFocused = it.isFocused }
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        trailingIcon = {
            if (isFocused && inputText.value.isNotEmpty())
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .clickable { inputText.value = "" }
                )
        },
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
private fun TopView(list: List<NewsItem>) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Popular List",
            color = Color.Black,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "See All",
            color = Color.LightGray,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight(500)
        )
    }

    LazyRow {
        items(list.size) { key ->
            RowView(list[key])
        }
    }
}

@Composable
private fun BottomViewHeader() {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Reading List",
            color = Color.Black,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "See All",
            color = Color.LightGray,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight(500)
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen("")
}
