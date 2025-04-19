package com.polarize.user

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polarize.R

class UserWalletActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private lateinit var listView: ListView
    private val coupons = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_wallet)

        listView = findViewById(R.id.walletListView)

        userId?.let { uid ->
            db.collection("wallets").document(uid)
                .collection("coupons")
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        coupons.add(doc.getString("title") ?: "Unnamed Coupon")
                    }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, coupons)
                    listView.adapter = adapter
                }
        }
    }
}
