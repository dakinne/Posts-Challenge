package com.noox.postschallenge.posts.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.noox.postschallenge.R
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import org.koin.android.ext.android.inject

import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity(), PostDetailView {

    companion object {
        const val EXTRA_POST = "POST"
    }

    private val presenter: PostDetailPresenter by inject()
    private var adapter: PostDetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val post: Post = intent.getParcelableExtra(PostDetailActivity.EXTRA_POST)

        initViews()
        initPresenter(post)
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initPresenter(post: Post) {
        presenter.view = this
        presenter.init(post)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message_loading_posts), Toast.LENGTH_SHORT).show()
    }

    override fun showPost(post: Post) {
        adapter = PostDetailAdapter(post)
        postItemsList.adapter = adapter
    }

    override fun showComments(comments: List<Comment>) {
        adapter?.addComments(comments)
    }
}
