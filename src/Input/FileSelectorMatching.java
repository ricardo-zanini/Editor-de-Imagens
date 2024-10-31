package Input;

import Alert.UserAlert;
import Screen.ImageScreen;
import Screen.Screen;
import Util.ImageFunctions;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelectorMatching extends FileSelector{

    private ImageScreen  imageScreen_1;
    private ImageScreen  imageScreen_2;
    private ImageScreen  imageScreen_Result;

    public FileSelectorMatching(int textFieldWidth, FileNameExtensionFilter filter, FileSelectorImages fileSelector){
        super(textFieldWidth, filter, "MATCHING");

        getButtonAction().addActionListener(event -> eventButtonAction(fileSelector));
        getButtonFile().addActionListener(event -> eventSelectFile());
    }

    private void eventButtonAction(FileSelectorImages fileSelector){
        String path = getFieldFile().getText();

        if(path.equals("")){
            UserAlert userAlert = new UserAlert("ERRO - Imagem não selecionada");
        }else{
            BufferedImage image_buffer_1 = fileSelector.getImageScreenModified().getImageBuffer();
            Screen.deleteDuplicatedScreens("Imagem Para Matching");
            setImageScreen_2(new ImageScreen("Imagem Para Matching", getFieldFile().getText(), 950, 20, JFrame.DISPOSE_ON_CLOSE));
            
            
            imageScreen_2.setImageBuffer(ImageFunctions.imageToGray(imageScreen_2.getImageBuffer()));
            imageScreen_2.setImageRGBA(ImageFunctions.imageToRGBA(imageScreen_2.getImageBuffer()));
            imageScreen_2.refreshComponents();

            BufferedImage image_buffer_2 = imageScreen_2.getImageBuffer();

            BufferedImage image_buffer_Result = ImageFunctions.imageHistogramMatchingMonochromatic(image_buffer_1, image_buffer_2);

            Screen.deleteDuplicatedScreens("Imagem Após Matching");
            setImageScreen_Result(new ImageScreen("Imagem Após Matching", image_buffer_Result, 950, 400, JFrame.DISPOSE_ON_CLOSE));
        }
    }

    private void eventSelectFile(){
        getWindowFile().setVisible(true);
        getWindowFile().showOpenDialog(null);
        getFieldFile().setText(getWindowFile().getSelectedFile().getPath());
    }


    public ImageScreen getImageScreen_1() {
        return imageScreen_1;
    }
    public void setImageScreen_1(ImageScreen imageScreen_1) {
        this.imageScreen_1 = imageScreen_1;
    }

    public ImageScreen getImageScreen_2() {
        return imageScreen_2;
    }
    public void setImageScreen_2(ImageScreen imageScreen_2) {
        this.imageScreen_2 = imageScreen_2;
    }

    public ImageScreen getImageScreen_Result() {
        return imageScreen_Result;
    }
    public void setImageScreen_Result(ImageScreen imageScreen_Result) {
        this.imageScreen_Result = imageScreen_Result;
    }
}
