package com.vaibhav.notesappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vaibhav.notesappcompose.ui.composables.LoginScreen
import com.vaibhav.notesappcompose.ui.composables.screens.*
import com.vaibhav.notesappcompose.ui.theme.NotesAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "loginScreen") {
                    composable("collectionScreen") { CollectionsScreen(navController) }
                    composable("loginScreen") { LoginScreen(navController) }
                    composable("registerScreen") { RegisterScreen(navController) }
                    composable("noteScreen") { NoteScreen(navController) }
                    composable("addNoteScreen") { AddNoteScreen(navController) }
                    composable("profileScreen") { ProfileScreen(navController = navController) }
                }
            }
        }
    }
}
