package com.kaplan.ktp_mobile_kmm.queries

import com.kaplan.ktp_mobile_kmm.entity.VideoEntity.*
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter

interface VideoEntityQueries : Transacter {
   fun <T : Any> getAllVideoLibrary(mapper: (
    title: String,
    recommendedTime: Int,
    activityType: String,
    name: String,
    description: String,
    source: String,
    url: String,
    is_learning_path_activity: Int,
    sequenceId: String
  ) -> T): Query<T>

   fun getAllVideoLibrary(): Query<Videolibrary>

   fun <T : Any> getVideoChapters(videoId: String, mapper: (
    pos: Int,
    videoId: String,
    topicTime: Int,
    topicName: String,
    status: String,
    qualityScore: String,
    isCurrentText: Int,
    endTime: Int
  ) -> T): Query<T>
   fun getVideoChapters(videoId: String): Query<Videochapters>
   fun <T : Any> getAllVideoTranscripts(mapper: (
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  ) -> T): Query<T>

  fun getAllVideoTranscripts(): Query<Videotranscripts>

  fun <T : Any> getVideoTranscripts(videoId: String, mapper: (
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  ) -> T): Query<T>

  fun getVideoTranscripts(videoId: String): Query<Videotranscripts>

  fun <T : Any> getAllVideoList(mapper: (
    id: String,
    name: String,
    source:String,
    title: String,
    description: String,
    status: String,
    recommendedTime: Int,
    activityType: String,
    is_tested_out: Int,
    sequenceId: String
  ) -> T): Query<T>

  fun getAllVideoList(): Query<VideoLibraryWithVideoStatus>

  fun <T : Any> getAllVideoListForSp(mapper: (
    id: String,
    name: String,
    source:String,
    title: String,
    description: String,
    status: String,
    recommendedTime: Int,
    activityType: String,
    is_tested_out: Int,
    sequenceId: String
  ) -> T): Query<T>

  fun getAllVideoListForSp(): Query<VideoLibraryWithVideoStatus>

  fun <T : Any> getVideoActivity(name: String, mapper: (
    id: String,
    name: String,
    source:String,
    title: String,
    description: String,
    status: String,
    recommendedTime: Int,
    activityType: String,
    is_tested_out: Int,
    sequenceId: String
  ) -> T): Query<T>

  fun getVideoActivity(name: String): Query<VideoLibraryWithVideoStatus>

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
  )

  fun insertVideoStatus(
    id: String,
    name: String,
    status: String,
    is_tested_out: Int,
    score: Int
  )

  fun insertVideoChapters(
    pos: Int,
    videoId: String,
    topicTime: Int,
    topicName: String,
    status: String,
    qualityScore: String,
    isCurrentText: Int,
    endTime: Int
  )

  fun insertVideoTranscripts(
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  )

  fun deleteFromVideoChapters(videoId: String)

  fun deleteFromVideoLibrary()

  fun deleteFromVideoStatus()

  fun updateVideoCompletion(
    id: String,
    status: String,
    is_tested_out: Int,
    name: String
  )

  fun updateVideoProgress(sequenceId: String, name: String)

  fun insertVideoLibraryObject(videoLibrarySql: Videolibrary)
}
