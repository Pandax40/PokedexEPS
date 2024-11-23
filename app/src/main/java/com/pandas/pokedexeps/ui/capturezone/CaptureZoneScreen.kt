package com.pandas.pokedexeps.ui.capturezone

import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import com.pandas.pokedexeps.R
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

@Composable
fun CaptureZoneScreen(
    modifier: Modifier = Modifier,
    viewModel: CaptureZoneViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    PokedexEPSTheme {
        val qrCode = viewModel.qrCode.value
        val areButtonsVisible = viewModel.areButtonsVisible.value
        val navigateBackToHomeScreen = viewModel.navigateBackToHomeScreen.value

        val context = LocalContext.current
        val activity = context as Activity

        var hasCameraPermission by remember { mutableStateOf(false) }
        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            hasCameraPermission = isGranted
            if (!isGranted) {
                Toast.makeText(context, "Camera permission is required to scan QR codes", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(Unit) {
            if (context.checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            } else {
                hasCameraPermission = true
            }
        }

        LaunchedEffect(navigateBackToHomeScreen) {
            if (navigateBackToHomeScreen) {
                navController.popBackStack()
                viewModel.onNavigatedBackToHomeScreen()
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { viewModel.onBackButtonClicked() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Pokémon Logo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (hasCameraPermission) {
                val barcodeView = remember { CompoundBarcodeView(context) }
                barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(listOf(BarcodeFormat.QR_CODE))
                barcodeView.initializeFromIntent(activity.intent)
                barcodeView.decodeContinuous(object : BarcodeCallback {
                    override fun barcodeResult(result: BarcodeResult?) {
                        result?.let {
                            viewModel.onQrCodeScanned(it.text)
                        }
                    }

                    override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
                })

                AndroidView(
                    factory = { barcodeView },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Scanned QR Code: $qrCode",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (areButtonsVisible) {
                Button(
                    onClick = { viewModel.registerZone() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Register Zone")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.capturePokemon() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Capture Pokémon")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptureZoneScreenPreview() {
    CaptureZoneScreen(navController = rememberNavController())
}