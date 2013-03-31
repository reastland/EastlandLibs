import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IntegerFilter extends DocumentFilter {
   
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
          AttributeSet attr) throws BadLocationException {

         Document doc = fb.getDocument();
         StringBuilder sb = new StringBuilder();
         sb.append(doc.getText(0, doc.getLength()));
         sb.insert(offset, string);

         if (test(sb.toString())) {
             super.insertString(fb, offset, string, attr);
         } else {
             // warn the user and don't allow the insert
         }
    }

    private boolean test(String text) {
       try {
          if ( text.trim().isEmpty() ) {
               return true;
           } //this allows the textbox to be emptied with setText("")
          Integer.parseInt(text);
          return true;
       } catch (NumberFormatException e) {
          return false;
       }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
          AttributeSet attrs) throws BadLocationException {

         Document doc = fb.getDocument();
         StringBuilder sb = new StringBuilder();
         sb.append(doc.getText(0, doc.getLength()));
         sb.replace(offset, offset + length, text);

         if (test(sb.toString())) {
             super.replace(fb, offset, length, text, attrs);
         } else {
             // warn the user and don't allow the insert
         }
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
          throws BadLocationException {

         Document doc = fb.getDocument();
         StringBuilder sb = new StringBuilder();
         sb.append(doc.getText(0, doc.getLength()));
         sb.delete(offset, offset + length);

         if (test(sb.toString())) {
             super.remove(fb, offset, length);
         } else {
             // warn the user and don't allow the insert
         }
    }
}

