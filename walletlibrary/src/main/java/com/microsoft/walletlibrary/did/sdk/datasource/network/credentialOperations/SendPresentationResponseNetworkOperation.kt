/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.walletlibrary.did.sdk.datasource.network.credentialOperations

import com.microsoft.walletlibrary.did.sdk.datasource.network.PostNetworkOperation
import com.microsoft.walletlibrary.did.sdk.datasource.network.apis.ApiProvider
import com.microsoft.walletlibrary.did.sdk.util.controlflow.Result
import retrofit2.Response

internal class SendPresentationResponseNetworkOperation(url: String, serializedIdToken: String, vpToken: String, state: String?, apiProvider: ApiProvider) :
    PostNetworkOperation<String, Unit>() {
    override val call: suspend () -> Response<String> = {
        apiProvider.presentationApis.sendResponse(url, serializedIdToken, vpToken, state) }

    override suspend fun onSuccess(response: Response<String>): Result<Unit> {
        return Result.Success(Unit)
    }
}

// The plural vp_token format
internal class SendPresentationResponsesNetworkOperation(url: String, serializedIdToken: String, vpToken: List<String>, state: String?, apiProvider: ApiProvider) :
    PostNetworkOperation<String, Unit>() {
    override val call: suspend () -> Response<String> = {
        apiProvider.presentationApis.sendResponses(url, serializedIdToken, vpToken, state) }

    override suspend fun onSuccess(response: Response<String>): Result<Unit> {
        return Result.Success(Unit)
    }
}