package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.ui.components.BotonPrincipal
import com.aura.cosmetica.ui.components.BotonSecundario
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.LinkTexto
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionLabel
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.ChampagneSuave
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Marfil
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 05 · PAGO
// ===========================================================================

private val metodosPago = listOf(
    Triple("Visa •••• 4242", "Expira 08 / 29", Icons.Filled.CreditCard),
    Triple("PSE · Débito bancario", "Todos los bancos", Icons.Filled.AccountBalance),
    Triple("Nequi", "Pago desde tu app", Icons.Filled.Smartphone)
)

@Composable
fun PantallaPago(nav: NavHostController) {
    var metodo by remember { mutableStateOf(0) }

    PantallaAura(
        barraInferior = {
            BotonPrincipal(
                texto = "Pagar ${cop(Repo.totalCarrito)}",
                modificador = Modifier.padding(horizontal = 20.dp),
                onClick = {
                    Repo.confirmarPedido()
                    irLimpio(nav, Rutas.CONFIRMACION)
                }
            )
        },
        cabecera = { CabeceraTitulo("Pago", onVolver = { nav.popBackStack() }) }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            SeccionLabel("ENTREGA")
            Spacer(Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Blanco)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Blush),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.LocationOn, null, tint = Ciruela, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Dirección de entrega", fontFamily = Inter, fontSize = 11.sp, color = GrisCalido)
                    Text(
                        "Cra 13 # 93-40, Chapinero",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Tinta
                    )
                    Text("Bogotá D.C., Colombia", fontFamily = Inter, fontSize = 12.sp, color = GrisCalido)
                }
                LinkTexto("Cambiar") { nav.navigate(Rutas.DIRECCION) }
            }

            Spacer(Modifier.height(18.dp))
            SeccionLabel("MÉTODO DE PAGO")
            Spacer(Modifier.height(8.dp))

            metodosPago.forEachIndexed { i, (titulo, detalle, icono) ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Blanco)
                        .border(
                            width = if (metodo == i) 1.5.dp else 1.dp,
                            color = if (metodo == i) Ciruela else Borde,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable { metodo = i }
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = metodo == i,
                        onClick = { metodo = i },
                        colors = RadioButtonDefaults.colors(selectedColor = Ciruela)
                    )
                    Icon(icono, null, tint = Ciruela, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            titulo,
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Tinta
                        )
                        Text(detalle, fontFamily = Inter, fontSize = 11.sp, color = GrisCalido)
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(12.dp))
            SeccionLabel("RESUMEN")
            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ResumenChip("Productos", "${Repo.unidadesCarrito} artículos", Modifier.weight(1f))
                ResumenChip(
                    "Envío",
                    if (Repo.costoEnvio == 0) "Gratis" else cop(Repo.costoEnvio),
                    Modifier.weight(1f)
                )
                ResumenChip("Total", cop(Repo.totalCarrito), Modifier.weight(1f), destacado = true)
            }

            Spacer(Modifier.height(16.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(ChampagneSuave, Blush)
                        )
                    )
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.WorkspacePremium, null, tint = ChampagneOscuro, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "Aura Rewards · +840 puntos",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = ChampagneOscuro
                    )
                    Text(
                        "Acumula con cada compra y accede a beneficios exclusivos",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        color = ChampagneOscuro.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Lock, null, tint = GrisCalido, modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    "Pago en modo prueba (sandbox) · Datos cifrados con tokenización",
                    fontFamily = Inter,
                    fontSize = 10.sp,
                    color = GrisCalido
                )
            }
        }
    }
}

@Composable
private fun ResumenChip(
    etiqueta: String,
    valor: String,
    modificador: Modifier,
    destacado: Boolean = false
) {
    Column(
        modificador
            .clip(RoundedCornerShape(12.dp))
            .background(if (destacado) Blush else Blanco)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            etiqueta.uppercase(),
            fontFamily = Inter,
            fontSize = 9.sp,
            letterSpacing = 1.sp,
            color = GrisCalido
        )
        Spacer(Modifier.height(3.dp))
        Text(
            valor,
            fontFamily = Inter,
            fontWeight = if (destacado) FontWeight.Bold else FontWeight.SemiBold,
            fontSize = 12.sp,
            color = if (destacado) CiruelaOscuro else Tinta,
            maxLines = 1
        )
    }
}

// ===========================================================================
// 06 · CONFIRMACIÓN
// ===========================================================================

@Composable
fun PantallaConfirmacion(nav: NavHostController) {
    val pedido = Repo.pedidoConfirmado

    Scaffold(containerColor = Marfil) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(48.dp))

            Box(
                Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Blush)
                    .border(3.dp, Champagne, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Check, null, tint = Ciruela, modifier = Modifier.size(44.dp))
            }

            Spacer(Modifier.height(22.dp))
            Text(
                buildAnnotatedString {
                    append("Gracias por tu ")
                    withStyle(SpanStyle(color = Ciruela, fontStyle = FontStyle.Italic)) {
                        append("compra")
                    }
                },
                fontFamily = Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = Tinta,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Tu pedido fue confirmado y ya lo estamos preparando con el cuidado que merece.",
                fontFamily = Inter,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = GrisCalido,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(18.dp))
            Text(
                "PEDIDO Nº ${pedido?.codigo ?: "A-1024"}",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                color = CiruelaOscuro,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Blush)
                    .padding(horizontal = 16.dp, vertical = 9.dp)
            )

            Spacer(Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Blanco)
                    .padding(18.dp)
            ) {
                FilaRecap("Productos", "${pedido?.items ?: 2} artículos")
                Spacer(Modifier.height(10.dp))
                FilaRecap("Total pagado", cop(pedido?.totalCop ?: 83980))
                Spacer(Modifier.height(10.dp))
                FilaRecap("Entrega estimada", com.aura.cosmetica.data.entregaEstimada())
                Spacer(Modifier.height(10.dp))
                FilaRecap("Dirección", "Cra 13 # 93-40")
            }

            Spacer(Modifier.height(22.dp))
            BotonPrincipal("Ver mis pedidos") {
                nav.navigate(Rutas.PEDIDOS) {
                    popUpTo(Rutas.INICIO)
                    launchSingleTop = true
                }
            }
            Spacer(Modifier.height(10.dp))
            BotonSecundario("Volver al inicio") {
                irLimpio(nav, Rutas.INICIO)
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "Enviaremos la confirmación a tu correo electrónico",
                fontFamily = Inter,
                fontSize = 11.sp,
                color = GrisCalido,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FilaRecap(etiqueta: String, valor: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            etiqueta,
            fontFamily = Inter,
            fontSize = 12.sp,
            color = GrisCalido,
            modifier = Modifier.weight(1f)
        )
        Text(
            valor,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Tinta
        )
    }
}
