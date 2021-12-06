package vander.gabriel.listpad.domain.entities

data class Task(
    val id: String,
    val checked: Boolean = false,
    val description: String,
)