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
                    composable("loginScreen") { LoginScreen(navController) }
                    composable("registerScreen") { RegisterScreen(navController) }
                    composable("collectionScreen") { CollectionsScreen(navController) }
                    composable("noteScreen/{collectionId}/{collectionName}") {
                        NoteScreen(
                            navController,
                            it.arguments?.getString("collectionId") ?: "0",
                            it.arguments?.getString("collectionName") ?: "Notes"
                        )
                    }
                    composable("addNoteScreen/{collectionId}") {
                        AddNoteScreen(
                            navController,
                            it.arguments?.getString("collectionId") ?: "0"
                        )
                    }
                    composable("profileScreen") { ProfileScreen(navController = navController) }
                }
            }
        }
    }
}
