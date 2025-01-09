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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_2.R

@Composable
fun TwitterLoginScreen(
    navController: NavController, auth: FirebaseAuth
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFocusedName by remember { mutableStateOf(false) }
    var isFocusedEmail by remember { mutableStateOf(false) }
    var signUpStatus by remember { mutableStateOf("") }
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

        Text(
            text = "Login", style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
            ), color = MaterialTheme.colorScheme.onPrimary

        )

        Spacer(modifier = Modifier.height(10.dp))

        InputField(
            label = "Email",
            value = email,
            onValueChange = { email = it }

        )

        Spacer(modifier = Modifier.height(10.dp))

        InputField(
            label="Name",
            value = name,
            onValueChange = { name = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isSelected by remember { mutableStateOf(false) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { isSelected = !isSelected },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedColor = MaterialTheme.colorScheme.onPrimary,
                        disabledSelectedColor = MaterialTheme.colorScheme.primary

                    )
                )
                Text(
                    text = "Remember me", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }

            TextButton(
                onClick = { navController.navigate("forgetPassword") },
            ) {
                Text(
                    text = "Forget password?", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (email.isNotBlank()&&name.isNotBlank()){
                    auth.signInWithEmailAndPassword(email, name).addOnCompleteListener { task ->
                        signUpStatus = if (task.isSuccessful) {
                            "Login successFully ${navController.navigate("TwitterSignUpScreen")}"
                        } else {
                            "Error:Something wrong signup failed!"
                        }
                    }
                }else{
                    if (email.isBlank()){isFocusedName=true}else{isFocusedEmail=true}
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 84.dp)
        ) {
            Text(
                text = "Continue", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                ), color = MaterialTheme.colorScheme.primary
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = signUpStatus)

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have account yet?", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                ), color = MaterialTheme.colorScheme.onPrimary
            )
            TextButton(
                onClick = { navController.navigate("TwitterSignUpScreen") },
            ) {
                Text(
                    text = "Sign Up", style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}



