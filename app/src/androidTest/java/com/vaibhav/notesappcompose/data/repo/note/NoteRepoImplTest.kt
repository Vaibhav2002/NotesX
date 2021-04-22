package com.vaibhav.notesappcompose.data.repo.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.vaibhav.notesappcompose.data.local.DataBase
import com.vaibhav.notesappcompose.data.local.NoteDao
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.repo.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteRepoImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DataBase
    private lateinit var dao: NoteDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DataBase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.noteDao
    }


    @Test
    fun insertIntoDbTest() = runBlocking {
        val note = Note("hello", false)
        dao.insertNotes(listOf(note))
        val allNotes = dao.getAllNotes("").getOrAwaitValue()
        assertThat(allNotes).contains(note)
    }

    @After
    fun teardown() {
        database.close()
    }


}

