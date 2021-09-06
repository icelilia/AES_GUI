package org.ez4drift.aes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AESController {
    private String bias;
    private String password;
    private String inputFilePath;
    private String outputFilePath;

    @FXML
    private Label inputFilePathText;

    @FXML
    private Label outputFilePathText;

    @FXML
    private TextField biasText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label resultText;

    @FXML
    public void onInputFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择输入文件");
        File inputFile = fileChooser.showOpenDialog(Stage.getWindows().get(0));
        inputFilePath = inputFile.getAbsolutePath();
        inputFilePathText.setText("输入文件：" + inputFilePath);
    }

    public void onOutputFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择输出文件");
        File outputFile = fileChooser.showSaveDialog(Stage.getWindows().get(0));
        outputFilePath = outputFile.getAbsolutePath();
        outputFilePathText.setText("输出路径：" + outputFilePath);
    }

    private boolean isEmpty() {
        bias = biasText.getText();
        password = passwordText.getText();
        if (bias.contentEquals("") || password.contentEquals("")) {
            resultText.setText("请输入偏移和密码");
            return true;
        }
        return false;
    }


    public void onEncryptButtonClick() {
        if (isEmpty()) {
            return;
        }

        bias = MD5Util.stringToMD5(bias);
        password = MD5Util.stringToMD5(password);
        AESCore aes = new AESCore(password, bias, 1);

        try {
            aes.encryptFile(inputFilePath, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            resultText.setText("加密过程出错，请重试");
            return;
        }

        resultText.setText("文件已加密");
    }

    public void onDecryptButtonClick() {
        if (isEmpty()) {
            return;
        }

        bias = MD5Util.stringToMD5(bias);
        password = MD5Util.stringToMD5(password);
        AESCore aes = new AESCore(password, bias, 1);

        try {
            aes.decryptFile(inputFilePath, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            resultText.setText("加密过程出错，请重试");
            return;
        }

        resultText.setText("文件已解密");
    }
}