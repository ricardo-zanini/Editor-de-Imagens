package Input;

import Alert.UserAlert;
import Screen.*;
import Util.ImageFunctions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelectorImages extends FileSelector {

    private ImageScreen  imageScreenOriginal;
    private ImageScreen  imageScreenModified;


    // Campo com tamanho e filtro
    public FileSelectorImages(int textFieldWidth, FileNameExtensionFilter filter){
        super(textFieldWidth, filter);
    }

    // Campo com tamanho
    public FileSelectorImages(int textFieldWidth){
        super(textFieldWidth);
    }

    private void eventButtonAction(ActionEvent e, FileNameExtensionFilter filter){
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
}
