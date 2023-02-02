/**---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.walletlibrary.requests.contract.attributes

data class VerifiedIdAttributes(
    // What locale the display information is in.
    val locale: String,

    // Title of the Verified Id.
    val title: String,

    // Issuer of the Verified Id.
    val issuer: String,

    // The background color of the Verified Id in hex.
    val backgroundColor: String,

    // The color of the text written on Verified Id in hex.
    val textColor: String,

    // Logo that should be displayed on the Verified Id.
    val logo: Logo? = null,

    // Description of the Verified Id
    val description: String,

    // Display attributes per claim
    val claimAttributes: List<ClaimAttributes>
)