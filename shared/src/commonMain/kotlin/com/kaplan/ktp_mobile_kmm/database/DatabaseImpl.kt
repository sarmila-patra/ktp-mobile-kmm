package com.kaplan.ktp_mobile_kmm.database

import com.kaplan.ktp_mobile_kmm.entity.VideoEntity.*
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import com.kaplan.ktp_mobile_kmm.queries.VideoEntityQueries
import kotlin.reflect.KClass

 val KClass<Database>.schema: SqlDriver.Schema
  get() = DatabaseImpl.Schema

 fun KClass<Database>.newInstance(driver: SqlDriver): Database = DatabaseImpl(driver)

private class DatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), Database {
 /* override val taxonomyEntityQueries: TaxonomyEntityQueriesImpl =
      TaxonomyEntityQueriesImpl(this, driver)*/

  override val videoEntityQueries: VideoEntityQueriesImpl = VideoEntityQueriesImpl(this,
      driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS taxonomy (
          |    active INTEGER NOT NULL,
          |    created_at TEXT NOT NULL,
          |    id TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    position INTEGER NOT NULL,
          |    title TEXT NOT NULL,
          |    updated_at TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS taxonomyitem (
          |    taxonomyid TEXT NOT NULL,
          |    active INTEGER NOT NULL,
          |    item_type TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    position INTEGER NOT NULL,
          |    title TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS difficultystats (
          |    tag_name TEXT NOT NULL,
          |    difficulty TEXT NOT NULL,
          |    correct INTEGER NOT NULL,
          |    total INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS question (
          |    id TEXT NOT NULL,
          |    type TEXT NOT NULL,
          |    exposure TEXT NOT NULL,
          |    marked INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS questiontags (
          |    id TEXT NOT NULL,
          |    tag TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS quizhistory (
          |    id TEXT NOT NULL,
          |    mode TEXT NOT NULL,
          |    title TEXT NOT NULL,
          |    isreviewcomplete INTEGER NOT NULL,
          |    isquizcomplete INTEGER NOT NULL,
          |    totalquestions INTEGER NOT NULL,
          |    percentage_correct INTEGER NOT NULL,
          |    startdate TEXT NOT NULL,
          |    completedate TEXT NOT NULL,
          |    tags TEXT NOT NULL,
          |    correct INTEGER NOT NULL,
          |    in_correct INTEGER NOT NULL,
          |    unseen INTEGER NOT NULL,
          |    timerMode INTEGER NOT NULL,
          |    tutorMode INTEGER NOT NULL,
          |    recipe_type TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS reviewquiz (
          |    id TEXT PRIMARY KEY,
          |    quiztitle TEXT NOT NULL,
          |    business TEXT NOT NULL,
          |    completed_at TEXT NOT NULL,
          |    is_complete TEXT NOT NULL,
          |    timer_mode INTEGER NOT NULL,
          |    tutor_mode INTEGER NOT NULL,
          |    version TEXT NOT NULL,
          |    start_date TEXT NOT NULL,
          |    currentIndex INTEGER NOT NULL,
          |    recipe_name TEXT NOT NULL,
          |    recipe_type TEXT NOT NULL,
          |    enrollment_id TEXT NOT NULL,
          |    program_code TEXT NOT NULL,
          |    product_code TEXT NOT NULL,
          |    product_line TEXT NOT NULL,
          |    currentSectionId TEXT NOT NULL,
          |    percent_correct INTEGER NOT NULL,
          |    totalQuestions INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS quizsection (
          |    id TEXT PRIMARY KEY,
          |    quizId TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    title TEXT NOT NULL,
          |    parent TEXT NOT NULL,
          |    active INTEGER NOT NULL,
          |    position INTEGER NOT NULL,
          |    max_questions INTEGER NOT NULL,
          |    min_questions INTEGER NOT NULL,
          |    start_date TEXT NOT NULL,
          |    created_at TEXT NOT NULL,
          |    updated_at TEXT NOT NULL,
          |    is_adaptive INTEGER NOT NULL,
          |    is_complete INTEGER NOT NULL,
          |    kaplan_type TEXT NOT NULL,
          |    complete_date TEXT NOT NULL,
          |    allocated_time INTEGER NOT NULL,
          |    question_suggested_time INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS quizcontent (
          |    id TEXT PRIMARY KEY,
          |    quizId TEXT NOT NULL,
          |    sectionId TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    tags TEXT NOT NULL,
          |    system TEXT NOT NULL,
          |    instance_id TEXT NOT NULL,
          |    source_type TEXT NOT NULL,
          |    first_exposure INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS quizitem (
          |    id TEXT PRIMARY KEY,
          |    quizId TEXT NOT NULL,
          |    contentId TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    tags TEXT NOT NULL,
          |    type TEXT NOT NULL,
          |    status TEXT NOT NULL,
          |    score INTEGER NOT NULL,
          |    previousScore INTEGER NOT NULL,
          |    position INTEGER NOT NULL,
          |    scored_response INTEGER NOT NULL,
          |    interpreted_score INTEGER NOT NULL,
          |    resource INTEGER NOT NULL,
          |    scorable INTEGER NOT NULL,
          |    test INTEGER NOT NULL,
          |    review INTEGER NOT NULL,
          |    review_explanation INTEGER NOT NULL,
          |    timestamp TEXT NOT NULL,
          |    is_reviewed INTEGER NOT NULL,
          |    strand_name TEXT NOT NULL,
          |    difficulty_label TEXT NOT NULL,
          |    stem TEXT NOT NULL,
          |    is_marked INTEGER NOT NULL,
          |    is_changedAnswer INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS quizperformance (
          |    quizId TEXT NOT NULL,
          |    tagsname TEXT NOT NULL,
          |    correct_percentage TEXT NOT NULL,
          |    totalquestions TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS flascarddeck (
          |    id INTEGER NOT NULL,
          |    name TEXT NOT NULL,
          |    typologyId INTEGER NOT NULL,
          |    newCount INTEGER NOT NULL,
          |    learnCount INTEGER NOT NULL,
          |    reviewCount INTEGER NOT NULL,
          |    practicingCount INTEGER NOT NULL,
          |    totalCount INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS flashcardmobiledeck (
          |    id TEXT PRIMARY KEY,
          |    backsidetext TEXT NOT NULL,
          |    frontsidetext TEXT NOT NULL,
          |    enrollmentId INTEGER NOT NULL,
          |    response INTEGER DEFAULT 100
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS videolibrary (
          |    title TEXT NOT NULL,
          |    recommendedTime INTEGER NOT NULL,
          |    activityType TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    description TEXT NOT NULL,
          |    source TEXT NOT NULL,
          |    url TEXT NOT NULL,
          |    is_learning_path_activity INTEGER NOT NULL,
          |    sequenceId TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS videostatus (
          |    id TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    status TEXT NOT NULL,
          |    is_tested_out INTEGER NOT NULL,
          |    score INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS videochapters (
          |    pos INTEGER NOT NULL,
          |    videoId TEXT NOT NULL,
          |    topicTime INTEGER NOT NULL ,
          |    topicName TEXT NOT NULL,
          |    status TEXT NOT NULL,
          |    qualityScore TEXT NOT NULL,
          |    isCurrentText INTEGER DEFAULT 0,
          |    endTime INTEGER DEFAULT 0
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS videotranscripts (
          |    videoId TEXT NOT NULL,
          |    num TEXT NOT NULL,
          |    startTime INTEGER NOT NULL,
          |    endTime INTEGER NOT NULL,
          |    textValue TEXT NOT NULL,
          |    isCurrentText INTEGER NOT NULL
          |
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE VIEW videoLibraryWithVideoStatus AS
          |SELECT vs.id , vl.name , vl.source , vl.title , vl.description, vs.status , vl.recommendedTime , vl.activityType , vs.is_tested_out , vl.sequenceId FROM videolibrary vl JOIN videostatus vs ON vl.name = vs.name
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

/*private class TaxonomyEntityQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), TaxonomyEntityQueries {
  val getAllTaxonomy: MutableList<Query<*>> = copyOnWriteList()

  val getAllTaxonomyItem: MutableList<Query<*>> = copyOnWriteList()

  val getAllDifficultyStats: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuestions: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuestionTags: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuizHistory: MutableList<Query<*>> = copyOnWriteList()

  val getAllReviewQuiz: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuizSection: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuizContent: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuizItem: MutableList<Query<*>> = copyOnWriteList()

  val getAllQuizPerformance: MutableList<Query<*>> = copyOnWriteList()

  val getAllFlascardDeck: MutableList<Query<*>> = copyOnWriteList()

  val getAllFlashcardMobileDeck: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> getAllTaxonomy(
    mapper: (
      active: Int,
      created_at: String,
      id: String,
      name: String,
      position: Int,
      title: String,
      updated_at: String
    ) -> T
  ): Query<T> = Query(
    -1685269756, getAllTaxonomy, driver, "taxonomyEntity.sq",
    "getAllTaxonomy", "SELECT * FROM taxonomy"
  ) { cursor ->
    mapper(
      cursor.getLong(0)!!.toInt(),
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getLong(4)!!.toInt(),
      cursor.getString(5)!!,
      cursor.getString(6)!!
    )
  }

  override fun getAllTaxonomy(): Query<Taxonomy> = getAllTaxonomy { active, created_at, id,
                                                                    name, position, title, updated_at ->
    Taxonomy(
      active,
      created_at,
      id,
      name,
      position,
      title,
      updated_at
    )
  }

  override fun <T : Any> getAllTaxonomyItem(
    mapper: (
      taxonomyid: String,
      active: Int,
      item_type: String,
      name: String,
      position: Int,
      title: String
    ) -> T
  ): Query<T> = Query(
    -1824088009, getAllTaxonomyItem, driver, "taxonomyEntity.sq",
    "getAllTaxonomyItem", "SELECT * FROM taxonomyitem"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!.toInt(),
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getLong(4)!!.toInt(),
      cursor.getString(5)!!
    )
  }

  override fun getAllTaxonomyItem(): Query<Taxonomyitem> = getAllTaxonomyItem { taxonomyid,
                                                                                active, item_type, name, position, title ->
    Taxonomyitem(
      taxonomyid,
      active,
      item_type,
      name,
      position,
      title
    )
  }

  override fun <T : Any> getAllDifficultyStats(
    mapper: (
      tag_name: String,
      difficulty: String,
      correct: Int,
      total: Int
    ) -> T
  ): Query<T> = Query(
    -124298607, getAllDifficultyStats, driver, "taxonomyEntity.sq",
    "getAllDifficultyStats", "SELECT * FROM difficultystats"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!.toInt(),
      cursor.getLong(3)!!.toInt()
    )
  }

  override fun getAllDifficultyStats(): Query<Difficultystats> =
    getAllDifficultyStats { tag_name, difficulty, correct, total ->
      Difficultystats(
        tag_name,
        difficulty,
        correct,
        total
      )
    }

  override fun <T : Any> getAllQuestions(
    mapper: (
      id: String,
      type: String,
      exposure: String,
      marked: Int
    ) -> T
  ): Query<T> = Query(
    1357289466, getAllQuestions, driver, "taxonomyEntity.sq",
    "getAllQuestions", "SELECT * FROM question"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!.toInt()
    )
  }

  override fun getAllQuestions(): Query<Question> = getAllQuestions { id, type, exposure,
                                                                      marked ->
    Question(
      id,
      type,
      exposure,
      marked
    )
  }

  override fun <T : Any> getAllQuestionTags(mapper: (id: String, tag: String) -> T): Query<T> =
    Query(
      -2107437230, getAllQuestionTags, driver, "taxonomyEntity.sq", "getAllQuestionTags",
      "SELECT * FROM questiontags"
    ) { cursor ->
      mapper(
        cursor.getString(0)!!,
        cursor.getString(1)!!
      )
    }

  override fun getAllQuestionTags(): Query<Questiontags> = getAllQuestionTags { id, tag ->
    Questiontags(
      id,
      tag
    )
  }

  override fun <T : Any> getAllQuizHistory(
    mapper: (
      id: String,
      mode: String,
      title: String,
      isreviewcomplete: Int,
      isquizcomplete: Int,
      totalquestions: Int,
      percentage_correct: Int,
      startdate: String,
      completedate: String,
      tags: String,
      correct: Int,
      in_correct: Int,
      unseen: Int,
      timerMode: Int,
      tutorMode: Int,
      recipe_type: String
    ) -> T
  ): Query<T> = Query(
    -1031577620, getAllQuizHistory, driver, "taxonomyEntity.sq",
    "getAllQuizHistory", "SELECT * FROM quizhistory"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!.toInt(),
      cursor.getLong(4)!!.toInt(),
      cursor.getLong(5)!!.toInt(),
      cursor.getLong(6)!!.toInt(),
      cursor.getString(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!.toInt(),
      cursor.getLong(11)!!.toInt(),
      cursor.getLong(12)!!.toInt(),
      cursor.getLong(13)!!.toInt(),
      cursor.getLong(14)!!.toInt(),
      cursor.getString(15)!!
    )
  }

  override fun getAllQuizHistory(): Query<Quizhistory> = getAllQuizHistory { id, mode, title,
                                                                             isreviewcomplete, isquizcomplete, totalquestions, percentage_correct, startdate, completedate,
                                                                             tags, correct, in_correct, unseen, timerMode, tutorMode, recipe_type ->
    Quizhistory(
      id,
      mode,
      title,
      isreviewcomplete,
      isquizcomplete,
      totalquestions,
      percentage_correct,
      startdate,
      completedate,
      tags,
      correct,
      in_correct,
      unseen,
      timerMode,
      tutorMode,
      recipe_type
    )
  }

  override fun <T : Any> getAllReviewQuiz(
    mapper: (
      id: String,
      quiztitle: String,
      business: String,
      completed_at: String,
      is_complete: String,
      timer_mode: Int,
      tutor_mode: Int,
      version: String,
      start_date: String,
      currentIndex: Int,
      recipe_name: String,
      recipe_type: String,
      enrollment_id: String,
      program_code: String,
      product_code: String,
      product_line: String,
      currentSectionId: String,
      percent_correct: Int,
      totalQuestions: Int
    ) -> T
  ): Query<T> = Query(
    939761856, getAllReviewQuiz, driver, "taxonomyEntity.sq",
    "getAllReviewQuiz", "SELECT * FROM reviewquiz"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!.toInt(),
      cursor.getLong(6)!!.toInt(),
      cursor.getString(7)!!,
      cursor.getString(8)!!,
      cursor.getLong(9)!!.toInt(),
      cursor.getString(10)!!,
      cursor.getString(11)!!,
      cursor.getString(12)!!,
      cursor.getString(13)!!,
      cursor.getString(14)!!,
      cursor.getString(15)!!,
      cursor.getString(16)!!,
      cursor.getLong(17)!!.toInt(),
      cursor.getLong(18)!!.toInt()
    )
  }

  override fun getAllReviewQuiz(): Query<Reviewquiz> = getAllReviewQuiz { id, quiztitle,
                                                                          business, completed_at, is_complete, timer_mode, tutor_mode, version, start_date,
                                                                          currentIndex, recipe_name, recipe_type, enrollment_id, program_code, product_code,
                                                                          product_line, currentSectionId, percent_correct, totalQuestions ->
    Reviewquiz(
      id,
      quiztitle,
      business,
      completed_at,
      is_complete,
      timer_mode,
      tutor_mode,
      version,
      start_date,
      currentIndex,
      recipe_name,
      recipe_type,
      enrollment_id,
      program_code,
      product_code,
      product_line,
      currentSectionId,
      percent_correct,
      totalQuestions
    )
  }

  override fun <T : Any> getAllQuizSection(
    mapper: (
      id: String,
      quizId: String,
      name: String,
      title: String,
      parent: String,
      active: Int,
      position: Int,
      max_questions: Int,
      min_questions: Int,
      start_date: String,
      created_at: String,
      updated_at: String,
      is_adaptive: Int,
      is_complete: Int,
      kaplan_type: String,
      complete_date: String,
      allocated_time: Int,
      question_suggested_time: Int
    ) -> T
  ): Query<T> = Query(
    11729469, getAllQuizSection, driver, "taxonomyEntity.sq",
    "getAllQuizSection", "SELECT * FROM quizsection"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!.toInt(),
      cursor.getLong(6)!!.toInt(),
      cursor.getLong(7)!!.toInt(),
      cursor.getLong(8)!!.toInt(),
      cursor.getString(9)!!,
      cursor.getString(10)!!,
      cursor.getString(11)!!,
      cursor.getLong(12)!!.toInt(),
      cursor.getLong(13)!!.toInt(),
      cursor.getString(14)!!,
      cursor.getString(15)!!,
      cursor.getLong(16)!!.toInt(),
      cursor.getLong(17)!!.toInt()
    )
  }

  override fun getAllQuizSection(): Query<Quizsection> = getAllQuizSection { id, quizId,
                                                                             name, title, parent, active, position, max_questions, min_questions, start_date, created_at,
                                                                             updated_at, is_adaptive, is_complete, kaplan_type, complete_date, allocated_time,
                                                                             question_suggested_time ->
    Quizsection(
      id,
      quizId,
      name,
      title,
      parent,
      active,
      position,
      max_questions,
      min_questions,
      start_date,
      created_at,
      updated_at,
      is_adaptive,
      is_complete,
      kaplan_type,
      complete_date,
      allocated_time,
      question_suggested_time
    )
  }

  override fun <T : Any> getAllQuizContent(
    mapper: (
      id: String,
      quizId: String,
      sectionId: String,
      name: String,
      tags: String,
      system: String,
      instance_id: String,
      source_type: String,
      first_exposure: Int
    ) -> T
  ): Query<T> = Query(
    -1006981167, getAllQuizContent, driver, "taxonomyEntity.sq",
    "getAllQuizContent", "SELECT * FROM quizcontent"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!.toInt()
    )
  }

  override fun getAllQuizContent(): Query<Quizcontent> = getAllQuizContent { id, quizId,
                                                                             sectionId, name, tags, system, instance_id, source_type, first_exposure ->
    Quizcontent(
      id,
      quizId,
      sectionId,
      name,
      tags,
      system,
      instance_id,
      source_type,
      first_exposure
    )
  }

  override fun <T : Any> getAllQuizItem(
    mapper: (
      id: String,
      quizId: String,
      contentId: String,
      name: String,
      tags: String,
      type: String,
      status: String,
      score: Int,
      previousScore: Int,
      position: Int,
      scored_response: Int,
      interpreted_score: Int,
      resource: Int,
      scorable: Int,
      test: Int,
      review: Int,
      review_explanation: Int,
      timestamp: String,
      is_reviewed: Int,
      strand_name: String,
      difficulty_label: String,
      stem: String,
      is_marked: Int,
      is_changedAnswer: Int
    ) -> T
  ): Query<T> = Query(
    1826062011, getAllQuizItem, driver, "taxonomyEntity.sq",
    "getAllQuizItem", "SELECT * FROM quizitem"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getLong(7)!!.toInt(),
      cursor.getLong(8)!!.toInt(),
      cursor.getLong(9)!!.toInt(),
      cursor.getLong(10)!!.toInt(),
      cursor.getLong(11)!!.toInt(),
      cursor.getLong(12)!!.toInt(),
      cursor.getLong(13)!!.toInt(),
      cursor.getLong(14)!!.toInt(),
      cursor.getLong(15)!!.toInt(),
      cursor.getLong(16)!!.toInt(),
      cursor.getString(17)!!,
      cursor.getLong(18)!!.toInt(),
      cursor.getString(19)!!,
      cursor.getString(20)!!,
      cursor.getString(21)!!,
      cursor.getLong(22)!!.toInt(),
      cursor.getLong(23)!!.toInt()
    )
  }

  override fun getAllQuizItem(): Query<Quizitem> = getAllQuizItem { id, quizId, contentId,
                                                                    name, tags, type, status, score, previousScore, position, scored_response, interpreted_score,
                                                                    resource, scorable, test, review, review_explanation, timestamp, is_reviewed, strand_name,
                                                                    difficulty_label, stem, is_marked, is_changedAnswer ->
    Quizitem(
      id,
      quizId,
      contentId,
      name,
      tags,
      type,
      status,
      score,
      previousScore,
      position,
      scored_response,
      interpreted_score,
      resource,
      scorable,
      test,
      review,
      review_explanation,
      timestamp,
      is_reviewed,
      strand_name,
      difficulty_label,
      stem,
      is_marked,
      is_changedAnswer
    )
  }

  override fun <T : Any> getAllQuizPerformance(
    mapper: (
      quizId: String,
      tagsname: String,
      correct_percentage: String,
      totalquestions: String
    ) -> T
  ): Query<T> = Query(
    -1549197432, getAllQuizPerformance, driver, "taxonomyEntity.sq",
    "getAllQuizPerformance", "SELECT * FROM quizperformance"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!
    )
  }

  override fun getAllQuizPerformance(): Query<Quizperformance> =
    getAllQuizPerformance { quizId, tagsname, correct_percentage, totalquestions ->
      Quizperformance(
        quizId,
        tagsname,
        correct_percentage,
        totalquestions
      )
    }

  override fun <T : Any> getAllFlascardDeck(
    mapper: (
      id: Int,
      name: String,
      typologyId: Int,
      newCount: Int,
      learnCount: Int,
      reviewCount: Int,
      practicingCount: Int,
      totalCount: Int
    ) -> T
  ): Query<T> = Query(
    -1745520252, getAllFlascardDeck, driver, "taxonomyEntity.sq",
    "getAllFlascardDeck", "SELECT * FROM flascarddeck"
  ) { cursor ->
    mapper(
      cursor.getLong(0)!!.toInt(),
      cursor.getString(1)!!,
      cursor.getLong(2)!!.toInt(),
      cursor.getLong(3)!!.toInt(),
      cursor.getLong(4)!!.toInt(),
      cursor.getLong(5)!!.toInt(),
      cursor.getLong(6)!!.toInt(),
      cursor.getLong(7)!!.toInt()
    )
  }

  override fun getAllFlascardDeck(): Query<Flascarddeck> = getAllFlascardDeck { id, name,
                                                                                typologyId, newCount, learnCount, reviewCount, practicingCount, totalCount ->
    Flascarddeck(
      id,
      name,
      typologyId,
      newCount,
      learnCount,
      reviewCount,
      practicingCount,
      totalCount
    )
  }

  override fun <T : Any> getAllFlashcardMobileDeck(
    mapper: (
      id: String,
      backsidetext: String,
      frontsidetext: String,
      enrollmentId: Int,
      response: Int?
    ) -> T
  ): Query<T> = Query(
    86741496, getAllFlashcardMobileDeck, driver, "taxonomyEntity.sq",
    "getAllFlashcardMobileDeck", "SELECT * FROM flashcardmobiledeck"
  ) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!.toInt(),
      cursor.getLong(4)?.toInt()
    )
  }

  override fun getAllFlashcardMobileDeck(): Query<Flashcardmobiledeck> =
    getAllFlashcardMobileDeck { id, backsidetext, frontsidetext, enrollmentId, response ->
      Flashcardmobiledeck(
        id,
        backsidetext,
        frontsidetext,
        enrollmentId,
        response
      )
    }

  override fun insertTaxonomy(
    active: Int,
    created_at: String,
    id: String,
    name: String,
    position: Int,
    title: String,
    updated_at: String
  ) {
    driver.execute(104283474, """INSERT INTO taxonomy VALUES (?, ?, ?, ?, ?, ?, ?)""", 7) {
      bindLong(1, active.toLong())
      bindString(2, created_at)
      bindString(3, id)
      bindString(4, name)
      bindLong(5, position.toLong())
      bindString(6, title)
      bindString(7, updated_at)
    }
    notifyQueries(104283474, { database.taxonomyEntityQueries.getAllTaxonomy })
  }

  override fun insertTaxonomyItem(
    taxonomyid: String,
    active: Int,
    item_type: String,
    name: String,
    position: Int,
    title: String
  ) {
    driver.execute(1928803205, """INSERT INTO taxonomyitem VALUES (?, ?, ?, ?, ?, ?)""", 6) {
      bindString(1, taxonomyid)
      bindLong(2, active.toLong())
      bindString(3, item_type)
      bindString(4, name)
      bindLong(5, position.toLong())
      bindString(6, title)
    }
    notifyQueries(1928803205, { database.taxonomyEntityQueries.getAllTaxonomyItem })
  }

  override fun insertDifficultyStats(
    tag_name: String,
    difficulty: String,
    correct: Int,
    total: Int
  ) {
    driver.execute(-35824509, """INSERT INTO difficultystats VALUES (?, ?, ?, ?)""", 4) {
      bindString(1, tag_name)
      bindString(2, difficulty)
      bindLong(3, correct.toLong())
      bindLong(4, total.toLong())
    }
    notifyQueries(-35824509, { database.taxonomyEntityQueries.getAllDifficultyStats })
  }

  override fun insertQuestions(
    id: String,
    type: String,
    exposure: String,
    marked: Int
  ) {
    driver.execute(998864748, """INSERT INTO question VALUES (?, ?, ?, ?)""", 4) {
      bindString(1, id)
      bindString(2, type)
      bindString(3, exposure)
      bindLong(4, marked.toLong())
    }
    notifyQueries(998864748, { database.taxonomyEntityQueries.getAllQuestions })
  }

  override fun insertQuestionTags(id: String, tag: String) {
    driver.execute(1645453984, """INSERT INTO questiontags VALUES (?, ?)""", 2) {
      bindString(1, id)
      bindString(2, tag)
    }
    notifyQueries(1645453984, { database.taxonomyEntityQueries.getAllQuestionTags })
  }

  override fun insertQuizHistory(
    id: String,
    mode: String,
    title: String,
    isreviewcomplete: Int,
    isquizcomplete: Int,
    totalquestions: Int,
    percentage_correct: Int,
    startdate: String,
    completedate: String,
    tags: String,
    correct: Int,
    in_correct: Int,
    unseen: Int,
    timerMode: Int,
    tutorMode: Int,
    recipe_type: String
  ) {
    driver.execute(
      -1880347938,
      """INSERT INTO quizhistory VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""", 16
    ) {
      bindString(1, id)
      bindString(2, mode)
      bindString(3, title)
      bindLong(4, isreviewcomplete.toLong())
      bindLong(5, isquizcomplete.toLong())
      bindLong(6, totalquestions.toLong())
      bindLong(7, percentage_correct.toLong())
      bindString(8, startdate)
      bindString(9, completedate)
      bindString(10, tags)
      bindLong(11, correct.toLong())
      bindLong(12, in_correct.toLong())
      bindLong(13, unseen.toLong())
      bindLong(14, timerMode.toLong())
      bindLong(15, tutorMode.toLong())
      bindString(16, recipe_type)
    }
    notifyQueries(-1880347938, { database.taxonomyEntityQueries.getAllQuizHistory })
  }

  override fun insertReviewQuiz(
    id: String,
    quiztitle: String,
    business: String,
    completed_at: String,
    is_complete: String,
    timer_mode: Int,
    tutor_mode: Int,
    version: String,
    start_date: String,
    currentIndex: Int,
    recipe_name: String,
    recipe_type: String,
    enrollment_id: String,
    program_code: String,
    product_code: String,
    product_line: String,
    currentSectionId: String,
    percent_correct: Int,
    totalQuestions: Int
  ) {
    driver.execute(
      -1581469810,
      """INSERT INTO reviewquiz VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""",
      19
    ) {
      bindString(1, id)
      bindString(2, quiztitle)
      bindString(3, business)
      bindString(4, completed_at)
      bindString(5, is_complete)
      bindLong(6, timer_mode.toLong())
      bindLong(7, tutor_mode.toLong())
      bindString(8, version)
      bindString(9, start_date)
      bindLong(10, currentIndex.toLong())
      bindString(11, recipe_name)
      bindString(12, recipe_type)
      bindString(13, enrollment_id)
      bindString(14, program_code)
      bindString(15, product_code)
      bindString(16, product_line)
      bindString(17, currentSectionId)
      bindLong(18, percent_correct.toLong())
      bindLong(19, totalQuestions.toLong())
    }
    notifyQueries(-1581469810, { database.taxonomyEntityQueries.getAllReviewQuiz })
  }

  override fun insertQuizSection(
    id: String,
    quizId: String,
    name: String,
    title: String,
    parent: String,
    active: Int,
    position: Int,
    max_questions: Int,
    min_questions: Int,
    start_date: String,
    created_at: String,
    updated_at: String,
    is_adaptive: Int,
    is_complete: Int,
    kaplan_type: String,
    complete_date: String,
    allocated_time: Int,
    question_suggested_time: Int
  ) {
    driver.execute(
      -837040849,
      """INSERT INTO quizsection VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""",
      18
    ) {
      bindString(1, id)
      bindString(2, quizId)
      bindString(3, name)
      bindString(4, title)
      bindString(5, parent)
      bindLong(6, active.toLong())
      bindLong(7, position.toLong())
      bindLong(8, max_questions.toLong())
      bindLong(9, min_questions.toLong())
      bindString(10, start_date)
      bindString(11, created_at)
      bindString(12, updated_at)
      bindLong(13, is_adaptive.toLong())
      bindLong(14, is_complete.toLong())
      bindString(15, kaplan_type)
      bindString(16, complete_date)
      bindLong(17, allocated_time.toLong())
      bindLong(18, question_suggested_time.toLong())
    }
    notifyQueries(-837040849, { database.taxonomyEntityQueries.getAllQuizSection })
  }

  override fun insertQuizContent(
    id: String,
    quizId: String,
    sectionId: String,
    name: String,
    tags: String,
    system: String,
    instance_id: String,
    source_type: String,
    first_exposure: Int
  ) {
    driver.execute(-1855751485, """INSERT INTO quizcontent VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""", 9)
    {
      bindString(1, id)
      bindString(2, quizId)
      bindString(3, sectionId)
      bindString(4, name)
      bindString(5, tags)
      bindString(6, system)
      bindString(7, instance_id)
      bindString(8, source_type)
      bindLong(9, first_exposure.toLong())
    }
    notifyQueries(-1855751485, { database.taxonomyEntityQueries.getAllQuizContent })
  }

  override fun insertQuizItem(
    id: String,
    quizId: String,
    contentId: String,
    name: String,
    tags: String,
    type: String,
    status: String,
    score: Int,
    previousScore: Int,
    position: Int,
    scored_response: Int,
    interpreted_score: Int,
    resource: Int,
    scorable: Int,
    test: Int,
    review: Int,
    review_explanation: Int,
    timestamp: String,
    is_reviewed: Int,
    strand_name: String,
    difficulty_label: String,
    stem: String,
    is_marked: Int,
    is_changedAnswer: Int
  ) {
    driver.execute(
      -679352055,
      """INSERT INTO quizitem VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""",
      24
    ) {
      bindString(1, id)
      bindString(2, quizId)
      bindString(3, contentId)
      bindString(4, name)
      bindString(5, tags)
      bindString(6, type)
      bindString(7, status)
      bindLong(8, score.toLong())
      bindLong(9, previousScore.toLong())
      bindLong(10, position.toLong())
      bindLong(11, scored_response.toLong())
      bindLong(12, interpreted_score.toLong())
      bindLong(13, resource.toLong())
      bindLong(14, scorable.toLong())
      bindLong(15, test.toLong())
      bindLong(16, review.toLong())
      bindLong(17, review_explanation.toLong())
      bindString(18, timestamp)
      bindLong(19, is_reviewed.toLong())
      bindString(20, strand_name)
      bindString(21, difficulty_label)
      bindString(22, stem)
      bindLong(23, is_marked.toLong())
      bindLong(24, is_changedAnswer.toLong())
    }
    notifyQueries(-679352055, { database.taxonomyEntityQueries.getAllQuizItem })
  }

  override fun insertQuizPerformance(
    quizId: String,
    tagsname: String,
    correct_percentage: String,
    totalquestions: String
  ) {
    driver.execute(-1460723334, """INSERT INTO quizperformance VALUES (?, ?, ?, ?)""", 4) {
      bindString(1, quizId)
      bindString(2, tagsname)
      bindString(3, correct_percentage)
      bindString(4, totalquestions)
    }
    notifyQueries(-1460723334, { database.taxonomyEntityQueries.getAllQuizPerformance })
  }

  public override fun insertFlascardDeck(
    id: Int,
    name: String,
    typologyId: Int,
    newCount: Int,
    learnCount: Int,
    reviewCount: Int,
    practicingCount: Int,
    totalCount: Int
  ) {
    driver.execute(2007370962, """INSERT INTO flascarddeck VALUES (?, ?, ?, ?, ?, ?, ?, ?)""", 8) {
      bindLong(1, id.toLong())
      bindString(2, name)
      bindLong(3, typologyId.toLong())
      bindLong(4, newCount.toLong())
      bindLong(5, learnCount.toLong())
      bindLong(6, reviewCount.toLong())
      bindLong(7, practicingCount.toLong())
      bindLong(8, totalCount.toLong())
    }
    notifyQueries(2007370962, { database.taxonomyEntityQueries.getAllFlascardDeck })
  }

  override fun insertFlashcardMobileDeck(
    id: String,
    backsidetext: String,
    frontsidetext: String,
    enrollmentId: Int,
    response: Int?
  ) {
    driver.execute(316361450, """INSERT INTO flashcardmobiledeck VALUES (?, ?, ?, ?, ?)""", 5) {
      bindString(1, id)
      bindString(2, backsidetext)
      bindString(3, frontsidetext)
      bindLong(4, enrollmentId.toLong())
      bindLong(5, response?.let { it.toLong() })
    }
    notifyQueries(316361450, { database.taxonomyEntityQueries.getAllFlashcardMobileDeck })
  }
}*/

private class VideoEntityQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), VideoEntityQueries {
   val getAllVideoLibrary: MutableList<Query<*>> = copyOnWriteList()

   val getVideoChapters: MutableList<Query<*>> = copyOnWriteList()

   val getAllVideoTranscripts: MutableList<Query<*>> = copyOnWriteList()

   val getVideoTranscripts: MutableList<Query<*>> = copyOnWriteList()

   val getAllVideoList: MutableList<Query<*>> = copyOnWriteList()

   val getAllVideoListForSp: MutableList<Query<*>> = copyOnWriteList()

   val getVideoActivity: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> getAllVideoLibrary(mapper: (
    title: String,
    recommendedTime: Int,
    activityType: String,
    name: String,
    description: String,
    source: String,
    url: String,
    is_learning_path_activity: Int,
    sequenceId: String
  ) -> T): Query<T> = Query(315472465, getAllVideoLibrary, driver, "videoEntity.sq",
      "getAllVideoLibrary", "SELECT * FROM videolibrary") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!.toInt(),
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getLong(7)!!.toInt(),
      cursor.getString(8)!!
    )
  }

  override fun getAllVideoLibrary(): Query<Videolibrary> = getAllVideoLibrary { title,
                                                                                recommendedTime, activityType, name, description, source, url, is_learning_path_activity,
                                                                                sequenceId ->
    Videolibrary(
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

  override fun <T : Any> getVideoChapters(videoId: String, mapper: (
    pos: Int,
    videoId: String,
    topicTime: Int,
    topicName: String,
    status: String,
    qualityScore: String,
    isCurrentText: Int,
    endTime: Int
  ) -> T): Query<T> = GetVideoChaptersQuery(videoId) { cursor ->
    mapper(
      cursor.getLong(0)!!.toInt(),
      cursor.getString(1)!!,
      cursor.getLong(2)!!.toInt(),
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!.toInt(),
      cursor.getLong(7)!!.toInt()
    )
  }

  override fun getVideoChapters(videoId: String): Query<Videochapters> =
      getVideoChapters(videoId) { pos, videoId_, topicTime, topicName, status, qualityScore,
      isCurrentText, endTime ->
    Videochapters(
      pos,
      videoId_,
      topicTime,
      topicName,
      status,
      qualityScore,
      isCurrentText,
      endTime
    )
  }

  override fun <T : Any> getAllVideoTranscripts(mapper: (
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  ) -> T): Query<T> = Query(-835855853, getAllVideoTranscripts, driver, "videoEntity.sq",
      "getAllVideoTranscripts", "SELECT * FROM videotranscripts") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!.toInt(),
      cursor.getLong(3)!!.toInt(),
      cursor.getString(4)!!,
      cursor.getLong(5)!!.toInt()
    )
  }

  override fun getAllVideoTranscripts(): Query<Videotranscripts> = getAllVideoTranscripts {
      videoId, num, startTime, endTime, textValue, isCurrentText ->
    Videotranscripts(
      videoId,
      num,
      startTime,
      endTime,
      textValue,
      isCurrentText
    )
  }

  override fun <T : Any> getVideoTranscripts(videoId: String, mapper: (
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  ) -> T): Query<T> = GetVideoTranscriptsQuery(videoId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!.toInt(),
      cursor.getLong(3)!!.toInt(),
      cursor.getString(4)!!,
      cursor.getLong(5)!!.toInt()
    )
  }

  override fun getVideoTranscripts(videoId: String): Query<Videotranscripts> =
      getVideoTranscripts(videoId) { videoId_, num, startTime, endTime, textValue, isCurrentText ->
    Videotranscripts(
      videoId_,
      num,
      startTime,
      endTime,
      textValue,
      isCurrentText
    )
  }

  override fun <T : Any> getAllVideoList(mapper: (
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
  ) -> T): Query<T> = Query(1453388488, getAllVideoList, driver, "videoEntity.sq",
      "getAllVideoList",
      "SELECT * FROM videoLibraryWithVideoStatus WHERE activityType ='video-set'") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!.toInt(),
      cursor.getString(7)!!,
      cursor.getLong(8)!!.toInt(),
      cursor.getString(9)!!
    )
  }

  override fun getAllVideoList(): Query<VideoLibraryWithVideoStatus> = getAllVideoList { id,
                                                                                                name, source ,title, description, status, recommendedTime, activityType, is_tested_out, sequenceId ->
    VideoLibraryWithVideoStatus(
      id,
      name,
      source,
      title,
      description,
      status,
      recommendedTime,
      activityType,
      is_tested_out,
      sequenceId
    )
  }

  override fun <T : Any> getAllVideoListForSp(mapper: (
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
  ) -> T): Query<T> = Query(461251358, getAllVideoListForSp, driver, "videoEntity.sq",
      "getAllVideoListForSp", "SELECT * FROM videoLibraryWithVideoStatus GROUP BY title") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!.toInt(),
      cursor.getString(7)!!,
      cursor.getLong(8)!!.toInt(),
      cursor.getString(9)!!
    )
  }

  public override fun getAllVideoListForSp(): Query<VideoLibraryWithVideoStatus> =
      getAllVideoListForSp { id, name, source,title, description, status, recommendedTime, activityType,
      is_tested_out, sequenceId ->
    VideoLibraryWithVideoStatus(
      id,
      name,
      source,
      title,
      description,
      status,
      recommendedTime,
      activityType,
      is_tested_out,
      sequenceId
    )
  }

  public override fun <T : Any> getVideoActivity(name: String, mapper: (
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
  ) -> T): Query<T> = GetVideoActivityQuery(name) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!.toInt(),
      cursor.getString(7)!!,
      cursor.getLong(8)!!.toInt(),
      cursor.getString(9)!!
    )
  }

  override fun getVideoActivity(name: String): Query<VideoLibraryWithVideoStatus> =
      getVideoActivity(name) { id, name_, source,title, description, status, recommendedTime, activityType,
      is_tested_out, sequenceId ->
    VideoLibraryWithVideoStatus(
      id,
      name_,
      source,
      title,
      description,
      status,
      recommendedTime,
      activityType,
      is_tested_out,
      sequenceId
    )
  }

  override fun insertVideoLibrary(
    title: String,
    recommendedTime: Int,
    activityType: String,
    name: String,
    description: String,
    source: String,
    url: String,
    is_learning_path_activity: Int,
    sequenceId: String
  )  {
    driver.execute(-226603617, """INSERT INTO videolibrary VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""", 9)
        {
      bindString(1, title)
      bindLong(2, recommendedTime.toLong())
      bindString(3, activityType)
      bindString(4, name)
      bindString(5, description)
      bindString(6, source)
      bindString(7, url)
      bindLong(8, is_learning_path_activity.toLong())
      bindString(9, sequenceId)
    }
    notifyQueries(-226603617) {
      database.videoEntityQueries.getVideoActivity +
              database.videoEntityQueries.getAllVideoLibrary +
              database.videoEntityQueries.getAllVideoListForSp +
              database.videoEntityQueries.getAllVideoList
    }
  }

  override fun insertVideoStatus(
    id: String,
    name: String,
    status: String,
    is_tested_out: Int,
    score: Int
  )  {
    driver.execute(203225742, """INSERT INTO videostatus VALUES (?, ?, ?, ? , ?)""", 5) {
      bindString(1, id)
      bindString(2, name)
      bindString(3, status)
      bindLong(4, is_tested_out.toLong())
      bindLong(5, score.toLong())
    }
    notifyQueries(203225742) {
      database.videoEntityQueries.getVideoActivity +
              database.videoEntityQueries.getAllVideoListForSp +
              database.videoEntityQueries.getAllVideoList
    }
  }

  public override fun insertVideoChapters(
    pos: Int,
    videoId: String,
    topicTime: Int,
    topicName: String,
    status: String,
    qualityScore: String,
    isCurrentText: Int,
    endTime: Int
  )  {
    driver.execute(2142372194, """INSERT INTO videochapters VALUES (?, ?, ?, ?, ?, ?, ?, ?)""", 8) {
      bindLong(1, pos.toLong())
      bindString(2, videoId)
      bindLong(3, topicTime.toLong())
      bindString(4, topicName)
      bindString(5, status)
      bindString(6, qualityScore)
      bindLong(7, isCurrentText.toLong())
      bindLong(8, endTime.toLong())
    }
    notifyQueries(2142372194) { database.videoEntityQueries.getVideoChapters }
  }

  override fun insertVideoTranscripts(
    videoId: String,
    num: String,
    startTime: Int,
    endTime: Int,
    textValue: String,
    isCurrentText: Int
  )  {
    driver.execute(1906841185, """INSERT INTO videotranscripts VALUES (?, ?, ?, ?, ?, ?)""", 6) {
      bindString(1, videoId)
      bindString(2, num)
      bindLong(3, startTime.toLong())
      bindLong(4, endTime.toLong())
      bindString(5, textValue)
      bindLong(6, isCurrentText.toLong())
    }
    notifyQueries(1906841185, {database.videoEntityQueries.getVideoTranscripts +
        database.videoEntityQueries.getAllVideoTranscripts})
  }

  public override fun deleteFromVideoChapters(videoId: String)  {
    driver.execute(746566822, """DELETE FROM videochapters WHERE videoId = ?""", 1) {
      bindString(1, videoId)
    }
    notifyQueries(746566822, {database.videoEntityQueries.getVideoChapters})
  }

  override fun deleteFromVideoLibrary()  {
    driver.execute(-548724261, """DELETE FROM videolibrary""", 0)
    notifyQueries(-548724261) {
      database.videoEntityQueries.getVideoActivity +
              database.videoEntityQueries.getAllVideoLibrary +
              database.videoEntityQueries.getAllVideoListForSp +
              database.videoEntityQueries.getAllVideoList
    }
  }

  override fun deleteFromVideoStatus()  {
    driver.execute(747024082, """DELETE FROM videostatus""", 0)
    notifyQueries(747024082, {database.videoEntityQueries.getVideoActivity +
        database.videoEntityQueries.getAllVideoListForSp +
        database.videoEntityQueries.getAllVideoList})
  }

  override fun updateVideoCompletion(
    id: String,
    status: String,
    is_tested_out: Int,
    name: String
  )  {
    driver.execute(-353236888,
        """UPDATE videostatus SET id = ? ,status = ? , is_tested_out = ? WHERE name = ?""", 4) {
      bindString(1, id)
      bindString(2, status)
      bindLong(3, is_tested_out.toLong())
      bindString(4, name)
    }
    notifyQueries(-353236888, {database.videoEntityQueries.getVideoActivity +
        database.videoEntityQueries.getAllVideoListForSp +
        database.videoEntityQueries.getAllVideoList})
  }

  override fun updateVideoProgress(sequenceId: String, name: String)  {
    driver.execute(1394645433, """UPDATE videolibrary SET sequenceId = ? WHERE name = ?""", 2) {
      bindString(1, sequenceId)
      bindString(2, name)
    }
    notifyQueries(1394645433, {database.videoEntityQueries.getVideoActivity +
        database.videoEntityQueries.getAllVideoLibrary +
        database.videoEntityQueries.getAllVideoListForSp +
        database.videoEntityQueries.getAllVideoList})
  }

  override fun insertVideoLibraryObject(videoLibrarySql: Videolibrary)  {
    driver.execute(1127541182, """INSERT INTO videolibrary VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""", 9)
        {
      bindString(1, videoLibrarySql.title)
      bindLong(2, videoLibrarySql.recommendedTime.toLong())
      bindString(3, videoLibrarySql.activityType)
      bindString(4, videoLibrarySql.name)
      bindString(5, videoLibrarySql.description)
      bindString(6, videoLibrarySql.source)
      bindString(7, videoLibrarySql.url)
      bindLong(8, videoLibrarySql.isLearningPathActivity.toLong())
      bindString(9, videoLibrarySql.sequenceId)
    }
    notifyQueries(1127541182) {
      database.videoEntityQueries.getVideoActivity +
              database.videoEntityQueries.getAllVideoLibrary +
              database.videoEntityQueries.getAllVideoListForSp +
              database.videoEntityQueries.getAllVideoList
    }
  }

  private inner class GetVideoChaptersQuery<out T : Any>(
    val videoId: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getVideoChapters, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(1051905457,
        """SELECT * FROM videochapters WHERE videoId = ?""", 1) {
      bindString(1, videoId)
    }

    override fun toString(): String = "videoEntity.sq:getVideoChapters"
  }

  private inner class GetVideoTranscriptsQuery<out T : Any>(
    val videoId: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getVideoTranscripts, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(-1350061134,
        """SELECT * FROM videotranscripts WHERE videoId = ?""", 1) {
      bindString(1, videoId)
    }

    public override fun toString(): String = "videoEntity.sq:getVideoTranscripts"
  }

  private inner class GetVideoActivityQuery<out T : Any>(
    public val name: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getVideoActivity, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-2038713606,
        """SELECT * FROM videoLibraryWithVideoStatus WHERE name = ?""", 1) {
      bindString(1, name)
    }

    public override fun toString(): String = "videoEntity.sq:getVideoActivity"
  }
}
