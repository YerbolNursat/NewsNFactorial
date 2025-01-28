package kz.nfactorial.news.presentation.splashXml

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.nfactorial.news.R
import kz.nfactorial.news.presentation.splashXml.NewsHorizontalAdapter.NewsHorizontalItem


class SplashFragmentXml : Fragment(R.layout.splash_fragment) {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var recyclerView3: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView2 = view.findViewById(R.id.recyclerView2)
        recyclerView3 = view.findViewById(R.id.recyclerView3)
        val adapter = NewsHorizontalAdapter()
        for (i in 0..10) {
            adapter.addNewElement(
                NewsHorizontalItem(
                    title = "12 Articles",
                    subTitle = "Material\n" +
                            "Design"
                )
            )

        }

        recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            this.adapter = adapter
        }
        recyclerView2.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            this.adapter = adapter
        }
        recyclerView3.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    override fun onStart() {
        super.onStart()
    }

}