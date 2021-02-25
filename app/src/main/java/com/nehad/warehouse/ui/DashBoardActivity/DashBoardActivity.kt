package com.nehad.warehouse.ui.DashBoardActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nehad.warehouse.R
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.ui.IssueActivity.IssueActivity
import com.nehad.warehouse.ui.ItemsActivity
import com.nehad.warehouse.ui.StockTransfer.StockTransferActivity
import com.nehad.warehouse.ui.balance.BalanceActivity
import com.nehad.warehouse.ui.stockRecevie.StockRecevieActivity
import com.nehad.warehouse.ui.storesActivity.StoresActivity
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    private lateinit var database: WareHouseDB
    private lateinit var repository: WareHouseRepository
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("username", "")
        user_name.setText(name)


        issuesCard.setOnClickListener {

            val intent = Intent(this, IssueActivity::class.java)
            startActivity(intent)
      }

        transferCard.setOnClickListener {

            val intent = Intent(this,StockTransferActivity ::class.java)
            startActivity(intent)
        }

        storesCard.setOnClickListener {
            val intent = Intent(this, StoresActivity::class.java)
            startActivity(intent)
        }

        reportsCard.setOnClickListener {
            val intent = Intent(this, StoresActivity::class.java)
            startActivity(intent)
        }

        itemsCard.setOnClickListener {

            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        balanceCard.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            startActivity(intent)
        }

        receiveCard.setOnClickListener {
             val intent = Intent(this, StockRecevieActivity::class.java)
             startActivity(intent)
         }



    }
}