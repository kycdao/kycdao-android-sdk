package com.kycdao.android.sdk.ui.progress_screen

import com.kycdao.android.sdk.model.State
import com.kycdao.android.sdk.ui.progress_screen.model.Progress
import com.kycdao.android.sdk.ui.progress_screen.model.Step
import kotlinx.coroutines.flow.*

class ProgressManager {
    companion object {

        private val steps = mapOf(
            State.CONNECT_WALLET to Step( "Connect wallet"),
            State.SESSION_REQUIRED to Step( "Create session"),
            State.LOGIN_REQUIRED to Step( "Login"),
            State.USER_INFORMATION_REQUIRED to Step( "Get user information"),
            State.WAIT_EMAIL_CONFIRMED to Step( "Wait email confirmed"),
            State.IDENTITY_VERIFICATION to Step( "Identity Verification"),
            State.POLL_IDENTITY_VERIFICATION_RESULT to Step( "Poll identity verification result"),
            State.NFT_IMAGE_SELECTION to Step( "NFT Image selection"),
            State.WAITING_FOR_MINTING_AUTHORISATION to Step( "Waiting for minting authorization"),
            State.CALCULATE_FEE to Step( "Fee calculation"),
            State.MINTING to Step( "Minting"),
            State.CHECK_MINTING to Step( "Check minting"),
            State.POST_MINT_TOKEN_ID to Step( "Send mint token"),
            State.COMPLETED to Step( "Completed"),
        )
        private val progressFlow = MutableSharedFlow<List<Step>>(1, 100)
        val output = progressFlow.map { list -> list.filter { step -> step.show } }

        fun get(stepId: State) : Step {
            return steps[stepId] ?: Step(stepId.name)
        }

        fun setInProgress(stepId : State) {
            setProgress(stepId, Progress.IN_PROGRESS)
        }

        fun setComplete(stepId : State) {
            setProgress(stepId, Progress.DONE)
        }

        fun setError(stepId : State) {
            setProgress(stepId, Progress.ERROR)
        }

        private fun setProgress(stepId : State, progress: Progress) {
            steps[stepId]?.apply {
                this.progress = progress
                this.show = true

            }
            updateFlow()
        }

        public fun updateFlow() {
            progressFlow.tryEmit(steps.values.toList())
        }
    }
}