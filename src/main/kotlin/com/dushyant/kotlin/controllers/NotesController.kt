package com.dushyant.kotlin.controllers

import com.dushyant.kotlin.models.Notes
import com.dushyant.kotlin.models.NotesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.HashMap

/**
 * Created by dushyant on 04/04/17.
 */
@RestController
@RequestMapping(value = "/notes")
class NotesController {
    @Autowired
    private val repository: NotesRepository? = null

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getAll(): Map<String, Any> {
        val notes = repository!!.findAll()
        val dataMap = HashMap<String, Any>()
        dataMap.put("message", "Note found successfully")
        dataMap.put("totalNotes", notes.size)
        dataMap.put("status", "1")
        dataMap.put("notes", notes)
        return dataMap
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody note: Notes): Map<String, Any> {
        val note = repository?.save(note)
        val dataMap = HashMap<String, Any>()
        dataMap.put("message", "Note Created Successfully")
        dataMap.put("status", "1")
        dataMap.put("notes", note!!)
        return dataMap
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.PUT))
    fun update(@PathVariable id: String, @RequestBody note: Notes): Map<String, Any> {

        var noteDB = repository?.findOne(id)
        val dataMap = HashMap<String, Any>()

        if (noteDB == null) {
            dataMap.put("message", "No Note Found with this ID")
            dataMap.put("status", "0")
        } else {
            var updateFlag = false
            if(!note.title.isNullOrBlank()) {
                noteDB.title = note.title
                updateFlag = true
            }

            if(!note.details.isNullOrBlank()) {
                noteDB.details = note.details
                updateFlag = true
            }

            if(updateFlag) {
                noteDB = repository?.save(noteDB)
                dataMap.put("message", "Note Updated Successfully")
                dataMap.put("status", "1")
                dataMap.put("notes", noteDB!!)
            } else {
                dataMap.put("message", "Nothing to Update")
                dataMap.put("status", "1")
            }
        }
        return dataMap
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id: String): Map<String, Any> {

        val dataMap = HashMap<String, Any>()

        val note = repository?.findOne(id)

        if (note == null) {
            dataMap.put("message", "No Note Found with this ID")
            dataMap.put("status", "0")
        } else {
            val deltedNote = repository?.delete(id)
            dataMap.put("message", "Note Deleted Successfully")
            dataMap.put("status", "1")
            dataMap.put("notes", deltedNote!!)
        }
        return dataMap
    }
}