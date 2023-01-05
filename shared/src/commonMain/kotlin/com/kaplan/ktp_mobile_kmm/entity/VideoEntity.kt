package com.kaplan.ktp_mobile_kmm.entity

class VideoEntity {

    data class Videochapters(
        val pos: Int,
        val videoId: String,
        val topicTime: Int,
        val topicName: String,
        val status: String,
        val qualityScore: String,
        var isCurrentText: Int = 0,
        var endTime: Int = 0
    )

    data class Videolibrary(
        val title: String,
        val recommendedTime: Int,
        val activityType: String,
        val name: String,
        val description: String,
        val source: String,
        val url: String,
        val isLearningPathActivity: Int,
        val sequenceId: String
    )

    data class VideoLibraryWithVideoStatus(
        val id: String,
        val name: String,
        val source:String,
        val title: String,
        val description: String,
        val status: String,
        val recommendedTime: Int,
        val activityType: String,
        val isTestedOut: Int,
        var sequenceId: String
    )

    data class Videostatus(
        val id: String,
        val name: String,
        val status: String,
        val isTestedOut: Int,
        val score: Int
    )

    data class Videotranscripts(
        val videoId: String,
        val num: String,
        val startTime: Int,
        val endTime: Int,
        val textValue: String,
        var isCurrentText: Int = 0
    )
}