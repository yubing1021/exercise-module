package com.darben.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * @description: 创建我的文件索引
 * @author: darben
 * @create: 2020-04-09 22:09
 */
public class LuceneHelperUtils {

    private static String storeIndexPath="G:\\MyPrivate\\MyKit\\lucene";

    public static void buildIndex(){
        //实现java.io.Closeable接口，必须用try-with-resource
        try (Directory directory = FSDirectory.open(new File("G:\\MyPrivate\\MyKit\\lucene").toPath())) {

            //指定一个标准分析器，对文档内容进行分析
            Analyzer analyzer = new StandardAnalyzer();

            //创建indexwriterCofig对象
            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            //创建一个indexwriter对象
            IndexWriter indexWriter = new IndexWriter(directory, config);

            File dirFile = new File("G:\\MyPrivate\\MyKit\\lucene\\demo");
            File[] fileList = dirFile.listFiles();
            for (File file : fileList){

                Document document = new Document();

                String filename = file.getName();
                long filesize = file.getUsableSpace();
                String filepath = file.getPath();
                String filecontent = TryWithResource.readFileToString(filepath);

                Field fileNameField = new TextField("fileName", filename, Field.Store.YES);
                Field fileSizeField  = new TextField("fileSize", filesize+"", Field.Store.YES);
                Field filePathField  = new StoredField("filePath", filename);
                Field fileContentField  = new TextField("fileName", filecontent, Field.Store.YES);

                document.add(fileNameField);
                document.add(fileSizeField);
                document.add(filePathField);
                document.add(fileContentField);

                //使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
                indexWriter.addDocument(document);
            }
            indexWriter.close();
        }
        catch (IOException e){
        }
    }

    public static void main(String[] args) {
        LuceneHelperUtils.buildIndex();
    }

}
