package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.R
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.ui.components.LogoAura
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Noche

// ===========================================================================
// SPLASH · transversal (todos los roles)
// Pantalla de apertura: logo + eslogan + línea de carga, y salta a Inicio.
// ===========================================================================

@Composable
fun PantallaSplash(nav: NavHostController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2400)
        irLimpio(nav, Rutas.INICIO)
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Noche, Color(0xFF3A2735))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LogoAura(alto = 38.dp, tinteTexto = Color.White)

            Spacer(Modifier.height(16.dp))
            Text(
                stringResource(R.string.splash_tagline),
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )

            Spacer(Modifier.height(30.dp))
            Box(
                Modifier
                    .width(150.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.15f))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.65f)
                        .height(3.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Champagne)
                )
            }
        }
    }
}
