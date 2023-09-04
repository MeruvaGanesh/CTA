package com.example.androidtest

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.PushPermissionResponseListener
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

//import com.google.gson.Gson


class MainActivity : AppCompatActivity(), DisplayUnitListener, PushPermissionResponseListener {
    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var cleverTapDefaultInstance: CleverTapAPI? = CleverTapAPI.getDefaultInstance(applicationContext)
        var firebaseAnalyticsInstance: FirebaseAnalytics? = FirebaseAnalytics.getInstance(this)
        // SETUP USER-PROPERTY FOR UNINSTALL TRACKING

        firebaseAnalyticsInstance?.setUserProperty("cleverTapId", cleverTapDefaultInstance?.cleverTapID.toString())


        val course = HashMap<String, Any>()
        course["Course Name"] = "Test Course"
        course["Course Duration"] = Date()
        course["Course Category"] = "Test Category"
        course["Course Level"] = "Easy"




        CleverTapAPI.setDebugLevel(3)
        cleverTapDefaultInstance?.enablePersonalization()
//        cleverTapDefaultInstance?.promptForPushPermission(true)
        CleverTapAPI.createNotificationChannel(applicationContext,"ganesh","Ganesh Test Channel","Channel to test Campaigns if user did add to cart and not charged in 30 min",
            NotificationManager.IMPORTANCE_MAX,true)

        cleverTapDefaultInstance?.registerPushPermissionNotificationResponseListener(this)
        if (cleverTapDefaultInstance != null) {
            Log.v("isPushPermissionGranted", cleverTapDefaultInstance.isPushPermissionGranted.toString())
        }


        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Ganesh" // String
        profileUpdate["Identity"] = "0000491" // String or number
        profileUpdate["Email"] = "ganesh20161m@gmail.com" // Email address of the user
        profileUpdate["Phone"] = "+91914290000491" // Phone (with the country code, starting with +)
        profileUpdate["Gender"] = "M" // Can be either M or F
//        profileUpdate["Father Name"] = "Father" // Can be either M or F
        profileUpdate.put("Photo", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/481px-Cat03.jpg")
        Log.v("DateinEpoch", Date().toString())
        profileUpdate["DOB"] = Date()
        cleverTapDefaultInstance?.onUserLogin(profileUpdate)
//        cleverTapDefaultInstance?.pushProfile(profileUpdate)
//        cleverTapDefaultInstance?.onUserLogin(profileUpdate,"__00350fb513c94b81a1ee63c4e966423b")
        cleverTapDefaultInstance?.incrementValue("1",10)
        cleverTapDefaultInstance?.decrementValue(2.toString(),10)
        cleverTapDefaultInstance?.enableDeviceNetworkInfoReporting(true)

        val chargeDetails = HashMap<String, Any>()
        chargeDetails["Amount"] = 300
        chargeDetails["Payment Mode"] = "Credit card"
        chargeDetails["Charged ID"] = 24052013


        val item1 = HashMap<String, Any>()
        item1["Product category"] = "books"
        item1["Book name"] = "book.name"
        item1["Quantity"] = 1

        val item2 = HashMap<String, Any>()
        item2["Product category"] = "books"
        item2["Book name"] = "Achieving inner zen"
        item2["Quantity"] = 1

        val item3 = HashMap<String, Any>()
        item3["Product category"] = "books"
        item3["Book name"] = "Chuck it, let's do it"
        item3["Quantity"] = 5

        val item4 = HashMap<String, Any>()
        item4["Product category"] = "CLOTHES"
        item4["CLOTHES name"] = "TSHIRT"
        item4["Quantity"] = 10

//        val item5 = HashMap<String, Any>()
//        item5["Product category"] = "CLOTHES"
//        item5["CLOTHES name"] = "SHIRT"
//        item5["Quantity"] = 15
//
//        val item6 = HashMap<String, Any>()
//        item6["Product category"] = "books"
//        item6["Book name"] = "The Millionaire next door"
//        item6["Quantity"] = 1
//
//        val item7 = HashMap<String, Any>()
//        item7["Product category"] = "books"
//        item7["Book name"] = "Achieving inner zen"
//        item7["Quantity"] = 1
//
//        val item8 = HashMap<String, Any>()
//        item8["Product category"] = "books"
//        item8["Book name"] = "Chuck it, let's do it"
//        item8["Quantity"] = 5
//
//        val item9 = HashMap<String, Any>()
//        item9["Product category"] = "CLOTHES"
//        item9["CLOTHES name"] = "TSHIRT"
//        item9["Quantity"] = 10
//
//        val item10 = HashMap<String, Any>()
//        item10["Product category"] = "CLOTHES"
//        item10["CLOTHES name"] = "SHIRT"
//        item10["Quantity"] = 15
//
        val items = ArrayList<HashMap<String, Any>>()
        items.add(item1)
        items.add(item2)
        items.add(item3)
        items.add(item4)
//        items.add(item5)
//        items.add(item6)
//        items.add(item7)
//        items.add(item8)
//        items.add(item9)
//        items.add(item10)
//
        cleverTapDefaultInstance?.pushChargedEvent(chargeDetails, items)

        // Custom event with
        val userDetails = HashMap<String, Any>()
        userDetails["User Name"] = "Ganesh Meruva"
        userDetails["Current Date"] = Date()

        cleverTapDefaultInstance?.pushEvent("User Details", userDetails)

        val userDetails1 = HashMap<String, Any>()
        chargeDetails["Amount"] = 300
        chargeDetails["Payment Mode"] = "Credit card"
        chargeDetails["Charged ID"] = 24052013

        val item11 = HashMap<String, Any>()
        item1["Product category"] = "books"
        item1["Book name"] = "The Millionaire next door"
        item1["Quantity"] = 1

        val item12 = HashMap<String, Any>()
        item2["Product category"] = "books"
        item2["Book name"] = "Achieving inner zen"
        item2["Quantity"] = 1

        val item13 = HashMap<String, Any>()
        item3["Product category"] = "books"
        item3["Book name"] = "Chuck it, let's do it"
        item3["Quantity"] = 5

        val items1 = ArrayList<HashMap<String, Any>>()
        items.add(item11)
        items.add(item12)
        items.add(item13)
        cleverTapDefaultInstance?.pushChargedEvent(userDetails1, items1)
//        cleverTapDefaultInstance?.pushError()

        cleverTapDefaultInstance?.apply {
            setDisplayUnitListener(this@MainActivity)
        }

    }

    override fun onDisplayUnitsLoaded(units:  ArrayList<CleverTapDisplayUnit>?) {
        if (units != null) {
//            Log.e("Unit", "onDisplayUnitsLoaded: " + units.size )
            var j = 1
            for (i in 0 until units.size)
            {
                val unit = units[i]
                Log.e("DiplayUnitOfNativeDisplay", "$unit")
//                Toast.makeText(applicationContext, unit.toString(), Toast.LENGTH_LONG).show()
                j++
            }
        }
    }

    override fun onPushPermissionResponse(accepted: Boolean) {
        Log.i(TAG, "onPushPermissionResponse :  InApp---> response() called accepted=$accepted")
        if (accepted) {
            CleverTapAPI.createNotificationChannel(applicationContext,"ganesh","Ganesh Test Channel","Channel to test Campaigns if user did add to cart and not charged in 30 min",
                NotificationManager.IMPORTANCE_MAX,true)
        }
    }
}