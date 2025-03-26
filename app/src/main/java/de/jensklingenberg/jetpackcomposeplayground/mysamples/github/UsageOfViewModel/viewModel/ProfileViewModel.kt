
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

package de.jensklingenberg.jetpackcomposeplayground.mysamples.github.UsageOfViewModel.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.jetpackcomposeplayground.mysamples.github.UsageOfViewModel.data.ProfileData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    // MutableStateFlow holds a single, mutable value
    // This value represents the current state
   private val _userProfile = MutableStateFlow<ProfileData?>(null)
    val userProfile : StateFlow<ProfileData?> = _userProfile

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage : StateFlow<String?> = _errorMessage

    init {
        fetchUserProfile()
    }

    fun fetchUserProfile(){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null // clear previous errors

            // handle Exception
            try {
                 delay(1500)
               // throw IOException("Network error occurred.")
                _userProfile.value = ProfileData(name = "", age = -1)
            }
            catch (e : Exception){
                _errorMessage.value = "Failed to load user profile."
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}