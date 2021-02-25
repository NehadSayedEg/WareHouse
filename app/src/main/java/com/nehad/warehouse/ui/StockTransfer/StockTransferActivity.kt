package com.nehad.warehouse.ui.StockTransfer

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
import com.nehad.warehouse.ui.IssueActivity.IssueViewModel
import com.nehad.warehouse.ui.IssueActivity.IssueViewModelFactory
import kotlinx.android.synthetic.main.activity_issue.*
import kotlinx.android.synthetic.main.activity_stock_recevie.*
import kotlinx.android.synthetic.main.activity_stock_transfer.*
import kotlinx.android.synthetic.main.activity_stock_transfer.itemCheckbox
import kotlinx.android.synthetic.main.activity_stock_transfer.itemEd
import kotlinx.android.synthetic.main.activity_stock_transfer.itemSpinner
import kotlinx.android.synthetic.main.activity_stock_transfer.qtyCheckbox
import kotlinx.android.synthetic.main.activity_stock_transfer.qtyEd
import kotlinx.android.synthetic.main.activity_stock_transfer.qtySpinner
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class StockTransferActivity : AppCompatActivity() {
    var fromStore:String =""
    var toStore:String =""
    var qty:String =""
    var item:String =""


    lateinit var stockTransferViewModel: StockTransferViewModel
    val fileName: String = "StockTransferInvoice.pdf"
    var storeId :String =""
    var fromstoreName :String =""
    var shelfName :String =""
    var itemName :String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_transfer)


        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = StockTransferViewModelFactory(repository)
        stockTransferViewModel = ViewModelProvider(this, factory).get(StockTransferViewModel::class.java)

        val fromStoreSpinner = findViewById<Spinner>(R.id.fromStoreSpinner)
        val fromStoreSpinnerAdapter = ArrayAdapter<Any>(this@StockTransferActivity,
            android.R.layout.simple_spinner_item)

        val toStoreSpinner = findViewById<Spinner>(R.id.toStoreSpinner)
        val toStoreSpinnerAdapter = ArrayAdapter<Any>(this@StockTransferActivity,
            android.R.layout.simple_spinner_item)

        stockTransferViewModel.getStoreByName().observe(this, Observer {

            it?.forEach {
                fromStoreSpinnerAdapter.add(it)
            }

        })
        fromStoreSpinner.adapter = fromStoreSpinnerAdapter



        fromStoreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    fromstoreName = parent.getItemAtPosition(position).toString()
                    Toast.makeText(applicationContext, fromstoreName , Toast.LENGTH_LONG).show()
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        fromStoreCheckbox.setOnClickListener {
            if (fromStoreCheckbox.isChecked) {
                fromStoreEd.visibility = View.VISIBLE
                fromStoreBtn.visibility = View.VISIBLE
                fromTv.visibility = View.INVISIBLE
                fromStoreSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                fromStore =  fromStoreEd.text.toString()
            }
            if (!fromStoreCheckbox.isChecked) {
                fromStoreEd.visibility = View.INVISIBLE
                fromStoreBtn.visibility =View.INVISIBLE

                fromTv.visibility = View.VISIBLE
                fromStoreSpinner.visibility = View.VISIBLE
            }

        }

        toStoreCheckbox.setOnClickListener {
            if (toStoreCheckbox.isChecked) {
                toStoreEd.visibility = View.VISIBLE
                toStoreBtn.visibility = View.VISIBLE
                toTv.visibility = View.INVISIBLE
                toStoreSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                toStore =  toStoreEd.text.toString()
            }
            if (!toStoreCheckbox.isChecked) {
                toStoreEd.visibility = View.INVISIBLE
                toStoreBtn.visibility =View.INVISIBLE

                toTv.visibility = View.VISIBLE
                toStoreSpinner.visibility = View.VISIBLE
            }

        }

        itemCheckbox.setOnClickListener {
            if (itemCheckbox.isChecked) {
                itemEd.visibility = View.VISIBLE
                addItemBtn.visibility = View.VISIBLE
                itemNameTV.visibility = View.INVISIBLE
                itemSpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                item =  itemEd.text.toString()
            }
            if (!itemCheckbox.isChecked) {
                itemEd.visibility = View.INVISIBLE
                addItemBtn.visibility =View.INVISIBLE

                itemNameTV.visibility = View.VISIBLE
                itemSpinner.visibility = View.VISIBLE
            }

        }

        qtyCheckbox.setOnClickListener {
            if (qtyCheckbox.isChecked) {
                qtyEd.visibility = View.VISIBLE
                addQtyBtn.visibility = View.VISIBLE
                QtyTv.visibility = View.INVISIBLE
                qtySpinner.visibility = View.INVISIBLE
//                shelfSpinnerAdapter.clear()
                qty =  qtyEd.text.toString()
            }
            if (!qtyCheckbox.isChecked) {
                qtyEd.visibility = View.INVISIBLE
                addQtyBtn.visibility =View.INVISIBLE
                QtyTv.visibility = View.VISIBLE
                qtySpinner.visibility = View.VISIBLE
            }

        }




        Dexter.withContext(this)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    transferInvoiceBtn.setOnClickListener {

                        createPDFFile(Common.getAppPath(this@StockTransferActivity) + fileName)


                    }
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
                    TODO("Not yet implemented")
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
            addNewItem(document , fromstoreName , Element.ALIGN_LEFT , valueStyle)
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
            val printAdapter = PdfDocumentAdapter(this@StockTransferActivity, Common.getAppPath(this@StockTransferActivity)+fileName)
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