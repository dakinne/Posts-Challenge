package com.noox.postschallenge.posts.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.noox.postschallenge.R
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import com.noox.postschallenge.posts.ui.form.CommentFormActivity
import kotlinx.android.synthetic.main.activity_post_detail.*
import org.koin.android.ext.android.inject

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

        initViews(post)
        initPresenter(post)
    }

    private fun initViews(post: Post) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            val intent = Intent(this, CommentFormActivity::class.java)
            intent.putExtra(CommentFormActivity.EXTRA_POST_ID, post.id)
            startActivity(intent)
        }
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

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
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

    override fun showNewComment(comment: Comment) {
        adapter?.addComment(comment)
    }

    override fun showNewCommentPublished() {
        Toast.makeText(this, getString(R.string.post_detail_new_comment_published), Toast.LENGTH_LONG).show()
    }
}
