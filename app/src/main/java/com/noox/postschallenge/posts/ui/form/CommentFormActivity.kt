package com.noox.postschallenge.posts.ui.form

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.noox.postschallenge.R
import com.noox.postschallenge.common.extensions.doAfterTextChanged
import hideKeyboard
import kotlinx.android.synthetic.main.activity_comment_form.*
import org.koin.android.ext.android.inject

class CommentFormActivity : AppCompatActivity(), CommentFormView {

    companion object {
        const val EXTRA_POST_ID = "POST_ID"
    }

    private val presenter: CommentFormPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_form)

        val postId: Int = intent.getIntExtra(CommentFormActivity.EXTRA_POST_ID, -1)

        initViews()
        initPresenter(postId)
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        name.doAfterTextChanged { commentDataChanged() }
        message.doAfterTextChanged { commentDataChanged() }

        publish.setOnClickListener {
            publishComment()
            hideKeyboard()
        }
    }

    private fun commentDataChanged() {
        presenter.commentDataChanged(name.text.toString(), message.text.toString())
    }

    private fun publishComment() {
        presenter.publishComment(name.text.toString(), message.text.toString())
    }

    private fun initPresenter(postId: Int) {
        presenter.view = this
        presenter.init(postId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun enablePublishButton(bool: Boolean) {
        publish.isEnabled = bool
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message_publishing_comment), Toast.LENGTH_SHORT).show()
    }

    override fun close() {
        finish()
    }
}
