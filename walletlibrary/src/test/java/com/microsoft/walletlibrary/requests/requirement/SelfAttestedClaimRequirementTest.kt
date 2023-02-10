package com.microsoft.walletlibrary.requests.requirement

import com.microsoft.did.sdk.credential.service.models.attestations.ClaimAttestation
import com.microsoft.did.sdk.credential.service.models.attestations.SelfIssuedAttestation
import com.microsoft.walletlibrary.mappings.toSelfAttestedClaimRequirement
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SelfAttestedClaimRequirementTest {
    private val claimAttestations = mutableListOf(ClaimAttestation("name", true, "string"))

    init {
        setupInput(claimAttestations, required = false, encrypted = false)
    }

    private lateinit var actualSelfIssuedAttestation: SelfIssuedAttestation

    @Test
    fun selfAttestedMapping_RequiredAndEncryptedFalse_Succeeds() {
        // Arrange
        val expectedClaimName = "name"
        val expectedClaimType = "string"

        // Act
        val expectedSelfAttestedRequirement = actualSelfIssuedAttestation.toSelfAttestedClaimRequirement()

        // Assert
        assertThat(expectedSelfAttestedRequirement.claim.first().claim).isEqualTo(expectedClaimName)
        assertThat(expectedSelfAttestedRequirement.claim.first().type).isEqualTo(expectedClaimType)
        assertThat(expectedSelfAttestedRequirement.claim.first().required).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.required).isEqualTo(false)
        assertThat(expectedSelfAttestedRequirement.encrypted).isEqualTo(false)
    }

    @Test
    fun selfAttestedMapping_RequiredAndEncryptedTrue_Succeeds() {
        // Arrange
        setupInput(claimAttestations, required = true, encrypted = true)
        val expectedClaimName = "name"
        val expectedClaimType = "string"

        // Act
        val expectedSelfAttestedRequirement = actualSelfIssuedAttestation.toSelfAttestedClaimRequirement()

        // Assert
        assertThat(expectedSelfAttestedRequirement.claim.first().claim).isEqualTo(expectedClaimName)
        assertThat(expectedSelfAttestedRequirement.claim.first().type).isEqualTo(expectedClaimType)
        assertThat(expectedSelfAttestedRequirement.claim.first().required).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.required).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.encrypted).isEqualTo(true)
    }

    @Test
    fun selfAttestedMapping_ListOfClaims_Succeeds() {
        // Arrange
        claimAttestations.add(ClaimAttestation("company", true, "string"))
        setupInput(claimAttestations, required = true, encrypted = true)
        val expectedClaimNames = listOf("name", "company")
        val expectedClaimType = "string"

        // Act
        val expectedSelfAttestedRequirement = actualSelfIssuedAttestation.toSelfAttestedClaimRequirement()

        // Assert
        assertThat(expectedSelfAttestedRequirement.claim.map { it.claim }
            .containsAll(expectedClaimNames)).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.claim.map { it.type }
            .contains(expectedClaimType)).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.claim.first().required).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.required).isEqualTo(true)
        assertThat(expectedSelfAttestedRequirement.encrypted).isEqualTo(true)
    }

    private fun setupInput(
        claimAttestations: List<ClaimAttestation>,
        required: Boolean,
        encrypted: Boolean
    ) {
        actualSelfIssuedAttestation = SelfIssuedAttestation(claimAttestations, required, encrypted)
    }
}