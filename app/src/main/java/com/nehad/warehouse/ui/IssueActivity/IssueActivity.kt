package com.nehad.warehouse.ui.IssueActivity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfDocument
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.itextpdf.text.pdf.draw.VerticalPositionMark
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.nehad.warehouse.PdfDocumentAdapter
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Common
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.StockheaderDoc
import com.nehad.warehouse.ui.balance.BalanceActivity
import kotlinx.android.synthetic.main.activity_issue.*
import kotlinx.android.synthetic.main.activity_issue.view.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_stock_recevie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log


class IssueActivity : AppCompatActivity() {

    lateinit var issueViewModel: IssueViewModel
    val fileName: String = "IssueInvoice.pdf"
    var storeId :String =""
    var storeName :String =""
    var shelfName :String =""
    var itemName :String =""


    var fromStore:String =""
    var toStore:String =""
    var qty:String =""
    var item:String =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue)

        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = IssueViewModelFactory(repository)
        issueViewModel = ViewModelProvider(this, factory).get(IssueViewModel::class.java)

        val storeSpinner = findViewById<Spinner>(R.id.SpinnerStore)
        val storeSpinnerAdapter = ArrayAdapter<Any>(this@IssueActivity,
                android.R.layout.simple_spinner_item)

        val shelfSpinner = findViewById<Spinner>(R.id.shelfSpinner)
        val shelfSpinnerAdapter = ArrayAdapter<Any>(this@IssueActivity,
                android.R.layout.simple_spinner_item)
//

        issueViewModel.getStoreByName().observe(this, Observer {

            it?.forEach {
                storeSpinnerAdapter.add(it)
            }
        })
        storeSpinner.adapter = storeSpinnerAdapter




        storeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                if (parent != null) {
                    storeName = parent.getItemAtPosition(position).toString()

                    GlobalScope.launch  (Dispatchers.IO){
                        storeId =  issueViewModel.getStoreID(storeName).toString()

                        Log.e("store Id IO" , storeId)


                    }
                    GlobalScope.launch (Dispatchers.Main){
                        Toast.makeText(applicationContext, storeId, Toast.LENGTH_LONG).show()

                        shelfSpinnerAdapter.clear()

                        issueViewModel.getShelfByStoreID(storeId).observe(this@IssueActivity , Observer {
                            it?.forEach {
                                Log.e("shelf list size", it.length.toString())
                                shelfSpinnerAdapter.add(it)
                            }
                        })
                        shelfSpinner.adapter = shelfSpinnerAdapter
                    }


                    Toast.makeText(applicationContext, storeName , Toast.LENGTH_LONG).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        shelfSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                if (parent != null) {
                    shelfName = parent.getItemAtPosition(position).toString()




                    Toast.makeText(applicationContext, shelfName, Toast.LENGTH_LONG).show()
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

                Log.e("shelf ", "Nothing is selscted")

            }

        }
          itemsSpinner()


        itemCheckbox.setOnClickListener {

            if (itemCheckbox.isChecked) {
                itemEd.visibility = View.VISIBLE
                itemSpinner.visibility = View.INVISIBLE




            }

            if (!itemCheckbox.isChecked) {
                itemEd.visibility = View.INVISIBLE
                itemSpinner.visibility = View.VISIBLE
            }
        }

        shelfCheckbox.setOnClickListener {

            if (shelfCheckbox.isChecked) {
                shelfEd.visibility = View.VISIBLE
                shelfSpinner.visibility = View.INVISIBLE
                shelfSpinnerAdapter.clear()
                shelfName =  shelfEd.text.toString()

            }

            if (!shelfCheckbox.isChecked) {
                shelfEd.visibility = View.INVISIBLE
                shelfSpinner.visibility = View.VISIBLE
            }
        }

        qtyCheckbox.setOnClickListener {

            if (qtyCheckbox.isChecked) {
                qtyEd.visibility = View.VISIBLE
                qtySpinner.visibility = View.INVISIBLE
            }

            if (!qtyCheckbox.isChecked) {
                qtyEd.visibility = View.INVISIBLE
                qtySpinner.visibility = View.VISIBLE
            }
        }

        saveIssueBtn.setOnClickListener {
            Log.e("fromStore", "fromStore")
            fromStore = "2"
            toStore ="2"
            Log.e("fromStore", fromStore)
            Log.e("toStore", toStore)
//            Log.e("qty", qty)
//            Log.e("item", item)
//            Log.e("storeId", storeId)

//            Log.e("storeName", storeName)
//            Log.e("shelfName", shelfName)
//            Log.e("itemName", itemName)
            val documentType = "2"



            GlobalScope.launch(Dispatchers.IO) {
                var stockCountHeader = StockheaderDoc()


                val  currentTime = System.currentTimeMillis()
                val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
                val resultdate = Date(currentTime)
                val timeFormate = sdf.format(resultdate)

                Log.e(" Timeformate " ,timeFormate )


                var documentHeader = DocumentHeader()
                documentHeader.storeToId = toStore
                documentHeader.storeFromId = fromStore
                documentHeader.documentTypeId = documentType
                documentHeader.createdDate = currentTime.toString()

                issueViewModel.insertDocumentHeader(documentHeader)

                Log.e("Login :" ,  issueViewModel.insertDocumentHeader(documentHeader).toString())


            }
            GlobalScope.launch(Dispatchers.Main) {
//                Log.e("item", item)
                issueViewModel.getAlldocuments().observe( this@IssueActivity  ,
                        Observer {

                            Log.e("Size :" ,   it.size.toString())



                        })
            }


        }

        Dexter.withContext(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                        makeInvoiceBtn.setOnClickListener {

                            createPDFFile(Common.getAppPath(this@IssueActivity) + fileName)


                        }
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    }

                    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
                    }

                })
                .check();

    }

    private fun itemsSpinner() {

        val itemsSpinner = findViewById<Spinner>(R.id.itemSpinner)
        val itemSpinnerAdapter = ArrayAdapter<Any>(this@IssueActivity,
            android.R.layout.simple_spinner_item)
        issueViewModel.getItemByName().observe(this, Observer {

            it?.forEach {
                itemSpinnerAdapter.add(it)
            }

        })
        itemsSpinner.adapter = itemSpinnerAdapter



        itemsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                     itemName = parent.getItemAtPosition(position).toString()
                    Toast.makeText(applicationContext, itemName , Toast.LENGTH_LONG).show()
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun ShelfSpinner() {


    }

 //   private fun StoreSpinner() {
//
//        val storeSpinner = findViewById<Spinner>(R.id.SpinnerStore)
//        val storeSpinnerAdapter = ArrayAdapter<Any>(this@IssueActivity,
//                android.R.layout.simple_spinner_item)
//
////        val shelfSpinner = findViewById<Spinner>(R.id.shelfSpinner)
////        val shelfSpinnerAdapter = ArrayAdapter<Any>(this@IssueActivity,
////                android.R.layout.simple_spinner_item)
////
//
//        issueViewModel.getStoreByName().observe(this, Observer {
//
//            it?.forEach {
//                storeSpinnerAdapter.add(it)
//            }
//        })
//        storeSpinner.adapter = storeSpinnerAdapter
//
//
//
//
//        storeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                if (parent != null) {
//                    storeName = parent.getItemAtPosition(position).toString()
//
//                    GlobalScope.launch  (Dispatchers.IO){
//                        storeId =  issueViewModel.getStoreID(storeName).toString()
//
//                        Log.e("store Id IO" , storeId)
//
//
//                    }
//                    GlobalScope.launch (Dispatchers.Main){
//                        Toast.makeText(applicationContext, storeId, Toast.LENGTH_LONG).show()
//
//                        shelfSpinnerAdapter.clear()
//
//                        issueViewModel.getShelfByStoreID(storeId).observe(this@IssueActivity , Observer {
//                            it?.forEach {
//                                Log.e("shelf list size", it.length.toString())
//                                shelfSpinnerAdapter.add(it)
//                            }
//                        })
//                        shelfSpinner.adapter = shelfSpinnerAdapter
//                    }
//
//
//                    Toast.makeText(applicationContext, storeName , Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//
//        shelfSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//            ) {
//                if (parent != null) {
//                    shelfName = parent.getItemAtPosition(position).toString()
//
//
//
//
//                    Toast.makeText(applicationContext, shelfName, Toast.LENGTH_LONG).show()
//                }
//            }
//
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//    }}



    private fun createPDFFile(path: String) {
        if (File(path).exists())
            File(path).delete()
        try {
            val document = Document()
            PdfWriter.getInstance(document , FileOutputStream(path))
            //open to write
            document.open()

            document.pageSize = PageSize.A4
            document.addCreationDate()
            document.addAuthor("StoreName")
            document.addCreator("UserName")


            //font setting
            val colorAccent = BaseColor(0 , 153, 204, 255)
            val headingFontSize = 20.0f
            val valueFontSize = 26.0f

            // custom font
            val fontName =BaseFont.createFont("assets/fonts/SourceSansPro-Regular.ttf", "UTF-8",BaseFont.EMBEDDED)
            //Add title to document

            var titleStyle = Font(fontName , 36.0f , Font.NORMAL ,BaseColor.BLACK )
            addNewItem(document , "Issue",Element.ALIGN_CENTER , titleStyle)

            val headingStyle=  Font(fontName ,headingFontSize , Font.NORMAL, colorAccent)
            addNewItem(document , "Store Name  ",Element.ALIGN_LEFT , headingStyle)

            val valueStyle=  Font(fontName ,valueFontSize , Font.NORMAL, BaseColor.BLACK)
            addNewItem(document , storeName ,Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addNewItem(document , "Order Date:",Element.ALIGN_LEFT , headingStyle)
            addNewItem(document , "  12345",Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addNewItem(document , "UserName:",Element.ALIGN_LEFT , headingStyle)
            addNewItem(document , "  Nehad",Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addLineSpace(document)

            addNewItem(document , "Issue Details",Element.ALIGN_CENTER , titleStyle)
            addLineSeperator(document)


            //shelf
            addNewItemWithLeftAndRight(document , "Shelf Name  " , shelfName , titleStyle , valueStyle)

            //item
            addNewItemWithLeftAndRight(document , "Item Name " , itemName, titleStyle , valueStyle)
            addLineSeperator(document)

            //Qty
            addNewItemWithLeftAndRight(document , "QTY " , "(0.0%)" , titleStyle , valueStyle)
            addLineSeperator(document)

            //Total
            addLineSpace(document)
            addLineSpace(document)


            document.close()

            Toast.makeText(this  , " Invoice successfully Created" ,Toast.LENGTH_LONG).show()

            printPDF()

        }


    catch (e:Exception){
        Log.e(" ErrorMessage" ,e.message.toString())

        }
    }

    private fun printPDF() {
        val printManager = getSystemService(Context.PRINT_SERVICE) as PrintManager
        try {
            val printAdapter = PdfDocumentAdapter(this@IssueActivity, Common.getAppPath(this@IssueActivity)+fileName)
            printManager.print("Invoice", printAdapter ,PrintAttributes.Builder().build())
        }catch (e:Exception){
            Log.e("Error Message ", e.message.toString())
        }
    }

    @Throws(DocumentException::class)
    private fun addNewItemWithLeftAndRight(document: Document, textLeft: String,
                                           textRight: String, leftStyle: Font ,
                                           rightStyle: Font) {
        val chunckTextLeft = Chunk(textLeft ,leftStyle)
        val chunckTextRight = Chunk(textRight ,rightStyle)
        val  p = Paragraph(chunckTextLeft)
        p.add(Chunk(VerticalPositionMark()))
        p.add(chunckTextRight)
        document.add(p)

    }

    @Throws(DocumentException::class)
    private fun addNewItem(document: Document, text : String, align: Int, style: Font) {
        val chunk = Chunk(text , style)
        val p = Paragraph(chunk)
        p.alignment = align
        document.add(p)



    }
    @Throws(DocumentException::class)
     private fun addLineSeperator(document: Document) {
         val lineSeparator = LineSeparator()
         lineSeparator.lineColor = BaseColor(0,0,0,68)

         addLineSpace(document)
         document.add(Chunk(lineSeparator))
         addLineSpace(document)

      }
    @Throws(DocumentException::class)
    private fun addLineSpace(document: Document) {
        document.add(Paragraph(""))

    }
}