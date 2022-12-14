package download.mishkindeveloper.shoppinglist.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ActivityNewNoteBinding
import download.mishkindeveloper.shoppinglist.entity.NoteItem
import download.mishkindeveloper.shoppinglist.fragments.NoteFragment
import download.mishkindeveloper.shoppinglist.utils.HtmlManager
import download.mishkindeveloper.shoppinglist.utils.TimeManager
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    private var note: NoteItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSettings()
        getNote()
    }

    private fun getNote(){
        val sNote = intent.getSerializableExtra(NoteFragment.NEW_NOTE_KEY)
        if (sNote!=null){
            note = sNote as NoteItem
            fillNote()
        }


    }

    private fun fillNote() = with( binding){

            edTitleNote.setText(note?.title)
            edDiscriptionNote.setText(HtmlManager.getFromHtml(note?.content!!).trim())


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.id_save) {
            setMainResult()
        } else if (item.itemId == android.R.id.home) {
        finish()
        } else if (item.itemId == R.id.id_bold) {
            setBoldForSelectedText()
        }
            return super.onOptionsItemSelected(item)
    }

    private fun setBoldForSelectedText()  = with(binding){
val startPosition = edDiscriptionNote.selectionStart
val endPosition = edDiscriptionNote.selectionEnd

        val styles = edDiscriptionNote.text.getSpans(startPosition,endPosition,StyleSpan::class.java)
        var boldStyle:StyleSpan? = null
        if (styles.isNotEmpty()){
            edDiscriptionNote.text.removeSpan(styles[0])
        } else {
            boldStyle = StyleSpan(Typeface.BOLD)
        }
        edDiscriptionNote.text.setSpan(boldStyle,startPosition,endPosition,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        edDiscriptionNote.text.trim()
        edDiscriptionNote.setSelection(startPosition)
    }

    private fun setMainResult(){
        var ediState = "new"
        val tempNote:NoteItem? = if (note==null){
            createNewNote()
        }else{
            ediState = "update"
            updateNote()
        }
        val i = Intent().apply {
            putExtra(NoteFragment.NEW_NOTE_KEY,tempNote)
            putExtra(NoteFragment.EDIT_STATE_KEY,ediState)
        }
        setResult(RESULT_OK,i)
        finish()
    }

    private fun updateNote():NoteItem? = with(binding){
       return note?.copy(
            title = edTitleNote.text.toString(),
            content = HtmlManager.toHtml(edDiscriptionNote.text)
        )
    }

    private fun createNewNote():NoteItem{
        return NoteItem(
            null,
            binding.edTitleNote.text.toString(),
            HtmlManager.toHtml(binding.edDiscriptionNote.text),
            TimeManager.getCurrentTime(),
            "")
    }


    private fun actionBarSettings(){
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
}