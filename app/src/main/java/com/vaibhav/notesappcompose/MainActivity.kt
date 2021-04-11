package com.vaibhav.notesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vaibhav.notesappcompose.composables.LoginScreen
import com.vaibhav.notesappcompose.composables.screens.AddNoteScreen
import com.vaibhav.notesappcompose.composables.screens.CollectionsScreen
import com.vaibhav.notesappcompose.composables.screens.NoteScreen
import com.vaibhav.notesappcompose.composables.screens.RegisterScreen
import com.vaibhav.notesappcompose.ui.theme.NotesAppComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "addNoteScreen") {
                    composable("collection") { CollectionsScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("noteScreen") { NoteScreen(navController) }
                    composable("addNoteScreen") { AddNoteScreen(navController) }
                }
            }
        }
    }
}
