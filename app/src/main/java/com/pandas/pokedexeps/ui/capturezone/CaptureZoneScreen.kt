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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    navController: NavController
) {
    val viewModel: CaptureZoneViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    PokedexEPSTheme {
        val qrCode by viewModel.qrCode
        val areButtonsVisible by viewModel.areButtonsVisible
        val navigateBackToHomeScreen by viewModel.navigateBackToHomeScreen
        val showCooldownPopup by viewModel.showCooldownPopup

        val context = LocalContext.current
        val activity = context as Activity

        var hasCameraPermission by remember { mutableStateOf(false) }
        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            hasCameraPermission = isGranted
            if (!isGranted) {
                Toast.makeText(context, "Se requiere permiso de cámara para escanear códigos QR", Toast.LENGTH_SHORT).show()
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
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            IconButton(onClick = { viewModel.onBackButtonClicked() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Logo de Pokémon",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (hasCameraPermission) {
                val barcodeView = remember { CompoundBarcodeView(context) }
                DisposableEffect(Unit) {
                    barcodeView.resume()
                    onDispose {
                        barcodeView.pause()
                    }
                }

                barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(listOf(BarcodeFormat.QR_CODE))
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
                text = if (qrCode.isNotEmpty()) "Código QR Encontrado" else "Escanea un código QR",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (areButtonsVisible) {
                Button(
                    onClick = { viewModel.capturePokemon() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Capturar Pokémon")
                }
            }
        }
        if (showCooldownPopup) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.showCooldownPopup.value = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.showCooldownPopup.value = false
                    }) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Cooldown Activo")
                },
                text = {
                    Text("No puedes cazar Pokémon en esa área porque tienes un cooldown.")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptureZoneScreenPreview() {
    CaptureZoneScreen(navController = rememberNavController())
}