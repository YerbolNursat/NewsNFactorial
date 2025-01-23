package kz.nfactorial.news.presentation.main.view

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.nfactorial.news.R
import kz.nfactorial.news.presentation.main.event.MainEvent
import kz.nfactorial.news.presentation.main.model.RowNewsItem
import kz.nfactorial.news.presentation.main.state.ColumnUIState
import kz.nfactorial.news.presentation.main.state.MainState

@Composable
fun MainScreen(
    onEvent: (MainEvent) -> Unit,
    state: State<MainState>
) {
    val timer = remember { mutableIntStateOf(0) }

    LaunchedEffect("OnStart") {
        onEvent(MainEvent.OnGetNews)
    }

    DisposableEffect(state.value.columnData is ColumnUIState.OnGetNews) {
        val scope = MainScope()
        val job = Job()
        scope.launch(job) {
            while (true) {
                delay(1000L)
                timer.value += 1
            }
        }

        onDispose { job.cancel() }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state.value.columnData) {
            is ColumnUIState.OnGetNews -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(top = 56.dp)
                ) {
                    item { SearchView(onEvent, state.value) }
                    item { TopView(state.value.rowData.news) }
                    item { BottomViewHeader() }
                    state.value.columnData.news.forEachIndexed { i, _ ->
                        item { ColumnView(state.value.columnData.news[i]) }
                    }
                }
            }

            is ColumnUIState.OnLoading -> {
                CircularProgressIndicator(Modifier.size(48.dp))
            }
        }

        AnimatedVisibility(timer.intValue > 0) {
            Text(
                "Current working time is ${timer.intValue} seconds",
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(33.dp))
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            SideEffect {
                println("Current working time is ${timer.intValue} seconds")
            }
        }
    }

}

@Composable
private fun SearchView(
    onEvent: (MainEvent) -> Unit,
    state: MainState
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = state.searchText,
        onValueChange = { onEvent(MainEvent.OnSearchTextChange(it)) },
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
            if (isFocused && state.searchText.isNotEmpty())
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .clickable { onEvent(MainEvent.OnSearchTextChange("")) }
                )
        },
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
private fun TopView(list: List<RowNewsItem>) {
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

}
