package com.kaplan.ktp_mobile_kmm

import com.kaplan.ktp_mobile_kmm.database.Database
import com.kaplan.ktp_mobile_kmm.database.DatabaseDriverFactory
import com.kaplan.ktp_mobile_kmm.entity.VideoEntity.*
import com.squareup.sqldelight.Query

class KmmSDK(dbDriverFactory: DatabaseDriverFactory) {
    private val database: Database = Database(dbDriverFactory)

    fun getAllVideoList(): List<VideoLibraryWithVideoStatus> {
        return database.getAllVideoList()
    }

    fun getAllVideoListForSp(): List<VideoLibraryWithVideoStatus> {
        return database.getAllVideoListForSp()
    }

    fun updateVideoCompletion(id: String, status: String, is_tested_out: Int, name: String) {
        return database.updateVideoCompletion(id, status, is_tested_out, name)
    }

    fun getVideoActivity(name: String): Query<VideoLibraryWithVideoStatus> {
        return database.getVideoActivity(name)
    }

    fun updateVideoProgress(sequenceId: String, name: String) {
        return database.updateVideoProgress(sequenceId, name)
    }

    fun getVideoChapters(videoId: String): Query<Videochapters> {
        return database.getVideoChapters(videoId)
    }

    fun getVideoTranscripts(videoId: String): Query<Videotranscripts> {
        return database.getVideoTranscripts(videoId)
    }

    fun deleteFromVideoChapters(videoId: String) {
        return database.deleteFromVideoChapters(videoId)
    }

    fun insertVideoChapters(
        pos: Int,
        videoId: String,
        topicTime: Int,
        topicName: String,
        status: String,
        qualityScore: String,
        isCurrentText: Int,
        endTime: Int
    ) {
        return database.insertVideoChapters(
            pos,
            videoId,
            topicTime,
            topicName,
            status,
            qualityScore,
            isCurrentText,
            endTime
        )
    }

    fun insertVideoTranscripts(
        videoId: String,
        num: String,
        startTime: Int,
        endTime: Int,
        textValue: String,
        isCurrentText: Int
    ) {
        return database.insertVideoTranscripts(
            videoId,
            num,
            startTime,
            endTime,
            textValue,
            isCurrentText
        )
    }

    fun deleteFromVideoStatus() {
        return database.deleteFromVideoStatus()
    }

    fun deleteFromVideoLibrary() {
        return database.deleteFromVideoLibrary()
    }

    fun insertVideoLibrary(
        title: String,
        recommendedTime: Int,
        activityType: String,
        name: String,
        description: String,
        source: String,
        url: String,
        is_learning_path_activity: Int,
        sequenceId: String
    ) {
        return database.insertVideoLibrary(
            title,
            recommendedTime,
            activityType,
            name,
            description,
            source,
            url,
            is_learning_path_activity,
            sequenceId
        )
    }

    fun insertVideoStatus(
        id: String,
        name: String,
        status: String,
        is_tested_out: Int,
        score: Int
    ) {
        return database.insertVideoStatus(id, name, status, is_tested_out, score)
    }
}