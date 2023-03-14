package com.microsoft.walletlibrarydemo.feature.issuance.idtokenhintflow.presentationlogic

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.microsoft.walletlibrary.VerifiedIdClientBuilder
import com.microsoft.walletlibrary.requests.VerifiedIdRequest
import com.microsoft.walletlibrary.requests.input.VerifiedIdRequestURL
import com.microsoft.walletlibrary.requests.requirements.GroupRequirement
import com.microsoft.walletlibrary.requests.requirements.PinRequirement

//TODO(Add dependency injection)
class IdTokenFlowViewModel(@SuppressLint("StaticFieldLeak") val context: Context): ViewModel() {
    var verifiedIdRequest: VerifiedIdRequest<*>? = null
    var verifiedIdResult: Result<Any?>? = null
    var pin: String? = null

    suspend fun initiateIssuance() {
        val verifiedIdClient = VerifiedIdClientBuilder(context).build()
        // Use the test uri here
        val verifiedIdRequestUrl =
            VerifiedIdRequestURL(Uri.parse(""))
        verifiedIdRequest = verifiedIdClient.createRequest(verifiedIdRequestUrl)
    }

    suspend fun completeIssuance() {
        when (val requirement = verifiedIdRequest?.requirement) {
            is GroupRequirement -> {
                val requirementsInGroup = requirement.requirements
                for (requirementInGroup in requirementsInGroup) {
                    if (requirementInGroup is PinRequirement)
                        pin?.let { requirementInGroup.fulfill(it) }
                }
                requirement.validate()
            }
        }
        verifiedIdResult = verifiedIdRequest?.complete()
    }
}