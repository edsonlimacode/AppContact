package com.edsonlimadev.appcontact.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.appcontact.domain.model.Profile
import com.edsonlimadev.appcontact.domain.usecase.profile.GetProfileByUseCase
import com.edsonlimadev.appcontact.domain.usecase.profile.SaveImageProfileUseCase
import com.edsonlimadev.appcontact.utils.getUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val saveImageProfileUseCase: SaveImageProfileUseCase,
    private val getProfileByUseCase: GetProfileByUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        getProfile()
    }

    fun saveImageProfile(imageUri: Uri) = viewModelScope.launch {
        _uiState.value = ProfileUiState.Loading
        saveImageProfileUseCase(imageUri).fold(
            onSuccess = {
                _uiState.value = ProfileUiState.Success(Profile(
                    image = it
                ))
            },
            onFailure = {
                Log.i("storage", "Storage erro: ${it.message}")
            }
        )
    }

    private fun getProfile() = viewModelScope.launch {

        getProfileByUseCase(getUserId().toString()).fold(
            onSuccess = { profile ->
                _uiState.value = ProfileUiState.Success(profile)
            },
            onFailure = {
                _uiState.value = ProfileUiState.Error(it.message)
            }
        )
    }

}