package com.yongjincompany.hackerthonandroid.features.nfc.view

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ActivityNfcBinding

class NFCActivity : AppCompatActivity() {

    private var intentFiltersArray: Array<IntentFilter>? = null
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var nfcPendingIntent: PendingIntent
    private lateinit var binding: ActivityNfcBinding
    private lateinit var bottomSheetView: View
    private lateinit var bottomSheetDialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        bottomSheetView = layoutInflater.inflate(R.layout.layout_nfc_bottom_sheet, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nfc)
        binding.activity = this
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                nfcPendingIntent = PendingIntent.getActivity(
                    applicationContext,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

            nfcAdapter = NfcAdapter.getDefaultAdapter(this)
            val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
            try {
                ndef.addDataType("text/plain")
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("fail", e)
            }
            intentFiltersArray = arrayOf(ndef)
            if (nfcAdapter == null) {
                Toast.makeText(this, "NFC를 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else if (!nfcAdapter!!.isEnabled) {
                Toast.makeText(this, "NFC가 꺼져있습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "가능합니다.", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showBottomSheet(view: View) {
        when (view.id) {
            binding.nfcBtn.id -> { bottomSheetDialog.show() }
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, intentFiltersArray, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    var type = "mmmm"
    var cycle = "28"
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("TAG", "onNewIntent")

        val action = intent?.action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            val parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            try {
                val inNdefMessage = parcelables?.get(0) as NdefMessage
                val inNdefRecords = inNdefMessage.records
                //if there are many records, you can call inNdefRecords[1] as array
                var ndefRecord_0 = inNdefRecords[0]
                var inMessage = String(ndefRecord_0.payload)
                type = inMessage.drop(3);

                ndefRecord_0 = inNdefRecords[1]
                inMessage = String(ndefRecord_0.payload)
                cycle = inMessage.drop(3);

                if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action
                    || NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action
                ) {

                    val tag =
                        intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG) ?: return
                    val ndef = Ndef.get(tag) ?: return

                    if (ndef.isWritable) {

                        var message = NdefMessage(
                            arrayOf(
                                NdefRecord.createTextRecord("en", type),
                                NdefRecord.createTextRecord("en", cycle),
                                NdefRecord.createTextRecord(
                                    "en",
                                    "test"
                                )

                            )
                        )
                        ndef.connect()
                        ndef.writeNdefMessage(message)
                        ndef.close()

                        Toast.makeText(
                            applicationContext,
                            "Successfully Wroted!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    try {
                        ndefRecord_0 = inNdefRecords[2]
                        inMessage = String(ndefRecord_0.payload)

                        Log.d("TAG", "${inMessage.drop(3)})")
                    } catch (ex: Exception) {
                        Toast.makeText(
                            applicationContext,
                            "User ID not writted!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}