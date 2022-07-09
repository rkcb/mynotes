package repos

import db.PojoJson.{NoteInputOutput, TagInputOutput, ThemeInputOutput}
import db.org.jooq.tables.Note.NOTE
import db.org.jooq.tables.NoteTag.NOTE_TAG
import db.org.jooq.tables.Tag.TAG
import db.org.jooq.tables.Theme.THEME
import db.org.jooq.tables.records.{NoteRecord, NoteTagRecord}
import models.DbApi
import org.jooq.scalaextensions.Conversions.ScalaField
import repos.Failure.ErrorMessage

import javax.inject.Inject
import scala.concurrent.Future
import scala.jdk.CollectionConverters.{CollectionHasAsScala, SeqHasAsJava}

class NoteRepo @Inject() (db: DbApi) {

	def getThemes: Future[Seq[ThemeInputOutput]] = {
		db.action { jooq =>
			jooq
				.selectFrom(THEME)
				.fetch(THEME.NAME)
				.asScala
				.map(name => ThemeInputOutput(name))
				.toSeq
		}
	}

	def getTags: Future[Seq[TagInputOutput]] = {
		db.action { jooq =>
			jooq
				.selectFrom(TAG)
				.fetch(TAG.NAME)
				.asScala
				.map(name => TagInputOutput(name))
				.toSeq
		}
	}

	def createNote(note: NoteInputOutput):
	Future[Either[ErrorMessage, NoteInputOutput]] = {

		db.action { jooq =>
			val created = jooq.transactionResult(trx => {

				val noteRecord = new NoteRecord()
					.setThemeId(note.themeId)
					.setNote(note.note)

				val noteId: Int = trx.dsl()
					.insertInto(NOTE)
					.set(noteRecord)
					.returning(NOTE.ID)
					.execute()

				note.tags.map(tagNames => {
					val noteTags = tagNames.map(tagName => new NoteTagRecord()
						.setNoteId(noteId)
						.setTagId(tagName)
					)
					trx.dsl()
						.batchInsert(noteTags.asJava)
						.execute
				})

				note.copy(id = Option(noteId))
			}) // transactionResult)

			Right(created)
		}
	} // createNote

	def deleteNote(noteId: Int): Future[OperationStatus] = {
		db.action { jooq =>

			jooq
				.deleteFrom(NOTE_TAG)
				.where(NOTE_TAG.NOTE_ID === noteId)
				.execute()

			val res = jooq
				.deleteFrom(NOTE)
				.where(NOTE.ID === noteId)
				.execute()

			if (res == 1)
				Success
			else
				Failure
		}
	}



}