/**---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.walletlibrary.requests

import com.microsoft.walletlibrary.requests.rawrequests.RawContract
import com.microsoft.walletlibrary.requests.requirements.Requirement
import com.microsoft.walletlibrary.requests.styles.RequesterStyle
import com.microsoft.walletlibrary.requests.styles.VerifiedIDStyle
import com.microsoft.walletlibrary.verifiedid.VerifiedId
import com.microsoft.walletlibrary.wrapper.VerifiedIdRequester

/**
 * Issuance request specific to OpenId protocol.
 */
class ContractIssuanceRequest(
    // Attributes describing the requester (eg. name, logo).
    override val requesterStyle: RequesterStyle,

    // Information describing the requirements needed to complete the flow.
    override val requirement: Requirement,

    // Root of trust of the requester (eg. linked domains).
    override val rootOfTrust: RootOfTrust,

    // Attributes describing the Verified ID (eg. name, issuer, logo, background and text colors).
    val verifiedIdStyle: VerifiedIDStyle,

    val request: RawContract
): VerifiedIdIssuanceRequest {
    override suspend fun complete(): Result<VerifiedId> {
        return try {
            val verifiedId = VerifiedIdRequester.sendIssuanceResponse(this)
            Result.success(verifiedId)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override fun isSatisfied(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel(message: String?): Result<Void> {
        TODO("Not yet implemented")
    }
}