package com.example.killmyself

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.killmyself.adapter.CategoryAdapter
import com.example.killmyself.databinding.ActivityHomeBinding
import com.example.killmyself.databinding.ActivitySignInBinding
import com.example.killmyself.models.CategoryModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        getCategories()

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)

            finish()
        }
    }



    fun getCategories() {
       FirebaseFirestore.getInstance().collection("category")
           .get().addOnSuccessListener {
               val categoryList = it.toObjects(CategoryModel::class.java)
               println(categoryList)
               setupCategoryRecyclerView(categoryList)
           }
    }


    fun setupCategoryRecyclerView(categoryList: List<CategoryModel>) {
        categoryAdapter = CategoryAdapter(categoryList)
        binding.cattegoriesRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.cattegoriesRecyclerView.adapter = categoryAdapter
    }
}