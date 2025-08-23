package com.example.discountclub

import com.example.discountclub.data.remote.PurchasesRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class PurchasesRemoteDataSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dataSource =
        PurchasesRemoteDataSource(UnconfinedTestDispatcher())
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    @Test
    fun `getPurchases should return correct number of purchases`() = runTest {
        // When
        val result = dataSource.getPurchases()

        // Then
        assertEquals(5, result.size)
    }

    @Test
    fun `getPurchases should return purchases with correct dates`() = runTest {
        // When
        val result = dataSource.getPurchases()

        // Then
        val expectedDates = listOf(
            dateFormat.parse("2022-09-10T21:55:33Z"),
            dateFormat.parse("2022-09-10T21:50:33Z"),
            dateFormat.parse("2022-09-08T01:55:33Z"),
            dateFormat.parse("2022-09-07T21:55:33Z"),
            dateFormat.parse("2022-09-07T11:55:33Z")
        )

        result.forEachIndexed { index, purchase ->
            assertEquals(expectedDates[index], purchase.date)
        }
    }

    @Test
    fun `getPurchases should return purchases with correct names`() = runTest {
        // When
        val result = dataSource.getPurchases()

        // Then
        val expectedNames = listOf(
            listOf("123", "321"),
            listOf("1234", "4321"),
            listOf("12345", "54321"),
            listOf("123456", "654321"),
            listOf("1234567", "7654321")
        )

        result.forEachIndexed { index, purchase ->
            assertEquals(expectedNames[index], purchase.name)
        }
    }
}