package com.edsonlimadev.appcontact.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

fun getCurrentUser(): FirebaseAuth = FirebaseAuth.getInstance()
fun getFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
fun getStorage(): FirebaseStorage = FirebaseStorage.getInstance()

fun isUserLoggedIn(): Boolean = getCurrentUser().currentUser != null

fun getUserId(): String? = getCurrentUser().currentUser?.uid