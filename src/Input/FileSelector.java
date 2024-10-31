package Input;

import Alert.UserAlert;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelector extends JPanel{

    private JButton         buttonFile;
    private JButton         buttonAction;
    private JTextField      fieldFile;
    private JFileChooser    windowFile;
    private String          fileContent;
    private File            file;

    public FileSelector(int textFieldWidth, FileNameExtensionFilter filter, String action){

        createComponents(action);
        configComponents(textFieldWidth, filter, action);
        addComponents(action);

    }

    public FileSelector(int textFieldWidth, FileNameExtensionFilter filter){

        createComponents(null);
        configComponents(textFieldWidth, filter, null);
        addComponents(null);
    }

    public FileSelector(int textFieldWidth, String action){

        createComponents(action);
        configComponents(textFieldWidth, null, action);
        addComponents(action);
    }

    public FileSelector(int textFieldWidth){

        createComponents(null);
        configComponents(textFieldWidth, null, null);
        addComponents(null);
    }

    private void createComponents(String action){
        ImageIcon imageIcon = new ImageIcon("img/folder.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30, 30, 1); // scale it the smooth way  

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setButtonFile(new JButton("", new ImageIcon(newimg)));

        setWindowFile(new JFileChooser());
        if(action != null)
            setButtonAction(new JButton(action));

        setFieldFile(new JTextField());
    }
    private void configComponents(int textFieldWidth, FileNameExtensionFilter filter, String action){

        setBackground(new Color(38,38,38));

        try{
            File fontStyle = new File("Fonts/pixelated_fancy_font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle);
            
            buttonAction.setFont(font.deriveFont(12f));
            fieldFile.setFont(font.deriveFont(12f));
        }catch(Exception e){
            UserAlert userAlert = new UserAlert("ERRO - Erro ao carregar Fonte"); 
        }

        buttonFile.setBackground(new Color(120, 90,148));
        buttonFile.setBorder(new LineBorder(new Color(33, 33, 33),0));
        buttonFile.setMaximumSize(new Dimension(60, 40));
        buttonFile.setFocusable(false);

        if(action != null){
            buttonAction.setBackground(new Color(120, 90,148));
            buttonAction.setBorder(new LineBorder(new Color(0, 0, 0),0));
            buttonAction.setMaximumSize(new Dimension(80, 40));
            buttonAction.setFocusable(false);
            buttonAction.setForeground(Color.WHITE);
        }

        fieldFile.setBackground(new Color(33, 33, 33));
        fieldFile.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldFile.setMaximumSize(new Dimension(textFieldWidth + (action != null ? 0 : 80), 40));
        fieldFile.setEditable(false);
        fieldFile.setFocusable(false);
        fieldFile.setForeground(new Color(204, 204, 204));

        windowFile.setVisible(false);
        
        if(filter != null){
            windowFile.setFileFilter(filter);
        }else{
            windowFile.setAcceptAllFileFilterUsed(false);
        }
    }    

    private void addComponents(String action){
        add(buttonFile,  BorderLayout.WEST);
        add(fieldFile,  BorderLayout.EAST);
        if(action != null)
            add(buttonAction, BorderLayout.EAST);
        add(windowFile);
    }    

    public JButton getButtonFile() {
        return buttonFile;
    }
    public void setButtonFile(JButton buttonFile) {
        this.buttonFile = buttonFile;
    }


    public JButton getButtonAction() {
        return buttonAction;
    }
    public void setButtonAction(JButton buttonAction) {
        this.buttonAction = buttonAction;
    }


    public JTextField getFieldFile() {
        return fieldFile;
    }
    public void setFieldFile(JTextField fieldFile) {
        this.fieldFile = fieldFile;
    }


    public JFileChooser getWindowFile() {
        return windowFile;
    }
    public void setWindowFile(JFileChooser windowFile) {
        this.windowFile = windowFile;
    }


    public String getFileContent() {
        return fileContent;
    }
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }


    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }


}