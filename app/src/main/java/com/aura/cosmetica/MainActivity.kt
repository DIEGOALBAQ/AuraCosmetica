package com.aura.cosmetica

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.aura.cosmetica.nav.AuraApp
import com.aura.cosmetica.ui.theme.AuraTheme

/**
 * Única Activity de la app.
 *
 * Extiende FragmentActivity (superclase de ComponentActivity) porque
 * androidx.biometric.BiometricPrompt — usado en la autenticación por huella —
 * exige un entorno Fragment. Todo lo demás funciona idéntico: la interfaz
 * es 100% Jetpack Compose vía setContent.
 */
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraTheme {
                AuraApp()
            }
        }
    }
}
