package com.nehad.warehouse.ui.stockRecevie

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
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
import com.nehad.warehouse.ui.IssueActivity.IssueViewModel
import com.nehad.warehouse.ui.IssueActivity.IssueViewModelFactory
import kotlinx.android.synthetic.main.activity_issue.*
import kotlinx.android.synthetic.main.activity_issue.shelfCheckbox
import kotlinx.android.synthetic.main.activity_stock_recevie.*
import kotlinx.android.synthetic.main.activity_stock_transfer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class StockRecevieActivity : AppCompatActivity() {

    lateinit var stockRecevieViewModel: StockRecevieViewModel
    var fromStore:String =""
    var toStore:String =""
    var qty:String =""
    var item:String =""

    val fileName: String = "ReceiveInvoice.pdf"
    var storeId :String =""
    var storeName :String =""
    var shelfName :String =""
    var itemName :String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_recevie)


        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = StockRecevieViewModelFactory(repository)
        stockRecevieViewModel = ViewModelProvider(this, factory).get(StockRecevieViewModel::class.java)

        val fromstoreSpinner = findViewById<Spinner>(R.id.recefromStoreSpinner)
        val fromstoreSpinnerAdapter = ArrayAdapter<Any>(this@StockRecevieActivity,
            android.R.layout.simple_spinner_item)

        val itemSpinner = findViewById<Spinner>(R.id.receitemSpinner)
        val itemSpinnerAdapter = ArrayAdapter<Any>(this@StockRecevieActivity,
            android.R.layout.simple_spinner_item)

        stockRecevieViewModel.getStoreByName().observe(this, Observer {

            it?.forEach {
                fromstoreSpinnerAdapter.add(it)
            }
        })
        fromstoreSpinner.adapter = fromstoreSpinnerAdapter

        saveReceiveBtn.setOnClickListener {
            Log.e("fromStore", "fromStore")
            fromStore = "1"
            toStore ="1"
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

                stockRecevieViewModel.insertDocumentHeader(documentHeader)

                Log.e("Login :" ,  stockRecevieViewModel.insertDocumentHeader(documentHeader).toString())


            }
            GlobalScope.launch(Dispatchers.Main) {
//                Log.e("item", item)
                stockRecevieViewModel.getAlldocuments().observe( this@StockRecevieActivity  ,
                    Observer {

                        Log.e("Size :" ,   it.size.toString())



                    })
            }


        }

        fromstoreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    storeName = parent.getItemAtPosition(position).toString()

                    GlobalScope.launch  (Dispatchers.IO){
                        storeId =  stockRecevieViewModel.getStoreID(storeName).toString()

                        Log.e("store Id IO" , storeId)


                    }
//                    GlobalScope.launch (Dispatchers.Main){
//                        Toast.makeText(applicationContext, storeId, Toast.LENGTH_LONG).show()
//
//                       // shelfSpinnerAdapter.clear()
//
//                        stockRecevieViewModel.getShelfByStoreID(storeId).observe(this@StockRecevieActivity , Observer {
//                            it?.forEach {
//                                Log.e("shelf list size", it.length.toString())
//                                shelfSpinnerAdapter.add(it)
//                            }
//                        })
//                        shelfSpinner.adapter = shelfSpinnerAdapter
//                    }


                    Toast.makeText(applicationContext, storeName , Toast.LENGTH_LONG).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

//        shelfSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
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
//
//                Log.e("shelf ", "Nothing is selscted")
//
//            }
//
//        }
//        itemsSpinner()



        recefromStoreCheckbox.setOnClickListener {
                    if (recefromStoreCheckbox.isChecked) {
                        recefromStoreEd.visibility = View.VISIBLE
                        recefromStoreBtn.visibility = View.VISIBLE
                        recefromTv.visibility = View.INVISIBLE
                        recefromStoreSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                        fromStore =  recefromStoreEd.text.toString()
                    }
                    if (!recefromStoreCheckbox.isChecked) {
                        recefromStoreEd.visibility = View.INVISIBLE
                        recefromStoreBtn.visibility =View.INVISIBLE

                        recefromTv.visibility = View.VISIBLE
                        recefromStoreSpinner.visibility = View.VISIBLE
                    }

                }

        recetoStoreCheckbox.setOnClickListener {
                    if (recetoStoreCheckbox.isChecked) {
                        recetoStoreEd.visibility = View.VISIBLE
                        recetoStoreBtn.visibility = View.VISIBLE
                        recetoTv.visibility = View.INVISIBLE
                        recetoStoreSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                        toStore =  recetoStoreEd.text.toString()
                    }
                    if (!recetoStoreCheckbox.isChecked) {
                        recetoStoreEd.visibility = View.INVISIBLE
                        recetoStoreBtn.visibility =View.INVISIBLE

                        recetoTv.visibility = View.VISIBLE
                        recetoStoreSpinner.visibility = View.VISIBLE
                    }

                }

        receitemCheckbox.setOnClickListener {
                    if (receitemCheckbox.isChecked) {
                        receitemEd.visibility = View.VISIBLE
                        receaddItemBtn.visibility = View.VISIBLE
                        receitemNameTV.visibility = View.INVISIBLE
                        receitemSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                        item =  receitemEd.text.toString()
                    }
                    if (!receitemCheckbox.isChecked) {
                        receitemEd.visibility = View.INVISIBLE
                        receaddItemBtn.visibility =View.INVISIBLE

                        receitemNameTV.visibility = View.VISIBLE
                        receitemSpinner.visibility = View.VISIBLE
                    }

                }

        receqtyCheckbox.setOnClickListener {
                    if (receqtyCheckbox.isChecked) {
                        receqtyEd.visibility = View.VISIBLE
                        receaddQtyBtn.visibility = View.VISIBLE
                        receQtyTv.visibility = View.INVISIBLE
                        receqtySpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                        qty =  receqtyEd.text.toString()
                    }
                    if (!receqtyCheckbox.isChecked) {
                        receqtyEd.visibility = View.INVISIBLE
                        receaddQtyBtn.visibility =View.INVISIBLE
                        receQtyTv.visibility = View.VISIBLE
                        receqtySpinner.visibility = View.VISIBLE
                    }

                }



        Dexter.withContext(this)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    receInvoiceBtn.setOnClickListener {

                        createPDFFile(Common.getAppPath(this@StockRecevieActivity) + fileName)


                    }
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                }

                override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
                }

            })
            .check();
    }



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
            val fontName =
                BaseFont.createFont("assets/fonts/SourceSansPro-Regular.ttf", "UTF-8", BaseFont.EMBEDDED)
            //Add title to document

            var titleStyle = Font(fontName , 36.0f , Font.NORMAL , BaseColor.BLACK )
            addNewItem(document , "Issue", Element.ALIGN_CENTER , titleStyle)

            val headingStyle=  Font(fontName ,headingFontSize , Font.NORMAL, colorAccent)
            addNewItem(document , "Store Name  ", Element.ALIGN_LEFT , headingStyle)

            val valueStyle=  Font(fontName ,valueFontSize , Font.NORMAL, BaseColor.BLACK)
            addNewItem(document , storeName , Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addNewItem(document , "Order Date:", Element.ALIGN_LEFT , headingStyle)
            addNewItem(document , "  12345", Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addNewItem(document , "UserName:", Element.ALIGN_LEFT , headingStyle)
            addNewItem(document , "  Nehad", Element.ALIGN_LEFT , valueStyle)
            addLineSeperator(document)

            addLineSpace(document)

            addNewItem(document , "Issue Details", Element.ALIGN_CENTER , titleStyle)
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

            Toast.makeText(this  , " Invoice successfully Created" , Toast.LENGTH_LONG).show()

            printPDF()

        }


        catch (e: Exception){
            Log.e(" ErrorMessage" ,e.message.toString())

        }
    }

    private fun printPDF() {
        val printManager = getSystemService(Context.PRINT_SERVICE) as PrintManager
        try {
            val printAdapter = PdfDocumentAdapter(this@StockRecevieActivity, Common.getAppPath(this@StockRecevieActivity)+fileName)
            printManager.print("Invoice", printAdapter , PrintAttributes.Builder().build())
        }catch (e: Exception){
            Log.e("Error Message ", e.message.toString())
        }
    }

    @Throws(DocumentException::class)
    private fun addNewItemWithLeftAndRight(document: Document, textLeft: String,
                                           textRight: String, leftStyle: Font,
                                           rightStyle: Font
    ) {
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