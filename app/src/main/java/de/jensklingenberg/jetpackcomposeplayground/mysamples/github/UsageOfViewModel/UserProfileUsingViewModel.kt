
/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.jensklingenberg.jetpackcomposeplayground.mysamples.github.UsageOfViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.jensklingenberg.jetpackcomposeplayground.mysamples.github.UsageOfViewModel.viewModel.ProfileViewModel

@Composable
fun UserProfileUsingViewModel(viewModel: ProfileViewModel = viewModel()){
    val userProifle = viewModel.userProfile.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        if (isLoading.value) {
            CircularProgressIndicator()

            Spacer(Modifier.padding(10.dp))

            Text(text = "isLoading",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        else if(errorMessage.value != null){

            Text(text = errorMessage.value.toString(),
                color = MaterialTheme.colors.error,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.padding(10.dp))

            Button(
                onClick = {
                    viewModel.fetchUserProfile()
                }
            ) {
                Text(text = "Retry",
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
        }
        else if(userProifle.value != null){
            Text(text = "User Profile: ${userProifle.value?.name}, " +
                    "Age: ${userProifle.value?.age}",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}