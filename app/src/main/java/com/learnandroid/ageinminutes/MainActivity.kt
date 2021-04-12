package com.learnandroid.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.learnandroid.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this,
                            "The selected year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth",
                            Toast.LENGTH_LONG).show()
                        val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                    binding.tvSelectedDate.setText(selectedDate)

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes  = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate!!.time / 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    binding.tvSelectDateInMinutes.setText(differenceInMinutes.toString())

                }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}