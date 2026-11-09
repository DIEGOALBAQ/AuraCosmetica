package com.aura.cosmetica.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aura.cosmetica.R
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.ChampagneSuave
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Cinzel
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Marfil
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta

// ---------------------------------------------------------------------------
// Estructura de pantalla
// ---------------------------------------------------------------------------

/** Scaffold marfil con scroll vertical: cabecera + contenido + barra inferior opcional. */
@Composable
fun PantallaAura(
    fondo: Color = Marfil,
    barraInferior: (@Composable () -> Unit)? = null,
    cabecera: @Composable () -> Unit = {},
    contenido: @Composable ColumnScope.() -> Unit
) {
    Scaffold(containerColor = fondo, bottomBar = { barraInferior?.invoke() }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            cabecera()
            contenido()
            Spacer(Modifier.height(28.dp))
        }
    }
}

// ---------------------------------------------------------------------------
// Logo y cabeceras
// ---------------------------------------------------------------------------

@Composable
fun LogoAura(alto: Dp = 24.dp, tinteTexto: Color = Ciruela) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.logo_aura),
            contentDescription = "Logo Aura",
            modifier = Modifier.height(alto)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            "AURA",
            fontFamily = Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            letterSpacing = 3.sp,
            color = tinteTexto
        )
    }
}

@Composable
fun IconoBoton(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    tinte: Color = Ciruela,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, contentDescription = descripcion, tint = tinte, modifier = Modifier.size(22.dp))
    }
}

@Composable
fun IconoCarrito(unidades: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Filled.ShoppingCart, null, tint = Ciruela, modifier = Modifier.size(21.dp))
        if (unidades > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Champagne),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    unidades.toString(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = CiruelaOscuro
                )
            }
        }
    }
}

/** Header con logo (pantallas 01, 11, 17) + chip de rol opcional. */
@Composable
fun CabeceraLogo(
    unidades: Int = 0,
    chip: String? = null,
    tinteLogo: Color = Ciruela,
    onBuscar: (() -> Unit)? = null,
    onCarrito: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogoAura(24.dp, tinteLogo)
        if (chip != null) {
            Spacer(Modifier.width(10.dp))
            ChipRolAura(chip)
        }
        Spacer(Modifier.weight(1f))
        if (onBuscar != null) IconoBoton(Icons.Filled.Search, "Buscar", onClick = onBuscar)
        if (onCarrito != null) {
            Spacer(Modifier.width(6.dp))
            IconoCarrito(unidades, onCarrito)
        }
    }
}

/** Header con título Cinzel + botón volver + contenido a la derecha. */
@Composable
fun CabeceraTitulo(
    titulo: String,
    onVolver: (() -> Unit)? = null,
    trailing: @Composable RowScope.() -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onVolver != null) {
            IconoBoton(Icons.Filled.ArrowBack, "Volver", onClick = onVolver)
            Spacer(Modifier.width(12.dp))
        }
        Text(
            titulo,
            fontFamily = Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            letterSpacing = 1.5.sp,
            color = Ciruela,
            modifier = Modifier.weight(1f)
        )
        trailing()
    }
}

// ---------------------------------------------------------------------------
// Chips y etiquetas
// ---------------------------------------------------------------------------

@Composable
fun ChipRolAura(texto: String) {
    Text(
        texto,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        color = CiruelaOscuro,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Blush)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}

@Composable
fun ChipAccion(texto: String, onClick: (() -> Unit)? = null) {
    Text(
        texto,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        color = Blanco,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Ciruela)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 12.dp, vertical = 7.dp)
    )
}

@Composable
fun ChipCategoria(texto: String, seleccionado: Boolean, onClick: () -> Unit) {
    Text(
        texto,
        fontFamily = Inter,
        fontSize = 13.sp,
        fontWeight = if (seleccionado) FontWeight.SemiBold else FontWeight.Medium,
        color = if (seleccionado) Blanco else Tinta,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (seleccionado) Ciruela else Blanco)
            .border(1.dp, if (seleccionado) Ciruela else Borde, RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.dp)
    )
}

@Composable
fun LinkTexto(texto: String, color: Color = Ciruela, onClick: (() -> Unit)? = null) {
    Text(
        texto,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = color,
        modifier = if (onClick != null) {
            Modifier.clip(RoundedCornerShape(8.dp)).clickable(onClick = onClick)
        } else Modifier
    )
}

@Composable
fun SeccionLabel(texto: String) {
    Text(
        texto,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        letterSpacing = 2.sp,
        color = GrisCalido
    )
}

// ---------------------------------------------------------------------------
// Barra de búsqueda
// ---------------------------------------------------------------------------

@Composable
fun BarraBusqueda(
    valor: String,
    onValor: (String) -> Unit,
    pista: String = "Buscar productos…",
    soloLectura: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(Blanco)
            .border(1.dp, Borde, RoundedCornerShape(50))
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Search, null, tint = GrisCalido, modifier = Modifier.size(19.dp))
        Spacer(Modifier.width(10.dp))
        if (soloLectura) {
            Text(pista, fontFamily = Inter, fontSize = 14.sp, color = GrisCalido, modifier = Modifier.weight(1f))
        } else {
            BasicTextField(
                value = valor,
                onValueChange = onValor,
                singleLine = true,
                textStyle = TextStyle(fontFamily = Inter, fontSize = 14.sp, color = Tinta),
                cursorBrush = SolidColor(Ciruela),
                decorationBox = { interno ->
                    Box {
                        if (valor.isEmpty()) {
                            Text(pista, fontFamily = Inter, fontSize = 14.sp, color = GrisCalido)
                        }
                        interno()
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Botones
// ---------------------------------------------------------------------------

@Composable
fun BotonPrincipal(
    texto: String,
    habilitado: Boolean = true,
    color: Color = Ciruela,
    icono: androidx.compose.ui.graphics.vector.ImageVector? = null,
    modificador: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modificador
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(if (habilitado) color else MarfilOscuro)
            .clickable(enabled = habilitado, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 15.dp)
    ) {
        if (icono != null) {
            Icon(
                icono, null,
                tint = if (habilitado) Blanco else GrisCalido,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            texto,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            letterSpacing = 0.6.sp,
            color = if (habilitado) Blanco else GrisCalido,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun BotonSecundario(texto: String, modificador: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modificador
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(Blanco)
            .border(1.5.dp, Ciruela, RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            texto,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Ciruela,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun BotonMini(texto: String, onClick: () -> Unit, primario: Boolean = true) {
    Text(
        texto,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        color = if (primario) Blanco else Ciruela,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (primario) Ciruela else Color.Transparent)
            .then(if (primario) Modifier else Modifier.border(1.dp, Ciruela, RoundedCornerShape(50)))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    )
}

// ---------------------------------------------------------------------------
// Campos de texto
// ---------------------------------------------------------------------------

@Composable
fun CampoTexto(
    valor: String,
    onValor: (String) -> Unit,
    pista: String,
    icono: androidx.compose.ui.graphics.vector.ImageVector? = null,
    esClave: Boolean = false,
    mostrarClave: Boolean = false,
    alternarClave: (() -> Unit)? = null,
    teclado: androidx.compose.ui.text.input.KeyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
    modificador: Modifier = Modifier
) {
    androidx.compose.material3.OutlinedTextField(
        value = valor,
        onValueChange = onValor,
        placeholder = { Text(pista, fontFamily = Inter, fontSize = 14.sp, color = GrisCalido) },
        leadingIcon = if (icono != null) {
            { Icon(icono, null, tint = GrisCalido, modifier = Modifier.size(19.dp)) }
        } else null,
        trailingIcon = if (esClave && alternarClave != null) {
            {
                IconoBoton(
                    if (mostrarClave) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    "Ver contraseña"
                ) { alternarClave() }
            }
        } else null,
        visualTransformation = if (esClave && !mostrarClave) {
            androidx.compose.ui.text.input.PasswordVisualTransformation()
        } else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = teclado),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Ciruela,
            unfocusedBorderColor = Borde,
            focusedContainerColor = Blanco,
            unfocusedContainerColor = Blanco
        ),
        textStyle = TextStyle(fontFamily = Inter, fontSize = 14.sp, color = Tinta),
        modifier = modificador.fillMaxWidth()
    )
}

@Composable
fun CampoLargo(valor: String, onValor: (String) -> Unit, pista: String, minLineas: Int = 3) {
    androidx.compose.material3.OutlinedTextField(
        value = valor,
        onValueChange = onValor,
        placeholder = { Text(pista, fontFamily = Inter, fontSize = 13.sp, color = GrisCalido) },
        shape = RoundedCornerShape(16.dp),
        minLines = minLineas,
        maxLines = 5,
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Ciruela,
            unfocusedBorderColor = Borde,
            focusedContainerColor = Blanco,
            unfocusedContainerColor = Blanco
        ),
        textStyle = TextStyle(fontFamily = Inter, fontSize = 14.sp, color = Tinta),
        modifier = Modifier.fillMaxWidth()
    )
}

// ---------------------------------------------------------------------------
// Avatar
// ---------------------------------------------------------------------------

@Composable
fun AvatarIniciales(nombre: String, tamano: Dp = 42.dp) {
    val iniciales = nombre.split(" ")
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
    Box(
        Modifier
            .size(tamano)
            .clip(CircleShape)
            .background(Blush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            iniciales,
            fontFamily = Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = (tamano.value * 0.32f).sp,
            color = Ciruela
        )
    }
}

// ---------------------------------------------------------------------------
// Título de sección con acción opcional (ej. "Destacados · Ver todo")
// ---------------------------------------------------------------------------

@Composable
fun SeccionTitulo(titulo: String, accion: String? = null, onAccion: (() -> Unit)? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 22.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            titulo,
            fontFamily = Cormorant,
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp,
            color = Tinta,
            modifier = Modifier.weight(1f)
        )
        if (accion != null && onAccion != null) {
            Text(
                accion,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Ciruela,
                modifier = Modifier.clickable { onAccion() }
            )
        }
    }
}
