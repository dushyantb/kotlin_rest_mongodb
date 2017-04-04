package com.dushyant.kotlin.models

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by dushyant on 04/04/17.
 */
@Transactional
interface NotesRepository : MongoRepository<Notes, String> {
    fun findByTitle(title: String): List<Notes>
}