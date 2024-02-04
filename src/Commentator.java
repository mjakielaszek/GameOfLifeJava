import javax.swing.*;
import java.awt.*;

public class Commentator {
    static private DefaultListModel comments;
    static private JList commentsList;
    static public void innitComments(JPanel texts) {
        comments = new DefaultListModel<>();
        commentsList = new JList(comments);
        commentsList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane commentsScroller = new JScrollPane(commentsList);
        commentsScroller.setPreferredSize(new Dimension(200, 400));
        commentsList.setVisible(true);
        texts.add(commentsScroller);
    }
    static public void resetComments(){
        comments.clear();
    }
    static public void addComment(String comment){
        comments.addElement(comment);
    }
}
