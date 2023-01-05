package com.kaplan.ktp_mobile_kmm.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import com.kaplan.ktp_mobile_kmm.queries.VideoEntityQueries

interface Database : Transacter {
  // val taxonomyEntityQueries: TaxonomyEntityQueries

   val videoEntityQueries: VideoEntityQueries

   companion object {
     val Schema: SqlDriver.Schema
      get() = Database::class.schema

     operator fun invoke(driver: SqlDriver): Database = Database::class.newInstance(driver)
  }
}
