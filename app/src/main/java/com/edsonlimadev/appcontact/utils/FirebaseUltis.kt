package com.edsonlimadev.appcontact.utils

import com.google.firebase.auth.FirebaseAuth

fun getCurrentUser(): FirebaseAuth = FirebaseAuth.getInstance()

fun isUserLoggedIn(): Boolean = getCurrentUser().currentUser != null

fun getUserUid(): String? = getCurrentUser().currentUser?.uid