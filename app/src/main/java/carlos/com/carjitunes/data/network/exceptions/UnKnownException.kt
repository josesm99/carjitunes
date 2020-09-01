package carlos.com.carjitunes.data.network.exceptions

class UnKnownException : Exception() {

    override val message: String?
        get() = "Some Unknown Error Occurred"
}