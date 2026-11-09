package com.aura.cosmetica.ui.screens

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import com.aura.cosmetica.R
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.ui.components.BotonPrincipal
import com.aura.cosmetica.ui.components.BotonSecundario
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.CampoTexto
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.LogoAura
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionLabel
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Noche
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 07 · REGISTRO / INICIO DE SESIÓN (pantalla oscura)
// ===========================================================================

@Composable
fun PantallaRegistro(nav: NavHostController) {
    var esLogin by remember { mutableStateOf(true) }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var verClave by remember { mutableStateOf(false) }
    var avisoBiometrico by remember { mutableStateOf<String?>(null) }

    // ---- Autenticación biométrica real (RF-04) ----
    // BiometricPrompt exige un entorno Fragment; MainActivity extiende
    // FragmentActivity, así que el contexto local es la actividad válida.
    val actividad = LocalContext.current as? FragmentActivity

    fun ingresoPorHuella() {
        val fa = actividad
        if (fa == null) {
            avisoBiometrico = "Autenticación no disponible aquí"
            return
        }
        val soporta = BiometricManager.from(fa)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        when (soporta) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                val prompt = BiometricPrompt(
                    fa,
                    ContextCompat.getMainExecutor(fa),
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(resultado: BiometricPrompt.AuthenticationResult) {
                            irLimpio(nav, Rutas.INICIO)
                        }

                        override fun onAuthenticationError(codigo: Int, mensaje: CharSequence) {
                            avisoBiometrico = "Huella cancelada o no válida"
                        }
                    }
                )
                val info = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Aura Cosmética")
                    .setSubtitle("Inicia sesión con tu huella dactilar")
                    .setNegativeButtonText("Usar contraseña")
                    .build()
                prompt.authenticate(info)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                avisoBiometrico = "Configura una huella en Ajustes del sistema para usar este ingreso"
            else ->
                avisoBiometrico = "Tu dispositivo no tiene lector de huella disponible"
        }
    }

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.img_hero),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xB3201420),
                            Color(0xF0241821)
                        )
                    )
                )
        )

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(26.dp))
            LogoAura(alto = 30.dp, tinteTexto = Blanco)

            Spacer(Modifier.height(34.dp))
            Text(
                "COSMÉTICA · BELLEZA CONSCIENTE",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )
            Spacer(Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    append("Bienvenida de ")
                    withStyle(SpanStyle(color = Champagne, fontStyle = FontStyle.Italic)) {
                        append("nuevo")
                    }
                },
                fontFamily = Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 31.sp,
                color = Blanco
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Ingresa para continuar con tu ritual de belleza.",
                fontFamily = Inter,
                fontSize = 13.sp,
                color = Blanco.copy(alpha = 0.72f)
            )

            Spacer(Modifier.height(26.dp))

            // ---- Tabs ----
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .background(Blanco.copy(alpha = 0.14f))
                    .padding(4.dp)
            ) {
                TabAuth("Iniciar sesión", esLogin, Modifier.weight(1f)) { esLogin = true }
                TabAuth("Registrarse", !esLogin, Modifier.weight(1f)) { esLogin = false }
            }

            Spacer(Modifier.height(20.dp))

            if (!esLogin) {
                CampoTexto(
                    valor = nombre,
                    onValor = { nombre = it },
                    pista = "Nombre completo",
                    icono = Icons.Filled.Person
                )
                Spacer(Modifier.height(10.dp))
            }

            CampoTexto(
                valor = correo,
                onValor = { correo = it },
                pista = "maria.lopez@email.com",
                icono = Icons.Filled.MailOutline,
                teclado = KeyboardType.Email
            )
            Spacer(Modifier.height(10.dp))
            CampoTexto(
                valor = clave,
                onValor = { clave = it },
                pista = "Contraseña",
                icono = Icons.Filled.Lock,
                esClave = true,
                mostrarClave = verClave,
                alternarClave = { verClave = !verClave }
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "¿Olvidaste tu contraseña?",
                fontFamily = Inter,
                fontSize = 12.sp,
                color = Blanco.copy(alpha = 0.72f),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { nav.navigate(Rutas.RECUPERAR) }
            )

            Spacer(Modifier.height(22.dp))
            BotonPrincipal(
                texto = if (esLogin) "Ingresar" else "Crear cuenta",
                onClick = { irLimpio(nav, Rutas.INICIO) }
            )

            Spacer(Modifier.height(22.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LineaDivisoria(modificador = Modifier.weight(1f))
                Text(
                    "  o continúa con  ",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = Blanco.copy(alpha = 0.6f)
                )
                LineaDivisoria(modificador = Modifier.weight(1f))
            }

            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                BotonSocial("Google", Modifier.weight(1f)) { irLimpio(nav, Rutas.INICIO) }
                BotonSocial("Facebook", Modifier.weight(1f)) { irLimpio(nav, Rutas.INICIO) }
            }

            Spacer(Modifier.height(24.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { ingresoPorHuella() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Fingerprint, null, tint = Champagne, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(
                    "Ingreso con huella · RF-04",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = Blanco.copy(alpha = 0.72f)
                )
            }
            if (avisoBiometrico != null) {
                Spacer(Modifier.height(6.dp))
                Text(
                    avisoBiometrico!!, // != null
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = Champagne,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
private fun TabAuth(texto: String, activo: Boolean, modificador: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modificador
            .clip(RoundedCornerShape(50))
            .background(if (activo) Blanco else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 11.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            texto,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = if (activo) Ciruela else Blanco.copy(alpha = 0.75f)
        )
    }
}

@Composable
private fun LineaDivisoria(modificador: Modifier = Modifier) {
    Box(
        modificador
            .height(1.dp)
            .background(Blanco.copy(alpha = 0.18f))
    )
}

@Composable
private fun BotonSocial(texto: String, modificador: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modificador
            .clip(RoundedCornerShape(14.dp))
            .background(Blanco.copy(alpha = 0.14f))
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            texto,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Blanco
        )
    }
}

// ===========================================================================
// 08 · DIRECCIÓN DE ENVÍO
// ===========================================================================

@Composable
fun PantallaDireccion(nav: NavHostController) {
    var calle by remember { mutableStateOf("Cra 13 # 93-40 · Apt 502") }
    var barrio by remember { mutableStateOf("Chapinero") }
    var ciudad by remember { mutableStateOf("Bogotá D.C.") }
    var tipo by remember { mutableStateOf("Casa") }

    PantallaAura(
        barraInferior = {
            BotonPrincipal(
                texto = "Confirmar dirección →",
                modificador = Modifier.padding(horizontal = 20.dp),
                onClick = { nav.popBackStack() }
            )
        },
        cabecera = {
            CabeceraTitulo("Mi dirección", onVolver = { nav.popBackStack() })
        }
    ) {
        // ---- Mapa estilizado ----
        Box(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(190.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFEDE2D3)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(Ciruela),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.LocationOn, null, tint = Blanco, modifier = Modifier.size(26.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Chapinero Alto · Bogotá D.C.",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = CiruelaOscuro
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        // ---- Tarjeta GPS ----
        Row(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Blanco)
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.GpsFixed, null, tint = Ciruela, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    "Ubicación detectada · GPS",
                    fontFamily = Inter,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = GrisCalido
                )
                Text(
                    "Cra 13 # 93-40",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Tinta
                )
                Text(
                    "Chapinero Alto, Bogotá D.C.",
                    fontFamily = Inter,
                    fontSize = 12.sp,
                    color = GrisCalido
                )
            }
            Text(
                "Precisa",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = CiruelaOscuro,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(com.aura.cosmetica.ui.theme.ChampagneSuave)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }

        Spacer(Modifier.height(18.dp))
        Column(Modifier.padding(horizontal = 20.dp)) {
            SeccionLabel("DIRECCIÓN DE ENTREGA")
            Spacer(Modifier.height(10.dp))
            CampoTexto(calle, { calle = it }, "Cra 13 # 93-40 · Apt 502")
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.weight(1f)) {
                    CampoTexto(barrio, { barrio = it }, "Barrio")
                }
                Box(Modifier.weight(1f)) {
                    CampoTexto(ciudad, { ciudad = it }, "Ciudad")
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Casa", "Trabajo", "Otro").forEach { t ->
                    ChipCategoria(t, tipo == t) { tipo = t }
                }
            }
        }
    }
}

// ===========================================================================
// RECUPERACIÓN DE CONTRASEÑA · transversal
// Flujo: correo → enviar enlace → confirmación.
// ===========================================================================

@Composable
fun PantallaRecuperar(nav: NavHostController) {
    var correo by remember { mutableStateOf("") }
    var enviada by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.img_hero),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xB3201420),
                            Color(0xF0241821)
                        )
                    )
                )
        )

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(26.dp))
            LogoAura(alto = 30.dp, tinteTexto = Blanco)

            Spacer(Modifier.height(34.dp))
            Text(
                "RECUPERACIÓN DE ACCESO",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )
            Spacer(Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    append("Recupera tu ")
                    withStyle(SpanStyle(color = Champagne, fontStyle = FontStyle.Italic)) {
                        append("contraseña")
                    }
                },
                fontFamily = Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 31.sp,
                color = Blanco
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Escribe tu correo y te enviaremos un enlace para crear una contraseña nueva.",
                fontFamily = Inter,
                fontSize = 13.sp,
                color = Blanco.copy(alpha = 0.72f)
            )

            Spacer(Modifier.height(26.dp))

            if (!enviada) {
                CampoTexto(
                    valor = correo,
                    onValor = { correo = it },
                    pista = "maria.lopez@email.com",
                    icono = Icons.Filled.MailOutline,
                    teclado = KeyboardType.Email
                )
                Spacer(Modifier.height(18.dp))
                BotonPrincipal(
                    texto = "Enviar enlace de recuperación",
                    habilitado = correo.contains("@"),
                    onClick = { enviada = true }
                )
                Spacer(Modifier.height(14.dp))
                BotonSecundario("Volver a iniciar sesión") { nav.popBackStack() }
            } else {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blanco.copy(alpha = 0.12f))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CheckCircle, null,
                        tint = Champagne, modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "Enlace enviado",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Blanco
                        )
                        Text(
                            "Revisa $correo y sigue el enlace para restablecer tu acceso.",
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = Blanco.copy(alpha = 0.72f)
                        )
                    }
                }
                Spacer(Modifier.height(18.dp))
                BotonPrincipal("Volver al inicio de sesión") { nav.popBackStack() }
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}
