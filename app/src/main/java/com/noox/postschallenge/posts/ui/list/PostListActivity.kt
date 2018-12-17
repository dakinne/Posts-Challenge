package com.noox.postschallenge.posts.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.noox.postschallenge.R
import com.noox.postschallenge.posts.domain.model.Post
import com.noox.postschallenge.posts.ui.detail.PostDetailActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import org.koin.android.ext.android.inject


class PostListActivity : AppCompatActivity(), PostListView {

    private val presenter: PostListPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        initViews()
        initPresenter()
    }

    private fun initViews() {
        postList.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }

    private fun initPresenter() {
        presenter.view = this
        presenter.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message_loading_posts), Toast.LENGTH_SHORT).show()
    }

    override fun showPosts(posts: List<Post>) {
        postList.adapter = PostListAdapter(posts) {
            val intent = Intent(this, PostDetailActivity::class.java)
            intent.putExtra(PostDetailActivity.EXTRA_POST, it)
            startActivity(intent)
        }
    }

    override fun showEmptyList() {
        emptyList.visibility = View.VISIBLE
    }
}
