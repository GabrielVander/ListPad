package vander.gabriel.listpad.presentation.view_models

import arrow.core.right
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.GetAllCollectionsUseCase

internal class CollectionsViewModelTest {

    private lateinit var getAllCollectionsUseCase: GetAllCollectionsUseCase

    @BeforeEach
    internal fun setUp() {
        getAllCollectionsUseCase = mock()
    }

    @Nested
    inner class GetCollections {
        @Test
        @DisplayName("Should call use case correctly")
        fun shouldCallUseCaseCorrectly() {
            getAllCollectionsUseCase.stub {
                onBlocking { execute(any()) }.doReturn(
                    emptyList<Collection>()
                        .right()
                )
            }

            CollectionsViewModel()

            verifyBlocking(getAllCollectionsUseCase) {
                execute(Unit)
            }
        }
    }
}