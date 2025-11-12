package com.mexiti.usecamera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.mexiti.usecamera.ui.theme.UseCameraTheme
import com.mexiti.usecamera.ui.views.CameraPreviewScreen

class MainActivity : ComponentActivity() {

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission() ){
            isGranted ->
            if( isGranted){
                setCameraPreview()
            }else{

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when(PackageManager.PERMISSION_GRANTED){
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            )->{
                setCameraPreview()
            }else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }

        }



    }

    private fun setCameraPreview() {
        enableEdgeToEdge()
        setContent {
            UseCameraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CameraPreviewScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun CameraPreviewExample(modifier: Modifier = Modifier){
    Text(text = "Mostrar Cámara",modifier.padding())

}


