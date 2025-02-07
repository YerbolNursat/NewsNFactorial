package kz.nfactorial.news.presentation.main.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kz.nfactorial.news.domain.entity.NewsItemDTO
import kz.nfactorial.news.presentation.main.event.MainEvent
import kz.nfactorial.news.presentation.main.model.ColumnNewsItem
import kz.nfactorial.news.presentation.main.model.RowNewsItem
import kz.nfactorial.news.presentation.main.state.ColumnUIState
import kz.nfactorial.news.presentation.main.state.RowUIState
import kz.nfactorial.news.stubs.GetNewsUseCaseStub
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class MainViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `check dispatch on search text changed`() {
        val viewModel = MainViewModel(getNewsUseCase = mock())

        viewModel.dispatch(MainEvent.OnSearchTextChange("Hello World"))

        assertEquals("Hello World", viewModel.mainState.value.searchText)
    }

    @Test
    fun `check dispatch on get news`() = runTest {
        val getNewsUseCaseStub = GetNewsUseCaseStub(
            rowData = listOf(
                NewsItemDTO(
                    id = 123,
                    image = "rowImage",
                    subTitle = "rowSubTitle",
                    category = "rowCategory",
                    author = "rowAuthor",
                    readTime = "rowReadTime",
                    title = "rowTitle",
                )
            ),
            columnData = listOf(
                NewsItemDTO(
                    id = 123,
                    image = "columnImage",
                    subTitle = "columnSubTitle",
                    category = "columnCategory",
                    author = "columnAuthor",
                    readTime = "columnReadTime",
                    title = "columnTitle",
                )
            )
        )
        val expectedRowUiState = RowUIState.OnGetNews(
            listOf(
                RowNewsItem(
                    imageSrc = "rowImage",
                    title = "rowTitle",
                    subTitle = "rowSubTitle",
                )
            )
        )
        val expectedColumnUiState = ColumnUIState.OnGetNews(
            listOf(
                ColumnNewsItem(
                    category = "columnCategory",
                    author = "columnAuthor",
                    readTime = "columnReadTime",
                    image = "columnImage",
                    title = "columnTitle",
                )
            )
        )
        val viewModel = MainViewModel(
            getNewsUseCase = getNewsUseCaseStub,
        )

        viewModel.dispatch(MainEvent.OnGetNews)

        viewModel.mainState.test {
            val state = awaitItem()
            assertEquals(state.rowData, expectedRowUiState)
            assertEquals(state.columnData, expectedColumnUiState)
        }
    }

    @Test
    fun `check dispatch on loading`() {
        val viewModel = MainViewModel(getNewsUseCase = mock())

        viewModel.dispatch(MainEvent.OnLoading)

        assertEquals(ColumnUIState.OnLoading, viewModel.mainState.value.columnData)
    }
}