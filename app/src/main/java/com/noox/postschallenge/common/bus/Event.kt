package com.noox.postschallenge.common.bus

import com.noox.postschallenge.posts.domain.model.Comment

sealed class Event {
    data class CommentPublished(val comment: Comment): Event()
}