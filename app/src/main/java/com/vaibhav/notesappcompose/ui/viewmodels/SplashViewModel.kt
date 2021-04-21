package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.vaibhav.notesappcompose.data.repo.preferences.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val preferencesRepo: PreferencesRepo) :
    ViewModel() {


    val isUserLoggedIn = preferencesRepo.isUserLoggedIn()
}