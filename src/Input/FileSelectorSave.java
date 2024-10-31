package Input;

import Alert.UserAlert;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelectorSave extends FileSelector{

    public FileSelectorSave(int textFieldWidth, FileNameExtensionFilter filter, FileSelectorImages fileSelector){
        super(textFieldWidth, filter, "SALVAR");
        getButtonAction().addActionListener(event -> eventButtonAction(filter, fileSelector));
        getButtonFile().addActionListener(event -> eventSelectFile());
    }

    private void eventButtonAction(FileNameExtensionFilter filter, FileSelectorImages fileSelector){
        try {
            File outputfile = new File(getFieldFile().getText());
            ImageIO.write(fileSelector.getImageScreenModified().getImageBuffer(), filter.getExtensions()[0], outputfile);
            UserAlert userAlert = new UserAlert("SUCESSO - Imagem Salva com Sucesso");
        } catch (IOException error) {
            UserAlert userAlert = new UserAlert("ERRO - A Imagem não pôde ser salva");
        }
    }

    private void eventSelectFile(){
        getWindowFile().setVisible(true);
        getWindowFile().showOpenDialog(null);
        getFieldFile().setText(getWindowFile().getSelectedFile().getPath());
    }
}
