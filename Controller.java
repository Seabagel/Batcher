import java.io.*;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.scene.control.*;

public class Controller {
    public Button bluBtn;
    public TextArea srcArea;
    public TextArea dstArea;
    public TextArea rgxArea;
    public TextField headField;
    public TextField footField;
    public CheckBox rgxBox;
    public CheckBox headBox;
    public CheckBox footBox;
    public CheckBox capBox;
    public CheckBox overBox;
    public CheckBox consoleBox;

    public void handleBtn() {
        File src = new File(srcArea.getText());
        File[] listFile = src.listFiles();
        boolean postConsole = consoleBox.isSelected();

        // iterate the files in the folder
        for (File oldFile : listFile) {
            if (oldFile.isFile()) {
                String fileName = oldFile.getName();
                String newSentence = fileName.substring(0, fileName.lastIndexOf("."));
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                if (postConsole) {
                    System.out.println(oldFile.getPath());
                }

                try {
                    if (rgxBox.isSelected()) {
                        // Main class code
                        String[] wordsArr = rgxArea.getText().split(";");
                        for (String x : wordsArr) {
                            String[] replaceArr = x.split("=");
                            if (replaceArr.length > 1) {
                                newSentence = newSentence.replaceAll(replaceArr[0], replaceArr[0].toLowerCase());
                                newSentence = newSentence.replaceAll(replaceArr[0].toLowerCase(), replaceArr[1]);
                            } else {
                                newSentence = newSentence.replaceAll(replaceArr[0], "");
                            }
                        }
                        if (postConsole) {
                            System.out.println("Regex: " + newSentence);
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Regex: " + e);
                }

                try {
                    if (capBox.isSelected()) {
                        // capitalize the sentence
                        // newSentence = newSentence.toLowerCase();
                        String[] words = newSentence.split(" ");
                        String tempSentence = "";
                        for (int i = 0; i < words.length; i++) {
                            String tempWord = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
                            if (i == words.length) {
                                tempSentence += tempWord;
                            } else {
                                tempSentence += tempWord + " ";
                            }
                            newSentence = tempSentence;
                        }
                        if (postConsole) {
                            System.out.println("Cap: " + newSentence);
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Capitalize: " + e);
                }

                try {
                    // Adds "Header - "
                    if (!headField.getText().isEmpty()) {
                        if (headBox.isSelected()) {
                            newSentence = headField.getText() + newSentence;
                        } else {
                            newSentence = headField.getText() + " - " + newSentence;
                        }
                        if (postConsole) {
                            System.out.println("Header: " + newSentence);
                        }
                    }

                    // Adds " - Footer"
                    if (!footField.getText().isEmpty()) {
                        if (footBox.isSelected()) {
                            newSentence = newSentence + footField.getText();
                        } else {
                            newSentence = newSentence + " - " + footField.getText();
                        }
                        if (postConsole) {
                            System.out.println("Footer: " + newSentence);
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Header/Footer: " + e);
                }

                try {
                    // C:\FileDirectory\...\fileName
                    // File tar = new File(dstArea.getText() + "\\" + fileName);
                    File newFile = new File((dstArea.getText() + "\\" + newSentence + "." + fileExtension).trim().replaceAll(" +", " "));
                    if (!newFile.exists()) {
                        Files.copy(oldFile.toPath(), newFile.toPath());
                        // newFile.renameTo(new File(newFile));
                    } else if (newFile.exists()) {
                        if (overBox.isSelected()) {
                            Files.deleteIfExists(newFile.toPath());
                            // BUG:
                            // If the Source and Destination Path is the same
                            // It will just delete the file
                            // Make sure it just renames it instead of Deleting
                            if (srcArea.getText().equals(dstArea.getText())){
                                
                            }
                            Files.copy(oldFile.toPath(), newFile.toPath());
                            // newFile.renameTo(new File(newFile));
                        } else {
                            System.out.println(newFile.getName() + " file not copied");
                            // StandardCopyOption.REPLACE_EXISTING
                        }
                    }

                    if (postConsole) {
                        System.out.println(("Output: " + newSentence + "." + fileExtension));
                    }

                    // if (overBox.isSelected()) {
                    // Files.deleteIfExists(newFile.toPath());
                    // Files.copy(oldFile.toPath(), newFile.toPath());
                    // newFile.renameTo(
                    // new File(dstArea.getText() + "\\" + newSentence.trim() + "." +
                    // fileExtension.trim()));

                    // } else {
                    // Files.copy(oldFile.toPath(), newFile.toPath());
                    // newFile.renameTo(
                    // new File(dstArea.getText() + "\\" + newSentence.trim() + "." +
                    // fileExtension.trim()));

                    // }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Copying: " + e);
                }

                if (postConsole) {
                    System.out.println("");
                }
                // copy the file
            }
        }
    }
}
