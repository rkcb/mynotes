package repos

sealed trait OperationStatus {
	type ErrorMessage = String
}
case object Success extends OperationStatus
case object Failure extends OperationStatus


