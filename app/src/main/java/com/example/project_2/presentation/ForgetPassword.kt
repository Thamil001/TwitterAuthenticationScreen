package com.example.project_2.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_2.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgetPassword(
    navController: NavController,
    auth: FirebaseAuth
) {
    var statusMessage by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFocusedName by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.twitter), contentDescription = "")
        Spacer(modifier = Modifier.weight(1f))
        BasicTextField(value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .padding(5.dp)
                .padding(horizontal = 32.dp)
                .clip(shape = RoundedCornerShape(32.dp))
                .onFocusChanged { focusState -> isFocusedName = focusState.isFocused }
                .background(Color.White),
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge.copy(Color.Black),
            visualTransformation = VisualTransformation.None,
            cursorBrush = SolidColor(Color.Black),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (email.isEmpty() && !isFocusedName) {
                            Text(
                                text = "Email", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily.SansSerif,
                                    textAlign = TextAlign.Center,
                                ), color = MaterialTheme.colorScheme.outline
                            )
                        }
                        innerTextField()
                    }

                }
            }
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            onClick = {
                if (email.isNotBlank() && emailCheck(email)){
                    statusMessage = if (emailCheck(email)) {
                        "Reset password send this email:$email  ${auth.sendPasswordResetEmail(email)}"
                        "${navController.navigate("loginScreen")}"
                    } else {
                        "Failed please Enter the valid email"
                    }
                }else{
                    isFocusedName=true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 84.dp)
        ) {
            Text(
                text = "Reset", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                ), color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Text(
            text = statusMessage, style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
            ), color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.weight(5f))

    }

}
fun emailCheck(email:String):Boolean{
    val result = email.contains("@gmail.com")
    return result
}

