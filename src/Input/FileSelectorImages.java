package Input;

import Alert.UserAlert;
import Screen.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelectorImages extends FileSelector {

    private ImageScreen  imageScreenOriginal;
    private ImageScreen  imageScreenModified;

    public FileSelectorImages(int textFieldWidth, FileNameExtensionFilter filter){
        super(textFieldWidth, filter, "CARREGAR");
        getButtonAction().addActionListener(event -> eventButtonAction());
        getButtonFile().addActionListener(event -> eventSelectFile());
    }

    private void eventButtonAction(){
        String path = getFieldFile().getText();

        if(path.equals("")){
            UserAlert userAlert = new UserAlert("ERRO - Imagem não selecionada");
        }else{
            // Antes de criar nova tela de imagem exclui a antiga
            Screen.deleteDuplicatedScreens("Imagem Original");
            setImageScreenOriginal(new ImageScreen("Imagem Original", getFieldFile().getText(), 440, 20, JFrame.EXIT_ON_CLOSE));

            // Antes de criar nova tela de imagem exclui a antiga
            Screen.deleteDuplicatedScreens("Imagem Modificada");
            setImageScreenModified(new ImageScreen("Imagem Modificada", imageScreenOriginal.getImageBuffer(), 440, 400, JFrame.EXIT_ON_CLOSE));
        }
    }

    private void eventSelectFile(){
        getWindowFile().setVisible(true);
        getWindowFile().showOpenDialog(null);
        getFieldFile().setText(getWindowFile().getSelectedFile().getPath());
    }

    public ImageScreen getImageScreenOriginal() {
        return imageScreenOriginal;
    }
    public void setImageScreenOriginal(ImageScreen imageScreenOriginal) {
        this.imageScreenOriginal = imageScreenOriginal;
    }

    public ImageScreen getImageScreenModified() {
        return imageScreenModified;
    }
    public void setImageScreenModified(ImageScreen imageScreenModified) {
        this.imageScreenModified = imageScreenModified;
    }
}
