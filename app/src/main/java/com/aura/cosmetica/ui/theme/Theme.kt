package com.aura.cosmetica.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val EsquemaAura = lightColorScheme(
    primary = Ciruela,
    onPrimary = Blanco,
    primaryContainer = Blush,
    onPrimaryContainer = CiruelaOscuro,
    secondary = Champagne,
    onSecondary = Color(0xFF3D2E14),
    secondaryContainer = ChampagneSuave,
    onSecondaryContainer = ChampagneOscuro,
    tertiary = Blush,
    onTertiary = CiruelaOscuro,
    background = Marfil,
    onBackground = Tinta,
    surface = Blanco,
    onSurface = Tinta,
    surfaceVariant = MarfilOscuro,
    onSurfaceVariant = GrisCalido,
    outline = Borde,
    outlineVariant = Borde,
    error = RojoSuave
)

private val FormasAura = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp)
)

@Composable
fun AuraTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaAura,
        typography = AuraTypography,
        shapes = FormasAura,
        content = content
    )
}
