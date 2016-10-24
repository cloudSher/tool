package com.sher.tool.frame;

import com.sher.tool.util.Serializtion;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Created by Administrator on 2016/10/21.
 *
 *  layout, component, pane
 */
public class JarFrame extends JFrame {

    private JTextArea response = new JTextArea();
    private JLabel addressLabel = new JLabel();
    private JTextField address = new JTextField();

    private JLabel mainLabel = new JLabel();
    private JTextField main = new JTextField();

    private JButton btn = new JButton();
    private JButton generator = new JButton();
    private JScrollPane pane = new JScrollPane();


   public  JarFrame(){
       doInit();
    }

    public void setUp(){
        this.getContentPane().setLayout(null);
        addressLabel.setText("Project Path:");
        addressLabel.setBounds(new Rectangle(6, 5, 69, 18));
        address.setText("F://");
        address.setBounds(new Rectangle(77, 7, 143, 19));

        mainLabel.setText("Main Class:");
        mainLabel.setBounds(new Rectangle(230,7, 30, 20));

        main.setText("f://demo");
        main.setBounds(new Rectangle(265, 7, 72, 18));

        btn.setText("Compile");
        btn.setBounds(new Rectangle(347, 6, 80, 58));
        btn.addActionListener(e -> doAction(e));

        this.getContentPane().add(addressLabel,null);
        this.getContentPane().add(address,null);
        this.getContentPane().add(mainLabel,null);
    }

    public void doInit(){
        invokeLayout();
        doInitComponent();
    }

    public void invokeLayout(){
        this.getContentPane().setLayout(null);
    }


    public void doInitComponent(){
        addressLabel.setText("Project");
        addressLabel.setBounds(new Rectangle(6, 5, 69, 18));
        address.setText("F:\\sample");
        address.setBounds(new Rectangle(77, 7, 200, 19));

        mainLabel.setText("MainClass");
        mainLabel.setBounds(new Rectangle(6,35, 70, 20));

        main.setText("com.sher.Main");
        main.setBounds(new Rectangle(77, 35, 200, 18));

        btn.setText("Create");
        btn.setBounds(new Rectangle(347, 6, 80, 58));
        btn.addActionListener(e -> doAction(e));

        generator.setText("Generate");
        generator.setBounds(new Rectangle(450,6,80,58));
        generator.addActionListener(e-> doGenerator(e));

        pane.setBounds(new Rectangle(6,78,560,270));
        pane.getViewport().add(response);

        addComponents(addressLabel,address,mainLabel,main,btn,pane,generator);
    }

    public void addComponents(Component... components){
        if(components != null && components.length>0){
            Arrays.asList(components).forEach(this.getContentPane()::add);
        }
    }


    public void doAction(ActionEvent event){
        System.out.println(event.getID());
        response.setText("==== compile project start =======\r\n");
        Compiler compiler = new Compiler();
        Result result = compiler.compile(address.getText());
        try {
            response.append(Serializtion.unSerialzation(result.getOutputStream().toByteArray()));
            if(result.getError() != null){
                response.append(Serializtion.unSerialzation(result.getError().toByteArray()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            response.append(e.toString());
        }
        response.append("======= compile project complete ======");

    }

    public void doGenerator(ActionEvent event){
        response.setText("====== generate Jar start =======\r\n");
        GenerateJar generateJar = new GenerateJar();
        try {
            generateJar.generateJar(address.getText(),main.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.append("======== generate Jar complete ======");
    }



    public static void main(String args[]){
        JarFrame jarFrame = new JarFrame();
        jarFrame.setBounds(0,0,600,400);
        jarFrame.setVisible(true);
    }


    class Compiler{

        private final String defaultPath = getCurrentPath();
        private final String targetDir = "target";
        /***
         *  编译
         * @param sourcePath
         * @return
         */
        // TODO: 2016/10/24  字符编码的问题，maven项目编译，依赖包
        public Result compile(String sourcePath){
            if(sourcePath == null || sourcePath.isEmpty()){
                sourcePath = defaultPath;
            }
            File file = new File(sourcePath);
            if(!file.exists()){
                file.mkdirs();
            }
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            java.util.List<String> files = files(file);
            final String finalSourcePath = getTargetDir(sourcePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            files.forEach(e -> compiler.run(null,bos,err,"-d", finalSourcePath,e));
            return new Result(bos,err);
        }

        public String getTargetDir(String path){
            if (path == null){
                return null;
            }
            String target = path + File.separator + targetDir;
            File file = new File(target);
            if(!file.exists())
                file.mkdirs();
            return file.toString();
        }

        public java.util.List<String> files(File file){
            java.util.List<String> jstrs = new ArrayList<>();
            listFiles(file,jstrs);
            return jstrs;
        }

        public void listFiles(File file,java.util.List<String> list){
            File[] files = file.listFiles();
            for(int i = 0 ; i < files.length; i++ ){
                File f = files[i];
                if(f.isDirectory()){
                    listFiles(f,list);
                }else if(f.isFile()){
                    if(f.getName().endsWith(".java"))
                        list.add(f.getPath());
                }
            }
        }

        /**
         *  返回当前路径
         * @return
         */
        public String getCurrentPath(){
            return System.getProperty("user.dir");
        }
    }


    class GenerateJar{

        private final String currPath = System.getProperty("user.dir");
        private final String TARGETDIR="target";
        private String target;

        public void generateJar(String jarPath,String mainClass) throws IOException {
            if(jarPath == null || jarPath.isEmpty()){
                jarPath = currPath;
            }
            File target = new File(jarPath);
            if(!target.exists())
                target.mkdirs();

            this.target = getTargetDir(jarPath);

            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,"1.0");
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS,mainClass);

            String targetJar = jarPath +".jar";
            JarOutputStream jos = new JarOutputStream(new FileOutputStream(targetJar),manifest);
            writeClass(new File(this.target),jos);
            jos.close();
            System.out.println("======= generate jar complete =====");
        }

        public String getTargetDir(String source){
            if(source == null){
                return null;
            }
            String target = source + File.separator + TARGETDIR;
            File file;
            if(!(file = new File(target)).exists()){
                file.mkdirs();
            }
            return file.toString();
        }

        public void writeClass(File source,JarOutputStream os){
            if(source.isDirectory()){
                Arrays.asList(source.listFiles()).forEach(f ->{
                    writeClass(f,os);
                });
                return;
            }
            String name = source.getPath().replace("\\","/").substring(target.length()+1);
            System.out.println(name);
            JarEntry entry = new JarEntry(name);
            entry.setTime(source.lastModified());
            BufferedInputStream bin = null;
            try {
                os.putNextEntry(entry);
                bin = new BufferedInputStream(new FileInputStream(source));
                readAndWrite(bin,os);
                os.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(bin!= null){
                    try {
                        bin.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        public void readAndWrite(InputStream in ,OutputStream out) throws IOException {
            byte[] buffer = new byte[1024];
            int len;
            while((len = in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
        }
    }


    class Result{
        private ByteArrayOutputStream os;
        private ByteArrayOutputStream err;
        Result(ByteArrayOutputStream os,ByteArrayOutputStream err){
            this.os = os;
            this.err = err;
        }
        public ByteArrayOutputStream getOutputStream(){
            return os;
        }
        public ByteArrayOutputStream getError(){
            return err;
        }
    }
}
