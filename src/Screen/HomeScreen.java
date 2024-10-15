package Screen;
import Input.FileSelector;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomeScreen extends Screen{

    private JLabel labelTitle;

    private JLabel labelFileSelector;
    private FileSelector fileSelector;
    
    private JLabel labelFileSaver;
    private FileSelector fileSaver;

    public HomeScreen(){
        super("Tela Inicial");
        
        createComponents();
        configComponents();
        addComponents();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createComponents(){

        setTitle("Menu"); 

        setLabelFileSelector(new JLabel("- Carregar Imagem -"));

        setFileSelector(new FileSelector(505, new FileNameExtensionFilter(".txt", "txt"), "Salvar"));       

        setLabelTitle(new JLabel("- Editor de Imagens -"));          

    }

    private void configComponents(){
        labelTitle.setBounds(175, 20, 400, 50);
        labelTitle.setFont(new Font(null, Font.BOLD, 25));

        //---------------------------------------------------------

        labelFileSelector.setBounds(20, 60, 200, 50);
        labelFileSelector.setFont(new Font(null, Font.BOLD, 15));

        fileSelector.setBounds(20, 110, 545, 40);

    }

    private void addComponents(){

        add(labelTitle);

        add(labelFileSelector);     // Label do seletor
        add(fileSelector);          // Seletor de Arquivos
    }
    
    public JLabel getLabelTitle() {
        return labelTitle;
    }
    public void setLabelTitle(JLabel labelTitle) {
        this.labelTitle = labelTitle;
    }


    
    public JLabel getLabelFileSelector() {
        return labelFileSelector;
    }
    public void setLabelFileSelector(JLabel labelFileSelector) {
        this.labelFileSelector = labelFileSelector;
    }

    public FileSelector getFileSelector() {
        return fileSelector;
    }
    public void setFileSelector(FileSelector fileSelector) {
        this.fileSelector = fileSelector;
    }



    public JLabel getLabelFileSaver() {
        return labelFileSaver;
    }
    public void setLabelFileSaver(JLabel labelFileSaver) {
        this.labelFileSaver = labelFileSaver;
    }
    
    public FileSelector getFileSaver() {
        return fileSaver;
    }
    public void setFileSaver(FileSelector fileSaver) {
        this.fileSaver = fileSaver;
    }
    
    
}
