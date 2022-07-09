package db

import play.api.libs.json.{Json, OFormat}

object PojoJson {
//	implicit val noteFormat = Json.format[Note]
	case class NoteInputOutput(
								 val id: Option[Int],
								 val themeId: String,
								 val note: String,
								 val tags: Option[Seq[String]]
							 )

	case class NoteTagInputOutput(
											val noteId: Int,
											val tagId: Int
										)

	case class TagInputOutput(
									val name: String
								)

	case class ThemeInputOutput(
										val name: String
									)


	implicit val formatNote: OFormat[NoteInputOutput] = Json.format[NoteInputOutput]
	implicit val formatNoteTag: OFormat[NoteTagInputOutput] = Json.format[NoteTagInputOutput]
	implicit val formatTag: OFormat[TagInputOutput] = Json.format[TagInputOutput]
	implicit val formatTheme: OFormat[ThemeInputOutput] = Json.format[ThemeInputOutput]
}
