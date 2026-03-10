package com.n.alian.volleyassignment

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.volley.toolbox.StringRequest
import com.n.alian.volleyassignment.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var usersAdapter: UsersAdapter
    lateinit var usersList: ArrayList<Users>
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Pleas Wait a sec..")
        progressDialog.setCancelable(false)
        getUsers()
    }

    private fun getUsers() {
        usersList = ArrayList()
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
            URLs.GET_USERS,

            { response ->
                //  Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                val jsonArray = JSONArray(response)
                for (i in 0 until 20) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    var completed = jsonObject.getBoolean("completed")
                    usersList.add(
                        Users(
                            jsonObject.getString("title"),
                            jsonObject.getBoolean("completed")
                        )
                    )
                }
                usersAdapter = UsersAdapter(this, usersList)
                binding.recycle.adapter = usersAdapter
                binding.recycle.layoutManager = LinearLayoutManager(this)
            },
            { error ->
                9
                progressDialog.dismiss()
                Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
            })
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

}