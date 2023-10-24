/**---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.walletlibrary.wrapper

import com.microsoft.walletlibrary.did.sdk.VerifiableCredentialSdk
import com.microsoft.walletlibrary.did.sdk.credential.service.PresentationRequest
import com.microsoft.walletlibrary.did.sdk.credential.service.PresentationResponse
import com.microsoft.walletlibrary.did.sdk.util.controlflow.Result
import com.microsoft.walletlibrary.mappings.presentation.addRequirements
import com.microsoft.walletlibrary.requests.requirements.GroupRequirement
import com.microsoft.walletlibrary.requests.requirements.Requirement
import com.microsoft.walletlibrary.util.IdInVerifiedIdRequirementDoesNotMatchRequestException
import com.microsoft.walletlibrary.util.OpenIdResponseCompletionException
import com.microsoft.walletlibrary.util.WalletLibraryLogger

/**
 * Wrapper class to wrap the send presentation response to VC SDK.
 */
object OpenIdResponder {

    // sends the presentation response to VC SDK and returns nothing if successful.
    internal suspend fun sendPresentationResponse(
        presentationRequest: PresentationRequest,
        requirement: Requirement
    ) {
        val presentationResponses = PresentationResponse(presentationRequest, presentationRequest.getPresentationDefinitions().id)
        presentationResponses.addRequirements(requirement)
        val presentationResponseResult =
            VerifiableCredentialSdk.presentationService.sendResponse(presentationRequest, listOf(presentationResponses))
        if (presentationResponseResult is Result.Failure) {
            throw OpenIdResponseCompletionException(
                "Unable to send presentation response",
                presentationResponseResult.payload
            )
        }
    }
}