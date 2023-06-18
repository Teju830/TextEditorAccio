// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener{
        JFrame frame;
        JMenuBar menubar;
        JMenu file,edit;
        JMenuItem Newfile,openfile,savefile;
        JMenuItem cut,copy,paste,selectall,close;
        JTextArea textArea;

        TextEditor(){
            frame = new JFrame(); // intialise frame
            menubar = new JMenuBar(); // intialize menubar
            textArea=new JTextArea();// intialize textarea
            // initialize menus
            file = new JMenu("File");
            edit = new JMenu("Edit");
            //intilize menuitems for file
            Newfile = new JMenuItem("New File");
            openfile= new JMenuItem("Open File");
            savefile = new JMenuItem("Save File");
            // before adding to menu we to add to addlistener class
            Newfile.addActionListener(this);
            openfile.addActionListener(this);
            savefile.addActionListener(this);
            // add file items to file menu
            file.add(Newfile);
            file.add(openfile);
            file.add(savefile);
            // intialize menuitems for edit
            cut = new JMenuItem("cut");
            copy = new JMenuItem("copy");
            paste = new JMenuItem("Paste");
            selectall = new JMenuItem("Select All");
            close = new JMenuItem("Close");
            //similar to edit
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectall.addActionListener(this);
            close.addActionListener(this);
            //add edit menu to edit items
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectall);
            edit.add(close);
            // add file and edit to menubar
            menubar.add(file);
            menubar.add(edit);
            //set menubar to frame
            frame.setJMenuBar(menubar);
            // create panel
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5,5,5,5));
            panel.setLayout(new BorderLayout(0,0));
            //add textarea to panel
            panel.add(textArea, BorderLayout.CENTER);
            //create scroll pane
            JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            // scrolpane to panel
            panel.add(scrollPane);
            //add panel to frame
            frame.add(panel);
            // we can also add title to frame
            frame.setTitle("NotePad");
            frame.setBounds(100, 100, 400,400); // set dimensions for frame
            frame.setVisible(true); // make frame visible to us in window
            frame.setLayout(null);

        }
        @Override
        public  void actionPerformed(ActionEvent actionEvent) {
            // perform cut operation
            if(actionEvent.getSource()==cut) {
                textArea.cut();
            }
            if(actionEvent.getSource()==copy) {
                textArea.copy();
            }
            if(actionEvent.getSource()==paste) {
                textArea.paste();
            }
            if(actionEvent.getSource()==selectall) {
                textArea.selectAll();
            }
            if(actionEvent.getSource()==close) {
                System.exit(0);
            }
            // openfile means taking text from file and paste it over textarea
            if(actionEvent.getSource()==openfile){
                //open file chooser
                JFileChooser fileChooser=new JFileChooser("c:"); // it will open default c drive
                int chooseoption = fileChooser.showOpenDialog(null);// we need to open file from c drive so show open option
                if(chooseoption == JFileChooser.APPROVE_OPTION){ //when we clickon open
                    //get the text into text area
                    File file = fileChooser.getSelectedFile();
                    //get the path of selected file
                    String filepath = file.getPath();
                    try{
                        // intialize file reader
                        FileReader fileReader = new FileReader(filepath);
                        // intialize buffer reader
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String intermedia = "" , output =""; // intermedia for reading single lines and output for reading whole text
                     while((intermedia = bufferedReader.readLine())!=null){ // read the text line by line and copy it to output
                         output += intermedia+"\n";
                     }
                     textArea.setText(output); // copy output text to textarea
                    }
                    catch(IOException FileNotFoundException){
                        FileNotFoundException.printStackTrace();// it prints throwable class along line num and class name
                    }

                }
            }
            if(actionEvent.getSource()==savefile){
                // intialize file picker
                JFileChooser fileChooser = new JFileChooser("c:");// choose
                int choosefile = fileChooser.showSaveDialog(null);// get save button
                if(choosefile== JFileChooser.APPROVE_OPTION){
                    // we use to create new file to copy text from textediter to selected file
                    File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+ ".txt");
                    // we have to save that file
                    try{
                        // here we need to write the text so we use filewriter
                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        // we are having fun in textarea to write text from texteditor to selected path
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }

            }
            if(actionEvent.getSource()== Newfile){
                TextEditor newTextEditor = new TextEditor();
            }
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            TextEditor texteditor = new TextEditor();

        }

    }
