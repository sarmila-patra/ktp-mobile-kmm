package com.kaplan.ktp_mobile_kmm.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import com.kaplan.ktp_mobile_kmm.queries.VideoEntityQueries

interface KmmDatabase : Transacter {

   val videoEntityQueries: VideoEntityQueries

   companion object {
     val Schema: SqlDriver.Schema
      get() = KmmDatabase::class.schema

     operator fun invoke(driver: SqlDriver): KmmDatabase = KmmDatabase::class.newInstance(driver)
  }
}
