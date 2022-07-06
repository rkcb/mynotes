package db

import play.api.libs.json.Json

object PojoJson {
//	implicit val noteFormat = Json.format[Note]
	case class Note(
								 val id: Int,
								 val themeId: String,
								 val note: String
							 )

	case class NoteTag(
											val noteId: Int,
											val tagId: Int
										)

	case class Tag(
									val name: String
								)

	case class Theme(
										val name: String
									)


	implicit val formatNote = Json.format[Note]
	implicit val formatNoteTag = Json.format[NoteTag]
	implicit val formatTag = Json.format[Tag]
	implicit val formatTheme = Json.format[Theme]
}
