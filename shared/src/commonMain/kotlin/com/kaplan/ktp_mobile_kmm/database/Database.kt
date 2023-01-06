package com.kaplan.ktp_mobile_kmm.database

import com.kaplan.ktp_mobile_kmm.entity.VideoEntity.*
import com.squareup.sqldelight.Query

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = KmmDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.videoEntityQueries

    internal fun getAllVideoList(): List<VideoLibraryWithVideoStatus> {
        return dbQuery.getAllVideoList().executeAsList()
    }
    internal fun getAllVideoListForSp(): List<VideoLibraryWithVideoStatus> {
        return dbQuery.getAllVideoListForSp().executeAsList()
    }
    internal fun updateVideoCompletion(id: String,status: String, is_tested_out: Int,name: String) {
        return dbQuery.updateVideoCompletion(id , status,is_tested_out,name)
    }
    internal fun getVideoActivity(name: String) : Query<VideoLibraryWithVideoStatus> {
        return dbQuery.getVideoActivity(name)
    }
    internal fun updateVideoProgress(sequenceId: String, name: String) {
        return dbQuery.updateVideoProgress(sequenceId,name)
    }
    internal fun getVideoChapters(videoId : String) : Query<Videochapters> {
        return dbQuery.getVideoChapters(videoId)
    }
    internal fun getVideoTranscripts(videoId : String) : Query<Videotranscripts> {
        return dbQuery.getVideoTranscripts(videoId)
    }
    internal fun deleteFromVideoChapters(videoId : String)  {
        return dbQuery.deleteFromVideoChapters(videoId)
    }
    internal fun insertVideoChapters(pos: Int,
                                      videoId: String,
                                      topicTime: Int,
                                      topicName: String,
                                      status: String,
                                      qualityScore: String,
                                      isCurrentText: Int,
                                      endTime: Int)  {
        return dbQuery.insertVideoChapters(pos, videoId, topicTime, topicName, status, qualityScore, isCurrentText, endTime)
    }
    internal fun insertVideoTranscripts(videoId: String,
                                        num: String,
                                        startTime: Int,
                                        endTime: Int,
                                        textValue: String,
                                        isCurrentText: Int)  {
        return dbQuery.insertVideoTranscripts(videoId, num, startTime, endTime, textValue, isCurrentText)
    }
    internal fun deleteFromVideoStatus(){
        return dbQuery.deleteFromVideoStatus()
    }
    internal fun deleteFromVideoLibrary()  {
        return dbQuery.deleteFromVideoLibrary()
    }
    internal fun insertVideoLibrary( title: String,
                                     recommendedTime: Int,
                                     activityType: String,
                                     name: String,
                                     description: String,
                                     source: String,
                                     url: String,
                                     is_learning_path_activity: Int,
                                     sequenceId: String){
        return dbQuery.insertVideoLibrary(title, recommendedTime, activityType, name, description, source, url, is_learning_path_activity, sequenceId)
    }
    internal fun insertVideoStatus( id: String,
                                    name: String,
                                    status: String,
                                    is_tested_out: Int,
                                    score: Int)  {
        return dbQuery.insertVideoStatus(id, name, status, is_tested_out, score)
    }
}