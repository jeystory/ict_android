package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.prefs.Preferences;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity9 extends AppCompatActivity {
    Button xml_btn1, xml_btn2, xml_btn3, xml_btn4;
    TextView result4;
    Handler handler = new Handler();
    String xml_msg,xml_str1, xml_str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        xml_btn1 = findViewById(R.id.xml_btn1);
        xml_btn2 = findViewById(R.id.xml_btn2);
        xml_btn3 = findViewById(R.id.xml_btn3);
        xml_btn4 = findViewById(R.id.xml_btn4);
        result4 = findViewById(R.id.result4);

        xml_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.65:8090/Server01/MyController02");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess01();
                            }
                        });
                    }
                }).start();
            }
        });

        xml_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.65:8090/Server01/MyController03");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess02();
                            }
                        });
                    }
                }).start();
            }
        });

        xml_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.65:8090/Server01/MyController02");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess03();
                                result4.setText(xml_str1);
                                // 초기화
                                xml_str1 = "";
                            }
                        });
                    }
                }).start();
            }
        });

        xml_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.55:8090/Server01/MyController03");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                xmlProcess04();
                                result4.setText(xml_str2);
                                // 초기화
                                xml_str2 = "";
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public void serverConnect(String str){
        StringBuffer sb = new StringBuffer();
        try{
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null) {
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch (Exception e){
            System.out.println(e);
        }
        xml_msg = sb.toString();
    }

    //DOM 방식 : tag들로만 이루어진 XML 처리
    private void xmlProcess01(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml_msg)));
            //태그 구하기
            NodeList member = document.getElementsByTagName("member");
            NodeList idx = document.getElementsByTagName("idx");
            NodeList id = document.getElementsByTagName("id");
            NodeList pw = document.getElementsByTagName("pw");
            NodeList name = document.getElementsByTagName("name");
            NodeList age = document.getElementsByTagName("age");
            NodeList addr = document.getElementsByTagName("addr");

            StringBuffer sb = new StringBuffer("XML Parsing(DOM)");

            for (int i=0;i<member.getLength();i++) {
                sb.append("\n\n");
                sb.append(
                        idx.item(i).getFirstChild().getNodeValue()+ "," +
                        id.item(i).getFirstChild().getNodeValue()+ "," +
                        pw.item(i).getFirstChild().getNodeValue()+ "," +
                        name.item(i).getFirstChild().getNodeValue()+ "," +
                        age.item(i).getFirstChild().getNodeValue()+ "," +
                        addr.item(i).getFirstChild().getNodeValue()

                );
            }
            result4.setText(sb.toString());


        } catch (Exception e) {

        }
    }

    //DOM 방식 : tag와  attribute로 이루어진 XML 처리
    private void xmlProcess02(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml_msg)));
            //태그 구하기
            NodeList member = document.getElementsByTagName("member");


            StringBuffer sb = new StringBuffer("XML_Attr Parsing(DOM)");

            for (int i=0;i<member.getLength();i++) {
                sb.append("\n\n");
                sb.append(
                        ((Element)member.item(i)).getAttribute("idx")+ ", "+
                                ((Element)member.item(i)).getAttribute("id")+ ", "+
                                ((Element)member.item(i)).getAttribute("pw")+ ", "+
                                member.item(i).getFirstChild().getNodeValue() + ", " +
                                ((Element)member.item(i)).getAttribute("age")+ ", "+
                                ((Element)member.item(i)).getAttribute("addr")
                );
            }
            result4.setText(sb.toString());

        } catch (Exception e) {

        }
    }

    //SAX 방식 : 태그로만 이루어진 xml 파일
    private void xmlProcess03(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            // SAX를 파싱하는 클래스 하나 생성
            reader.setContentHandler(new SAX_MyProcess01());
            reader.parse(new InputSource(new StringReader(xml_msg)));
        }catch (Exception e){
        }
    }

    // SAX 방식 : 속성 처리하는 방식
    private void xmlProcess04(){
        try {
            xml_str2 = "XML_ATTR 파싱 (SAX 방식)\n";
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            // SAX를 파싱하는 클래스 하나 생성
            reader.setContentHandler(new SAX_MyProcess02());
            reader.parse(new InputSource(new StringReader(xml_msg)));
        }catch (Exception e){
        }
    }

    class SAX_MyProcess01 extends DefaultHandler{
        // 시작 태그와 끝 태그 사이의 문자 추출 하자
        StringBuffer sb = new StringBuffer("XML 파싱 (SAX 방식)\n\n");

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            sb.append(new String(ch,start,length).trim()+" ");
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if(qName.equals("addr")){
                sb.append("\n");
            }
            xml_str1=sb.toString();
        }
    }

    class SAX_MyProcess02 extends DefaultHandler{
        StringBuffer sb = new StringBuffer();

        // 시작태그 안에 존재하는 속성 추출
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equals("member")){
                sb.append(attributes.getValue("idx")+", ");
                sb.append(attributes.getValue("id")+", ");
                sb.append(attributes.getValue("pw")+", ");
                sb.append(attributes.getValue("age")+", ");
                sb.append(attributes.getValue("addr")+",");
            }
            xml_str2 = sb.toString();
        }

        // 시작 태그와 끝 태그 사이의 문자 추출 하자
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch,start, length);
            xml_str2 = sb.append(str).toString();
        }
    }
}
