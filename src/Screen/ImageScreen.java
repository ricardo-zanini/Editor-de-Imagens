package Screen;

import Alert.UserAlert;
import Util.ImageFunctions;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageScreen extends Screen{
    private BufferedImage imageBuffer;
    private int[][] imageRGBA;
    
    private JLabel containerImage;

    public ImageScreen(String tituloPagina, BufferedImage imagemBuffer, int locationX, int locationY, int eventOnClose){
        super(tituloPagina, 1, 1);
        setLocation(locationX, locationY);
        
        loadImage(imagemBuffer, null);
        
        createComponents();
        configComponents();
        addComponents();

        setDefaultCloseOperation(eventOnClose);
        setVisible(true);
    }

    public ImageScreen(String tituloPagina, String imagePath, int locationX, int locationY, int eventOnClose){
        super(tituloPagina, 1, 1);
        setLocation(locationX, locationY);

        loadImage(null, imagePath);

        createComponents();
        configComponents();
        addComponents();

        setSize(imageBuffer.getWidth(), imageBuffer.getHeight());

        setDefaultCloseOperation(eventOnClose);
        setVisible(true);
        
    }
    
    // Alimenta atributos de acordo com imagem recebida
    private void loadImage(BufferedImage imageBuffer, String imagePath) {
        
        if(imagePath != null){
            try
            {
                setImageBuffer(ImageIO.read(new File(imagePath))); // Alimenta Buffer da imagem

                setImageRGBA(ImageFunctions.imageToRGBA(getImageBuffer())); // Alimenta Matriz[l][c] com as cores da imagem (em padrão com bits corrigidos)

            }catch (IOException e) {
                UserAlert userAlert = new UserAlert("ERRO - Erro ao Gerar Imagem"); 
            }
        }else{
            // !! Imagem deve ter o buffer clonado para que a mesma região de memória não seja editada
            BufferedImage bufferedImage = ImageFunctions.deepCopy(imageBuffer);
            setImageBuffer(bufferedImage);
            
            // Cria matriz rgba da imagem
            setImageRGBA(ImageFunctions.imageToRGBA(imageBuffer));
        }
    }

    private void createComponents() {
        setContainerImage(new JLabel(new ImageIcon(getImageBuffer())));
    }

    private void configComponents() {
        containerImage.setBounds(0, 0, imageBuffer.getWidth(), imageBuffer.getHeight());

        setSize(imageBuffer.getWidth(), imageBuffer.getHeight());
    }
    
    private void addComponents() {
        add(containerImage);
    }

    public void refreshComponents(){
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(38,38,38));

        createComponents();
        addComponents();
        configComponents();
    }


    public BufferedImage getImageBuffer() {
        return imageBuffer;
    }
    public void setImageBuffer(BufferedImage imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    public JLabel getContainerImage() {
        return containerImage;
    }
    public void setContainerImage(JLabel containerImage) {
        this.containerImage = containerImage;
    }

    public int[][] getImageRGBA() {
        return imageRGBA;
    }
    public void setImageRGBA(int[][] imageRGBA) {
        this.imageRGBA = imageRGBA;
    }

}
