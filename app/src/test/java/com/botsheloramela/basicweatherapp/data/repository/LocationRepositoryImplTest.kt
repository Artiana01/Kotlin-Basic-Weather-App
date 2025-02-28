package com.botsheloramela.basicweatherapp.data.repository

import android.content.Context
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LocationRepositoryImplTest {
    @Mock
    private lateinit var mockLocationProvider: FusedLocationProviderClient

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockLocation: Location

    @Mock
    private lateinit var mockTask: Task<Location>

    private lateinit var locationRepository: LocationRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        locationRepository = LocationRepositoryImpl(mockLocationProvider, mockContext)
    }

    @Test
    fun `getDeviceLocation returns location when permission granted and location available`() = runTest  {
        // Given
        mockPermissionGranted()
        mockLocationAvailable()

        val result = locationRepository.getDeviceLocation()

        assert(result == mockLocation)
    }

    @Test
    fun `getDeviceLocation returns null when permission not granted`() = runTest  {
        // Given
        mockPermissionDenied()

        val result = locationRepository.getDeviceLocation()

        assert(result == null)
    }

    @Test
    fun `getDeviceLocation returns null when permissions granted but location not available`() = runTest  {
        // Given
        mockPermissionGranted()
        mockLocationUnavailable()

        val result = locationRepository.getDeviceLocation()

        assert(result == null)
    }

    @Test(expected = Exception::class)
    fun `getDeviceLocation throws exception when location provider fails`() = runBlockingTest {
        // Given
        mockPermissionGranted()
        mockLocationProviderFailure()

        locationRepository.getDeviceLocation()
    }

    private fun mockPermissionGranted() {
        `when`(ContextCompat.checkSelfPermission(
            mockContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )).thenReturn(android.content.pm.PackageManager.PERMISSION_GRANTED)
    }

    private fun mockPermissionDenied() {
        `when`(ContextCompat.checkSelfPermission(
            mockContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )).thenReturn(android.content.pm.PackageManager.PERMISSION_DENIED)
    }

    private fun mockLocationAvailable() {
        `when`(mockLocationProvider.lastLocation).thenReturn(mockTask)
        `when`(mockTask.addOnSuccessListener(any())).thenAnswer {
            val listener = it.arguments[0] as OnSuccessListener<Location>
            listener.onSuccess(mockLocation)
            mockTask
        }
    }

    private fun mockLocationUnavailable() {
        `when`(mockLocationProvider.lastLocation).thenReturn(mockTask)
        `when`(mockTask.addOnSuccessListener(any())).thenAnswer {
            val listener = it.arguments[0] as OnSuccessListener<Location>
            listener.onSuccess(null)
            mockTask
        }
    }

    private fun mockLocationProviderFailure() {
        `when`(mockLocationProvider.lastLocation).thenReturn(mockTask)
        `when`(mockTask.addOnFailureListener(any())).thenAnswer {
            val listener = it.arguments[0] as (Exception) -> Unit
            listener(Exception("Location provider failed"))
            mockTask
        }
    }
}