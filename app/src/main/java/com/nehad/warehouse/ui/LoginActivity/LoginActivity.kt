package com.nehad.warehouse.ui.LoginActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.ui.DashBoardActivity.DashBoardActivity
import com.nehad.warehouse.R
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.AllData
import com.nehad.warehouse.network.ApiService
import com.nehad.warehouse.network.Client
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var database: WareHouseDB
    private lateinit var repository: WareHouseRepository
    private lateinit var factory: LoginViewModelFactory
    lateinit var sharedPreferences: SharedPreferences

    var isRemember = false
     var loginValue  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        database = WareHouseDB.getInstance(this)
        repository = WareHouseRepository(database.wareHouseDao)
        factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]


        sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE)
        isRemember = sharedPreferences.getBoolean("CheckBox", false)
        if (isRemember) {
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }


        signInBtn.setOnClickListener {
            val username = userName_ed.text.toString()
            val password = password_ed.text.toString()
            val checked = checkbox.isChecked


            if (userName_ed.text != null && password_ed.text != null) {
                viewModel.getAllUsers()

                GlobalScope.launch(Dispatchers.IO) {
                     loginValue = viewModel.login(username, password)

                  //  viewModel.login(username, password)

                  //  Log.e("Login :" , viewModel.login(username,password).toString()  )
                }
                if(loginValue == true){
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.putBoolean("CheckBox", checked)
                    editor.apply()



                    Toast.makeText(applicationContext, "Information Saved", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
//                    finish()
                }
                else{
                    Toast.makeText(applicationContext, "Please Enter a valid User Name And Password ", Toast.LENGTH_LONG).show()

                }



            }




        }


        downloadBtn.setOnClickListener {
            loadData()
        }


    }


    private fun loadData() {

        progressbar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiservice: ApiService =
                    Client.getRetrofitInstance().create(ApiService::class.java)

                val response: Response<AllData> = apiservice.getData().awaitResponse()

                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.e(" User name ", data.data.users.get(1).userNameAr + "")

                    val userList = data.data.users
                    val userGroupList = data.data.usergroup
                    val itemList = data.data.items
                    val shelfList = data.data.shelves
                    val storeList = data.data.stores
                    val storeTypeList = data.data.storetypes
                    val documentTypeList = data.data.documenttypes


                    withContext(Dispatchers.IO) {
                        viewModel.insertUsers(userList)
                        viewModel.insertItems(itemList)
                        viewModel.insertUserGroup(userGroupList)
                        viewModel.insertDocumentType(documentTypeList)
                        viewModel.insertStoreType(storeTypeList)
                        viewModel.insertStore(storeList)
                        viewModel.insertSelf(shelfList)


                    }



                    withContext(Dispatchers.Main) {
                        progressbar.visibility = View.INVISIBLE


                    }
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext, "Something went wrong with internet connection",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


}