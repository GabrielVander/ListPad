package vander.gabriel.listpad.domain.entities

data class Task(
    var checked: Boolean = false,
    val description: String,
)