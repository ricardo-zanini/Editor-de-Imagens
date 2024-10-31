
package Screen;
import Alert.UserAlert;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class HistogramScreen extends Screen{

    JButton[] graphBars;
    JButton   barX;
    JButton   barY;
    int[] histogramArray;

    JLabel labelY;
    JLabel labelX;
    JLabel labelZero;

    public HistogramScreen(String title, int[] arrayElements, boolean normalize, int positionX, int positionY){
        super("Histograma - " + title, 350, 370);

        setLocation(positionX, positionY);
        setLayout(null);

        setHistogramArray(arrayElements);

        createComponents(arrayElements);
        configComponents(arrayElements, normalize);
        addComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void createComponents(int[] arrayElements){
        setGraphBars(new JButton[arrayElements.length]);
        for(int position = 0; position < arrayElements.length; position++){
            addGraphBar(new JButton(""), position);
        }

        setLabelX(new JLabel("255"));
        setLabelY(new JLabel(""));
        setLabelZero(new JLabel("0"));

        setBarX(new JButton(""));
        setBarY(new JButton(""));
    }
    private void configComponents(int[] arrayElements, boolean normalize){
        try{
            File fontStyle = new File("Fonts/pixelated_fancy_font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle);
            
            labelX.setFont(font.deriveFont(16f));
            labelY.setFont(font.deriveFont(16f));
            labelZero.setFont(font.deriveFont(16f));

        }catch(Exception e){
            UserAlert userAlert = new UserAlert("ERRO - Erro ao carregar Fonte"); 
        }

        labelX.setBounds(300, 285, 400, 50);
        labelX.setForeground(Color.white);
        labelX.setAlignmentX(LEFT_ALIGNMENT);

        labelY.setBounds(30, 0, 100, 50);
        labelY.setForeground(Color.white);
        labelY.setAlignmentX(LEFT_ALIGNMENT);

        labelZero.setBounds(30, 285, 100, 50);
        labelZero.setForeground(Color.white);
        labelZero.setAlignmentX(LEFT_ALIGNMENT);

        int greater = foundGreater(arrayElements);
        int valueDiv = normalize == true ? (int)(greater / 255) : 1;
        for(int position = 0; position < arrayElements.length; position++){
            graphBars[position].setBounds((1 * position) + 40, (int)(295 - (arrayElements[position] / valueDiv)), 1, (int) (arrayElements[position] / valueDiv));
            graphBars[position].setBackground(new Color(120, 90,148));
            graphBars[position].setBorder(new LineBorder(new Color(0, 0, 0),0));
            graphBars[position].setFocusable(false);
        }

        barX.setBounds(37, 295, 259, 2);
        barX.setBackground(new Color(255, 255,255));
        barX.setBorder(new LineBorder(new Color(0, 0, 0),0));
        barX.setFocusable(false);

        barY.setBounds(37, 40, 2, 255);
        barY.setBackground(new Color(255, 255,255));
        barY.setBorder(new LineBorder(new Color(0, 0, 0),0));
        barY.setFocusable(false);

        labelY.setText(String.valueOf(greater));
    }
    private void addComponents(){
        add(barX);
        add(barY);

        for(int position = 0; position < graphBars.length; position++){
            add(graphBars[position]);
        } 
        add(labelY);
        add(labelX);
        add(labelZero);

    }

    private int foundGreater(int[] arrayElements){
        int returnInt = 0;
        for(int i = 0; i < arrayElements.length; i++){
            if (arrayElements[i] > returnInt){ 
                returnInt = arrayElements[i];
            }
        }
        return returnInt;
    }

    public void setGraphBar(JButton graphBar, int position){
        this.graphBars[position] = graphBar;
    }
    public void setGraphBars(JButton[] graphBars){
        this.graphBars = graphBars;
    }
    public void addGraphBar(JButton graphBar, int position){
        this.graphBars[position] = graphBar;
    }
    public JButton getGraphBar(int position){
        return graphBars[position];
    }
    public JButton[] getGraphBars(){
        return graphBars;
    }


    public JLabel getLabelY() {
        return labelY;
    }
    public void setLabelY(JLabel labelY) {
        this.labelY = labelY;
    }


    public JLabel getLabelX() {
        return labelX;
    }
    public void setLabelX(JLabel labelX) {
        this.labelX = labelX;
    }


    public JLabel getLabelZero() {
        return labelZero;
    }
    public void setLabelZero(JLabel labelZero) {
        this.labelZero = labelZero;
    }

    public int[] getHistogramArray() {
        return histogramArray;
    }
    public void setHistogramArray(int[] histogramArray) {
        this.histogramArray = histogramArray;
    }

    public JButton getBarX() {
        return barX;
    }
    public void setBarX(JButton barX) {
        this.barX = barX;
    }

    public JButton getBarY() {
        return barY;
    }
    public void setBarY(JButton barY) {
        this.barY = barY;
    }

}
