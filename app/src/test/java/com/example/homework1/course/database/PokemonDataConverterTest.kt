package com.example.homework1.course.database

import com.example.homework1.course.models.PokemonData
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PokemonDataConverterTest {

    var testObject: PokemonDataConverter? = null

    @Before
    fun setUp() {
        testObject = PokemonDataConverter()
    }

    @After
    fun tearDown() {
        testObject = null
    }

    @Test
    fun stringToListOfObjectsTestNull() {
        assertEquals(null, testObject!!.stringToListOfObjects(null))
    }

    @Test
    fun stringToListOfObjectsTestEmpty() {
        assertEquals(null, testObject!!.stringToListOfObjects(""))
    }

    @Test
    fun stringToListOfObjectsTestOkay() {
        val okayJson = PokemonDataConverterTest::class.java.getResource("/com.example.homework1/course.views/charmander.json")!!.readText()
        val retVal = testObject!!.stringToListOfObjects(okayJson)
        assertTrue(retVal is PokemonData)
    }

    @Test(expected = com.google.gson.JsonSyntaxException::class)
    fun stringToListOfObjectsTestException() {
        // Get Pokemon Data.
        val okayJson = ":/"
        testObject!!.stringToListOfObjects(okayJson)
    }

    @Test
    fun listOfObjectsToStringOk() {
        // Get Pokemon Data.
        val okayJson = PokemonDataConverterTest::class.java.getResource("/com.example.homework1/course.views/charmander.json")!!.readText()
        val retVal = testObject!!.stringToListOfObjects(okayJson)
        assertTrue(retVal is PokemonData)

        val retVal2 = testObject!!.listOfObjectsToString(retVal!!)
        assertNotEquals("", retVal2)
    }


}