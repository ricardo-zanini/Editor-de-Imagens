package Input;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TextArea extends JTextArea{
    
    public TextArea(){
        super();
        setBackground(Color.white);
        setBorder(new LineBorder(Color.white,10));
        setLineWrap(true);
    }
}
