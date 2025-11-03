package com.example.calcx.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter

object DateUtils{
   @SuppressLint("NewApi")
   public val formatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}