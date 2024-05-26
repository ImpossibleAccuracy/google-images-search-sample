package com.googleimagesearch.feature.feed.data.database

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.googleimagesearch.feature.feed.data.FeedDatabase

fun createFeedDatabase(context: Context): FeedDatabase {
    val driver = AndroidSqliteDriver(FeedDatabase.Schema, context, "feed_database.db")

    return FeedDatabase(driver)
}
