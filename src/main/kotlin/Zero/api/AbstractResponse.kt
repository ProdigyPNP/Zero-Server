package Zero.api

/** Abstract Response  */
abstract class AbstractResponse {
    /** Abstract Method for getting the response.  */
    abstract fun getResponse(req: Request): Response
}