package com.kaplan.ktp_mobile_kmm.database

import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KmmDatabase.Schema, "KaplanSQLDatabase.db")
    }
}