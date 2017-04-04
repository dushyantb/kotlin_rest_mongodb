package com.dushyant.kotlin.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by dushyant on 04/04/17.
 */

@Document
data class Notes (@Id var id: String? = null, var title: String? = "", var details: String? = "")
