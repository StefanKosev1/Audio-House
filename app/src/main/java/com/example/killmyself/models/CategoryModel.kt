package com.example.killmyself.models

data class CategoryModel(
   val name : String,
   val coverUrl : String,
) {
    constructor() : this(" "," ")
}
