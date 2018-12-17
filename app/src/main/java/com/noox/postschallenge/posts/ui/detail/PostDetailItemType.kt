package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post

sealed class PostDetailItemType(val itemType: Int) {
    companion object {
        const val POST = 0
        const val COMMENT = 1
        const val LOADING = 2
    }
}

class PostItemType(val post: Post): PostDetailItemType(POST)
class CommentItemType(val comment: Comment): PostDetailItemType(COMMENT)
class LoadingItemType: PostDetailItemType(LOADING)
