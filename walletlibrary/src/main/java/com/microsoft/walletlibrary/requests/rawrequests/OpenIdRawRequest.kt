/**---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.walletlibrary.requests.rawrequests

import com.microsoft.did.sdk.credential.service.PresentationRequest
import com.microsoft.walletlibrary.requests.VerifiedIdRequestContent

/**
 * Represents the OpenID raw request and the request type.
 */
internal interface OpenIdRawRequest: RawRequest {
    override val rawRequest: PresentationRequest
    fun mapToRequestContent(): VerifiedIdRequestContent
}
