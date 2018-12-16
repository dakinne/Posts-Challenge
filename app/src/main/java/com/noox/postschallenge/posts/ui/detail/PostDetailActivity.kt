package com.noox.postschallenge.posts.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.noox.postschallenge.R
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import org.koin.android.ext.android.inject

class PostDetailActivity : AppCompatActivity(), PostDetailView {

    private val presenter: PostDetailPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        initViews()
        initPresenter()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initPresenter() {
        presenter.view = this
        presenter.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showLoading() {
        //loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        //loading.visibility = View.INVISIBLE
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message_loading_posts), Toast.LENGTH_SHORT).show()
    }

    override fun showPost(post: Post) {
        Toast.makeText(this, "show comments ${post.id}", Toast.LENGTH_SHORT).show()
    }

    override fun showComments(comments: List<Comment>) {
        Toast.makeText(this, "show comments ${comments.size}", Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyList() {
        //emptyList.visibility = View.VISIBLE
    }
}
