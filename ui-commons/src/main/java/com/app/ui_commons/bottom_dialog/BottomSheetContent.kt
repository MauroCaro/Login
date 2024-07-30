package com.app.ui_commons.bottom_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ui_commons.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    icon: Int? = null,
    title: String?,
    desc: String?,
    secondLine: String? = null,
    closable: Boolean = true,
    positiveButtonText: String?,
    positiveButtonOnClick: () -> Unit = {},
    negativeButtonText: String?,
    negativeButtonOnClick: () -> Unit = {},
    onCloseClick: () -> Unit,
    shouldNotifyNegativeListenerWhenClosing: Boolean = false,
) {
    ModalBottomSheet(
        onDismissRequest = onCloseClick,
        containerColor = Color.Transparent
    ) {
        BottomSheetScreen(
            icon = icon,
            title = title,
            desc = desc,
            secondLine = secondLine,
            closable = closable,
            positiveButtonText = positiveButtonText,
            positiveButtonOnClick = positiveButtonOnClick,
            negativeButtonText = negativeButtonText,
            negativeButtonOnClick = negativeButtonOnClick,
            onCloseClick = onCloseClick,
            shouldNotifyNegativeListenerWhenClosing = shouldNotifyNegativeListenerWhenClosing
        )
    }
}


@Composable
fun BottomSheetScreen(
    icon: Int? = null,
    title: String?,
    desc: String?,
    secondLine: String? = null,
    closable: Boolean = true,
    positiveButtonText: String?,
    positiveButtonOnClick: () -> Unit = {},
    negativeButtonText: String?,
    negativeButtonOnClick: () -> Unit = {},
    onCloseClick: () -> Unit,
    shouldNotifyNegativeListenerWhenClosing: Boolean = false,
){
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surface) // Usamos el color de superficie de Material 3
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Close Button (Optional)
        if (closable) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Filled.Close, // Icono de Material 3
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurface // Color de icono acorde al tema
                    )
                }
            }
        }

        // Icon or Lottie Animation (Conditionally displayed)
        Box(
            modifier = Modifier
                .widthIn(max = 100.dp)
                .aspectRatio(1f)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            icon?.let { icon ->
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Title
        Text(
            text = title ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.headlineSmall, // Tipografía de Material 3
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface // Color de texto acorde al tema
        )

        // Description
        Text(
            text = desc ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            style = MaterialTheme.typography.bodyMedium, // Tipografía de Material 3
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface // Color de texto acorde al tema
        )

        // Second Line (Optional)
        if (secondLine != null) {
            Text(
                text = secondLine,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 32.dp, end = 32.dp),
                style = MaterialTheme.typography.titleMedium, // Tipografía de Material 3
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface // Color de texto acorde al tema
            )
        }

        Spacer(modifier = Modifier.height(10.dp)) // Pushes buttons to the bottom

        // Buttons (Conditionally displayed)
        if (positiveButtonText != null || negativeButtonText != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Negative Button
                if (negativeButtonText != null) {
                    Button(
                        onClick = { negativeButtonOnClick(); if (shouldNotifyNegativeListenerWhenClosing) onCloseClick() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.outline // Color de borde
                        )
                    ) {
                        Text(
                            text = negativeButtonText,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Positive Button
                if (positiveButtonText != null) {
                    Button(
                        onClick = positiveButtonOnClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = positiveButtonText,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewBottomSheetContent() {
    BottomSheetScreen(
        icon = R.drawable.ic_warning, // Reemplaza con tu icono
        title = "Título del Bottom Sheet",
        desc = "Esta es una descripción de ejemplo para el Bottom Sheet.",
        secondLine = "Línea adicional opcional.",
        closable = true,
        positiveButtonText = "Aceptar",
        positiveButtonOnClick = { /* Acción al aceptar */ },
        negativeButtonText = "Cancelar",
        negativeButtonOnClick = { /* Acción al cancelar */ },
        onCloseClick = { /* Acción al cerrar */ },
        shouldNotifyNegativeListenerWhenClosing = false
    )
}

