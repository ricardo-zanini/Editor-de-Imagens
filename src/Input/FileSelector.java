package Input;

import java.awt.*;
import java.awt.event.ActionEvent;
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

    // Campo com tamanho e filtro
    public FileSelector(int textFieldWidth, FileNameExtensionFilter filter, String action){

        createComponents(action);
        configComponents(textFieldWidth, filter, action);
        addComponents(action);

        buttonFile.addActionListener(event -> eventSelectFile(event, action));
    }

    // Campo com tamanho
    public FileSelector(int textFieldWidth, String action){

        createComponents(action);
        configComponents(textFieldWidth, null, action);
        addComponents(action);


        buttonFile.addActionListener(event -> eventSelectFile(event, action));
    }


    private void createComponents(String action){
        ImageIcon imageIcon = new ImageIcon("img/fileIcon.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setButtonFile(new JButton("", new ImageIcon(newimg)));

        if(action != null)
            setButtonAction(new JButton(action));

        setFieldFile(new JTextField());
    }
    private void configComponents(int textFieldWidth, FileNameExtensionFilter filter, String action){

        buttonFile.setBackground(new Color(216, 225, 240));
        buttonFile.setBorder(new LineBorder(new Color(216, 225, 240),0));
        buttonFile.setMaximumSize(new Dimension(60, 40));
        buttonFile.setFocusable(false);

        if(action != null){
            buttonAction.setBackground(new Color(216, 225, 240));
            buttonAction.setBorder(new LineBorder(new Color(0, 0, 0),0));
            buttonAction.setFont(new Font(null, Font.BOLD, 12));
            buttonAction.setMaximumSize(new Dimension(80, 40));
            buttonAction.addActionListener(event -> eventButtonAction(event, action));
            buttonAction.setFocusable(false);
        }

        fieldFile.setFont(new Font(null, Font.BOLD, 12));
        fieldFile.setBackground(Color.white);
        fieldFile.setBorder(new LineBorder(Color.white,10));
        fieldFile.setMaximumSize(new Dimension(textFieldWidth, 40));
        fieldFile.setEditable(false);
        fieldFile.setFocusable(false);
        fieldFile.setForeground(new Color(204, 204, 204));

        windowFile  = new JFileChooser();
        windowFile.setVisible(false);
        
        if(filter != null){
            windowFile.addChoosableFileFilter(filter);
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

    private void eventSelectFile(ActionEvent e, String filter){

    }

    private void eventButtonAction(ActionEvent e, String action){

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