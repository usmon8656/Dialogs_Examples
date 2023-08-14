package com.example.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import com.example.dialogs.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
         btnAlter.setOnClickListener {
             val dialog = AlertDialog.Builder(this@MainActivity)
             dialog.setTitle("Alert dialog title")
             dialog.setMessage("Sbros berishga rozimisiz")
             dialog.setCancelable(false)

             dialog.setPositiveButton("Ha "
             ) { dialog, which ->
                 Toast.makeText(this@MainActivity, "Bajarildi ", Toast.LENGTH_SHORT).show()
             }

             dialog.setNegativeButton("Yo`q ", object : DialogInterface.OnClickListener {
                 override fun onClick(dialog: DialogInterface?, which: Int) {
                     Toast.makeText(this@MainActivity, "Bajarilmadi ", Toast.LENGTH_SHORT).show()
                 }
             })

             dialog.setNeutralButton("Neutral ", object : DialogInterface.OnClickListener {
                 override fun onClick(dialog: DialogInterface?, which: Int) {
                     Toast.makeText(this@MainActivity, "O`ylab ko`ring ", Toast.LENGTH_SHORT).show()
                 }
             })
             dialog.show()
         }

         btnCustom.setOnClickListener {
             val alertDialog = AlertDialog.Builder(this@MainActivity)
             val dialog = alertDialog.create()

             dialog.setTitle("Title Custom dialog")
             val dialogView = layoutInflater.inflate(R.layout.item_dialog, null, false)
             dialog.setView(dialogView)

             dialogView.findViewById<ImageView>(R.id.img).setOnClickListener {
                 Toast.makeText(this@MainActivity, "Custom Dialog Ishladi", Toast.LENGTH_SHORT).show()
             }

             dialog.show()
         }

         btnFragment.setOnClickListener {
             val myDialogFragment = BlankFragment.newInstance("key_1", "key_2")
             myDialogFragment.show(supportFragmentManager.beginTransaction(), "keys")
         }

         btnDate.setOnClickListener {
             val datePickerDialog = DatePickerDialog(this@MainActivity)

             datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                 Toast.makeText(this@MainActivity, "${dayOfMonth}.${month+1}.$year", Toast.LENGTH_SHORT).show()
             }
             datePickerDialog.show()
         }

         btnTime.setOnClickListener {
             val timePickerDialog = TimePickerDialog(
                 this@MainActivity,
                 object : TimePickerDialog.OnTimeSetListener {
                     override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                         Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                     }

                 },
                 24,
                 60,
                 true
             )
             timePickerDialog.updateTime(12, 0)
             timePickerDialog.show()
         }

         btnBottom.setOnClickListener {
             val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
             bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.item_bottom_sheet, null, false))
             bottomSheetDialog.show()
         }

         btnSnack.setOnClickListener {
             val snackbar = Snackbar.make(it, "SnackBar Ishladi", Snackbar.LENGTH_LONG)

             snackbar.setAction("Click", object : View.OnClickListener {
                 override fun onClick(v: View?) {
                     Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                 }
             })

             snackbar.show()
         }
        }
    }
}