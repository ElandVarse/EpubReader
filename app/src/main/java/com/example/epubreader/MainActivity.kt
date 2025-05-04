package com.example.epubreader

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.folioreader.FolioReader
import java.io.File

class MainActivity : ComponentActivity() {

    private val folioReader by lazy { FolioReader.get() }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            openEpub()
        } else {
            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> openEpub()

            else -> permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun openEpub() {
        val epubPath = "/sdcard/Download/meulivro.epub"
        val file = File(epubPath)
        if (file.exists()) {
            folioReader.openBook(epubPath)
        } else {
            Toast.makeText(this, "Arquivo EPUB não encontrado.", Toast.LENGTH_LONG).show()
        }
    }
}
